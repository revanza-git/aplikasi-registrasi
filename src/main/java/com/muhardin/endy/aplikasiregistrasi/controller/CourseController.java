package com.muhardin.endy.aplikasiregistrasi.controller;

import com.muhardin.endy.aplikasiregistrasi.dao.MateriDao;
import com.muhardin.endy.aplikasiregistrasi.dao.PesertaDao;
import com.muhardin.endy.aplikasiregistrasi.entity.Materi;
import com.muhardin.endy.aplikasiregistrasi.entity.Peserta;
import com.muhardin.endy.aplikasiregistrasi.entity.Tagihan;
import com.muhardin.endy.aplikasiregistrasi.service.RegistrationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequestMapping("/course")
public class CourseController {

    @Autowired private MateriDao materiDao;
    @Autowired private PesertaDao pesertaDao;

    @Autowired private RegistrationService registrationService;

    @GetMapping("/list")
    public ModelMap list(Pageable pageable) {
        Page<Materi> hasilQuery = materiDao.findAll(pageable);

        log.info("Hasil query ada {} record", hasilQuery.getSize());

        return new ModelMap()
                .addAttribute("daftarMateri", hasilQuery);
    }

    @GetMapping("/enroll")
    public ModelMap displayEnrollment(@RequestParam Materi materi, Authentication auth) {
        log.info(auth.toString());
        User u = (User) auth.getPrincipal();
        Peserta p = pesertaDao.findByEmail(u.getUsername());
        return new ModelMap()
                .addAttribute("peserta", p)
                .addAttribute("materi", materi);
    }

    @PostMapping("/enroll")
    public String processEnrollment(@RequestParam Peserta peserta, @RequestParam Materi materi) {
        registrationService.daftarWorkshop(peserta, materi);
        return "redirect:enrollment_confirmation";
    }

    @GetMapping("/enrollment_confirmation")
    public ModelMap displayEnrollmentConfirmation() {
        return new ModelMap();
    }
}
