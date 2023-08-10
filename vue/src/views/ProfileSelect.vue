<template>
  <div>
    <header>
      <nav>
        <img src="..\img\123123.png" class="logo" />
      </nav>
    </header>
    <div class="select-profile-header" v-if="!showProfileOptions">
      <h1 class="header-text">Select a Profile</h1>
      <div role="alert" v-if="profileCreationErrors">
        {{ profileCreationErrorMsg }}
      </div>
    </div>
    <div v-if="showProfileOptions" class="profileOptions">
      <profile-options
        :profile="selectedProfile"
        @close="closeProfile"
      ></profile-options>
    </div>
    <ul class="profile-list" v-if="!showProfileOptions">
      <li
        class="profile-item"
        v-for="profile in profiles"
        :key="profile.ID"
        @click="selectProfile(profile)"
      >
        <div class="profile-box">
          <div v-if="profile.profileIcon == 'Horror'">
            <img src="../../img/profileicons/horror.png" class="icons" />
          </div>
          <div v-if="profile.profileIcon == 'Comedy'">
            <img src="../../img/profileicons/comedy.png" class="icons" />
          </div>
          <div v-if="profile.profileIcon == 'Action'">
            <img src="../../img/profileicons/action.png" class="icons" />
          </div>
          <div v-if="profile.profileIcone == 'Fantasy'">
            <img src="../../img/profileicons/wizard.png" class="icons" />
          </div>
          <div v-if="profile.profileIcon == 'Sci-Fi'">
            <img src="../../img/profileicons/ufo.png" class="icons" />
          </div>
          <div class="profile-name">{{ profile.name }}</div>
        </div>
      </li>
      <li class="add-profile-item" v-if="!showProfileOptions">
        <div class="add-profile-box" @click="showAddProfileForm = true">
          Add Profile
        </div>
      </li>
    </ul>
    <div v-if="showAddProfileForm" class="add-profile-form">
      <form @submit.prevent="addProfile">
        <label for="name" class="addProfileLabels">Name</label>
        <input v-model="newProfile.profileName" name="name" class="addProfileInputs" />
        <label for="Genre" id="Dropdown" class="addProfileLabels"
          >Favorite Genre</label
        >
        <br />
        <select
          name="Genre"
          v-model="newProfile.profileIcon"
          class="addProfileInputs"
        >
          <option value="Comedy">Comedy</option>
          <option value="Sci-Fi">Sci-Fi</option>
          <option value="Action">Action</option>
          <option value="Fantasy">Fantasy</option>
          <option value="Horror">Horror</option>
        </select>
        <br />
        <button type="submit">Add</button>
        <button @click="cancelAddProfile">Cancel</button>
      </form>
    </div>
  </div>
</template>

<script>
import ProfileOptions from '../components/ProfileOptions.vue';
import ProfileService from '../services/ProfileService';
import profileService from "../services/ProfileService";

export default {
  components: { ProfileOptions },
  name: "profileselect",
  data() {
    return {
      profiles: [],
      newProfile: {
        profileName: "",
        profileIcon: "",
        userId: this.$store.state.userId,
      },
      profileCreationErrors: false,
      profileCreationErrorMsg: "",
      showAddProfileForm: false,
      showProfileOptions: false,
      selectedProfile: "",
    };
  },
  created() {
    ProfileService.getProfiles(this.newProfile.userId)
    .then(response => {
      this.profiles = response.data;
      console.log(this.profiles);
    })
    .catch(error => {
      console.error('Error fetching profiles:', error);
    });
  },
  methods: {
    addProfile() {
      console.log("USER ID BEFORE SUBMISSION" + this.$store.state.userId);
      console.log("newProfile before submission:", this.newProfile);
      profileService
        .create(this.newProfile)
        .then((response) => {
          if (response.status == 201) {
            //Not sure if we do something here? Something in $store.state?
            // maybe something with UI
            this.cancelAddProfile();
          }
        })
        .catch((error) => {
          const response = error.response;
          this.profileCreationErrors = true;
          if (response.status === 400) {
            this.profileCreationErrorMsg = "Bad Request";
            this.cancelAddProfile();
          }
        });

      //(api call to add profile using newProfile)
      //this.profiles = (api call for get profiles using user id)
    },
    closeProfile(action) {
      if (action == 1) {
        //open the browse page
      } else if (action == 2) {
        //(api call to delete profile using profile name and user id)
        //this.profiles = (api call for get profiles using user id)
      }
      this.selectedProfile = "";
      this.showProfileOptions = false;
    },
    selectProfile(clickedProfile) {
      this.selectedProfile = { ...clickedProfile };
      this.showProfileOptions = true;
    },
    cancelAddProfile() {
      this.showAddProfileForm = false;
      this.newProfile.profileName = "";
      this.newProfile.profileIcon = "";
    },
  },
};
</script>

<style>
.profile-name {
  font-weight: bold;
  font-size: 25px;
  margin-top: 10px;
  color: #ccc;
}
h1 {
  margin-top: 85px;
  font-size: 50px;
  text-shadow: -1px 0 black, 0 1px black, 1px 0 black, 0 -1px black;
  color: white;
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
.profileOptions {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5);
  display: flex;
  justify-content: center;
  align-items: center;
}
.profile-box {
  border-radius: 100px;
  width: 200px;
  height: 200px;
  border: 2px solid rgb(74, 101, 250);
  padding: 5px;
  cursor: pointer;
  transition: background-color 0.2s, transform 0.2s;
  text-align: center;
  position: relative;
}
.addProfileLabels {
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
  background-color: #7846ff;
  transform: scale(1.05);
}
.profile-box {
  background-color: #ccc;
}
.icons {
  width: 100%;
  height: 100%;
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
