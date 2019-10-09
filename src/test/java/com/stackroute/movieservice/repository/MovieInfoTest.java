package com.stackroute.movieservice.repository;

import com.stackroute.movieservice.domain.MovieInfo;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;


@RunWith(SpringRunner.class)
@DataJpaTest
public class MovieInfoTest {
    @Autowired
    MovieRepository movieRepository;
    MovieInfo movieInfo;

    @Before
    public void setUp()
    {
        movieInfo= new MovieInfo();
        movieInfo.setMovieLanguage("telugu");
        movieInfo.setMovieName("abcd");
        movieInfo.setMovieId(100);
        movieInfo.setMovieOverview("bad");
    }
    @After
    public void tearDown(){

        movieRepository.deleteAll();
    }
    @Test
    public void testSaveMovie(){
        movieRepository.save(movieInfo);
        MovieInfo fetchMovie = movieRepository.findById(movieInfo.getMovieId()).get();
        Assert.assertEquals(100,fetchMovie.getMovieId());
    }

    @Test
    public void testSaveMovieFailure(){
        MovieInfo testUser = new MovieInfo(1,"a","a","b");
        movieRepository.save(movieInfo);
        MovieInfo fetchUser = movieRepository.findById(movieInfo.getMovieId()).get();
        Assert.assertNotSame(testUser,fetchUser);
    }

    @Test(expected= NoSuchElementException.class)
    public void testDeleteMovie()
    {
        MovieInfo testMovie1 = new MovieInfo(5,"a","a","b");
        movieRepository.save(testMovie1);
        movieRepository.delete(testMovie1);
        MovieInfo fetchUser1 = movieRepository.findById(testMovie1.getMovieId()).get();
    }

    @Test
    public void testDeleteMovieFailure(){
        MovieInfo u = new MovieInfo(5,"a","a","i");
        movieRepository.save(u);
        movieRepository.deleteById(Integer.toUnsignedLong(5));
        Boolean b = movieRepository.findAll().isEmpty();
        Assert.assertNotEquals(false,b);
    }


    @Test
    public void testUpdateMovie()
    {
        movieInfo.setMovieOverview("okayish");
        movieRepository.save(movieInfo);
        Assert.assertEquals("okayish",movieRepository.findById((long)100).get().getMovieOverview());
    }


    @Test
    public void testGetByName(){
        MovieInfo u = new MovieInfo(5,"john1","Based on True Story of John part 1","Hindi");
        MovieInfo u1 = new MovieInfo(6,"john1","Based on True Story of John part 2","Hindi");
        movieRepository.save(u1);
        movieRepository.save(u);
        List<MovieInfo> m=new ArrayList<>();
        m.add(u);
        m.add(u1);
        List<MovieInfo> movie=movieRepository.getMovieInfoByName("Hindi");
        Assert.assertEquals(m,movie);
    }

    @Test
    public void testGetByNameFailure(){
        MovieInfo u = new MovieInfo(5,"john1","Based on True Story of John part 1","Hindi");
        MovieInfo u1 = new MovieInfo(6,"john2","Based on True Story of John part 2","Hindi");
        movieRepository.save(u1);
        movieRepository.save(u);
        List<MovieInfo> m=new ArrayList<>();
        m.add(u);
        m.add(u1);
        List<MovieInfo> movie=movieRepository.getMovieInfoByName("Hndi");
        Assert.assertNotEquals(m,movie);
    }
    @Test
    public void testGetAllUser(){
        MovieInfo u = new MovieInfo(3,"a","A","A");
        MovieInfo u1 = new MovieInfo(4,"q","q","q");
        movieRepository.save(u);
        movieRepository.save(u1);

        List<MovieInfo> list = movieRepository.findAll();
        Assert.assertEquals("A",list.get(0).getMovieName());
    }
}
