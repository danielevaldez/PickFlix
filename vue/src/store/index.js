import Vue from "vue";
import Vuex from "vuex";
import axios from "axios";
import sound from "../../public/icq2.mp3";

Vue.use(Vuex);

/*
 * The authorization header is set for axios when you login but what happens when you come back or
 * the page is refreshed. When that happens you need to check for the token in local storage and if it
 * exists you should set the header so that it will be attached to each request
 */
const currentToken = localStorage.getItem("token");
const currentUser = JSON.parse(localStorage.getItem("user"));

if (currentToken != null) {
  axios.defaults.headers.common["Authorization"] = `Bearer ${currentToken}`;
}

var loginSound = new Audio(sound);

export default new Vuex.Store({
  state: {
    token: currentToken || "",
    user: currentUser || {},
    userId: "",
    recommendedMovies: [],
    profiles: [],
    profileId: "",
    profileName: "",
    runAnimation: true,
  },
  mutations: {
    ANIMATION_BOOLEAN(state, bool){
      state.runAnimation = bool;
    },
    PLAY_LOGIN_SOUND() {
      loginSound.play();
    },
    SET_PROFILES(state, profiles) {
      state.profiles = profiles;
    },
    SET_RECOMMENDED_MOVIES(state, recommendedMovies) {
      state.recommendedMovies = recommendedMovies;
    },
    SET_AUTH_TOKEN(state, token) {
      state.token = token;
      localStorage.setItem("token", token);
      axios.defaults.headers.common["Authorization"] = `Bearer ${token}`;
    },
    SET_USER(state, user) {
      state.user = user;
      localStorage.setItem("user", JSON.stringify(user));
    },
    SET_USER_ID(state, user_id) {
      state.userId = user_id;
    },
    SET_PROFILE_ID(state, profile_id) {
      state.profileId = profile_id;
    },
    SET_PROFILE_NAME(state, profile_name) {
      state.profileName = profile_name;
    },
    LOGOUT(state) {
      localStorage.removeItem("token");
      localStorage.removeItem("user");
      state.token = "";
      state.user = {};
      state.profiles = [];
      axios.defaults.headers.common = {};
    },
  },
});
