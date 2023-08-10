package com.techelevator.model;

import java.util.Objects;

public class Movie {
    private int movieId;
    private String movieTitle;
    private int movieDuration;
    private int movieRelease;
    private String movieDescription;
    private int genreId;
    private String imagePath;

    public Movie() {

    }



    public Movie(int movieId, String movieTitle, int movieDuration, int movieRelease, String movieDescription, int genreId, String imagePath) {
        this.movieId = movieId;
        this.movieTitle = movieTitle;
        this.movieDuration = movieDuration;
        this.movieRelease = movieRelease;
        this.movieDescription = movieDescription;
        this.genreId = genreId;
        this.imagePath = imagePath;

    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public int getMovieDuration() {
        return movieDuration;
    }

    public void setMovieDuration(int movieDuration) {
        this.movieDuration = movieDuration;
    }

    public int getMovieRelease() {
        return movieRelease;
    }

    public void setMovieRelease(int movieRelease) {
        this.movieRelease = movieRelease;
    }

    public String getMovieDescription() {
        return movieDescription;
    }

    public void setMovieDescription(String movieDescription) {
        this.movieDescription = movieDescription;
    }

    public int getGenreId() {
        return genreId;
    }

    public void setGenreId(int genreId) {
        this.genreId = genreId;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "movieId=" + movieId +
                ", movieTitle='" + movieTitle + '\'' +
                ", movieDuration=" + movieDuration +
                ", movieRelease=" + movieRelease +
                ", movieDescription='" + movieDescription + '\'' +
                ", genreId=" + genreId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return movieId == movie.movieId &&
                movieDuration == movie.movieDuration &&
                movieRelease == movie.movieRelease &&
                genreId == movie.genreId &&
                Objects.equals(movieTitle, movie.movieTitle) &&
                Objects.equals(movieDescription, movie.movieDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movieId, movieTitle, movieDuration, movieRelease, movieDescription, genreId);
    }
}
