package com.stackroute.movieservice.controller;


import com.stackroute.movieservice.domain.MovieInfo;
import com.stackroute.movieservice.exceptions.MovieAlreadyExists;
import com.stackroute.movieservice.exceptions.MovieDoesNotExist;
import com.stackroute.movieservice.service.MovieService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/")
@Api(value="Movie Management System", description="Operations pertaining to movie in Movie Management System")
public class MovieController {
   private MovieService movieService;
   private ResponseEntity responseEntity;


    public MovieController(MovieService movieService)
    {
        this.movieService = movieService;
    }

    @ApiOperation(value = "Add a movie into the list of movies", response = ResponseEntity.class)
    @PostMapping("movie")
    public ResponseEntity<?> saveMovieInfo(@RequestBody MovieInfo movieInfo) throws MovieAlreadyExists,SQLException {
        movieService.saveMovieInfo(movieInfo);
        responseEntity= new ResponseEntity<String>("Successfully created!", HttpStatus.CREATED);
        return responseEntity;
    }

    @ApiOperation(value = "View a list of available movies", response = ResponseEntity.class)
    @GetMapping("movie")
    public ResponseEntity<?> getMovies() throws SQLException
    {
        return new ResponseEntity<List<MovieInfo>>(movieService.getMovies(), HttpStatus.OK);
    }

    @ApiOperation(value = "Delete a movie from the list of movies", response = ResponseEntity.class)
    @DeleteMapping("movie")
    public ResponseEntity<?> deleteMovieInfo(@RequestBody Long repoId) throws MovieDoesNotExist,SQLException {
        movieService.deleteMovieInfo(repoId);
        responseEntity= new ResponseEntity<String>("Deleted Successfully",HttpStatus.ACCEPTED);
        return responseEntity;
    }

    @ApiOperation(value = "Update a movie in the list of movies", response = ResponseEntity.class)
    @PutMapping("movie")
    public ResponseEntity<?> updateMovie(@RequestBody MovieInfo movieInfo) throws MovieDoesNotExist,SQLException
    {
        movieService.updateMovieInfo(movieInfo);
        responseEntity= new ResponseEntity<String>("Updated Successfully",HttpStatus.ACCEPTED);
        return responseEntity;
    }

    @ApiOperation(value = "Find a movie by Name from list of movies", response = ResponseEntity.class)
    @GetMapping("a")
    public ResponseEntity<?> getMovieByName(@RequestBody String movieName) throws MovieDoesNotExist,SQLException {
        List<MovieInfo> list;
        list = movieService.getMovieInfoByName(movieName);
        responseEntity= new ResponseEntity<List<MovieInfo>>(list,HttpStatus.ACCEPTED);
        return responseEntity;
    }

}
