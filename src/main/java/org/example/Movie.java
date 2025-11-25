package org.example;

public class Movie {
    private int movieID;
    private String title;
    private String genre;
    private int releaseYear;

    public Movie(int movieID, String title, String genre, int releaseYear) {
        this.movieID = movieID;
        this.title = title;
        this.genre = genre;
        this.releaseYear = releaseYear;
    }
    // Getters and setters that match the actual fields
    public int getMovieID() {
        return movieID;
    }

    public void setMovieID(int movieID) {
        this.movieID = movieID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }
}