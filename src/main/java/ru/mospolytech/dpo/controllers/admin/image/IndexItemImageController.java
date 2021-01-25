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
import ru.mospolytech.dpo.domain.IndexItem;
import ru.mospolytech.dpo.domain.image.IndexItemImage;
import ru.mospolytech.dpo.service.IndexItemService;
import ru.mospolytech.dpo.service.image.IndexItemImageService;

@Slf4j
@Controller("adminIndexItemImageController")
public class IndexItemImageController {
    @Value("${upload.path}")
    private String uploadPath;
    private final String imageControllerDir = "index";
   
    private final IndexItemImageService indexItemImageService;
    private final IndexItemService indexItemService;
    private final AmazonClient amazonClient;

    public IndexItemImageController(IndexItemImageService indexItemImageService, IndexItemService indexItemService, AmazonClient amazonClient) {
        this.indexItemImageService = indexItemImageService;
        this.indexItemService = indexItemService;
        this.amazonClient = amazonClient;
    }

    
    @PostMapping("/admin/index/{indexItemId}/image/new")
    public @ResponseBody String addIndexItemImage(
            @PathVariable Long indexItemId,
            @RequestParam("file") MultipartFile file
    ) throws IOException {
        String url = amazonClient.uploadFile(file, imageControllerDir);
        
        IndexItem indexItem = indexItemService.findById(indexItemId);

        IndexItemImage indexItemImage = new IndexItemImage();
        indexItemImage.setName(url);
        indexItemImage.setIndexItem(indexItem);
        indexItem.setIndexImage(indexItemImage);
        indexItemImageService.save(indexItemImage);
        
        
        return url;
    }
    
    @DeleteMapping("/admin/index/{indexItemId}/image")
    public @ResponseBody void deleteIndexItemImage(
            @PathVariable Long indexItemId
    ) throws IOException {
        IndexItem indexItem = indexItemService.findById(indexItemId);
        IndexItemImage indexItemImage = indexItemImageService.findByIndexItemId(indexItemId);
        indexItem.setIndexImage(null);
        
        amazonClient.deleteFileFromS3Bucket(indexItemImage.getName(), imageControllerDir);
        
        indexItemImageService.delete(indexItemImage);
    }
}
