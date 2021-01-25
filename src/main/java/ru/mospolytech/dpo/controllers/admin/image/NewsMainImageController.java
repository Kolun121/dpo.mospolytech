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
import ru.mospolytech.dpo.domain.News;
import ru.mospolytech.dpo.domain.image.NewsMainImage;
import ru.mospolytech.dpo.service.NewsService;
import ru.mospolytech.dpo.service.image.NewsMainImageService;

@Slf4j
@Controller("adminNewsMainImageController")
public class NewsMainImageController {
    @Value("${upload.path}")
    private String uploadPath;
    private final String imageControllerDir = "news";
   
    private final NewsMainImageService newsMainImageService;
    private final NewsService newsService;
    private final AmazonClient amazonClient;

    public NewsMainImageController(NewsMainImageService newsMainImageService, NewsService newsService, AmazonClient amazonClient) {
        this.newsMainImageService = newsMainImageService;
        this.newsService = newsService;
        this.amazonClient = amazonClient;
    }

    
    @PostMapping("/admin/news/{newsId}/image/new")
    public @ResponseBody String addMainNewsImage(
            @PathVariable Long newsId,
            @RequestParam("file") MultipartFile file
    ) throws IOException {
        String url = amazonClient.uploadFile(file, imageControllerDir);
        
        News news = newsService.findById(newsId);

        NewsMainImage newsMainImage = new NewsMainImage();
        newsMainImage.setName(url);
        newsMainImage.setNews(news);
        news.setNewsMainImage(newsMainImage);
            
        newsMainImageService.save(newsMainImage);
        
        return url;
    }
    
    @DeleteMapping("/admin/news/{newsId}/image")
    public @ResponseBody void deleteMainNewsImage(
            @PathVariable Long newsId
    ) throws IOException {
        News news = newsService.findById(newsId);
        NewsMainImage newsMainImage = newsMainImageService.findByNewsId(newsId);
        news.setNewsMainImage(null);
        
        amazonClient.deleteFileFromS3Bucket(newsMainImage.getName(), imageControllerDir);
        
        newsMainImageService.delete(newsMainImage);
    }
}
