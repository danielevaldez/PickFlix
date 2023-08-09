<template>
  <div>
    <div class="header">
      <h1>Select a Profile</h1>
    </div>
    <ul class="profile-list">
      <!-- Loop through the profiles and create a profile item for each -->
      <li
        class="profile-item"
        v-for="(profile, index) in profiles"
        :key="index"
        @click="selectProfile(profile)" 
      >
        <div class="profile-box">
          <div class="profile-name">{{ profile.name }}</div>
          <div class="profile-info">{{ profile.info }}</div>
          <button class="remove-profile-button" @click="removeProfile(index)">Remove</button>
        </div>
      </li>
      <!-- Add Profile button -->
      <li class="add-profile-item">
        <div class="add-profile-box" @click="showAddProfileForm = true">Add Profile</div>
      </li>
    </ul>

    <!-- Add Profile Form -->
    <div v-if="showAddProfileForm" class="add-profile-form">
      <input v-model="newProfileName" placeholder="Name" />
      <textarea v-model="newProfileInfo" placeholder="Information"></textarea>
      <button @click="addProfile">Add</button>
      <button @click="cancelAddProfile">Cancel</button>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      profiles: [],
      showAddProfileForm: false,
      newProfileName: '',
      newProfileInfo: ''
    };
  },
  methods: {
    selectProfile() {},
    addProfile() {
      if (this.newProfileName.trim() !== '' && this.newProfileInfo.trim() !== '') {
        const newProfile = {
          name: this.newProfileName,
          info: this.newProfileInfo
        };
        this.profiles.push(newProfile);
        this.cancelAddProfile();
      }
    },
    cancelAddProfile() {
      this.showAddProfileForm = false;
      this.newProfileName = '';
      this.newProfileInfo = '';
    },
    removeProfile(index) {
      this.profiles.splice(index, 1);
    }
  }
};
</script>

<style >
body  {
  background-image: url("C:\Users\Student\workspace\java-red-finalcapstone-team0\vue\img\browsebg.png");
  background-size: cover;
  background-repeat: no-repeat;
  background-attachment: fixed;
  color:white;
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
  content: '';
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-image: url("../../img/addition.png");
  background-size: cover;
  background-position: center;
  opacity: .50;
}

.profile-box:hover {
  background-color: #FF3131;
  transform: scale(1.05);
}

.add-profile-box:hover {
  background-color: #FF3131;
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
  background-color: #FF3131;
  border: none;
  color: white;
  padding: 5px 10px;
  cursor: pointer;
}

.remove-profile-button:hover {
  background-color: #FF0000;
}
</style>
