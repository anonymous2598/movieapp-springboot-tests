package com.stackroute.movieservice.service;

import com.stackroute.movieservice.domain.MovieInfo;
import com.stackroute.movieservice.exceptions.MovieAlreadyExists;
import com.stackroute.movieservice.exceptions.MovieDoesNotExist;
import com.stackroute.movieservice.repository.MovieRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


public class MovieServiceImplTest {
MovieInfo movieInfo;

@Mock
MovieRepository movieRepository;
@InjectMocks
MovieServiceImpl movieService;

    private List<MovieInfo> list=null;
    @Before
    public void setUp(){

        MockitoAnnotations.initMocks(this);

        movieInfo = new MovieInfo();
        movieInfo.setMovieOverview("okay");
        movieInfo.setMovieId(1);
        movieInfo.setMovieLanguage("konkani");
        movieInfo.setMovieName("Oneplus");
        list = new ArrayList();

        list.add(movieInfo);
    }

    @Test
    public void saveMovieTestSuccess() throws MovieAlreadyExists {

        when(movieRepository.save((MovieInfo)any())).thenReturn(movieInfo);
        MovieInfo savedMovie = movieService.saveMovieInfo(movieInfo);
        Assert.assertEquals(savedMovie,movieInfo);

        //verify here verifies that userRepository save method is only called once
        verify(movieRepository,times(1)).save(movieInfo);

    }

    @Test(expected = MovieAlreadyExists.class)
    public void saveMovieTestFailure() throws MovieAlreadyExists {
//        when(movieRepository.save((MovieInfo) any())).thenReturn(null);
        MovieInfo savedMovie = movieService.saveMovieInfo(movieInfo);
        //Assert.assertEquals(user,savedUser);

       doThrow(new MovieAlreadyExists()).when(movieRepository).findById(eq(Integer.toUnsignedLong(1)));
       movieService.saveMovieInfo(movieInfo);

    }

    @Test
    public void updateMovieTestSuccess() throws MovieDoesNotExist {

//      movieInfo.setMovieName("jaffa");
        when(movieRepository.existsById(anyLong())).thenReturn(true);
        MovieInfo updateMovieInfo = movieService.updateMovieInfo(movieInfo);
        //verify here verifies that movieRepository save method is only called once
        verify(movieRepository,times(1)).save(movieInfo);

    }
    @Test(expected = MovieDoesNotExist.class)
    public void updateMovieTestFailure() throws MovieDoesNotExist {
        when(movieRepository.existsById(anyLong())).thenReturn(false);
        MovieInfo savedMovie = movieService.updateMovieInfo(movieInfo);

    }

    @Test
    public void deleteMovieTestSuccess() throws MovieDoesNotExist {
        when(movieRepository.existsById(anyLong())).thenReturn(true);
        Boolean deleteMovie = movieService.deleteMovieInfo(Integer.toUnsignedLong(12));
        Assert.assertEquals(true,deleteMovie);
        //verify here verifies that movieRepository save method is only called once
        verify(movieRepository,times(1)).existsById(Integer.toUnsignedLong(12));
        verify(movieRepository,times(1)).deleteById(Integer.toUnsignedLong(12));
    }

    @Test(expected = MovieDoesNotExist.class)
    public void deleteMovieTestFailure() throws MovieDoesNotExist {
        when(movieRepository.existsById(anyLong())).thenReturn(false);
        Boolean deleteMovie = movieService.deleteMovieInfo(Integer.toUnsignedLong(15));
    }
    @Test
    public void getMovieByNameTestSuccess() throws MovieDoesNotExist {
        when(movieRepository.getMovieInfoByName(any())).thenReturn(list);
        List<MovieInfo> getMovie = movieService.getMovieInfoByName("abc");
        Assert.assertEquals(list,getMovie);
        //verify here verifies that movieRepository save method is only called once
        verify(movieRepository,times(1)).getMovieInfoByName("abc");
    }
    @Test(expected = MovieDoesNotExist.class)
    public void getMovieByNameTestFailure() throws MovieDoesNotExist {
        when(movieRepository.getMovieInfoByName(any())).thenReturn(new ArrayList<>());
        List<MovieInfo> list = movieService.getMovieInfoByName("asfdgnh");
    }

}
