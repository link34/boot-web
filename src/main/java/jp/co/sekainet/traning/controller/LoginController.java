package jp.co.sekainet.traning.controller;

import jp.co.sekainet.traning.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @Autowired private UserService userService;

    @RequestMapping(path="/login", method=RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(path="/login", method=RequestMethod.POST)
    public String login(Model model, @RequestParam String loginId, @RequestParam String password) {
        if(userService.login(loginId, password)) return "redirect:/";        
        
        model.addAttribute("message", "ログインに失敗しました");
        return "login";
    }
    @RequestMapping(path="/logout")
    public String logout() {
        userService.logout();
        return "redirect:/";
    }
//    @RequestMapping(method=RequestMethod.POST)
//    public String login(Model model, @RequestParam String loginId, @RequestParam String password) {
//        String message = null;
//        if (userService.login(loginId, password)) {
//            message = "ログインに成功しました";
//        } else {
//            message = "ログインに失敗しました";
//        }
//        model.addAttribute("message", message);
//        return "login";
//    }
}
