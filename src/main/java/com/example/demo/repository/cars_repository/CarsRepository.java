package com.example.demo.repository.cars_repository;

import com.example.demo.cars_entity.Cars;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarsRepository extends JpaRepository<Cars, Long> {

}