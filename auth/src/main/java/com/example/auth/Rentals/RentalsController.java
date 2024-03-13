package com.example.auth.Rentals;

import com.example.auth.Car.CarEntity;
import com.example.auth.Car.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rentals")
public class RentalsController {

    private final RentalsService rentalsService;

    @Autowired
    public RentalsController(RentalsService rentalsService) {
        this.rentalsService = rentalsService;
    }

    @GetMapping("/getAllRentals")
    public ResponseEntity<List<RentalsEntity>> getAllRentals() {
        List<RentalsEntity> rentals = rentalsService.getAllRentals();
        return new ResponseEntity<>(rentals, HttpStatus.OK);
    }

    @PostMapping("/addRental")
    public ResponseEntity<RentalsEntity> addRental(@RequestBody RentalsEntity rental) {
        RentalsEntity addedRental = rentalsService.addRental(rental);
        return new ResponseEntity<>(addedRental, HttpStatus.CREATED);
    }
    @GetMapping("/details")
    public List<RentalDetailsDTO> getAllRentalDetails(@RequestParam Long adminId) {
        return rentalsService.getAllRentalDetails(adminId);
    }
}
