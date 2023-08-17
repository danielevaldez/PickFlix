<template>
  <div class="container">
    <header>
      <div class="tom" v-if="isTomProfile">
        <img class="spin-image" src="../../img/profileicons/tom.png" @click="tomSounds" :class="{ 'spin-animation': isSpinning }" />
      </div>
      <div class="txt" v-if="this.$store.state.runAnimation">PICKFLIX</div>
      <div class="txtOther" v-else >PICKFLIX</div>
    </header>
    <div v-if="!stillLoading">
      <h1 id="watching">Who's watching?</h1>
      <div role="alert" v-if="profileCreationErrors">
        {{ profileCreationErrorMsg }}
      </div>
      <ul class="profile-list" v-if="!showProfileOptions">
        <li
          class="profile-item"
          v-for="profile in this.$store.state.profiles"
          :key="profile.ID"
          @click="selectProfile(profile)"
        >
          <div class="editDeleteButtons" v-if="showEditDelete">
            <img src="../../img/editIcon.png" @click.stop="editProfile(profile)"/>
            <img
              src="../../img/deleteIcon.png"
              @click.stop="deleteProfile(profile)"
            />
          </div>
          <div
            class="profile-box"
            :class="{ 'shake-animation': showEditDelete }"
          >
            <div v-if="profile.profileIcon == 'Horror'">
              <img src="../../img/profileicons/horror.png" class="icons" />
            </div>
            <div v-if="profile.profileIcon == 'Comedy'">
              <img src="../../img/profileicons/comedy.png" class="icons" />
            </div>
            <div v-if="profile.profileIcon == 'Action'">
              <img src="../../img/profileicons/action.png" class="icons" />
            </div>
            <div v-if="profile.profileIcon == 'Fantasy'">
              <img src="../../img/profileicons/wizard.png" class="icons" />
            </div>
            <div v-if="profile.profileIcon == 'Sci-Fi'">
              <img src="../../img/profileicons/ufo.png" class="icons" />
            </div>
            <div class="profile-name">{{ profile.profileName }}</div>
          </div>
        </li>
        <li
          class="add-profile-box"
          v-if="!showProfileOptions && !showEditDelete"
          @click="showAddProfileForm = true"
        >
          <img id="plus-sign" src="../../img/plus.png" />
        </li>
      </ul>
      <div v-if="showAddProfileForm" class="modal-backdrop">
        <div class="add-profile-modal">
          <h1>New Profile</h1>
          <form @submit.prevent="addProfile()">
            <input
              v-model="newProfile.profileName"
              name="name"
              class="addProfileInputs"
              placeholder="Profile Name"
            />
            >
            <br />
            <select
              name="Genre"
              v-model="newProfile.profileIcon"
              class="addProfileInputs"
            >
              <option value="" disabled selected hidden>Favorite Genre</option>
              <option value="Comedy">Comedy</option>
              <option value="Sci-Fi">Sci-Fi</option>
              <option value="Action">Action</option>
              <option value="Fantasy">Fantasy</option>
              <option value="Horror">Horror</option>
            </select>
            <div class="button-container">
              <button type="submit">Save Profile</button>
              <button @click="cancelAddProfile">Cancel</button>
            </div>
          </form>
        </div>
        </div>


        <!-- UPDATE PROFILE -->
        <div v-if="showEditProfileForm" class="modal-backdrop">
        <div class="add-profile-modal">
          <h1>Edit Profile</h1>
          <form @submit.prevent="saveEdits">
            <input
              v-model="newProfile.profileName"
              name="name"
              class="addProfileInputs"
            />
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
            <div class="button-container">
              <button type="submit">Save Profile</button>
              <button @click="cancelAddProfile">Cancel</button>
            </div>
          </form>
        </div>
        </div>
        <!-- -->
      <div class="manage-profiles" v-if="!showEditDelete">
        <button @click="showEditDelete = true">Manage Profiles</button>
      </div>
    </div>
    <div class="manage-profiles" v-if="showEditDelete">
      <button @click="showEditDelete = false">Close</button>
    </div>
  </div>
</template>

<script>
import profileService from "../services/ProfileService";

