package com.revanza.raytama.controller;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import com.revanza.raytama.dao.PembayaranDao;
import com.revanza.raytama.dao.TagihanDao;
import com.revanza.raytama.entity.Pembayaran;
import com.revanza.raytama.service.DokuService;
import com.revanza.raytama.service.EmailService;
import com.revanza.raytama.service.dto.request.DokuHostedIdentifyDTO;
import com.revanza.raytama.service.dto.request.DokuHostedNotifyDTO;
import com.revanza.raytama.service.dto.request.DokuHostedRedirectDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;

/**
 * DokuController
 */
@Controller
@RequestMapping("/doku")
@Slf4j
public class DokuController {


    @Value("${gmail.account.username}")
    private String mailFrom;

    @Autowired private MustacheFactory mustacheFactory;

    @Autowired
    EmailService emailService;

    @Autowired
    DokuService dokuService;

    @Autowired
    TagihanDao tagihanDao;

    @Autowired
    PembayaranDao pembayaranDao;
    
    @GetMapping("/continue")
    public String kirimCustomerKeDoku(ModelMap model){
        return "doku";
    }

    @PostMapping(value = "/notify")
    @ResponseBody public String notify(DokuHostedNotifyDTO request){
        log.info(request.toString());
        Pembayaran bayar = dokuService.processNotify(request);
        if (bayar != null) {
            Mustache templateEmail =
                    mustacheFactory.compile("templates/notification/payment.html");
            Map<String, String> data = new HashMap<>();
            data.put("bank", bayar.getTagihan().getBank());
            data.put("rekening", bayar.getTagihan().getNomorRekening());
            data.put("nama", bayar.getTagihan().getNamaRekening());
            data.put("nilai", bayar.getTagihan().getNilai().toPlainString());
            data.put("waktu", bayar.getWaktuPembayaran().toString());
            data.put("referensi", bayar.getReferensi());

            StringWriter output = new StringWriter();
            templateEmail.execute(output, data);

            emailService.kirimEmail(
                    mailFrom,
                    bayar.getTagihan().getPendaftaran().getPeserta().getEmail(),
                    "Konfirmasi Pembayaran Tagihan "+bayar.getTagihan().getNomorInvoice(),
                    output.toString());
        }

        return "CONTINUE";
    }

    //Controller untuk notify dari DOKU merchant
    @PostMapping(value = "/identify")
    @ResponseBody public String identify(DokuHostedIdentifyDTO request, Model model) {
        return "CONTINUE";
    }
    
    //Controller untuk menghandle redirect dari DOKU merchant
    @PostMapping("/redirect")
    public String redirect(DokuHostedRedirectDTO request) {
        log.info(request.toString());   
        // Pasang logic membaca redirect
        
        return "doku_redirect";
    }
    
}