package com.hicollege.webapp;

import java.util.List;
import java.util.Set;

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
    
    @RequestMapping(value = "/users/{username}", method = RequestMethod.GET)
    public User findUser(@PathVariable String username) {
        return dao.getUserByName(username);
    }
    
    @RequestMapping(value = "/users/{name}/albums")
    public Set<Album> findAlbumsForUser(@PathVariable(value = "name") String username) {
        User user = dao.getUserByName(username);
        return user.getAlbums();
    }
    
    @RequestMapping(value = "/albums/{title}/users")
    public Set<User> findUsersForAlbum(@PathVariable(value = "title") String title) {
        return dao.getAlbumByTitle(title).getUsers();
    }
}
