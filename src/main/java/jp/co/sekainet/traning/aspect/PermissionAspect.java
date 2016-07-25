package jp.co.sekainet.traning.aspect;

import jp.co.sekainet.traning.service.UserService;
import lombok.val;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

@Aspect @Component
public class PermissionAspect {
    @Autowired
    private UserService userService;
    
    @Before("@within(adminOnly) && @annotation(requestMapping)")
    public void verifyAdminOnly(JoinPoint jp, AdminOnly adminOnly, RequestMapping requestMapping) {
        val loginUser = userService.getLoginUser();
        if (loginUser == null || !loginUser.isAdmin()) {
            throw new PermissionException();
        }
    }
}
