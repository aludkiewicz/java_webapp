package com.hicollege.webapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hicollege.webapp.dtos.Album;
import com.hicollege.webapp.dtos.Status;
import com.hicollege.webapp.dtos.Status.StatusCode;
import com.hicollege.webapp.dtos.User;

@RequestMapping(value = "/update")
@RestController
public class UpdateController {
    
    @Autowired
    private Dao dao;
    
    @RequestMapping(value = "/users/{name}")
    public Status updateUser(
        @PathVariable(value = "name") String username,
        @RequestParam(value = "email", required = false) String email,
        @RequestParam(value = "age", required = false) Integer age) {
        
        try {
            User user = dao.getUserByName(username);
            user.setAge(Integer.toString(age));
            user.setEmail(email);
            dao.update(user);
            return new Status(StatusCode.OK, "User successfully updated!");
        } catch(Exception e) {
            e.printStackTrace();
            return new Status(StatusCode.ERROR, "Could not update user due to the following error: " + e.getMessage());
        }
    }
    
    @RequestMapping(value = "/users/{name}/add/albums/{title}")
    public Status addAlbumToUser(
        @PathVariable(value = "name") String username,
        @PathVariable(value = "title") String title) {
        
        try {
            Album album = dao.getAlbumByTitle(title);
            User user = dao.getUserByName(username);
            album.getUsers().add(user);
            user.getAlbums().add(album);
            
            dao.merge(album);
            dao.merge(user);
            return new Status(StatusCode.OK, "Album successfully added!");
        } catch (Exception e) {
            e.printStackTrace();
            return new Status(StatusCode.ERROR, "Could not add the album to the user due to the following error: " + e.getMessage());
        }
        
    }
}
