package com.muhardin.endy.aplikasiregistrasi.service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

import com.muhardin.endy.aplikasiregistrasi.dao.PembayaranDao;
import com.muhardin.endy.aplikasiregistrasi.dao.TagihanDao;
import com.muhardin.endy.aplikasiregistrasi.entity.Pembayaran;
import com.muhardin.endy.aplikasiregistrasi.entity.Pendaftaran;
import com.muhardin.endy.aplikasiregistrasi.entity.Peserta;
import com.muhardin.endy.aplikasiregistrasi.entity.Tagihan;
import com.muhardin.endy.aplikasiregistrasi.service.dto.request.DokuHostedNotifyDTO;
import com.muhardin.endy.aplikasiregistrasi.service.dto.request.DokuHostedRequestDTO;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

/**
 * DokuService
 */
@Slf4j
@Service
public class DokuService {

    @Value("${doku.merchantid}")
    private String merchantId;

    @Value("${doku.sharedkey}")
    private String sharedKey;

    @Value("${doku.url}")
    private String dokuUrl;

    @Autowired
    TagihanDao tagihanDao;

    @Autowired
    PembayaranDao pembayaranDao;

    private DecimalFormat decFormat = new DecimalFormat("0.00");

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

    public String getDokuUrl(){
        return dokuUrl;
    }

    public DokuHostedRequestDTO createDokuRequest(Tagihan tagihan){
        Peserta p = tagihan.getPendaftaran().getPeserta();
        Pendaftaran pendaf = tagihan.getPendaftaran();

        DokuHostedRequestDTO request = DokuHostedRequestDTO.builder().NAME(p.getNama())
                .EMAIL(p.getEmail()).MALLID(merchantId).CHAINMERCHANT("NA")
                .AMOUNT(decFormat.format(pendaf.getMateri().getBiaya())).PURCHASEAMOUNT(decFormat.format(pendaf.getMateri().getBiaya()))
                .TRANSIDMERCHANT(tagihan.getNomorInvoice()).PAYMENTCHANNEL("").REQUESTDATETIME(dateFormat.format(new Date()))
                .CURRENCY("360").PURCHASECURRENCY("360").SESSIONID("1234567890").BASKET(tagihan.getKeterangan()).build();

        String words = DigestUtils.sha1Hex(request.getAMOUNT() + request.getMALLID() + sharedKey + request.getTRANSIDMERCHANT());
        request.setWORDS(words);
        
        return request;
    }

    public Pembayaran processNotify(DokuHostedNotifyDTO request){
        String wordsComponent = "".concat(new DecimalFormat("0.00").format(request.getAMOUNT())).concat(merchantId).concat(sharedKey).concat(request.getTRANSIDMERCHANT()).concat(request.getRESULTMSG()).concat(request.getVERIFYSTATUS());
        if(request.getCURRENCY()!=null && !request.getCURRENCY().equals("360")){
            wordsComponent = wordsComponent.concat(request.getCURRENCY());
        }

        Boolean checkWords = DigestUtils.sha1Hex(wordsComponent).equals(request.getWORDS());
        
        if(checkWords){
            Tagihan t = tagihanDao.findByNomorInvoice(request.getTRANSIDMERCHANT());
            Pembayaran pembayaran = new Pembayaran();
            pembayaran.setTagihan(t);
            pembayaran.setReferensi(request.getAPPROVALCODE());
            pembayaran.setWaktuPembayaran(LocalDateTime.now());
            pembayaran.setDokuResponseCode(request.getRESPONSECODE());
            pembayaranDao.save(pembayaran);
            Boolean amountOk = t.getPendaftaran().getMateri().getBiaya().equals(request.getAMOUNT());
            if("0000".equals(request.getRESPONSECODE()) && amountOk){
                t.setLunas(true);
                tagihanDao.save(t); 
            }

            return pembayaran;
        }
        log.warn("Checkword gagal");
        return null;
    }


}