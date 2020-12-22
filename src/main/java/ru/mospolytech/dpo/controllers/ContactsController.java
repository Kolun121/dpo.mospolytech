package ru.mospolytech.dpo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.mospolytech.dpo.service.ContactService;

@Controller
@RequestMapping("/contacts")
public class ContactsController {
    private final ContactService contactService;

    ContactsController(ContactService contactService) {
        this.contactService = contactService;
    }
    
    @GetMapping()
    public String getNewsList(Model model) {
        model.addAttribute("contacts", contactService.findAll());
        return "contacts/contactsList";
    }
    
}
