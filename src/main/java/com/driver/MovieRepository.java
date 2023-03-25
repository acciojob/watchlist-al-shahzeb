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

    Map<String,String> pairMap=new HashMap<>();

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
        for(Map.Entry<String,String> map: pairMap.entrySet()){
            if(map.getValue().equals(name)) {
                String movieName = map.getKey();
                movieMap.remove(movieName);
                pairMap.remove(movieName);
            }
        }
        directorMap.remove(name);

        return "Success";
    }

    public String addMovieDirectorPair(String mName, String dName){
        pairMap.put(mName,dName);
        return "Success";
    }


    public List<String> getMoviesByDirectorName(String name){
        List<String> movieList=new ArrayList<>();
        for(Map.Entry<String,String> map: pairMap.entrySet()){
            if(map.getValue().equals(name))
                movieList.add(map.getKey());
        }
        return movieList;
    }

    public String deleteAllDirectors(){
        for(String movieName: pairMap.keySet())
            movieMap.remove(movieName);

//        for(String directorName: pairMap.values())
//            directorMap.remove(directorName);

        directorMap.clear();
        pairMap.clear();
        return "Success";
    }
}
