package com.example.demo.repository.cars_repository;

import com.example.demo.cars_entity.Transmission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransmissionRepository extends JpaRepository<Transmission, Long> {
}