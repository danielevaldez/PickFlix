<template>
  <div class="container">
    <header>
      <h1 class="logo">PICKFLIX</h1>
    </header>
    <div class="center-container">
      <div v-if="stillLoading"><img src="../../img/loading.gif" /></div>
      <div v-else class="register-container">
        <form @submit.prevent="register">
          <h1>Create Account</h1>
          <div role="alert" v-if="registrationErrors">
            {{ registrationErrorMsg }}
          </div>
          <div class="form-input-group">
            <input
              type="text"
              id="username"
              v-model="user.username"
              required
              placeholder="Username"
            />
          </div>
          <div class="form-input-group">
            <input
              type="password"
              id="password"
              v-model="user.password"
              required
              placeholder="Password"
            />
          </div>
          <div class="form-input-group">
            <input type="password" v-model="user.confirmPassword" required placeholder="Confirm Password"/>
          </div>
          <button type="submit">Create Account</button>
          <p>
            <router-link :to="{ name: 'login' }">Already have an account? Log in.</router-link>
          </p>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
import authService from "../services/AuthService";

export default {
  name: "register",
  data() {
    return {
      user: {
        username: "",
        password: "",
        confirmPassword: "",
        role: "user",
      },
      stillLoading: true,
      registrationErrors: false,
      registrationErrorMsg: "There were problems registering this user.",
    };
  },
  created() {
    setTimeout(() => {
      this.stillLoading = false;
    }, 1000);
  },
  methods: {
    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = "Password & Confirm Password do not match.";
      } else {
        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              this.$router.push({
                path: "/login",
                query: { registration: "success" },
              });
            }
          })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = "Bad Request: Validation Errors";
            }
          });
      }
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = "There were problems registering this user.";
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
p { 
  font-size: 20px;
}
button {
    margin-top: 3px;
    background-color: #e90418;
    color: rgb(241, 237, 237);
    font-size: 25px;
    font-weight: bold;
    height: 40px;
    width: 259px;
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
.register-container {
  background-color: rgba(0, 0, 0, 0.774);
  border-radius: 10px;
  padding: 30px;
}

</style>
