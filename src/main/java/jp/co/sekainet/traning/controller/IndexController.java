package jp.co.sekainet.traning.controller;

import jp.co.sekainet.traning.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @Autowired
    private UserService userService;
    
    @RequestMapping("/")
    public String index() {
        if(userService.getLoginUser() == null) return "redirect:/login";
        if(userService.getLoginUser().isAdmin()) return "redirect:/user/list";
        return "redirect:/content";
    }
}
