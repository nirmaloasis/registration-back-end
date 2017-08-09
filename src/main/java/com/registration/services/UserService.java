package com.registration.services;

import com.registration.Pojo.Request;
import com.registration.entities.User;
import com.registration.entities.UserRole;
import com.registration.repositories.IUserRepository;
import com.registration.repositories.IUserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Service
public class UserService {
    private IUserRepository userRepository;
    private IUserRoleRepository userRoleRepository;

    private MailService mailService;

    @Autowired
    public void setMailService(MailService mailService) {
        this.mailService = mailService;
    }

    @Autowired
    public void setUserRepository(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setUserRoleRepository(IUserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    public User create(Request request) throws Exception {
        User user = new User(request.getUsername(),request.getEmail(),request.getPassword(),0);
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date dobDate = format.parse(request.getDob());
        user.setDob(dobDate);

        UserRole userRole = new UserRole(request.getUsername(),request.getRole());
        if(findByUsername(request.getUsername()).size() != 0){
            user.setEnabled(2);
            return user;
        }
        else {
            mailService.sendEmail(request.getEmail(),"registration@gmail.com",request.getUsername());
            this.userRoleRepository.save(userRole);
            return this.userRepository.save(user);
        }
    }

    public List<User> findByUsername(String username){
        return this.userRepository.findByUsername(username);
    }
    public String findByUsernameAndPassword(String username,String password){
        List<User> users=this.userRepository.findByUsernameAndPassword(username,password);
        return (users.size() == 0)?"Failure":String.valueOf(users.get(0).getEnabled());
    }

    public String verifyEmail(String encryptedUserName) throws NoSuchPaddingException, UnsupportedEncodingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        String username = mailService.decryptData(encryptedUserName);
        List<User> users = findByUsername(username);
        users.get(0).setEnabled(1);
        this.userRepository.save(users.get(0));
        return "success";
    }

}
