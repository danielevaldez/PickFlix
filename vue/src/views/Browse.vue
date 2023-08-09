<template>
  <div>
    <header>
      <nav>
        <img src="../img/123123.png" class="logo">
        <div class="join-box">
          <p class="join-msg">Movies based on YOUR preference</p>
          <button class="btn join-btn">JOIN NOW</button>
          <button class="btn">SIGN IN</button>
        </div>
      </nav>
    </header>
    
    <main>
      <!-- Movie grid section -->
      <section class="movie-grid">
        <!-- Loop through movies -->
        <article v-for="movie in movies" :key="movie.profile_id" class="movie-item">
          <!-- Display profile name -->
          <h3 class="movie-title">{{ movie.profile_name }}</h3>
          <!-- Display genres for each movie -->
          <p v-for="genre in movie.genres" :key="genre.genre_id">{{ genre.genre_name }}</p>
        </article>
      </section>
    </main>
  </div>
</template>

<script>
export default {
  name: 'Browse',
  data() {
    return {
      movies: [
        {
          profile_id: 1,
          profile_name: 'Profile 1',
          genres: [
            { genre_id: 1, genre_name: 'Action' },
            { genre_id: 2, genre_name: 'Comedy' }
          ]
        },
        {
          profile_id: 2,
          profile_name: 'Profile 2',
          genres: [
            { genre_id: 3, genre_name: 'Drama' },
            { genre_id: 4, genre_name: 'Adventure' }
          ]
        },
        
      ],
    };
  }
};
</script>

<style>


.movie-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(200px, 1fr));
  gap: 20px;
  padding: 20px;
}

.movie-item {
  background-color: rgba(255, 255, 255, 0.1);
  padding: 10px;
  border-radius: 5px;
  text-align: center;
  color: #fff;
}

.movie-title {
  margin-bottom: 10px;
  font-size: 1.2rem;
}

/* Additional styling for genres */
.movie-item p {
  font-size: 1rem;
}
</style>
