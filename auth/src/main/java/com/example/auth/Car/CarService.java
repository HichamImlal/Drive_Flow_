package com.example.auth.Car;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {
    private final CarRepository postRepository;

    @Autowired
    public CarService(CarRepository postRepository) {
        this.postRepository = postRepository;
    }

    public CarEntity addPost(CarEntity post) {
        return postRepository.save(post);
    }
    public List<CarEntity> getAllPosts() {
        return postRepository.findAll();
    }
    public void updateAvailability(Long carId, boolean available) {
        CarEntity car = postRepository.findById(carId)
                .orElseThrow(() -> new RuntimeException("Car not found"));
        car.setAvailable(available);
        postRepository.save(car);
    }
    public List<CarEntity> getPostsByAdminId(Long adminId) {
        return postRepository.findByAdminId(adminId);
    }
}
