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
   <div id="register" class="text-center">
    <form @submit.prevent="register">
      <h1>Create Account</h1>
      <div role="alert" v-if="registrationErrors">
        {{ registrationErrorMsg }}
      </div>
      <div class="form-input-group">
        <label for="username">Username</label>
        <input type="text" id="username" v-model="user.username" required autofocus />
      </div>
      <div class="form-input-group">
        <label for="password">Password</label>
        <input type="password" id="password" v-model="user.password" required />
      </div>
      <div class="form-input-group">
        <label for="confirmPassword">Confirm Password</label>
        <input type="password" id="confirmPassword" v-model="user.confirmPassword" required />
      </div>
      <button type="submit">Create Account</button>
      <p><router-link :to="{ name: 'login' }">Already have an account? Log in.</router-link></p>
    </form>
  </div>
  </div>
  </div>
</template>

<script>
import authService from '../services/AuthService';

export default {
  name: 'register',
  data() {
    return {
      user: {
        username: '',
        password: '',
        confirmPassword: '',
        role: 'user',
      },
      stillLoading: true,
      registrationErrors: false,
      registrationErrorMsg: 'There were problems registering this user.',
    };
  },
   created() {
    setTimeout(() => {
      this.stillLoading = false;
    }, 1000)
  },
  methods: {
    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = 'Password & Confirm Password do not match.';
      } else {
        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              this.$router.push({
                path: '/login',
                query: { registration: 'success' },
              });
            }
          })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = 'Bad Request: Validation Errors';
            }
          });
      }
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = 'There were problems registering this user.';
    },
  },
};
</script>

<style scoped>
.form-input-group {
  margin-bottom: 1rem;
}
label {
  margin-right: 0.5rem;
  font-weight: bold;
  font-size: 20px;
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
#register {
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
#logo{
  margin-top: 100px;
  display: flex;
  align-items: top;
  justify-content: center;
}
button {
  font-size: 15px;
  background-color: #BFD7FF;
}
</style>
