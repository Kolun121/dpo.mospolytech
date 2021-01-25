package ru.mospolytech.dpo.controllers.admin.image;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
import javax.imageio.ImageIO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import ru.mospolytech.dpo.amazon.AmazonClient;
import ru.mospolytech.dpo.domain.Contact;
import ru.mospolytech.dpo.domain.image.ContactMainImage;
import ru.mospolytech.dpo.service.ContactService;
import ru.mospolytech.dpo.service.image.ContactMainImageService;

@Slf4j
@Controller("adminContactMainImageController")
public class ContactMainImageController {
    @Value("${upload.path}")
    private String uploadPath = "resources/uploads/";
    private final String imageControllerDir = "contacts";
   
    private final ContactMainImageService contactMainImageService;
    private final ContactService contactService;
    private final AmazonClient amazonClient;

    public ContactMainImageController(ContactMainImageService contactMainImageService, ContactService contactService, AmazonClient amazonClient) {
        this.contactMainImageService = contactMainImageService;
        this.contactService = contactService;
        this.amazonClient = amazonClient;
    }

    
    @PostMapping("/admin/contacts/{contactId}/image/new")
    public @ResponseBody String addMainContactImage(
            @PathVariable Long contactId,
            @RequestParam("file") MultipartFile file
    ) throws IOException {
        String url = amazonClient.uploadFile(file, imageControllerDir);
        
        Contact contact = contactService.findById(contactId);

        ContactMainImage contactMainImage = new ContactMainImage();
        contactMainImage.setName(url);
        contactMainImage.setContact(contact);
        contact.setContactMainImage(contactMainImage);        
        contactMainImageService.save(contactMainImage);
        
        return url;
    }
    
    @DeleteMapping("/admin/contacts/{contactId}/image")
    public @ResponseBody void deleteMainContactImage(
            @PathVariable Long contactId
    ) throws IOException {
        Contact contact = contactService.findById(contactId);
        ContactMainImage contactMainImage = contactMainImageService.findByContactId(contactId);
        contact.setContactMainImage(null);
        
        amazonClient.deleteFileFromS3Bucket(contactMainImage.getName(), imageControllerDir);

        contactMainImageService.delete(contactMainImage);
    }
}
