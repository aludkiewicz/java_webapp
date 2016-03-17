package com.hicollege.webapp;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.hicollege.webapp.dtos.User;

//@EnableAutoConfiguration
@Controller
public class UiController {
    
    @Autowired
    private UserDao dao;
	

    @RequestMapping("/hello")
    @ResponseBody
    String home() {
        return "Hello World!";
    }
    
    @RequestMapping(value = "/ui", method = RequestMethod.GET)
    public String getUI() throws IOException {
        return "ui/ui";
    }
    
    @RequestMapping(value = "/get/allusers", method = RequestMethod.GET)
    @ResponseBody
    public List<User> getUsers() {
        return dao.getAllUsers();
    }
    
    
    @RequestMapping(value = "/add/user", method = RequestMethod.PUT)
    @ResponseBody
    public void createUser(
        @RequestParam(value = "username", required = true)   String username,
        @RequestParam(value = "age",      required = true)   int age,
        @RequestParam(value = "email",    required = true)   String email,
        @RequestParam(value = "albums",   required = false)  List<String> albums) {
        
        User newUser = new User(username, Integer.toString(age), email);
        dao.save(newUser);
    }
}
