package com.stackroute.movieservice.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.movieservice.domain.MovieInfo;
import com.stackroute.movieservice.exceptions.GlobalExceptionHandler;
import com.stackroute.movieservice.exceptions.MovieAlreadyExists;
import com.stackroute.movieservice.exceptions.MovieDoesNotExist;
import com.stackroute.movieservice.service.MovieService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest
public class MovieControllerTest {
    @Autowired
    private MockMvc mockMvc;
    private MovieInfo movieInfo;
    @MockBean
    private MovieService movieService;
    @InjectMocks
    private MovieController movieController;

    private List<MovieInfo> list=null;

    @Before
    public void setUp(){

        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(movieController).setControllerAdvice(new GlobalExceptionHandler()).build();
        movieInfo = new MovieInfo();
        movieInfo.setMovieId(1);
        movieInfo.setMovieOverview("average");
        movieInfo.setMovieName("jaffa");
        movieInfo.setMovieLanguage("french");
        list = new ArrayList();

        list.add(movieInfo);
    }

    @Test
    public void saveMovieInfo() throws Exception {
        when(movieService.saveMovieInfo(any())).thenReturn(movieInfo);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/movie")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(movieInfo)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());
    }
    @Test
    public void saveMovieInfoFailure() throws Exception {
        when(movieService.saveMovieInfo(any())).thenThrow(MovieAlreadyExists.class);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/movie")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(movieInfo)))
                .andExpect(MockMvcResultMatchers.status().isNotAcceptable())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void updateMovieInfo() throws Exception {
        when(movieService.updateMovieInfo(any())).thenReturn(movieInfo);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/movie")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(movieInfo)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());
    }
    @Test
    public void updateMovieInfoFailure() throws Exception {
        when(movieService.updateMovieInfo(any())).thenThrow(MovieDoesNotExist.class);
        mockMvc.perform(MockMvcRequestBuilders.put("/api/movie")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(movieInfo)))
                .andExpect(MockMvcResultMatchers.status().isNotAcceptable())
                .andDo(MockMvcResultHandlers.print());
    }


    @Test
    public void deleteMovieInfo() throws Exception {
        when(movieService.deleteMovieInfo(anyLong())).thenReturn(true);
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/movie")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(1)))
                .andExpect(MockMvcResultMatchers.status().isAccepted())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void deleteMovieInfoFailure() throws Exception {
        when(movieService.deleteMovieInfo(any())).thenThrow(MovieDoesNotExist.class);
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/movie")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(1)))
                .andExpect(MockMvcResultMatchers.status().isNotAcceptable())
                .andDo(MockMvcResultHandlers.print());
    }
    @Test
    public void getAllUser() throws Exception {
        when(movieService.getMovies()).thenReturn(list);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/movie")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(movieInfo)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }
    @Test
    public void getMovieByName() throws Exception {
        when(movieService.getMovieInfoByName(anyString())).thenReturn(list);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/a")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString("abc")))
                .andExpect(MockMvcResultMatchers.status().isAccepted())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void getMovieInfoByNameFailure() throws Exception {
        when(movieService.getMovieInfoByName(any())).thenThrow(MovieDoesNotExist.class);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/a")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString("abc")))
                .andExpect(MockMvcResultMatchers.status().isNotAcceptable())
                .andDo(MockMvcResultHandlers.print());
    }


    private static String asJsonString(final Object obj)
    {
        try{
            return new ObjectMapper().writeValueAsString(obj);

        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }


}
