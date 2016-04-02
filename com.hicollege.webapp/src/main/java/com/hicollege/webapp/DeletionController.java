package com.hicollege.webapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hicollege.webapp.dtos.Status;
import com.hicollege.webapp.dtos.Status.StatusCode;

@RequestMapping(value = "/delete")
@RestController
public class DeletionController {
    
    @Autowired
    private Dao dao;
    
    @RequestMapping(value = "/users/{name}")
    public Status deleteUser(@PathVariable(value = "name") String username) {
        try {
            dao.deleteUserByName(username);
            return new Status(StatusCode.OK, "User successfully deleted!");
        } catch (Exception e) {
            e.printStackTrace();
            return new Status(StatusCode.ERROR, "Could not delete the user due to the following error: " + e.getMessage());
        }
    }
    
    @RequestMapping(value = "/albums/{title}")
    public Status deleteAlbum(@PathVariable(value = "title") String title) {
        try {
            dao.deleteAlbumByName(title);
            return new Status(StatusCode.OK, "Album successfully deleted!");
        } catch (Exception e) {
            e.printStackTrace();
            return new Status(StatusCode.ERROR, "Could not delete the album due to the following error: " + e.getMessage());
        }
    }

}
