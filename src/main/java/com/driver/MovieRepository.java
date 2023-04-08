//package com.driver;
//
//import org.springframework.stereotype.Repository;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@Repository
//public class MovieRepository {
//
//    Map<String,Movie> movieMap= new HashMap<>();
//    Map<String,Director> directorMap = new HashMap<>();
//
//    Map<String,List<String>> pairMap=new HashMap<>();
//
//    public String addMovie(Movie movie){
//        movieMap.put(movie.getName(),movie);
//        return  "Success";
//    }
//
//    public String addDirector(Director director){
//        directorMap.put(director.getName(),director);
//        return  "Success";
//    }
//
//    public List<Movie> getAllMovies(){
//        return movieMap.values().stream().toList();
//    }
//
//    public List<Director> getAllDirectors(){
//        return directorMap.values().stream().toList();
//    }
//
//    public String deleteDirector(String name){
//        for(String movie:pairMap.get(name)){
//            movieMap.remove(movie);
//        }
//        directorMap.remove(name);
//        pairMap.remove(name);
//        return "Success";
//    }
//
//    public String addMovieDirectorPair(String mName, String dName){
//        List<String> movieList = new ArrayList<>();
//        if(pairMap.containsKey(dName))
//            movieList=pairMap.get(dName);
//        movieList.add(mName);
//        pairMap.put(dName,movieList);
//        return "Success";
//    }
//
//
//    public List<String> getMoviesByDirectorName(String name){
//
//        return pairMap.get(name);
//    }
//
//    public String deleteAllDirectors(){
//        for(List<String> movie: pairMap.values()){
//            for(String s:movie)
//                movieMap.remove(s);
//        }
//
//        directorMap.clear();
//        pairMap.clear();
//        return "Success";
//    }
//}

package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MovieRepository {

    HashMap<String,Movie> movieDb = new HashMap<>();
    HashMap<String,Director> directorDb = new HashMap<>();
    HashMap<String,List<String>> movieDirectorPair = new HashMap<>();     // key - Director , value : list of movies

    public String addMovie(Movie movie){

        String key = movie.getName();
        movieDb.put(key,movie);
        return "Movie added Successfully";
    }

    public String addDirector(Director director){

        String key = director.getName();
        directorDb.put(key,director);
        return "Director added Successfully";
    }

    public String addMovieDirectorPair(String movieName,String directorName){

        List<String> list = movieDirectorPair.get(directorName);

        if(list == null){
            list = new ArrayList<>();
        }

        list.add(movieName);
        movieDirectorPair.put(directorName,list);

        return "Movie with Director added Successfully";
    }

    public Movie getMovieByName(String MovieName){

        return movieDb.get(MovieName);
    }

    public Director getDirectorByName(String directorName){

        return directorDb.get(directorName);
    }

    public List<String> getMoviesByDirectorName(String directorName){

        return movieDirectorPair.get(directorName);
    }

    public List<String> getAllMovies(){

        List<String> list = new ArrayList<>();

        for(String st : movieDb.keySet()){
            list.add(st);
        }
        return list;
    }

    public String deleteDirectorByName(String directorName){

        //   1. directorDb
        //   2. also need to remove the entries in movie_director hashmap
        //   3. Corresponding movies also

        for(String st : movieDirectorPair.get(directorName)){
            movieDb.remove(st);
        }
        movieDirectorPair.remove(directorName);
        directorDb.remove(directorName);

        return "Director removed Successfully";
    }

    public String removeAllDirector(){

        for(String director : directorDb.keySet())
        {

            for(String st : movieDirectorPair.get(director)){
                movieDb.remove(st);
            }
            movieDirectorPair.remove(director);
            directorDb.remove(director);
        }

        return "All Director removed Successfully";
    }
}