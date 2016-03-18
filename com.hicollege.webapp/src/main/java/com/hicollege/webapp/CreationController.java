package com.hicollege.webapp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.hicollege.webapp.dtos.Album;
import com.hicollege.webapp.dtos.User;

@RestController
@RequestMapping(value = "/add")
public class CreationController {
    
    @Autowired
    private UserDao dao;
    
    @RequestMapping(value = "/user", method = RequestMethod.PUT)
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
    
    
    @RequestMapping(value = "/album", method = RequestMethod.PUT)
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
