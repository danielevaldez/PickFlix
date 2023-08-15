// An array to hold the star ratings for different movies
const starRatings = {};

// Method to set a star rating for a movie
function setStarRating(movieId, rating) {
  starRatings[movieId] = rating;
}

// Method to get the star rating for a movie
function getStarRating(movieId) {
  return starRatings[movieId] || 0;
}

export default {
  setStarRating,
  getStarRating,
};