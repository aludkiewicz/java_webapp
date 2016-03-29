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
    private Dao dao;
    
    @RequestMapping(value = "/allusers", method = RequestMethod.GET)
    public List<User> getUsers() {
        return dao.getAllUsers();
    }
    
    @RequestMapping(value = "/allalbums", method = RequestMethod.GET)
    public List<Album> getAlbums() {
        return dao.getAllAlbums();
    }
    
    
    @RequestMapping(value = "/album/{title}", method = RequestMethod.GET)
    public Album findAlbum(@PathVariable String title) {
        return dao.getAlbumByTitle(title);
    }
    
    @RequestMapping(value = "/user/{username}", method = RequestMethod.GET)
    public User findUser(@PathVariable String username) {
        return dao.getUserByName(username);
    }
    
}
