package com.stackroute.movieservice.service;

import com.stackroute.movieservice.domain.MovieInfo;
import com.stackroute.movieservice.exceptions.MovieAlreadyExists;
import com.stackroute.movieservice.exceptions.MovieDoesNotExist;
import com.stackroute.movieservice.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
@Qualifier("dummy")
public class MovieServiceDummyImpl implements MovieService {

    MovieRepository movieRepository;

    @Autowired
    public MovieServiceDummyImpl(MovieRepository movieRepository)
    {
        this.movieRepository = movieRepository;
    }

    @Override
    public List<MovieInfo> getMovies() throws SQLException {
//        System.out.println("bada tum tuss");
        return movieRepository.findAll();

    }

    @Override
    public MovieInfo saveMovieInfo(MovieInfo movieInfo) throws MovieAlreadyExists,SQLException {
        if(movieRepository.existsById(movieInfo.getMovieId()))
        {
            throw new MovieAlreadyExists("Movie already exists!");
        }

        MovieInfo movieInfo1 = movieRepository.save(movieInfo);
        if(movieInfo1==null)
        {
            throw new MovieAlreadyExists("Movie already exists!");
        }
        return movieInfo1;
    }

    @Override
    public boolean deleteMovieInfo(Long repoId) throws MovieDoesNotExist,SQLException {
        if(movieRepository.existsById(repoId))
        {
            movieRepository.deleteById(repoId);
            return true;
        }
        else
        {
            throw new MovieDoesNotExist("Movie Does Not Exist!");
        }

    }

    @Override
    public MovieInfo updateMovieInfo(MovieInfo movieInfo) throws MovieDoesNotExist,SQLException {
        if(movieRepository.existsById(movieInfo.getMovieId()))
        {
            return movieRepository.save(movieInfo);
        }
        else
        {
            throw  new MovieDoesNotExist("Movie Does Not Exist!");
        }
    }

    @Override
    public List<MovieInfo> getMovieInfoByName(String movieName) throws MovieDoesNotExist,SQLException {
        List<MovieInfo> list;

        list= movieRepository.getMovieInfoByName(movieName);
        if(list.isEmpty())
        {
            throw new MovieDoesNotExist("No Movies Found By Given Name");
        }
        return list;
    }
}
