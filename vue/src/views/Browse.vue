<template>
  <div>
    <header>
      <nav>
        <router-link to="/browse">
          <!-- Add router-link around the image -->
          <img src="../../img/123123.png" class="logo" alt="Logo" />
          <!-- Use relative path for the image source -->
        </router-link>
        <div class="join-box">
          <button class="btn logout-btn" @click="logout">LOG OUT</button>
        </div>
      </nav>
    </header>
    <main>
      <!-- Top 5 recommended movies -->
      <section class="recommended-movies">
        <h2 class="recommendation-text">
          Top 5 picks for {{ selectedProfile }}
        </h2>
        <div class="movie-containers">
          <div
            v-for="movie in recommendedMovies"
            :key="movie.id"
            class="movie-container"
            @click="showMovieDetails(movie)"
          >
            <img :src="movie.poster" alt="Movie Poster" class="movie-poster" />
          </div>
        </div>
      </section>
    </main>
  </div>
</template>

<script>
export default {
  name: "Browse",
  data() {
    return {
      selectedProfile: this.$store.state.profileName,
      recommendedMovies: [],
      movies: [
        // ... Movie data ...
      ],
    };
  },
  methods: {
    async fetchRecommendedMoviesForProfile() {},
    logout() {
      this.$store.commit("LOGOUT"); // Clears authentication state
      this.$router.push("/login"); // Redirects to login page
    },
  },
  mounted() {
    this.fetchRecommendedMoviesForProfile();
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

.btn {
  border: 1px solid #fff;
  border-radius: 2px;
  background: none;
  color: #fff;
  height: 35px;
  padding: 0 20px;
  margin-left: 10px;
  margin-bottom: 10px;
  text-transform: uppercase;
  cursor: pointer;
}

.logout-btn {
  background: #dd0e15;
  border-color: #dd0e15;
  font-family: Arial;
}

.recommended-movies {
  padding: 20px;
}

.recommended-movies ul {
  list-style: none;
  padding-left: 0;
}

.recommended-movies li {
  margin-bottom: 10px;
  font-size: 1.2rem;
}

.recommendation-text {
  margin-top: 250px;
  font-size: 30px;
  color: rgb(255, 255, 255);
  font-family: "Franklin Gothic Medium", "Arial Narrow", Arial, sans-serif;
  margin-left: 50px;
}

.genre-movies {
  /* allows user to scroll if movies do not fit screen */
  flex-wrap: nowrap;
  overflow-x: auto;
}
</style>
