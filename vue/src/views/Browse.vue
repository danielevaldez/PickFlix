<template>
  <body>
<nav>
    <img src="..\img\123123.png" class="logo">
    <div class="join-box">
        <p class="join-msg">Movies based on YOUR preference</p>
        <button class="btn join-btn">JOIN NOW</button>
        <button class="btn">SIGN IN</button>
    </div>
</nav>
  </body>
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
