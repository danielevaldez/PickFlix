<template>
  <div>
    <header>
      <nav>
        <img src="..\img\123123.png" class="logo" />
        <div class="join-box">
          <p class="join-msg">Movies based on YOUR preference</p>
          <button class="btn join-btn">JOIN NOW</button>
          <button class="btn">SIGN IN</button>
        </div>
      </nav>
    </header>
    <div class="select-profile-header">
      <p class="header-text">Select a Profile</p>
      <div role="alert" v-if="profileCreationErrors">
        {{ profileCreationErrorMsg }}
      </div>
    </div>
    <ul class="profile-list">
      <li
        class="profile-item"
        v-for="(profile, index) in profiles"
        :key="index"
        @click="selectProfile(profile)"
      >
        <div class="profile-box">
          <div class="profile-name">{{ profile.name }}</div>
          <div class="profile-info">{{ profile.info }}</div>
          <button class="remove-profile-button" @click="removeProfile(index)">
            Remove
          </button>
        </div>
      </li>
      <li class="add-profile-item">
        <div class="add-profile-box" @click="showAddProfileForm = true">
          Add Profile
        </div>
      </li>
    </ul>
    <div v-if="showAddProfileForm" class="add-profile-form">
      <!-- Can't get profile to show on page and add to DB -->
      <form @submit.prevent="create">
        <input v-model="profile.profileName" placeholder="Name" />
        <textarea v-model="newProfileInfo" placeholder="Information"></textarea>
        <button type="submit" @click="combinedMethod">Add</button>
        <button @click="cancelAddProfile">Cancel</button>
      </form>
    </div>
  </div>
</template>

<script>
import profileService from "../services/ProfileService";

export default {
  name: "profileselect",
  data() {
    return {
      // profiles = getProfiles (controller) maybe?  So it persists through logging out etc.
      profiles: [],
      profile: {
        profileName: "",
        //Get userId of currently logged in user instead of hard coding
        userId: 3,
      },
      newProfileInfo: "",
      profileCreationErrors: false,
      profileCreationErrorMsg: "",
      showAddProfileForm: false,
    };
  },
  methods: {
    combinedMethod() {
      this.addProfile();
      this.create();
    },
    create() {
      profileService
        .create(this.profile)
        .then((response) => {
          if (response.status == 201) {
            //Not sure if we do something here? Something in $store.state?
          }
        })
        .catch((error) => {
          const response = error.response;
          this.profileCreationErrors = true;
          if (response.status === 400) {
            this.profileCreationErrorMsg = "Bad Request";
          }
        });
    },
    selectProfile() {},
    addProfile() {
      if (
        this.profile.profileName.trim() !== "" &&
        this.newProfileInfo.trim() !== ""
      ) {
        const newProfile = {
          name: this.profile.profileName,
          info: this.newProfileInfo,
        };
        this.profiles.push(newProfile);
        this.cancelAddProfile();
      }
    },
    cancelAddProfile() {
      this.showAddProfileForm = false;
      this.profile.profileName = "";
      this.newProfileInfo = "";
    },
    removeProfile(index) {
      this.profiles.splice(index, 1);
    },
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
  background-image: url("C:\Users\Student\workspace\java-red-finalcapstone-team0\vue\img\browsebg.png");
  background-size: cover;
  background-repeat: no-repeat;
  background-attachment: fixed;
  color: white;
  font-family: Arial, sans-serif;
}

.header {
  text-align: center;
}

.profile-box {
  width: 120px;
  height: 120px;
  border: 2px solid #ccc;
  padding: 20px;
  cursor: pointer;
  transition: background-color 0.2s, transform 0.2s;
  text-align: center;
  position: relative;
}

.add-profile-box {
  width: 120px;
  height: 120px;
  border: 2px solid #ccc;
  padding: 20px;
  cursor: pointer;
  transition: background-color 0.2s;
  text-align: center;
  margin: 100px;
  position: relative;
}

.add-profile-box:hover::after {
  content: "";
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-image: url("../../img/addition.png");
  background-size: cover;
  background-position: center;
  opacity: 0.5;
}

.profile-box:hover {
  background-color: #ff3131;
  transform: scale(1.05);
}

.add-profile-box:hover {
  background-color: #ff3131;
}

.add-profile-form {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  padding: 20px;
  border: 1px solid #ccc;
  margin-top: 20px;
  max-width: 300px;
  margin: 0 auto;
}

.add-profile-form input,
.add-profile-form textarea {
  display: block;
  width: 100%;
  margin-bottom: 10px;
}

.profile-list {
  display: flex;
  justify-content: center;
  align-items: center;
  flex-wrap: wrap;
  list-style: none;
  padding: 0;
  margin: 0;
}

.profile-item {
  margin: 20px;
}

.remove-profile-button {
  position: absolute;
  bottom: 10px;
  left: 50%;
  transform: translateX(-50%);
  background-color: #ff3131;
  border: none;
  color: white;
  padding: 5px 10px;
  cursor: pointer;
}

.remove-profile-button:hover {
  background-color: #ff0000;
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
.select-profile-header {
  text-align: center;
  padding: 20px;
  color: white;
}
</style>
