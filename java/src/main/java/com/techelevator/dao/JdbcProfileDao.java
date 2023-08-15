package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.CreateProfileDto;
import com.techelevator.model.Profile;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcProfileDao implements ProfileDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcProfileDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Profile> getProfiles(int userId) {
        List<Profile> profiles = new ArrayList<>();
        String sql = "SELECT profile_id, user_id, profile_name, profile_icon FROM profile WHERE user_id = ?;";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
            while (results.next()) {
                Profile profile = mapRowToProfile(results);
                profiles.add(profile);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return profiles;
    }

    @Override
    public Profile getProfileById(int id) {
        Profile profile = null;
        String sql = "SELECT profile_id, user_id, profile_name, profile_icon FROM profile WHERE profile_id = ?;";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
            if (results.next()) {
                profile = mapRowToProfile(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return profile;
    }

    @Override
    public List<Profile> getProfilesByUserId(int id) {
        List<Profile> profiles = new ArrayList<>();
        String sql = "SELECT profile_id, user_id, profile_name, profile_icon FROM profile WHERE user_id = ?;";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
            while (results.next()) {
                Profile profile = mapRowToProfile(results);
                profiles.add(profile);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return profiles;
    }

    @Override
    public Profile createProfile(CreateProfileDto profile) {
        Profile newProfile = null;
        String insertProfileSql = "INSERT INTO profile (user_id, profile_name, profile_icon) values (?, ?, ?) RETURNING profile_id;";

        try {
            int newProfileId = jdbcTemplate.queryForObject(insertProfileSql, int.class, profile.getUserId(), profile.getProfileName(), profile.getProfileIcon());
            newProfile = getProfileById(newProfileId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return newProfile;
    }

    @Override
    public boolean deleteProfile(int userId, int profileId) {
        String deleteProfileSql = "DELETE FROM profile WHERE user_id = ? AND profile_id = ?;";
        String deleteProfileGenreSql = "DELETE FROM profile_genre WHERE profile_id = ?";
        boolean success = false;

        try {
            int rowsAffected = jdbcTemplate.update(deleteProfileGenreSql, profileId);
            if (rowsAffected < 1) {
                success = true;
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }

        try {
            int rowsAffected = jdbcTemplate.update(deleteProfileSql, userId, profileId);
            success = rowsAffected == 1;
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }

        return success;
    }

    private Profile mapRowToProfile(SqlRowSet rs) {
        Profile profile = new Profile();
        profile.setProfileId(rs.getInt("profile_id"));
        profile.setUserId(rs.getInt("user_id"));
        profile.setProfileName(rs.getString("profile_name"));
        profile.setProfileIcon(rs.getString("profile_icon"));
        return profile;
    }
}
