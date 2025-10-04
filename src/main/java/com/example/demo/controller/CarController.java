package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;

@RestController
@RequestMapping("/api/v2/car")
public class CarController {

    // http://localhost:8080/api/v2/car/add-car
    @PostMapping("/add-car")
    public String addCar(){
        return "car add successfully";
    }

    @GetMapping("/message")
    public String getMessage(){
        return "This is my final project- second hand car";
    }
}
