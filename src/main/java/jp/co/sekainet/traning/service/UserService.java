package jp.co.sekainet.traning.service;

import java.util.List;

import jp.co.sekainet.traning.entity.User;
import jp.co.sekainet.traning.repository.UserRepository;
import lombok.Getter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

@Service("userService")
@Scope( value=WebApplicationContext.SCOPE_SESSION,
        proxyMode=ScopedProxyMode.TARGET_CLASS)
public class UserService {

    @Autowired private UserRepository userRepo;
    @Getter private User loginUser;

    public boolean login(String loginId, String password) {
        User user = userRepo.findByLoginId(loginId);
        if (user == null) return false;
        if (user.getPassword().equals(password)) {
            loginUser = user;
            return true;
        } else {
            return false;
        }
    }

    public User findOne(Long id) {        
        return userRepo.findOne(id);
    }

    public void save(User formUser) {
        userRepo.save(formUser);        
    }
    
    public List<User> findAll() {
        return userRepo.findAll();    
    }
    
    public void delete(Long id) {
        userRepo.delete(id);
    }
    
    public boolean hasUniqueLoginId(User user){
        User sameLoginIdUser = userRepo.findByLoginId(user.getLoginId());
        if(sameLoginIdUser == null) return true;
        if(sameLoginIdUser.getId() == user.getId()) return true;
        return false;
    }
    
    public void logout(){
        loginUser = null;
    }
}
