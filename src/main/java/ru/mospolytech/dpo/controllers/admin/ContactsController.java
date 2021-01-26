package ru.mospolytech.dpo.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.mospolytech.dpo.domain.Contact;
import ru.mospolytech.dpo.service.ContactService;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.mospolytech.dpo.amazon.AmazonClient;
import ru.mospolytech.dpo.domain.image.ContactMainImage;
import ru.mospolytech.dpo.service.image.ContactMainImageService;


@Controller("adminContactsController")
@RequestMapping("/admin/contacts")
public class ContactsController {
    private final String mainImageControllerDir = "contacts";
    
    private final ContactService contactService;
    private final ContactMainImageService contactMainImageService;
    private final AmazonClient amazonClient;

    ContactsController(
            ContactService contactService,
            ContactMainImageService contactMainImageService,
            AmazonClient amazonClient
    ) {
        this.contactService = contactService;
        this.contactMainImageService = contactMainImageService;
        this.amazonClient = amazonClient;
    }
    
    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }
    
    @GetMapping
    public String getContactsListPage(Model model) {
        model.addAttribute("contacts", contactService.findAll());
        
        return "admin/contacts/contactsList";
    }
    
    @GetMapping("{id}")
    public String getContactById(@PathVariable Long id, Model model) {
        model.addAttribute("contact", contactService.findById(id));
        return "admin/contacts/updateContact";
    }
    
    @PostMapping("/new")
    public @ResponseBody String newContact() {
        Contact savedContact = contactService.save(new Contact());
        return savedContact.getId().toString();
    }
    
    @PostMapping("{id}")
    public String updateContactById(@PathVariable Long id, @ModelAttribute Contact contact){
        contact.setId(id);
        
        ContactMainImage contactMainImage = contactMainImageService.findByContactId(id);
        if(contactMainImage != null){
            contact.setContactMainImage(contactMainImage);
        }

        Contact savedContact = contactService.save(contact);

        return "redirect:/admin/contacts/" + savedContact.getId();
    }
    
    @DeleteMapping("{id}")
    public @ResponseBody void deleteById(@PathVariable Long id){
        Contact contact = contactService.findById(id);
        
        if(contact.getContactMainImage()!= null){
            ContactMainImage contactMainImage = contactMainImageService.findByContactId(id);
            amazonClient.deleteFileFromS3Bucket(contactMainImage.getName(), mainImageControllerDir);
            contactMainImageService.delete(contactMainImage);
        }
        

        contactService.deleteById(id);
    }
}
