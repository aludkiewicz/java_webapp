package com.hicollege.webapp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.hicollege.webapp.dtos.Album;
import com.hicollege.webapp.dtos.User;

@RestController
@RequestMapping(value = "/find")
public class SearchController {

    @Autowired
    private UserDao dao;
    
    @RequestMapping(value = "/allusers", method = RequestMethod.GET)
    @ResponseBody
    public List<User> getUsers() {
        return dao.getAllUsers();
    }
    
    @RequestMapping(value = "/album/{title}", method = RequestMethod.GET)
    @ResponseBody
    public Album findAlbum(@PathVariable String title) {
        return dao.getAlbumByTitle(title);
    }
    
    @RequestMapping(value = "/user/{username}", method = RequestMethod.GET)
    @ResponseBody
    public User findUser(@PathVariable String username) {
        return dao.getUserByName(username);
    }
    
}