export default {
  name: "profileselect",
  data() {
    return {
      newProfile: {
        profileId: "",
        profileName: "",
        profileIcon: "",
        userId: this.$store.state.userId,
      },
      profileCreationErrors: false,
      profileCreationErrorMsg: "",
      showAddProfileForm: false,
      showProfileOptions: false,
      stillLoading: this.$store.state.runAnimation,
      showEditDelete: false,
      tomTrue: false,
      showEditProfileForm: false,
      isSpinning: false
    };
  },
  created() {
    this.getProfiles();
    setTimeout(() => {
      this.stillLoading = false;
    }, 5500);
  },
  methods: {
    tomSounds(){
      this.$store.commit("PLAY_TOM");
      this.isSpinning = true;
      setTimeout(() => {
      this.isSpinning = false;
    }, 1000);
    },
    getProfiles() {
      profileService
        .getProfiles(this.$store.state.userId)
        .then((response) => {
          this.$store.commit("SET_PROFILES", response.data);
        })
        .catch((error) => {
          console.error("Error fetching profiles:", error);
        });
    },
    editProfile(profile){
      this.showEditProfileForm = true;
      this.newProfile = {...profile};
    },
    saveEdits(){
      profileService
        .updateProfile(this.newProfile.userId, this.newProfile.profileId, this.newProfile)
        .then((response) => {
          if(response.status == 200){
            this.getProfiles();
            this.cancelAddProfile();
          }
        })
        .catch((error) => {
          console.error("Response:", error.response);
          const response = error.response;
          this.profileCreationErrors = true;
          if (response.status === 400) {
            this.profileCreationErrorMsg = "Bad Request";
            this.cancelAddProfile();
          }
        })
    },
    addProfile() {
      profileService
        .create(this.newProfile)
        .then((response) => {
          if (response.status == 201) {
            this.getProfiles();
            this.cancelAddProfile();
          }
        })
        .catch((error) => {
          console.error("Response:", error.response);
          const response = error.response;
          this.profileCreationErrors = true;
          if (response.status === 400) {
            this.profileCreationErrorMsg = "Bad Request";
            this.cancelAddProfile();
          }
        });
    },
    deleteProfile(selectedProfile) {
      if (confirm("Are you sure you want to delete this profile?")) {
        profileService
          .deleteProfile(selectedProfile.userId, selectedProfile.profileId)
          .then((response) => {
            if (response.status === 204) {
              this.getProfiles();
            }
          })
          .catch((error) => {
            console.error("Error deleting profile:", error);
          });
      }
    },
    selectProfile(clickedProfile) {
      if(!this.showEditDelete){
      this.$store.commit("ANIMATION_BOOLEAN", false);
      this.$store.commit("SET_PROFILE_ID", clickedProfile.profileId);
      this.$store.commit("SET_PROFILE_NAME", clickedProfile.profileName);
      this.$router.push("/browse");
      }
    },
    cancelAddProfile() {
      this.showEditProfileForm = false;
      this.showAddProfileForm = false;
      this.newProfile.profileName = "";
      this.newProfile.profileIcon = "";
    },
  },
  computed: {
    isTomProfile() {
      return this.$store.state.profiles.some(
        (profile) => profile.profileName === "Tom"
      );
    },
  },
};
</script>

<style scoped>
.spin-animation {
  animation: spin 1s linear infinite;
}

@keyframes spin {
  0% { transform: rotate(0deg); }
  100% { transform: rotate(360deg); }
}
#watching {
  font-size: 50px;
  margin-top: 100px;
}
.editDeleteButtons img {
  width: 30px;
  height: auto;
  margin-right: 80px;
  margin-left: 25px;
}
.manage-profiles {
  display: flex;
  justify-content: center;
  margin-top: 100px;
}
.manage-profiles button {
  border-style: solid;
  border-radius: 50px;
  padding: 15px;
  width: 200px;
  font-size: 20px;
  background-color: rgb(104, 102, 102);
  color: #ccc;
}
.manage-profiles:hover {
  transform: scale(1.15);
}
h1 {
  font-size: 30px;
  color: rgb(241, 237, 237);
}

header {
  display: flex;
  align-items: center;
}

#plus-sign {
  width: 100%;
  height: 100%;
}
.container {
  font-family: "Franklin Gothic Medium";
  background-image: url("../../img/profileselectbackground.jpg");
  background-size: cover;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: flex-start;
  height: 100vh;
  width: 100%;
}
.profile-name {
  font-weight: bold;
  font-size: 25px;
  margin-top: 10px;
  color: #ccc;
}

button {
  margin-top: 10px;
  margin-right: 5px;
  padding: 5px;
  font-weight: bold;
}

.addProfileInputs {
  width: 100%;
  font-size: 20px;
  margin: 0;
}
.header {
  text-align: center;
}
.profile-box {
  border-radius: 200px;
  width: 200px;
  height: 200px;
  border: 2px solid #ccc;
  padding: 10px;
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
  border-radius: 200px;
  width: 150px;
  height: 150px;
  border-color: black;
  border: 2px solid #ccc;
  cursor: pointer;
  transition: background-color 0.2s, transform 0.2s;
  text-align: center;
  position: relative;
  background-color: #ccc;
  margin-left: 20px;
}
.add-profile-box:hover {
  background-color: #303df3e7;
  transform: scale(1.4);
}

