<template>
  <!-- Star rating container -->
  <div id="rating">
    <!-- Loop to create 5 stars -->
    <span
      v-for="index in 5"
      :key="index"
      :class="{ 'hover': index <= selectedStar }"
      @mouseenter="enterStarListener(index)"
      @click="selectStar(index)"
    >
      ★ <!-- Unicode star character -->
    </span>
  </div>
</template>

<script>
export default {
  props: {
    movieId: Number,
    initialRating: Number,
  },
  data() {
    return {
      selectedStar: this.initialRating,
    };
  },
  methods: {
    enterStarListener(index) {
      this.selectedStar = index;
    },
    selectStar(index) {
      this.selectedStar = index;
      this.$emit("ratingChange", index); // Emit the rating change back to the parent component
    },
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