<template>
<body>
  <div>
    <header>
      <nav>
        <router-link to="/browse">
          <img src="../../img/123123.png" class="logo" alt="Logo">
        </router-link>
        <div class="join-box">
          <button class="btn logout-btn" @click="logout">LOG OUT</button>
        </div>
      </nav>
    </header>
    <main>
      <!-- Top 5 recommended movies section -->
      <section class="recommended-movies">
        <h2 class="recommendation-text">Top 5 picks for {{ selectedProfile }}</h2>
        <!-- Container for recommended movie posters -->
        <div class="movie-containers">
          <!-- Loop through recommendedMovies and display movie images -->
          <div
            v-for="recommendedMovie in recommendedMovies"
            :key="recommendedMovie.id"
            class="movie-container"
            @click="showMovieDetails(recommendedMovie)"
          >
            <img :src="recommendedMovie.poster" alt="Movie Poster" class="movie-poster">
          </div>
        </div>
      </section>
    </main>
  </div>
</body>
</template>

<script>
export default { 
  name: 'Browse',
  data() {
    return {
      // profile information
      selectedProfile: 'Profile 1',
      // array storing recommended movies
      recommendedMovies: [],
      // array containing movie data
      movies: [
      
      ],
    };
  },
  methods: {
    // gets recommended movies for selected profile
    async fetchRecommendedMoviesForProfile() {
      try {
        const profileId = 1; // Replace with the actual profile ID
        // gets recommended movies from the server
        const response = await fetch(`/movies/browse/${profileId}`);
        const data = await response.json();
        // stores movies in the recommendedMovies array
        this.recommendedMovies = data;
      } catch (error) {
        console.error('Error fetching recommended movies:', error);
      }
    },
    // function to clear authentication state and redirects to login
    logout() {
      this.$store.commit('LOGOUT');
      this.$router.push('/login');
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

  body {
    background-image:url("C:\Users\Student\workspace\java-red-finalcapstone-team0\vue\src\views\browsebg.png");
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
    font-family: 'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif;
    margin-left: 50px;
  }

  .genre-movies {
   /* allows user to scroll if movies do not fit screen */
    flex-wrap: nowrap;
    overflow-x: auto;
  }

</style>
