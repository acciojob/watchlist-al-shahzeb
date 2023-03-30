package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MovieRepository {

    Map<String,Movie> movieMap= new HashMap<>();
    Map<String,Director> directorMap = new HashMap<>();

    Map<String,List<String>> pairMap=new HashMap<>();

    public String addMovie(Movie movie){
        movieMap.put(movie.getName(),movie);
        return  "Success";
    }

    public String addDirector(Director director){
        directorMap.put(director.getName(),director);
        return  "Success";
    }

    public List<Movie> getAllMovies(){
        return movieMap.values().stream().toList();
    }

    public List<Director> getAllDirectors(){
        return directorMap.values().stream().toList();
    }

    public String deleteDirector(String name){
        for(String movie:pairMap.get(name)){
            movieMap.remove(movie);
        }
        directorMap.remove(name);
        pairMap.remove(name);
        return "Success";
    }

    public String addMovieDirectorPair(String mName, String dName){
        List<String> movieList = new ArrayList<>();
        if(pairMap.containsKey(dName))
            movieList=pairMap.get(dName);
        movieList.add(mName);
        return "Success";
    }


    public List<String> getMoviesByDirectorName(String name){

        return pairMap.get(name);
    }

    public String deleteAllDirectors(){
        for(List<String> movie: pairMap.values()){
            for(String s:movie)
                movieMap.remove(s);
        }

        directorMap.clear();
        pairMap.clear();
        return "Success";
    }
}
