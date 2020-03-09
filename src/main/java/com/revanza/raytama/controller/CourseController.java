package com.revanza.raytama.controller;

import com.revanza.raytama.dao.MateriDao;
import com.revanza.raytama.dao.PesertaDao;
import com.revanza.raytama.entity.Materi;
import com.revanza.raytama.entity.Peserta;
import com.revanza.raytama.service.DokuService;
import com.revanza.raytama.service.RegistrationService;
import com.revanza.raytama.service.dto.request.DokuHostedRequestDTO;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@Controller
@RequestMapping("/course")
public class CourseController {

    @Autowired private MateriDao materiDao;
    @Autowired private PesertaDao pesertaDao;

    @Autowired private RegistrationService registrationService;
    @Autowired private DokuService dokuService;


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
    public String processEnrollment(@RequestParam Peserta peserta, @RequestParam Materi materi, RedirectAttributes redir) {
        DokuHostedRequestDTO dokuRequest = registrationService.daftarWorkshop(peserta, materi);
        redir.addFlashAttribute("dokuUrl", dokuService.getDokuUrl()+"Receive");
        redir.addFlashAttribute("redirect", dokuRequest);
        return "redirect:/doku/continue";
    }

    @GetMapping("/enrollment_confirmation")
    public ModelMap displayEnrollmentConfirmation() {
        return new ModelMap();
    }
}
