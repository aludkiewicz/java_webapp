package com.hicollege.webapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.hicollege.webapp.dtos.Album;
import com.hicollege.webapp.dtos.Status;
import com.hicollege.webapp.dtos.Status.StatusCode;
import com.hicollege.webapp.dtos.User;

/**
 * Controller dealing with all operations that update an album or a user. Note
 * that it uses RequestMethod.GET. This is just to make it easier to test in a
 * browser, because I didn't include any Javascript for it in the app :)
 * 
 * This controller, like most others in this app deals directly with the Dao
 * (Data Access Object), while in reality in a bigger app you'd probably want a
 * service-layer in between. For example, ideally we would separate between the
 * objects returned from our API-calls and the objects saved to the database.
 * The objects returned in an API are typically "prettified", i.e. have e.g. the
 * database ID stripped from them, maybe have more/fewer fields and so on.
 *
 */
@RequestMapping(value = "/update", method = RequestMethod.GET)
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
        } catch (Exception e) {
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
            return new Status(StatusCode.ERROR,
                "Could not add the album to the user due to the following error: " + e.getMessage());
        }

    }
}
