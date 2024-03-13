package com.example.auth.Rentals;

import com.example.auth.Car.CarEntity;
import com.example.auth.Car.CarRepository;
import com.example.auth.User.UserEntity;
import com.example.auth.User.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RentalsService {

    private  RentalsRepository rentalsRepository;
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private CarRepository carRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    public RentalsService(RentalsRepository rentalsRepository) {
        this.rentalsRepository = rentalsRepository;
    }

    public List<RentalsEntity> getAllRentals() {
        return rentalsRepository.findAll();
    }

    public RentalsEntity addRental(RentalsEntity rental) {
        return rentalsRepository.save(rental);
    }
    public List<RentalDetailsDTO> getAllRentalDetails(Long adminId) {
        List<RentalDetailsDTO> rentalDetailsList = new ArrayList<>();

        String queryString = "SELECT r, c, u FROM RentalsEntity r " +
                "JOIN CarEntity c ON r.carId = c.id " +
                "JOIN UserEntity u ON r.userId = u.id " +
                "WHERE c.adminId = :adminId";

        Query query = entityManager.createQuery(queryString);
        query.setParameter("adminId", adminId);

        List<Object[]> results = query.getResultList();
        for (Object[] result : results) {
            RentalsEntity rental = (RentalsEntity) result[0];
            CarEntity car = (CarEntity) result[1];
            UserEntity user = (UserEntity) result[2];
            RentalDetailsDTO rentalDetailsDTO = new RentalDetailsDTO();
            rentalDetailsDTO.setImage(car.getImage());
            rentalDetailsDTO.setTotalPrice(rental.getRentalPrice());
            rentalDetailsDTO.setMark(car.getMark());
            rentalDetailsDTO.setModel(car.getModel());
            rentalDetailsDTO.setEmail(user.getEmail());
            rentalDetailsDTO.setDateIn(rental.getDateIn());
            rentalDetailsDTO.setDateOut(rental.getDateOut());

            rentalDetailsList.add(rentalDetailsDTO);
        }

        return rentalDetailsList;
    }
}
