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
    public static void addExistingMovies(){
        Movie newMovie = new Movie(2, "Pirates of the Caribbean", "Action, Thriller, Comedy", 2003);
        ProjectDB.movieList.add(newMovie);
        Movie newMovie2 = new Movie(3, "Jurassic Park", "Action, Thriller, Horror", 1993);
        ProjectDB.movieList.add(newMovie2);
        Movie newMovie3 = new Movie(4, "Frozen", "Drama, Comedy", 2013);
        ProjectDB.movieList.add(newMovie3);

    }
}