package com.techelevator.controller;

import com.techelevator.dao.ProfileDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping(path = "profileselect/")
public class ProfileController {

    private ProfileDao profileDao;

    public ProfileController(ProfileDao profileDao) {
        this.profileDao = profileDao;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "created", method = RequestMethod.POST)
    public void addProfile(@Valid @RequestBody Profile newProfile) {
        try {
            Profile profile = profileDao.createProfile(newProfile);
            if (profile == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Profile creation failed.");
            }
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Profile creation failed.");
        }
    }
}
