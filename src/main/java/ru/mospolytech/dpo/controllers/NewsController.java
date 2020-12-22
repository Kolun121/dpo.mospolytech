package ru.mospolytech.dpo.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.mospolytech.dpo.domain.News;
import ru.mospolytech.dpo.domain.enumeration.Status;
import ru.mospolytech.dpo.service.NewsService;
import static ru.mospolytech.dpo.specification.NewsSpecification.*;

@Controller
@RequestMapping("/news")
public class NewsController {
    private final NewsService newsService;

    NewsController(NewsService newsService) {
        this.newsService = newsService;
    }
    
    @GetMapping()
    public String getNewsList(Model model ,
            @PageableDefault(sort = { "id" }, direction = Sort.Direction.DESC, value = 9) Pageable pageable) {
         Page<News> news = newsService.findAllPageableSpec(Specification
                        .where(hasPublishedStatus()),
                        pageable);
        model.addAttribute("news", news);
        return "news/newsList";
    }
    
    @GetMapping("{urlSegment}")
    public String getNewsByUrlSegment(@PathVariable String urlSegment, Model model) {
        News news = newsService.findByUrlSegment(urlSegment);
        model.addAttribute("news", news);
        
        return "news/showNews";
    }
}
