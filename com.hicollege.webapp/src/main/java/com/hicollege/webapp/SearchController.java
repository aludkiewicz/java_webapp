package com.hicollege.webapp;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.hicollege.webapp.dtos.Album;
import com.hicollege.webapp.dtos.User;

/**
 * Controller dealing with all search-related activities. 
 * 
 * This controller, like most others in this app deals directly with the Dao
 * (Data Access Object), while in reality in a bigger app you'd probably want a
 * service-layer in between. For example, ideally we would separate between the
 * objects returned from our API-calls and the objects saved to the database.
 * The objects returned in an API are typically "prettified", i.e. have e.g. the
 * database ID stripped from them, maybe have more/fewer fields and so on.
 *
 */
@RestController
@RequestMapping(value = "/find", method = RequestMethod.GET)
public class SearchController {

    @Autowired
    private Dao dao;
    
    @RequestMapping(value = "/users")
    public List<User> getUsers() {
        return dao.getAllUsers();
    }
    
    @RequestMapping(value = "/albums")
    public List<Album> getAlbums() {
        return dao.getAllAlbums();
    }
    
    
    @RequestMapping(value = "/albums/{title}")
    public Album findAlbum(@PathVariable String title) {
        return dao.getAlbumByTitle(title);
    }
    
    @RequestMapping(value = "/users/{username}")
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
