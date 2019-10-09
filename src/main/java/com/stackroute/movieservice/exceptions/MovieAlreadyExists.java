package com.stackroute.movieservice.exceptions;


public class MovieAlreadyExists extends Exception {
    private String message;

    public MovieAlreadyExists(String message) {
        super(message);
        this.message = message;
    }

    public MovieAlreadyExists() {

    }
}
