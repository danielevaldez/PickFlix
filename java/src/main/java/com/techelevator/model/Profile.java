package com.techelevator.model;

import javax.validation.constraints.NotEmpty;
import java.util.Objects;

public class Profile {
    private int profileId;
    private int userId;
    private String profileName;
    private String profileIcon;

    public Profile() {
    }

    public Profile(int profileId, int userId, String profileName, String profileIcon) {
        this.profileId = profileId;
        this.userId = userId;
        this.profileName = profileName;
        this.profileIcon = profileIcon;
    }

    public int getProfileId() {
        return profileId;
    }

    public void setProfileId(int profileId) {
        this.profileId = profileId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public String getProfileIcon() {
        return profileIcon;
    }

    public void setProfileIcon(String profileIcon) {
        this.profileIcon = profileIcon;
    }

    @Override
    public String toString() {
        return "Profile{" +
                "profileId=" + profileId +
                ", userId=" + userId +
                ", profileName='" + profileName + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(profileId, userId, profileName, profileIcon);
    }
}

