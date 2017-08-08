package com.registration.controllers;


import com.registration.Pojo.Request;
import com.registration.entities.User;
import com.registration.services.UserService;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
public class UserController {

    private UserService userService;


    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value ="/enroll" , method = RequestMethod.POST)
    public User create(@RequestBody Request request) throws ParseException {
        return this.userService.create(request);
    }
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String findUser(@RequestBody Request request){
        return this.userService.findByUsernameAndPassword(request.getUsername(),request.getPassword());
    }


}
