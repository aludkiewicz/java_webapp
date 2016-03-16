package com.hicollege.webapp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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
        User user = new User(1, "Alexander", "27", "alexander.ludkiewicz@hiq.se", new ArrayList<>());
        dao.saveOrUpdate(user);
        return "ui/ui";
    }
    
    @RequestMapping(value = "/find/allusers", method = RequestMethod.GET)
    @ResponseBody
    public List<User> getUsers() {
        return Arrays.asList(new User(1, "Alexander", "27", "alexander.ludkiewicz@hiq.se", new ArrayList<>()));
    }
    
    @RequestMapping(value = "/find/user/{id}", method = RequestMethod.GET)
    @ResponseBody
    public List<User> getUser(@PathVariable int id) {
        return Arrays.asList(new User(id, "Alexander", "27", "alexander.ludkiewicz@hiq.se", new ArrayList<>()));
    }
    
    @RequestMapping(value = "/delete/user/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public List<User> deleteUser(@PathVariable int id) {
        return Arrays.asList(new User(id, "Alexander", "27", "alexander.ludkiewicz@hiq.se", new ArrayList<>()));
    }
    
    @RequestMapping(value = "/create/user/", method = RequestMethod.GET)
    @ResponseBody
    public String createUser(
        @RequestParam(value = "username", required = true)  String username,
        @RequestParam(value = "age",      required = true)  int age,
        @RequestParam(value = "email",    required = false) String email,
        @RequestParam(value = "albums",   required = true)  List<String> albumNames) {
        
        //User newUser = new User(id, name, age, email, albums)
        
        return "Derp";
    }

}
