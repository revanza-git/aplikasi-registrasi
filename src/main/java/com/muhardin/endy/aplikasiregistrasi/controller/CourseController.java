package com.muhardin.endy.aplikasiregistrasi.controller;

import com.muhardin.endy.aplikasiregistrasi.dao.MateriDao;
import com.muhardin.endy.aplikasiregistrasi.entity.Materi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/course")
public class CourseController {

    @Autowired private MateriDao materiDao;

    @GetMapping("/list")
    public ModelMap list(Pageable pageable) {
        Page<Materi> hasilQuery = materiDao.findAll(pageable);

        log.info("Hasil query ada {} record", hasilQuery.getSize());

        return new ModelMap()
                .addAttribute("daftarMateri", hasilQuery);
    }

    @GetMapping("/enroll")
    public ModelMap displayEnrollment() {
        return new ModelMap();
    }

    @PostMapping("/enroll")
    public String processEnrollment() {
        return "redirect:enrollment_confirmation";
    }

    @GetMapping("/enrollment_confirmation")
    public ModelMap displayEnrollmentConfirmation() {
        return new ModelMap();
    }
}
