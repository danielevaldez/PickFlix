<template>
  <div>
    <header>
      <nav>
        <img src="..\img\123123.png" class="logo" />
      </nav>
    </header>
    <div class="select-profile-header">
      <h1 class="header-text">Select a Profile</h1>
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
          <br>
          <div class="profile-genre">{{ profile.favoriteGenre }}</div>
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
        <label for="name" class="addProfileLabels">Name</label>
        <input v-model="profile.profileName" name="name" class="addProfileInputs"/>
        <label for="Genre" id="Dropdown" class="addProfileLabels">Favorite Genre</label>
        <br>
        <select name="Genre" v-model="profile.favoriteGenre" class="addProfileInputs">
          <option value="blank">   </option>
          <option value="Comedy">Comedy</option>
          <option value="Sci-Fi">Sci-Fi</option>
          <option value="Action">Action</option>
          <option value="Fantasy">Fantasy</option>
          <option value="Horror">Horror</option>
        </select>
        <br>
        <button type="submit">Add</button>
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
        userId: this.$store.state.userId,
        favoriteGenre: ""
      },
      profileCreationErrors: false,
      profileCreationErrorMsg: "",
      showAddProfileForm: false,
    };
  },
  methods: {
    create() {
      if (
        this.profile.profileName.trim() !== "" &&
        this.profile.favoriteGenre !== "blank"
      ) {
        const newProfile = {
          name: this.profile.profileName,
          favoriteGenre: this.profile.favoriteGenre
        };
        this.profiles.push(newProfile);
      }
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
      this.cancelAddProfile();
    },
    selectProfile() {},
    cancelAddProfile() {
      this.showAddProfileForm = false;
      this.profile.profileName = "";
      this.profile.favoriteGenre = "";
    },
    removeProfile(index) {
      this.profiles.splice(index, 1);
    },
  },
};
</script>

<style>
@import url('https://fonts.googleapis.com/css?family=Roboto:700,900');
h1 {
  margin-top: 85px;
  font-size: 60px;
  color:#ff3131;
  font-family: 'Roboto';
  letter-spacing:0px;
   text-shadow: 0px 4px 3px rgba(0, 0, 0, 0.8),
               0px 8px 13px rgba(0, 0, 0, 0.4),
               0px 18px 23px rgba(0, 0, 0, 0.4); 

}
button {
  margin-top: 10px;
  margin-right: 5px;
  padding: 5px;
  font-weight: bold;
  
}
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
.addProfileInputs {
  width: 200px;
  font-size: 20px;
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
.addProfileLabels{
  font-size: 25px;
  text-shadow: -1px 0 black, 0 1px black, 1px 0 black, 0 -1px black;
  color: white;
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
  border-radius: 10px;
  box-shadow: rgba(255, 255, 255, 0.4) 0px 2px 4px, rgba(255, 255, 255, 0.3) 0px 7px 13px -3px, rgba(255, 255, 255, 0.2) 0px -3px 0px inset; 
  /* alt shadow?
  box-shadow: rgba(255, 255, 255, 0.4) 5px 5px, rgba(255, 255, 255, 0.3) 10px 10px, rgba(255, 255, 255, 0.2) 15px 15px, rgba(255, 255, 255, 0.1) 20px 20px, rgba(255, 255, 255, 0.05) 25px 25px;*/
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
