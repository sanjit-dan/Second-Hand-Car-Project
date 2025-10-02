package com.example.demo.cars_controller;

import com.example.demo.cars_entity.Cars;
import com.example.demo.cars_service.CarsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/second-hand-car")
public class CarsController {
    private CarsService carsService;

    public CarsController(CarsService carsService) {
        this.carsService = carsService;
    }

    // http://localhost:8080/api/second-hand-car/add-car
    @PostMapping("/add-car")
    public ResponseEntity<String> addCars(@RequestBody Cars cars){
        carsService.saveCars(cars);
        return new ResponseEntity<>("cars add success", HttpStatus.CREATED);
    }
}
