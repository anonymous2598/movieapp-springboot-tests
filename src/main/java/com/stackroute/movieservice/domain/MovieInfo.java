package com.stackroute.movieservice.domain;


import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel(description = "All details about the Movie")
public class MovieInfo {

    @Id
    private long movieId;
    private String movieLanguage;
    private String movieOverview;
    private String movieName;

}
