package ru.mospolytech.dpo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/about")
public class AboutController {
    @GetMapping()
    public String getAboutPage(Model model) {
        return "about/aboutPage";
    }
    
    @GetMapping("/bank_documents")
    public String getBankDocumentsPage(Model model) {
        return "about/bankDocumentsPage";
    }
    
}
