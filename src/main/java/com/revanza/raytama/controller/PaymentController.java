package com.revanza.raytama.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/payment")
public class PaymentController {

    @GetMapping("/invoice/list")
    public ModelMap invoiceList() {
        return new ModelMap();
    }

    @GetMapping("/invoice")
    public ModelMap invoiceDetail() {
        return new ModelMap();
    }
}
