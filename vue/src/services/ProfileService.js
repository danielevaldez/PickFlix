import axios from "axios";

export default {
  create(profile) {
    return axios.post("/profileselect/created", profile);
  },

  getProfiles(userID) {
    return axios.get("/profileselect/profiles/" + userID);
  },

  deleteProfile(userID, profileID){
    return axios.delete("/profileselect/profiles/" + userID + "/" + profileID)
  }
};
