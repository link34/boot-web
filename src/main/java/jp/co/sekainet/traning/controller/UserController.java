package jp.co.sekainet.traning.controller;

import javax.servlet.http.HttpServletRequest;

import jp.co.sekainet.traning.aspect.AdminOnly;
import jp.co.sekainet.traning.aspect.PermissionException;
import jp.co.sekainet.traning.entity.User;
import jp.co.sekainet.traning.service.UserService;
import lombok.val;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

@AdminOnly
@Controller
@RequestMapping("/user")
public class UserController {
    
    @Autowired
    private UserService userService;
        
    @RequestMapping(path="edit", method=RequestMethod.GET)
    public String edit(@RequestParam(required=false) Long id, Model model) {
        User user = id == null ? new User() : userService.findOne(id);
        if(user == null) return "redirect:/user/edit";
        model.addAttribute("user", user);
        return "user/edit";
    }
    
    @RequestMapping(path="edit", method=RequestMethod.POST)
    public String edit(@Validated User formUser,BindingResult result, RedirectAttributes attributes) {
        if (!userService.hasUniqueLoginId(formUser)) {
            result.rejectValue("loginId" , null, "このログインIDは重複しているため使用できません");            
        }
        if(result.hasErrors()) return "user/edit";
        userService.save(formUser);
        attributes.addAttribute("id", formUser.getId());
        attributes.addFlashAttribute("saved", true);
        return "redirect:/user/edit";
    }
    
    @RequestMapping(path="list", method=RequestMethod.GET)
    public String list(Model model){
        val users = userService.findAll();
        model.addAttribute("users", users);
        return "user/list";
    }
    
    @RequestMapping("delete")
    public String delete(@RequestParam Long id){
        userService.delete(id);
        return "redirect:/user/list";
    }
    
    @ExceptionHandler(PermissionException.class)
    public String handlePermissionException(HttpServletRequest request) {
        FlashMap flashMap = RequestContextUtils.getOutputFlashMap(request);
        flashMap.put("message", "ユーザー管理機能にアクセスするには管理者としてログインしてください。");        
        return "redirect:/login";        
    }
}
