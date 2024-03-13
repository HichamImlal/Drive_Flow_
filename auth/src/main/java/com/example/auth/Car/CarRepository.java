package com.example.auth.Car;

import com.example.auth.Rentals.RentalsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepository extends JpaRepository<CarEntity, Long> {
    List<CarEntity> findByAdminId(Long adminId);
}
