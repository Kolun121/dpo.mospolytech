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
import org.springframework.web.bind.annotation.ResponseBody;
import ru.mospolytech.dpo.domain.UserFeedBack;
import ru.mospolytech.dpo.service.UserFeedBackService;

@Controller("adminUserFeedBackController")
@RequestMapping("/admin/userFeedBack")
public class UserFeedBackController {
    private final UserFeedBackService userFeedBackService;

    UserFeedBackController(UserFeedBackService userFeedBackService) {
        this.userFeedBackService = userFeedBackService;
    }
    
    @GetMapping
    public String getTeachersListPage(Model model) {
        model.addAttribute("userFeedBack", userFeedBackService.findAll());
        return "admin/user_feed_back/userFeedBackList";
    }
    
    @DeleteMapping("{id}")
    public @ResponseBody void deleteTeacherById(@PathVariable Long id){
        userFeedBackService.deleteById(id);
    }

}
