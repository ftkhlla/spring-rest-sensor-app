package ru.springcourse.FirstRestApp.util;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


public class SensorNotCreatedException extends RuntimeException{
    public SensorNotCreatedException(String msg){
        super(msg);
    }
}
