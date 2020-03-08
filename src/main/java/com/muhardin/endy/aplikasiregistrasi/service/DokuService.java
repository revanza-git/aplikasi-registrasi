package com.muhardin.endy.aplikasiregistrasi.service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import com.muhardin.endy.aplikasiregistrasi.entity.Pendaftaran;
import com.muhardin.endy.aplikasiregistrasi.entity.Peserta;
import com.muhardin.endy.aplikasiregistrasi.entity.Tagihan;
import com.muhardin.endy.aplikasiregistrasi.service.dto.request.DokuHostedRequestDTO;

import org.apache.commons.codec.digest.DigestUtils;
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

    private DecimalFormat decFormat = new DecimalFormat("0.00");

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

    public String getDokuUrl(){
        return dokuUrl;
    }

    public DokuHostedRequestDTO createDokuRequest(Tagihan tagihan){
        Peserta p = tagihan.getPendaftaran().getPeserta();
        Pendaftaran pendaf = tagihan.getPendaftaran();

        String keterangan = "Pembayaran materi" + pendaf.getMateri() + " - " + pendaf.getPeserta();
        DokuHostedRequestDTO request = DokuHostedRequestDTO.builder().NAME(p.getNama())
                .EMAIL(p.getEmail()).MALLID(merchantId).CHAINMERCHANT("NA")
                .AMOUNT(decFormat.format(pendaf.getMateri().getBiaya())).PURCHASEAMOUNT(decFormat.format(pendaf.getMateri().getBiaya()))
                .TRANSIDMERCHANT(tagihan.getNomorInvoice()).PAYMENTCHANNEL("").REQUESTDATETIME(dateFormat.format(new Date()))
                .CURRENCY("360").PURCHASECURRENCY("360").SESSIONID("1234567890").BASKET(keterangan).build();

        String words = DigestUtils.sha1Hex(request.getAMOUNT() + request.getMALLID() + sharedKey + request.getTRANSIDMERCHANT());
        request.setWORDS(words);
        
        return request;
    }

}