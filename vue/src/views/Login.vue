<template>
  <div class="container">
    <div v-if="stillLoading">
      <img id="logo" src="../../img/logo.png" />
      <img src="../../img/loading.gif" class="loading"/>
    </div>
  <div v-else>
    <img id="logo" src="../../img/logo.png" />
    <br>
    <br>
  <div id="login">
    <form @submit.prevent="login">
      <h1>Login</h1>
      <div role="alert" v-if="invalidCredentials">
        Invalid username and password!
      </div>
      <div role="alert" v-if="this.$route.query.registration">
        Thank you for registering, please sign in.
      </div>
      <div class="form-input-group">
        <label for="username">Username</label>
        <br>
        <input type="text" id="username" v-model="user.username" required autofocus />
      </div>
      <div class="form-input-group">
        <label for="password">Password</label>
        <br>
        <input type="password" id="password" v-model="user.password" required />
      </div>
      <button type="submit">Sign in</button>
      <p>
      <router-link :to="{ name: 'register' }">Need an account? Sign up.</router-link></p>
    </form>
  </div>
  </div>
  </div>
</template>

<script>
import authService from "../services/AuthService";

export default {
  name: "login",
  components: {
  },
  data() {
    return {
      user: {
        username: "",
        password: ""
      },
      invalidCredentials: false,
      stillLoading: true
    };
  },
  created() {
    setTimeout(() => {
      this.stillLoading = false;
    }, 1000)
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then(response => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$router.push("/");
          }
        })
        .catch(error => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    },
  }
};
</script>

<style scoped>
#logo{
  margin-top: 100px;
  display: flex;
  align-items: top;
  justify-content: center;
}
.form-input-group {
  margin-bottom: 1rem;
}
label {
  margin-right: 0.5rem;
}
.container {
  display: flex;
  justify-content: center;
  align-items: top;
  min-height: 100vh;
  background-color: #b32222;
  background-image: url("../../img/spotlights.png");
  background-position: center top;
  background-repeat: no-repeat;
}
#login {
  justify-content: center;
  align-items: center;
  border-style: solid;
  border-width: 2px;
  padding: 30px;
  font-family: "Franklin Gothic Medium";
  background-color: #ffffff;
  border-radius: 75px;
  font-size: 15px;
}
h1 {
  font-size: 30px;
  display: flex;
  justify-content: center;
}
label {
  font-weight: bold;
  font-size: 20px;
}
button {
  font-size: 15px;
  background-color: #BFD7FF;
}
</style>