
<template>
  <body>
    <div>
      <header>
        <nav>
          <router-link to="/browse">
            <img src="../../img/123123.png" class="logo" alt="Logo" />
          </router-link>
          <div class="join-box">
            <button class="btn logout-btn" @click="logout">LOG OUT</button>
            <button class="switch-profile-btn" @click="switchProfiles">
              SWITCH PROFILES
            </button>
          </div>
        </nav>
      </header>
      <main>
        <!-- Top 5 recommended movies section -->
        <h2 class="recommendation-text">
          Top 5 picks for {{ selectedProfile }}
        </h2>
        <!-- Movie details view -->
        <div
          class="movie-details-container"
          v-if="displayMovieDetails"
          @click="hideDetails($event)"
        >
          <div class="movie-details">
            <img
              class="movie-details-poster"
              :src="selectedMovie.imagePath"
              alt="Movie Poster"
            />
            <div class="movie-info">
              <h3 class="movie-title">{{ selectedMovie.movieTitle }}</h3>
              <p class="movie-release-duration">
                Released: {{ selectedMovie.movieRelease }} | Duration: {{ selectedMovie.movieDuration }}
              </p>
              <p class="movie-description">{{ selectedMovie.movieDescription }}</p>
              <!-- Star Rating -->
              <div id="rating">
              <span
                v-for="index in 5"
                :key="index"
                :class="{ 'hover': index <= selectedStar }"
                @mouseenter="enterStarListener(index)"
                @click="rateMovie(index)"
              >
                ★
              </span>
            </div>
                    <!-- Heart Button -->
          <button class="heart-button" @click="toggleHeart">
            <span :class="{'red-heart': isHeartClicked}">♡</span>
          </button>
            </div>
          </div>
        </div>
        <!-- Container for recommended movie posters -->
        <div class="movie-containers">
          <!-- Loop through recommendedMovies and display movie images -->
          <ul class="movie-poster-list">
            <li
              class="movie-container"
              v-for="recommendedMovie in this.$store.state.recommendedMovies"
              :key="recommendedMovie.ID"
              @click="showMovieDetails(recommendedMovie)"
            >
              <img
                class="movie-poster"
                :src="recommendedMovie.imagePath"
                alt="Movie Poster"
              />
            </li>
          </ul>
        </div>
      </main>
    </div>
  </body>
</template>

<script>
import browseService from "../services/BrowseService";
import StarRatingService from "../components/StarRating.vue";

export default {
  name: "Browse",
  data() {
    return {
      selectedMovie: {
        movieTitle: "",
        movieDuration: "",
        movieRelease: "",
        movieDescription: "",
        imagePath: "",
      },
      selectedProfile: this.$store.state.profileName,
      displayMovieDetails: false,
      
      selectedStar: 0,
      selectedMovieId: 1, // Adjust with the actual movie ID
      isHeartClicked: false,
    };
  },
  created() {
    this.getRecommendedMovies();
  },
  
  methods: {
    // gets recommended movies for selected profile
    getRecommendedMovies() {
      browseService
        .getMovies(this.$store.state.profileId)
        .then((response) => {
          this.$store.commit("SET_RECOMMENDED_MOVIES", response.data);
        })
        .catch((error) => {
          console.error("Error fetching movies:", error);
        });
    },
    showMovieDetails(recommendedMovie) {
      this.displayMovieDetails = true;
      this.selectedMovie.movieTitle = recommendedMovie.movieTitle;
      this.selectedMovie.movieDescription = recommendedMovie.movieDescription;
      this.selectedMovie.movieDuration = recommendedMovie.movieDuration;
      this.selectedMovie.movieRelease = recommendedMovie.movieRelease;
      this.selectedMovie.imagePath = recommendedMovie.imagePath;
    },
    hideDetails(event) {
      if (!this.$el.querySelector(".movie-details").contains(event.target)) {
        this.displayMovieDetails = false;
      }
    },
    switchProfiles() {
      this.$router.push({ path: "/profileselect", query: { load: "false" } });
    },
    logout() {
      this.$store.commit("LOGOUT");
      this.$router.push("/login");
    },
    enterStarListener(index) {
    this.selectedStar = index;
  },
    rateMovie(rating) {
      StarRatingService.setStarRating(this.selectedMovieId, rating);
    },
    toggleHeart() {
      this.isHeartClicked = !this.isHeartClicked;
    },
  },
};
</script>

<style scoped>
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

