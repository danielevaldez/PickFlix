import axios from 'axios';

export default {

    getProfiles(userID){
        return axios.get("/profiles/" + userID);
    },

    addProfile(userID, profile){
        return axios.post("/profiles/"+userID, profile);
    },

    updateProfile(userID, profile){
        return axios.put("/profiles/"+userID, profile);
    },

    deleteProfile(profileID){
        return axios.delete("/profiles/" + profileID);
    }
    
}