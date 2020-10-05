package ru.mospolytech.dpo.controllers.admin;

import ru.mospolytech.dpo.controllers.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("adminIndexController")
@RequestMapping("/admin")
public class IndexController {

    @GetMapping("/")
    public String getIndexPage(Model model) {

        return "index";
    }

}
