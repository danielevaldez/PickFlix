import axios from "axios";

export default {
  create(profile) {
    return axios.post("/profileselect/created", profile);
  },

  getProfiles(userID) {
    return axios.get("/profileselect/profiles/" + userID);
  },

  deleteProfile(userId, profileId) {
    return axios.delete("/profileselect/profiles/" + userId + "/" + profileId);
  },

  updateProfile(userId, profileId, profile) {
    return axios.put("/profileselect/profiles/" + userId + "/" + profileId, profile);
  },
};
