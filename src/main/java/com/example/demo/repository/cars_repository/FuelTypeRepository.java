package com.example.demo.repository.cars_repository;

import com.example.demo.cars_entity.FuelType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuelTypeRepository extends JpaRepository<FuelType, Long> {
}