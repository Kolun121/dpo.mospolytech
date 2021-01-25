package ru.mospolytech.dpo.controllers.admin;

import java.util.Arrays;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import ru.mospolytech.dpo.amazon.AmazonClient;
import ru.mospolytech.dpo.domain.IndexItem;
import ru.mospolytech.dpo.domain.image.IndexItemImage;
import ru.mospolytech.dpo.service.IndexItemService;
import ru.mospolytech.dpo.service.image.IndexItemImageService;

@Controller("adminIndexController")
@RequestMapping("/admin")
public class IndexController {
    private final String mainImageControllerDir = "index";

    private final IndexItemService indexItemService;
    private final IndexItemImageService indexItemImageService;
    private final AmazonClient amazonClient;

    public IndexController(IndexItemService indexItemService,
            IndexItemImageService indexItemImageService,
            AmazonClient amazonClient
    ){
        this.indexItemService = indexItemService;
        this.indexItemImageService = indexItemImageService;
        this.amazonClient = amazonClient;
    }
    
    @GetMapping
    public String getIndexPage(Model model) {
        model.addAttribute("indexItems", indexItemService.findAll());
        return "admin/index";
    }
    @PostMapping("/index/new")
    public ModelAndView createIndexItem() {
        ModelAndView mav = new ModelAndView("admin/fragments/index/indexItems :: indexItems");
        IndexItem indexItem = new IndexItem();
        
        mav.addObject("indexItems", Arrays.asList(indexItemService.save(indexItem)));
        return mav;
    }
    
    @PostMapping("/index/{indexItemId}")
    public @ResponseBody void updateIndexItemById(
            @RequestParam(value = "text", required = false) String text, 
            @RequestParam(value = "url", required = false) String url, 
            @PathVariable Long indexItemId)
    {
        IndexItem indexItem = new IndexItem();
        
        indexItem.setId(indexItemId);
        indexItem.setText(text);
        indexItem.setUrl(url);
        
        indexItem.setIndexImage(indexItemImageService.findByIndexItemId(indexItemId));
        indexItemService.save(indexItem);
    }
    
    @DeleteMapping("/index/{indexItemId}")
    public @ResponseBody void deleteIndexItemById(@PathVariable Long indexItemId){
        IndexItem indexItem = indexItemService.findById(indexItemId);
        
        if(indexItem.getIndexImage() != null){
            IndexItemImage indexItemImage = indexItemImageService.findByIndexItemId(indexItemId);
            amazonClient.deleteFileFromS3Bucket(indexItemImage.getName(), mainImageControllerDir);
        }
        
        
        indexItemService.deleteById(indexItemId);
    }
}
