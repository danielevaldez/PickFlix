<template>
  <div>
    <header>
      <nav>
        <img src="..\img\123123.png" class="logo">
        <div class="join-box">
          <p class="join-msg">Movies based on YOUR preference</p>
          <button class="btn join-btn">JOIN NOW</button>
          <button class="btn">SIGN IN</button>
        </div>
      </nav>
    </header>
    <main>
      <!-- Top 5 recommended movies -->
      <section class="recommended-movies">
        <ul>
          <li v-for="movie in recommendedMovies" :key="movie.id">{{ movie.title }}</li>
        </ul>
        <p class="recommendation-text">Top 5 picks for {{ selectedProfile }} </p>
      </section>
    </main>
  </div>
</template>

<script>
export default {
  name: 'Browse', // component name
  data() {
    return {
      selectedProfile: 'Profile 1', // The selected profile's name
      recommendedMovies: [], // Array to store recommended movies
      movies: [
        // ... Movie data ...
      ],
    };
  },
  methods: {
    async fetchRecommendedMoviesForProfile() {
      try {
        // fetches recommended movies for selected profile from API
        const response = await fetch(`/browse/${this.selectedProfile}`);
        const data = await response.json();
        this.recommendedMovies = data.movies; // updates recommended movies array to fit profile preferences
      } catch (error) {
        console.error('Error fetching recommended movies:', error);
      }
    },
  },
  mounted() {
    // fetches and shows recommended movies for selected profile
    this.fetchRecommendedMoviesForProfile();
  },
};
</script>

<style>
  * {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
  }

  body {
    background-image:url("C:\Users\Student\workspace\java-red-finalcapstone-team0\vue\img\browsebg.png");
    background-size: cover;
    background-repeat: no-repeat;
    background-attachment: fixed;
    font-family: Arial, sans-serif;
    color: white;
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
    padding: 0 10px;
    margin-left: 10px;
    text-transform: uppercase;
    cursor: pointer;
  }
  
  .join-btn {
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
