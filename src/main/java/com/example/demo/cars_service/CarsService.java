package com.example.demo.cars_service;

import com.example.demo.cars_entity.Cars;
import com.example.demo.repository.cars_repository.CarsRepository;
import org.springframework.stereotype.Service;

@Service
public class CarsService {


    private CarsRepository carsRepository;

    public CarsService(CarsRepository carsRepository) {
        this.carsRepository = carsRepository;
    }

    public void saveCars(Cars cars) {
        carsRepository.save(cars);

    }
}
