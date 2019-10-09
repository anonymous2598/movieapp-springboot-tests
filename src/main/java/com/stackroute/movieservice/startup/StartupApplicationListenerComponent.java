package com.stackroute.movieservice.startup;

import com.stackroute.movieservice.domain.MovieInfo;
import com.stackroute.movieservice.exceptions.MovieAlreadyExists;
import com.stackroute.movieservice.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value = {"classpath:application.properties"})
public class StartupApplicationListenerComponent implements ApplicationListener<ContextRefreshedEvent> {

    MovieService movieService;

    @Value("${movie1.id}")
    private long id;
    @Value("${movie1.name}")
    private String name;
    @Value("${movie1.language}")
    private String language;
    @Value("${movie1.overview}")
    private String overview;


    @Autowired
    public StartupApplicationListenerComponent(MovieService movieService)
    {
        this.movieService=movieService;
    }


    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        MovieInfo movieInfo= new MovieInfo(id,language,overview,name);
        try {
//            System.out.println(movieInfo);
            movieService.saveMovieInfo(movieInfo);

        } catch (MovieAlreadyExists movieAlreadyExists) {
            movieAlreadyExists.printStackTrace();
        }
    }
}
