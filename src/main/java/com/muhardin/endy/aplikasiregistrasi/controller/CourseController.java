package com.muhardin.endy.aplikasiregistrasi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/course")
public class CourseController {

    @GetMapping("list")
    public ModelMap list() {
        return new ModelMap();
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
