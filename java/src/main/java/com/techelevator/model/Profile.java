package com.techelevator.model;

public class Profile {
    private int profileId;
    private int userId;
    private String profileName;

    public Profile() {
    }

    public Profile(int profileId, int userId, String profileName) {
        this.profileId = profileId;
        this.userId = userId;
        this.profileName = profileName;
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

    @Override
    public String toString() {
        return "Profile{" +
                "profileId=" + profileId +
                ", userId=" + userId +
                ", profileName='" + profileName + '\'' +
                '}';
    }
}