.profile-box:hover {
  background-color: #303df3e7;
  transform: scale(1.3);
}
.profile-box {
  background-color: #ccc;
}
.icons {
  width: 100%;
  height: 100%;
}

.modal-backdrop {
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

.add-profile-modal {
  background-color: rgb(0, 0, 0);
  padding: 30px;
  border-radius: 10px;
  box-shadow: 0px 0px 10px #e90417de;
  width: 200px;
  height: auto;
}

.add-profile-modal form {
  padding: 0;
}

.button-container {
  display: flex;
  justify-content: center;
  align-items: center;
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

.txtOther{
   color: #e90418;
  text-align: center;
  font-size: 12vmin;
  font-weight: 700;
  outline: none;
  white-space: nowrap;
  text-shadow: 0px 6px 4px rgba(0, 0, 0, 0.8), 0px 10px 15px rgba(0, 0, 0, 0.4),
    0px 20px 30px rgba(0, 0, 0, 0.4);
  margin-top: 20px;
}

.txt {
  color: #e90418;
  text-align: center;
  font-size: 12vmin;
  font-weight: 700;
  outline: none;
  white-space: nowrap;
  text-shadow: 0px 6px 4px rgba(0, 0, 0, 0.8), 0px 10px 15px rgba(0, 0, 0, 0.4),
    0px 20px 30px rgba(0, 0, 0, 0.4);
  margin-top: 20px;
}

.tom {
  margin: 0 10px;
}

/* STYLING FOR SHAKE ANIMATION */
@keyframes shake {
  0%,
  100% {
    transform: translateX(0);
  }
  10%,
  30%,
  50%,
  70%,
  90% {
    transform: translateX(-5px);
  }
  20%,
  40%,
  60%,
  80% {
    transform: translateX(5px);
  }
}

.shake-animation {
  animation: shake 2s infinite;
}

/* STYLING FOR LOGO ANIMATION */
@import url("https://fonts.googleapis.com/css?family=Roboto:700,900");
body {
  background: radial-gradient(#fff, #aaa);
  background-size: 100vw 100vh;
  overflow: hidden;
  padding-top: 40vh;
  margin: 0px;
}
.txt {
  color: #e90418;
  text-align: center;
  font-size: 12vmin;
  font-weight: 700;
  animation: netflix_style 6s 1;
  outline: none;
  white-space: nowrap;
  text-shadow: 0px 4px 3px rgba(0, 0, 0, 0.8), 0px 8px 13px rgba(0, 0, 0, 0.4),
    0px 18px 23px rgba(0, 0, 0, 0.4);
}
@keyframes netflix_style {
  0% {
    text-shadow: 0px 0px transparent, 1px 1px #aaa, 2px 2px #aaa, 3px 3px #aaa,
      4px 4px #aaa, 5px 5px #aaa, 6px 6px #aaa, 7px 7px #aaa, 8px 8px #aaa,
      9px 9px #aaa, 10px 10px #aaa, 11px 11px #aaa, 12px 12px #aaa,
      13px 13px #aaa, 14px 14px #aaa, 15px 15px #aaa, 16px 16px #aaa,
      17px 17px #aaa, 18px 18px #aaa, 19px 19px #aaa, 20px 20px #aaa,
      21px 21px #aaa, 22px 22px #aaa, 23px 23px #aaa, 24px 24px #aaa,
      25px 25px #aaa, 26px 26px #aaa, 27px 27px #aaa, 28px 28px #aaa,
      29px 29px #aaa, 30px 30px #aaa, 31px 31px #aaa, 32px 32px #aaa,
      33px 33px #aaa, 34px 34px #aaa, 35px 35px #aaa, 36px 36px #aaa,
      37px 37px #aaa, 38px 38px #aaa, 39px 39px #aaa, 40px 40px #aaa,
      41px 41px #aaa, 42px 42px #aaa, 43px 43px #aaa, 44px 44px #aaa,
      45px 45px #aaa, 46px 46px #aaa, 47px 47px #aaa, 48px 48px #aaa,
      49px 49px #aaa, 50px 50px #aaa, 51px 51px #aaa, 52px 52px #aaa,
      53px 53px #aaa, 54px 54px #aaa, 55px 55px #aaa, 56px 56px #aaa,
      57px 57px #aaa, 58px 58px #aaa, 59px 59px #aaa, 60px 60px #aaa,
      61px 61px #aaa, 62px 62px #aaa, 63px 63px #aaa, 64px 64px #aaa,
      65px 65px #aaa, 66px 66px #aaa, 67px 67px #aaa, 68px 68px #aaa,
      69px 69px #aaa, 70px 70px #aaa, 71px 71px #aaa, 72px 72px #aaa,
      73px 73px #aaa, 74px 74px #aaa, 75px 75px #aaa, 76px 76px #aaa,
      77px 77px #aaa, 78px 78px #aaa, 79px 79px #aaa, 80px 80px #aaa,
      81px 81px #aaa, 82px 82px #aaa, 83px 83px #aaa, 84px 84px #aaa,
      85px 85px #aaa, 86px 86px #aaa, 87px 87px #aaa, 88px 88px #aaa,
      89px 89px #aaa, 90px 90px #aaa, 91px 91px #aaa, 92px 92px #aaa,
      93px 93px #aaa, 94px 94px #aaa, 95px 95px #aaa, 96px 96px #aaa,
      97px 97px #aaa, 98px 98px #aaa, 99px 99px #aaa, 100px 100px #aaa;
    color: #f3f3f3;
    transform: scale(4, 4);
    margin-top: 300px;
  }
  10% {
    text-shadow: 0px 0px transparent, 1px 1.5px #aaa, 2px 3px #aaa,
      3px 4.5px #aaa, 4px 6px #aaa, 5px 7.5px #aaa, 6px 9px #aaa,
      7px 10.5px #aaa, 8px 12px #aaa, 9px 13.5px #aaa, 10px 15px #aaa,
      11px 16.5px #aaa, 12px 18px #aaa, 13px 19.5px #aaa, 14px 21px #aaa,
      15px 22.5px #aaa, 16px 24px #aaa, 17px 25.5px #aaa, 18px 27px #aaa,
      19px 28.5px #aaa, 20px 30px #aaa, 21px 31.5px #aaa, 22px 33px #aaa,
      23px 34.5px #aaa, 24px 36px #aaa, 25px 37.5px #aaa, 26px 39px #aaa,
      27px 40.5px #aaa, 28px 42px #aaa, 29px 43.5px #aaa, 30px 45px #aaa,
      31px 46.5px #aaa, 32px 48px #aaa, 33px 49.5px #aaa, 34px 51px #aaa,
      35px 52.5px #aaa, 36px 54px #aaa, 37px 55.5px #aaa, 38px 57px #aaa,
      39px 58.5px #aaa, 40px 60px #aaa, 41px 61.5px #aaa, 42px 63px #aaa,
      43px 64.5px #aaa, 44px 66px #aaa, 45px 67.5px #aaa, 46px 69px #aaa,
      47px 70.5px #aaa, 48px 72px #aaa, 49px 73.5px #aaa, 50px 75px #aaa,
      51px 76.5px #aaa, 52px 78px #aaa, 53px 79.5px #aaa, 54px 81px #aaa,
      55px 82.5px #aaa, 56px 84px #aaa, 57px 85.5px #aaa, 58px 87px #aaa,
      59px 88.5px #aaa, 60px 90px #aaa, 61px 91.5px #aaa, 62px 93px #aaa,
      63px 94.5px #aaa, 64px 96px #aaa, 65px 97.5px #aaa, 66px 99px #aaa,
      67px 100.5px #aaa, 68px 102px #aaa, 69px 103.5px #aaa, 70px 105px #aaa,
      71px 106.5px #aaa, 72px 108px #aaa, 73px 109.5px #aaa, 74px 111px #aaa,
      75px 112.5px #aaa, 76px 114px #aaa, 77px 115.5px #aaa, 78px 117px #aaa,
      79px 118.5px #aaa, 80px 120px #aaa, 81px 121.5px #aaa, 82px 123px #aaa,
      83px 124.5px #aaa, 84px 126px #aaa, 85px 127.5px #aaa, 86px 129px #aaa,
      87px 130.5px #aaa, 88px 132px #aaa, 89px 133.5px #aaa, 90px 135px #aaa,
      91px 136.5px #aaa, 92px 138px #aaa, 93px 139.5px #aaa, 94px 141px #aaa,
      95px 142.5px #aaa, 96px 144px #aaa, 97px 145.5px #aaa, 98px 147px #aaa,
      99px 148.5px #aaa, 100px 150px #aaa;
    color: #f3f3f3;
    transform: scale(3, 3);
  }
  15% {
    color: #f3f3f3;
  }
  20% {
    color: #e90418;
    text-shadow: none;
    transform: scale(2.5, 2.5);
  }
  75% {
    opacity: 1;
  }
  80% {
    opacity: 0;
    color: #e90418;
    transform: scale(1.5, 1.5);
  }
  100% {
    opacity: 1;
    color: #e90418;
    transform: scale(1, 1);
  }
}
</style>
