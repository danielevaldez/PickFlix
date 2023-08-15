package com.techelevator.controller;

import com.techelevator.dao.GenreDao;
import com.techelevator.dao.ProfileDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.CreateProfileDto;
import com.techelevator.model.Genre;
import com.techelevator.model.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path = "/profileselect/")
public class ProfileController {

    private ProfileDao profileDao;
    private GenreDao genreDao;

    public ProfileController(ProfileDao profileDao, GenreDao genreDao) {
        this.profileDao = profileDao;
        this.genreDao = genreDao;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "created", method = RequestMethod.POST)
    public void addProfile(@RequestBody CreateProfileDto newProfile) {
        int genreId = -1;
        Profile profile = null;
        try {
            profile = profileDao.createProfile(newProfile);
            if (profile == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Profile creation failed.");
            }
            genreId = genreDao.getGenreByName(newProfile.getProfileIcon()).getGenreId();
            if (genreId == -1) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Could not find genre with specified name.");
            }
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Profile creation failed.");
        }
        try {
            Genre favoriteGenre = genreDao.addFavoriteGenre(profile.getProfileId(), genreId);
            if (favoriteGenre == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Profile_genre favorite failed.");
            }
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Profile creation failed.");
        }
    }

    @RequestMapping(path = "profiles/{userId}", method = RequestMethod.GET)
    public List<Profile> getProfiles(@PathVariable int userId) {
        List<Profile> profileList = new ArrayList<>();
        try {
            profileList = profileDao.getProfiles(userId);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Profile creation failed.");
        }
        return profileList;
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(path = "profiles/{userId}/{profileId}", method = RequestMethod.DELETE)
    public void deleteProfile(@PathVariable int userId, @PathVariable int profileId) {
        try {
            boolean deleted = profileDao.deleteProfile(userId, profileId);
            if (!deleted) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Profile not found or unable to delete.");
            }
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to delete profile.");
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "profiles/{userId}/{profileId}", method = RequestMethod.PUT)
    public Profile updateProfile(@PathVariable int userId, @PathVariable int profileId, @RequestBody Profile updatedProfile) {
        Profile existingProfile = profileDao.getProfileById(profileId);
        if (existingProfile == null || existingProfile.getUserId() != userId) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Profile not found or unauthorized to update.");
        }
        updatedProfile.setProfileId(profileId);
        updatedProfile.setUserId(userId);
        try {
            return profileDao.updateProfile(updatedProfile);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to update profile.");
        }
    }

}
