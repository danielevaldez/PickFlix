<template>
  <div class="container">
    <div class="profile-info">
      <h2>{{ profile.profileName }}'s Profile</h2>
      <br />
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
    </div>
    <form>
      <button @click="close(1)">Select</button>
      <button @click="close(0)">Close</button>
      <button @click="close(2)" id="remove">Remove Profile</button>
    </form>
  </div>
</template>

<script>
export default {
  props: ["profile"],
  methods: {
    close(action) {
      this.$emit("close", action);
    },
  },
};
</script>

<style scoped>
.container {
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
  height: 100vh;
}

.profile-info {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-bottom: 20px;
}

img {
  width: 100%;
  height: 100%;
}
#remove {
  background-color: rgb(255, 87, 45);
}
button {
  padding: 5px;
  font-weight: bold;
  font-size: 20px;
  border: 2px solid black;
}
</style>
