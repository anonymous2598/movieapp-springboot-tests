package com.stackroute.movieservice.service;


import com.stackroute.movieservice.domain.MovieInfo;
import com.stackroute.movieservice.exceptions.MovieAlreadyExists;
import com.stackroute.movieservice.exceptions.MovieDoesNotExist;

import java.sql.SQLException;
import java.util.List;

public interface MovieService {
     List<MovieInfo> getMovies() throws SQLException;
     MovieInfo saveMovieInfo(MovieInfo movieInfo) throws MovieAlreadyExists,SQLException;
     boolean deleteMovieInfo(Long repoId) throws MovieDoesNotExist,SQLException;
//     Optional<MovieInfo> getRepoInfoById (Long repoId);
     MovieInfo updateMovieInfo(MovieInfo movieInfo) throws MovieDoesNotExist,SQLException;
     List<MovieInfo> getMovieInfoByName(String movieName) throws MovieDoesNotExist,SQLException;

}
