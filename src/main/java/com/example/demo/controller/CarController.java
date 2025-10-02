package com.example.demo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

@RestController
@RequestMapping("/api/v2/car")
public class CarController {

    // http://localhost:8080/api/v2/car/add-car
    @PostMapping("/add-car")
    public String addCar(){
        return "car add successfully";
    }
}
