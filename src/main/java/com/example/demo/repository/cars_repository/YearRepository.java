package com.example.demo.repository.cars_repository;

import com.example.demo.cars_entity.Year;
import org.springframework.data.jpa.repository.JpaRepository;

public interface YearRepository extends JpaRepository<Year, Long> {
}