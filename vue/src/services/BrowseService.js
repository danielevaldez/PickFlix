import axios from "axios";

export default {
  getMovies(profileId) {
    return axios.get("/movies/browse/" + profileId);
  },
};
