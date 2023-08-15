<template>
  <!-- Star rating container -->
  <div id="rating">
    <!-- Loop to create 5 stars -->
    <span
      v-for="index in 5"
      :key="index"
      :class="{ 'hover': index <= selectedStar }"
      @mouseenter="enterStarListener(index)"
    >
      ★ <!-- Unicode star character -->
    </span>
  </div>
</template>

<script>
export default {
  data() {
    return {
      // Tracks the index of the currently selected star
      selectedStar: 0,
    };
  },
  methods: {
    // Triggered when the mouse enters a star
    enterStarListener(index) {
      // Update selectedStar to match the hovered star's index
      this.selectedStar = index;
    },
     movieRatings: {}, // to store star ratings for each movie
  },
};
</script>

<style scoped>
#rating {
  text-align: center;
  perspective: 250px;
  position: absolute;
  top: 40%; /* vertically position the star rating */
  width: 100%;
}

#rating span {
  cursor: pointer;
  font-size: 50px;
  padding: 0 10px;
  color: #fff;
  opacity: 0.5;
  transition: all 150ms; /* transition effect for hover */
  display: inline-block;
  transform: rotateX(45deg);
  transform-origin: center bottom;
}


#rating span.hover {
  color: #ff0;
  opacity: 1; 
  transform: rotateX(0deg); /* reset rotation */
  text-shadow: 0 0 30px #ffc; /* shadow when hovered */
}
</style>
