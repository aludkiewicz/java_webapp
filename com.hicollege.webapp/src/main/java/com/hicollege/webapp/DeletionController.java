package com.hicollege.webapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/delete")
@RestController
public class DeletionController {
    
    @Autowired
    private Dao dao;
    
    @RequestMapping(value = "/users/{name}")
    public void deleteUser(@PathVariable(value = "name") String username) {
        dao.deleteUserByName(username);
    }
    
    @RequestMapping(value = "/albums/{title}")
    public void deleteAlbum(@PathVariable(value = "title") String title) {
        dao.deleteAlbumByName(title);
    }

}
