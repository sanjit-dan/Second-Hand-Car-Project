package com.example.demo.repository.cars_repository;

import com.example.demo.cars_entity.Model;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModelRepository extends JpaRepository<Model, Long> {
}