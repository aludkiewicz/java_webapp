package com.hicollege.webapp;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.hicollege.webapp.dtos.Album;
import com.hicollege.webapp.dtos.Status;
import com.hicollege.webapp.dtos.User;
import com.hicollege.webapp.dtos.Status.StatusCode;

/**
 * Controller dealing with all requests relating to creating something. All
 * methods return a {@link Status}-object that lets the front end know how an
 * operation went, and a message to display.
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
@RequestMapping(value = "/add")
public class CreationController {

    @Autowired
    private Dao dao;

    @RequestMapping(value = "/user", method = RequestMethod.PUT)
    public Status createUser(
        @RequestParam(value = "username", required = true) String username,
        @RequestParam(value = "age", required = true) int age,
        @RequestParam(value = "email", required = true) String email,
        @RequestParam(value = "albums", required = false) List<String> albums) {

        try {
            User newUser = new User(username, Integer.toString(age), email);
            if (albums != null) {
                Set<Album> userAlbums = new HashSet<>();
                for (String title : albums) {
                    Album album = dao.getAlbumByTitle(title);
                    if (album != null) {
                        userAlbums.add(album);
                    }
                }
                newUser.setAlbums(userAlbums);
            }
            dao.save(newUser);
            return new Status(StatusCode.OK, "User successfully created!");
        } catch (Exception e) {
            return new Status(StatusCode.ERROR, "Could not create user due to the following error: " + e.getMessage());
        }

    }

    @RequestMapping(value = "/album", method = RequestMethod.PUT)
    public Status createAlbum(
        @RequestParam(value = "title", required = true) String title,
        @RequestParam(value = "songs", required = true) List<String> songs,
        @RequestParam(value = "artists", required = true) List<String> artists) {

        try {
            Album album = new Album();
            album.setArtists(artists);
            album.setSongs(songs);
            album.setTitle(title);
            dao.save(album);
            return new Status(StatusCode.OK, "Album successfully added!");
        } catch (Exception e) {
            e.printStackTrace();
            return new Status(StatusCode.ERROR,
                "Could not update the album due to the following error: " + e.getMessage());
        }
    }
}
