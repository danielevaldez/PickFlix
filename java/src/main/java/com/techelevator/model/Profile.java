package com.techelevator.model;

import javax.validation.constraints.NotEmpty;
import java.util.Objects;

public class Profile {
    private int profileId;
    @NotEmpty
    private int userId;
    @NotEmpty
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

    @Override
    public int hashCode() {
        return Objects.hash(profileId, userId, profileName);
    }
}

