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
import ru.mospolytech.dpo.domain.Contact;
import ru.mospolytech.dpo.domain.image.ContactMainImage;
import ru.mospolytech.dpo.service.ContactService;
import ru.mospolytech.dpo.service.image.ContactMainImageService;

@Slf4j
@Controller("adminContactMainImageController")
public class ContactMainImageController {
    @Value("${upload.path}")
    private String uploadPath;
    private final String imageControllerDir = "/contacts";
   
    private final ContactMainImageService contactMainImageService;
    private final ContactService contactService;

    public ContactMainImageController(ContactMainImageService contactMainImageService, ContactService contactService) {
        this.contactMainImageService = contactMainImageService;
        this.contactService = contactService;
    }

    
    @PostMapping("/admin/contacts/{contactId}/image/new")
    public @ResponseBody String addMainContactImage(
            @PathVariable Long contactId,
            @RequestParam("file") MultipartFile file
    ) throws IOException {
        
        String resultFilename = "none";
        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath + imageControllerDir);

            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            String uuidFile  = UUID.randomUUID().toString();
            resultFilename = uuidFile + "." + "png";

            BufferedImage image = ImageIO.read(file.getInputStream());

            File outputFile = new File(uploadPath + imageControllerDir + "/" + resultFilename);
            ImageIO.write(image, "png", outputFile);  
            
            Contact contact = contactService.findById(contactId);
            
            ContactMainImage contactMainImage = new ContactMainImage();
            contactMainImage.setName(resultFilename);
            contactMainImage.setContact(contact);
            contact.setContactMainImage(contactMainImage);
            
            contactMainImageService.save(contactMainImage);
        }
        
        return resultFilename;
    }
    
    @DeleteMapping("/admin/contacts/{contactId}/image")
    public @ResponseBody void deleteMainContactImage(
            @PathVariable Long contactId
    ) throws IOException {
        Contact contact = contactService.findById(contactId);
        ContactMainImage contactMainImage = contactMainImageService.findByContactId(contactId);
        contact.setContactMainImage(null);
        
        Path imageToDeletePath = Paths.get(uploadPath + imageControllerDir + "/" + contactMainImage.getName());
        Files.delete(imageToDeletePath);
        
        contactMainImageService.delete(contactMainImage);
    }
}
