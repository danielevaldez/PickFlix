package com.techelevator.dao;

import com.techelevator.model.CreateProfileDto;
import com.techelevator.model.Profile;

import java.util.List;

public interface ProfileDao {
    List<Profile> getProfiles(int userId);
    Profile getProfileById(int id);
    List<Profile> getProfilesByUserId(int id);
    Profile createProfile(CreateProfileDto profile);
    boolean deleteProfile(int userId, int profileId);
    //updateProfile
}
