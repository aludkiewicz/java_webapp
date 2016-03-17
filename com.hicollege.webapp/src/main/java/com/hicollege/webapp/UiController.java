package com.hicollege.webapp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.hicollege.webapp.dtos.Album;
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
        return "ui/ui";
    }
    
    @RequestMapping(value = "/ui_album", method = RequestMethod.GET)
    public String getUIAlbums() throws IOException {
        return "ui/ui_album";
    }
    
    @RequestMapping(value = "/get/allusers", method = RequestMethod.GET)
    @ResponseBody
    public List<User> getUsers() {
        return dao.getAllUsers();
    }
    
    
    @RequestMapping(value = "/add/user", method = RequestMethod.PUT)
    @ResponseBody
    public void createUser(
        @RequestParam(value = "username", required = true)   String username,
        @RequestParam(value = "age",      required = true)   int age,
        @RequestParam(value = "email",    required = true)   String email,
        @RequestParam(value = "albums",   required = false)  List<String> albums) {
        
        User newUser = new User(username, Integer.toString(age), email);
        
        List<Album> userAlbums = new ArrayList<>();
        for(String title : albums) {
            Album album = dao.getAlbumByTitle(title);
            if(album != null) {
                userAlbums.add(album);
            }
        }
        newUser.setAlbums(userAlbums);
        dao.save(newUser);
    }
    
    @RequestMapping(value = "/add/album", method = RequestMethod.PUT)
    @ResponseBody
    public void createAlbum(
        @RequestParam(value = "title",      required = true)   String title,
        @RequestParam(value = "songs",      required = true)   List<String> songs,
        @RequestParam(value = "artists",    required = true)   List<String> artists) {
        
        Album album = new Album();
        album.setArtists(artists);
        album.setSongs(songs);
        album.setTitle(title);
        
        
        dao.saveAlbum(album);
    }
}
