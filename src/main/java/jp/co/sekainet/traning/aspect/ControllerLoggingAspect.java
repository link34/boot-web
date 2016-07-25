package jp.co.sekainet.traning.aspect;

import jp.co.sekainet.traning.entity.User;
import jp.co.sekainet.traning.service.UserService;
import lombok.extern.slf4j.Slf4j;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

@Aspect @Component @Slf4j
public class ControllerLoggingAspect {
    
    @Autowired
    private UserService userService;
    
    @Before("@annotation(requestMapping)")
    public void logging(JoinPoint jp, RequestMapping requestMapping){
        User loginUser = userService.getLoginUser();
        long loginUserId = loginUser == null ? 0L : loginUser.getId();
        log.info("アクセスログ:ユーザーＩＤ=" + loginUserId +"機能=" + jp.getSignature());
    }
}
