package com.stackroute.movieservice.startup;

import com.stackroute.movieservice.domain.MovieInfo;
import com.stackroute.movieservice.exceptions.MovieAlreadyExists;
import com.stackroute.movieservice.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties()
public class CommandLineRunnerStartup implements CommandLineRunner {
    MovieService movieService;

    @Autowired
    private Environment environment;

    @Autowired
    public CommandLineRunnerStartup(MovieService movieService)
    {
        this.movieService=movieService;
    }

    @Override
    public void run(String... args) throws Exception {
        MovieInfo movieInfo1= new MovieInfo(Long.parseLong(environment.getProperty("movie2.id")),environment.getProperty("movie2.language"),
                environment.getProperty("movie2.overview"),environment.getProperty("movie2.name"));
        try {
            movieService.saveMovieInfo(movieInfo1);

        } catch (MovieAlreadyExists movieAlreadyExists) {
            movieAlreadyExists.printStackTrace();
        }
    }
}
