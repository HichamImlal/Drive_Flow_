package com.example.auth.Car;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CarController {
    private final CarService postService;

    @Autowired
    public CarController(CarService postService) {
        this.postService = postService;
    }

//    @PostMapping("/posts")
//    public ResponseEntity<PostEntity> addPost(@RequestBody PostEntity post) {
//        PostEntity addedPost = postService.addPost(post);
//        return new ResponseEntity<>(addedPost, HttpStatus.CREATED);
//    }
@PostMapping("/addPost")
public ResponseEntity<CarEntity> savedPost(@RequestParam("mark") String mark,
                                           @RequestParam("price") double price,
                                           @RequestParam("id_admin") Long dminId,
                                           @RequestParam("model") String model,
                                           @RequestParam("description") String description,
                                           @RequestParam("available") Boolean available,
                                           @RequestParam("image") MultipartFile image) {
    try {
        CarEntity Car = new CarEntity();
        Car.setMark(mark);
        Car.setAdminId(dminId);
        Car.setPrice(price);
        Car.setModel(model);
        Car.setDescription(description);
        Car.setAvailable(available);
        Car.setImage(image.getBytes());
        CarEntity savedPost = postService.addPost(Car);
        return new ResponseEntity<>(savedPost, HttpStatus.CREATED);
    } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
    @GetMapping("getAllPosts")
    public ResponseEntity<List<CarEntity>> getAllPosts() {
        try {
            List<CarEntity> posts = postService.getAllPosts();
            return new ResponseEntity<>(posts, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/getByIdAdmin/{adminId}")
    public ResponseEntity<List<CarEntity>> getPostsByAdminId(@PathVariable Long adminId) {
        List<CarEntity> cars = postService.getPostsByAdminId(adminId);

        // Filter out cars with availability set to false
        List<CarEntity> availableCars = cars.stream()
                .filter(car -> car.isAvailable())
                .collect(Collectors.toList());
        return ResponseEntity.ok(availableCars);
    }
    @PostMapping("/cars/updateAvailability")
    public void updateCarAvailability(@RequestParam Long carId) {
        postService.updateAvailability(carId, false);
    }
}
