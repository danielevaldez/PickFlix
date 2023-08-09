<template>
<<<<<<< HEAD
  <div>
    <header>
      <nav>
        <img src="img\123123.png" class="logo">
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
=======
<div>
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
</div>

<div class="body-content">  
  <body>

  </body>
  </div>
>>>>>>> f6e8ef22b1c81b13bd9a6d1f2ee5c9045ecc2ddd
  </div>
</template>

<script>
export default {
  name: 'Browse',
  data() {
    return {
      movies: [
        // array of movies with title, poster, etc.
      ],
    };
  }
  
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
 nav{
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

.logo{
    height: auto;
    width: 150px;
    padding-top: 10px;
}

.join-box{
    width: fit-content;
    display: flex;
    justify-content: center;
    align-items: center;
    height: auto;
    margin-left: auto;
}

.join-msg{
    color: #fff;
    
    font-family:Arial;
}

.btn{
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

.join-btn{
    background: #dd0e15;
    border-color: #dd0e15;
    font-family: Arial;
}

/* Additional styling rules here */
</style>