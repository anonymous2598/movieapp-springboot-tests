package com.stackroute.movieservice.exceptions;


public class MovieDoesNotExist extends Exception {
    public MovieDoesNotExist(String message) {
        super(message);
        this.message = message;
    }

    public MovieDoesNotExist() {
    }

    private String message;

}
