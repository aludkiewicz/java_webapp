package com.hicollege.webapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hicollege.webapp.dtos.Album;
import com.hicollege.webapp.dtos.User;

@RequestMapping(value = "/update")
@Controller
public class UpdateController {
    
    @Autowired
    private Dao dao;
    
    @RequestMapping(value = "/users/{name}")
    public void updateUser(
        @PathVariable(value = "name") String username,
        @RequestParam(value = "email", required = false) String email,
        @RequestParam(value = "age", required = false) int age) {
        
        User user = dao.getUserByName(username);
        user.setAge(Integer.toString(age));
        user.setEmail(email);
        dao.updateBatch(user);
    }
    
    @RequestMapping(value = "/users/{name}/add/albums/{title}")
    public void addAlbumToUser(
        @PathVariable(value = "name") String username,
        @PathVariable(value = "title") String title) {
        
        Album album = dao.getAlbumByTitle(title);
        User user = dao.getUserByName(username);
        album.getUsers().add(user);
        user.getAlbums().add(album);
        
        dao.updateBatch(user);
        dao.updateBatch(album);
        
    }
}
