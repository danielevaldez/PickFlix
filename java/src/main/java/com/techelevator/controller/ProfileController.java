package com.techelevator.controller;

import com.techelevator.dao.ProfileDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.CreateProfileDto;
import com.techelevator.model.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path = "/profileselect/")
public class ProfileController {

    private ProfileDao profileDao;

    public ProfileController(ProfileDao profileDao) {
        this.profileDao = profileDao;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "created", method = RequestMethod.POST)
    public void addProfile(@RequestBody CreateProfileDto newProfile) {
        try {
            Profile profile = profileDao.createProfile(newProfile);
            if (profile == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Profile creation failed.");
            }
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Profile creation failed.");
        }
    }

    @RequestMapping(path = "profiles/{userId}", method = RequestMethod.GET)
    public List<Profile> getProfiles(@PathVariable int userId) {
        List<Profile> profileList;
        try {
            profileList = profileDao.getProfiles(userId);
            if (profileList.size() < 1) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Failed to get profiles.");
            }
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Profile creation failed.");
        }
        return profileList;
    }
}
