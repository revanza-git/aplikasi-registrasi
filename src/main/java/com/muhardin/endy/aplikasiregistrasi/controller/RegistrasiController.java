package com.muhardin.endy.aplikasiregistrasi.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;

@Slf4j
@Controller
public class RegistrasiController {

    @GetMapping("/registrasi")
    public ModelMap tampilkanFormRegistrasi() {
        log.info("Menjalankan method tampilkanFormRegistrasi");
        ModelMap mm = new ModelMap();
        mm.addAttribute("nama", "Endy");
        mm.addAttribute("waktu", LocalDateTime.now());
        return mm;
    }

    @PostMapping("/registrasi")
    public String prosesFormRegistrasi() {
        log.info("Seharusnya nanti di sini insert ke database");

        /* jangan return html, return redirect supaya tidak dobel submit
        ModelAndView mav = new ModelAndView("konfirmasi");
        return mav;
         */

        return "redirect:konfirmasi";
    }

    @GetMapping("/konfirmasi")
    public void tampilkanHalamanKonfirmasi() {

    }
}