main {
  background-image: url("../../img/browsebg.png");
  background-size: cover;
  background-repeat: no-repeat;
  font-family: Arial, sans-serif;
  height: 100vh;
  width: 100vw;
  padding: 0px;
  margin: 0px;
}

nav {
  width: 100%;
  height: 60px;
  position: fixed;
  top: 0;
  z-index: 9;
  background: #000;
  padding: 0 2.5vw;
  display: flex;
  align-items: center;
}

.logo {
  height: auto;
  width: 150px;
  padding-top: 10px;
}

.join-box {
  width: fit-content;
  display: flex;
  justify-content: center;
  align-items: center;
  height: auto;
  margin-left: auto;
}

.join-msg {
  color: #fff;
  font-family: Arial;
}

.logout-btn {
  background: #dd0e15;
  border-color: #dd0e15;
  font-family: Arial;
  border-style: solid;
  border-radius: 50px;
  padding: 8px 15px;
  font-size: 16px;
  background-color: rgb(221, 14, 21);
  color: #ffffff;
  cursor: pointer;
  margin-top: 10px;
  margin-right: 10px;
}
.switch-profile-btn {
  border-style: solid;
  border-radius: 50px;
  padding: 8px 15px;
  font-size: 16px;
  background-color: rgb(104, 102, 102);
  color: #ffffff;
  cursor: pointer;
  margin-top: 10px;
  margin-right: 10px;
}

body {
  background-image: url("../../img/browsebg.png");
  background-size: cover;
  background-repeat: no-repeat;
  background-attachment: fixed;
  font-family: Arial, sans-serif;
  width: 100%;
  height: 100vh;
}

nav {
  width: 100%;
  height: 60px;
  position: fixed;
  top: 0;
  z-index: 9;
  background: #000;
  display: flex;
  align-items: center;
}

.logo {
  height: auto;
  width: 150px;
  padding-top: 10px;
}

.join-box {
  width: fit-content;
  display: flex;
  justify-content: center;
  align-items: center;
  height: auto;
  margin-left: auto;
}

.join-msg {
  color: #fff;
  font-family: Arial;
}

.movie-poster-list {
  display: flex;
  justify-content: center;
  align-items: center;
  flex-wrap: wrap;
  list-style: none;
}

.movie-containers {
  margin: 20px;
  padding: 20px;
}

.movie-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 65vh;
  width: 15vw;
  margin: 20px;
  padding: 20px;
}

.movie-details-container {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: rgba(0, 0, 0, 0.5);
  backdrop-filter: blur(5px);
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 1000;
}

.movie-details {
  background-color: rgba(68, 68, 68, 0.445);
  color: white;
  width: 45%;
  padding: 20px;
  border-radius: 5px;
  box-shadow: 0px 0px 10px 0px rgba(0, 0, 0, 0.5);
}

.movie-poster {
  height: 45vh;
  width: 15vw;
  cursor: pointer;
}

.movie-details-poster {
  float: left;
  margin-right: 20px;
  height: 45vh;
  width: 15vw;
}

.movie-poster:hover {
  transform: scale(1.15);
}

.movie-title {
   text-decoration: underline;
  font-size: 50px;
  margin-bottom: 10px;
}

.movie-description {
  margin-bottom: 10px;
  margin-top: 25px;
}

.movie-release-duration {

  color: #ccc;
}

.recommendation-text {
  display: flex;
  justify-content: flex-start;
  align-items: flex-end;
  height: auto;
  width: 100%;
  font-size: 60px;
  color: rgb(255, 255, 255);
  font-family: "Franklin Gothic Medium", "Arial Narrow", Arial, sans-serif;
  padding-top: 120px;
  padding-left: 50px;
  padding-bottom: 20px;
}

.genre-movies {
  /* allows user to scroll if movies do not fit screen */
  flex-wrap: nowrap;
  overflow-x: auto;
}

#rating {
  text-align: center;
  perspective: 250px;
  width: 100%;
  margin-top: 10px;
}

#rating span {
  cursor: pointer;
  font-size: 50px;
  padding: 0 10px;
  color: #fff;
  opacity: 0.5;
  transition: all 150ms;
  display: inline-block;
  transform: rotateX(45deg);
  transform-origin: center bottom;
}

#rating span.hover {
  color: #ff0;
  opacity: 1;
  transform: rotateX(0deg);
  text-shadow: 0 0 30px #ffc;
}
.heart-button {
  background: none;
  border: none;
  cursor: pointer;
  margin-top: 10px;
  display: flex;
  align-items: center;
  font-size: 50px;
  
}

.red-heart {
  color: red;
  font-size: 50px;
}
</style>
