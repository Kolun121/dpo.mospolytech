package ru.mospolytech.dpo.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("adminNewsController")
@RequestMapping("/admin/news")
public class NewsController {
    
    @GetMapping
    public String getIndexPage(Model model) {

        return "admin/news/index";
    }
}
