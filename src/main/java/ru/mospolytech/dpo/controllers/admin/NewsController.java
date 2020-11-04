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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.mospolytech.dpo.domain.News;
import ru.mospolytech.dpo.service.NewsService;

@Controller("adminNewsController")
@RequestMapping("/admin/news")
public class NewsController {
    
    private final NewsService newsService;

    NewsController(NewsService newsService) {
        this.newsService = newsService;
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
    public String updateNewsItemById(@PathVariable Long id, @ModelAttribute News news){
        news.setId(id);
        News savedNewsItem = newsService.save(news);

        return "redirect:/admin/news/" + savedNewsItem.getId();
    }
    
    @DeleteMapping("{id}")
    public @ResponseBody void deleteById(@PathVariable Long id){
        newsService.deleteById(id);
    }
}
