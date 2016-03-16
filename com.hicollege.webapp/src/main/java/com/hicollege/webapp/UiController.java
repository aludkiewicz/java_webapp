package com.hicollege.webapp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hicollege.webapp.dtos.User;

//@EnableAutoConfiguration
@Controller
public class UiController {
	

    @RequestMapping("/hello")
    @ResponseBody
    String home() {
        return "Hello World!";
    }
    
    @RequestMapping(value = "/ui", method = RequestMethod.GET)
    public String getUI() throws IOException {
        return "ui/ui";
    }
    
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    @ResponseBody
    public List<User> getUsers() {
        return Arrays.asList(new User("Alexander", "27", "alexander.ludkiewicz@hiq.se", new ArrayList<>()));
    }
    
    @RequestMapping(value = "/create/user/derp/foo/bar/lol/what", method = RequestMethod.GET)
    @ResponseBody
    public String createUser() {
        return "Derp";
    }

}
