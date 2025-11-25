package org.example;

import java.util.ArrayList;
import java.util.Comparator;

public class ProjectDB {

    public static ArrayList<Movie> movieList = new ArrayList<Movie>();

    public static void sortMoviesByID() {
        movieList.sort(Comparator.comparingInt(Movie::getMovieID));
    }
    public static void sortMoviesByOldest() {
        movieList.sort(Comparator.comparingInt(Movie::getReleaseYear));
    }
    public static void sortMoviesByIDReversed() {
        movieList.sort(Comparator.comparingInt(Movie::getMovieID));
        movieList = new ArrayList<>(movieList.reversed());
    }

    public static void sortMoviesByNewest() {
        movieList.sort(Comparator.comparingInt(Movie::getReleaseYear));
        movieList = new ArrayList<>(movieList.reversed());
    }
}