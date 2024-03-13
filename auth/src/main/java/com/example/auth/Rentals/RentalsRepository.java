package com.example.auth.Rentals;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface RentalsRepository extends JpaRepository<RentalsEntity, Integer> {
    // Define the method to find active rentals by carId and currentDate
    List<RentalsEntity> findActiveRentalsByCarId(Long carId);

    // Define the method to find active rentals by carId and currentDate
    List<RentalsEntity> findActiveRentalsByCarIdAndDateOutAfter(Long carId, Date currentDate);
}
