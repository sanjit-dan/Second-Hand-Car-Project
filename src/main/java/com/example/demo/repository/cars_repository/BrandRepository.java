package com.example.demo.repository.cars_repository;

import com.example.demo.cars_entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand, Long> {
}