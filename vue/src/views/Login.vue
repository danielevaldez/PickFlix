<template>
  <div class="container">
    <header>
      <h1 class="logo">PICKFLIX</h1>
    </header>
    <div class="center-container">
    <div v-if="stillLoading"><img src="../../img/loading.gif" /></div>
    <div v-else class = "login-container">
        <form @submit.prevent="login">
          <h1>Login</h1>
          <div role="alert" class="alert" v-if="invalidCredentials">Invalid username and password!</div>
          <div role="alert" class="alert" v-if="this.$route.query.registration">Thank you for registering, please sign in.</div>
          <div class="form-input-group">
            <input type="text" id="username" v-model="user.username" required placeholder="Username"/>
          </div>
          <div class="form-input-group">
            <input type="password" id="password" v-model="user.password" required placeholder="Password"/>
          </div>
          <button type="submit" class="form-input-group">Sign In</button>
          <p>
            <router-link :to="{ name: 'register' }"
              >Need an account? Sign up.</router-link
            >
          </p>
        </form>
      </div>
    </div>
    </div>
</template>

<script>
import authService from "../services/AuthService";

export default {
  name: "login",
  components: {},
  data() {
    return {
      user: {
        username: "",
        password: "",
      },
      invalidCredentials: false,
      stillLoading: true,
    };
  },
  created() {
    setTimeout(() => {
      this.stillLoading = false;
    }, 1000);
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then((response) => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$store.commit("SET_USER_ID", response.data.user.id);
            this.$router.push("/profileselect");
          }
        })
        .catch((error) => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    },
  },
};
</script>

<style scoped>
.logo {
  color: #e90418;
}
header {
  position: absolute;
  top: 0;
  left: 0;
  padding: 10px 30px; 
}
.alert {
  font-size: 20px;
  color: rgb(241, 237, 237);
}
p { 
  font-size: 20px;
}
button {
    margin-top: 3px;
    height: 40px;
    width: 259px;
    background-color: #e90418;
    color: rgb(241, 237, 237);
    font-size: 25px;
    font-weight: bold;
}
input {
    margin-top: 3px;
    height: 30px;
    width: 250px;
    font-size: 20px;
}
h1 {
    font-size: 40px;
    color: rgb(241, 237, 237);
}
.container {
  font-family: "Franklin Gothic Medium";
  background-image: url("../../img/login-register-back.jpg");
  background-size: cover;
  background-position: center;
  background-repeat: no-repeat;
  display: flex;
  flex-direction: column; 
  align-items: center;
  justify-content: flex-start; 
  height: 100vh;
  width: 100%;
}
.center-container {
  display: flex;
  align-items: center; 
  justify-content: center; 
  flex-grow: 1; 
}
.login-container {
  background-color: rgba(0, 0, 0, 0.774);
  border-radius: 10px;
  padding: 30px;
}

</style>
