package ru.mospolytech.dpo.controllers.admin;

import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.mospolytech.dpo.amazon.AmazonClient;
import ru.mospolytech.dpo.domain.News;
import ru.mospolytech.dpo.domain.image.NewsMainImage;
import ru.mospolytech.dpo.service.NewsService;
import ru.mospolytech.dpo.service.image.NewsMainImageService;

@Controller("adminNewsController")
@RequestMapping("/admin/news")
public class NewsController {
    private final String mainImageControllerDir = "news";
    
    private final NewsService newsService;
    private final NewsMainImageService newsMainImageService;
    private final AmazonClient amazonClient;

    NewsController(
            NewsService newsService,
            NewsMainImageService newsMainImageService,
            AmazonClient amazonClient
    ) {
        this.newsService = newsService;
        this.newsMainImageService = newsMainImageService;
        this.amazonClient = amazonClient;
    }
    
    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }
    
    @GetMapping
    public String getNewsListPage(Model model) {
        model.addAttribute("news", newsService.findAll());
        return "admin/news/newsList";
    }
    
    @GetMapping("{id}")
    public String getNewsItemById(@PathVariable Long id, Model model) {
        model.addAttribute("news", newsService.findById(id));
        return "admin/news/updateNews";
    }
    
    @PostMapping("/new")
    public @ResponseBody String newNewsItem() {
        News savedNewsItem = newsService.save(new News());
        return savedNewsItem.getId().toString();
    }
    
    @PostMapping("{id}")
    public String updateNewsItemById(@PathVariable Long id, @Valid News news, BindingResult result){
        news.setId(id);
        News savedNewsItem = newsService.save(news);

        return "redirect:/admin/news/" + savedNewsItem.getId();
    }
    
    @DeleteMapping("{id}")
    public @ResponseBody void deleteById(@PathVariable Long id){
        News news = newsService.findById(id);
        
        if(news.getNewsMainImage()!= null){
            NewsMainImage newsMainImage = newsMainImageService.findByNewsId(id);
            amazonClient.deleteFileFromS3Bucket(newsMainImage.getName(), mainImageControllerDir);
            newsMainImageService.delete(newsMainImage);
        }
        newsService.deleteById(id);
    }
}
