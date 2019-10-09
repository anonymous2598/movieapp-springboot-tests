package com.stackroute.movieservice.service;


import com.stackroute.movieservice.domain.MovieInfo;
import com.stackroute.movieservice.exceptions.MovieAlreadyExists;
import com.stackroute.movieservice.exceptions.MovieDoesNotExist;

import java.util.List;

public interface MovieService {
     List<MovieInfo> getMovies();
     MovieInfo saveMovieInfo(MovieInfo movieInfo) throws MovieAlreadyExists;
     boolean deleteMovieInfo(Long repoId) throws MovieDoesNotExist;
//     Optional<MovieInfo> getRepoInfoById (Long repoId);
     MovieInfo updateMovieInfo(MovieInfo movieInfo) throws MovieDoesNotExist;
     List<MovieInfo> getMovieInfoByName(String movieName) throws MovieDoesNotExist;

}
