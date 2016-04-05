package com.hicollege.webapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hicollege.webapp.dtos.Status;
import com.hicollege.webapp.dtos.Status.StatusCode;

/**
 * Controller dealing with all deletion-related requests from the webapp. All
 * methods return a {@link Status}-object that tells the front-end how the
 * operation went along with a message.
 * 
 * This controller, like most others in this app deals directly with the Dao
 * (Data Access Object), while in reality in a bigger app you'd probably want a
 * service-layer in between. For example, ideally we would separate between the
 * objects returned from our API-calls and the objects saved to the database.
 * The objects returned in an API are typically "prettified", i.e. have e.g. the
 * database ID stripped from them, maybe have more/fewer fields and so on.
 */
@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
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
            return new Status(StatusCode.ERROR,
                "Could not delete the user due to the following error: " + e.getMessage());
        }
    }

    @RequestMapping(value = "/albums/{title}")
    public Status deleteAlbum(@PathVariable(value = "title") String title) {
        try {
            dao.deleteAlbumByName(title);
            return new Status(StatusCode.OK, "Album successfully deleted!");
        } catch (Exception e) {
            e.printStackTrace();
            return new Status(StatusCode.ERROR,
                "Could not delete the album due to the following error: " + e.getMessage());
        }
    }

}
