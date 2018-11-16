package com.web.restaurant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.web.restaurant.repository.RestaurantDetailsRepository;
import com.web.restaurant.model.*;

@SpringBootApplication
public class RestaurantApplication implements CommandLineRunner {

    private final RestaurantDetailsRepository restaurantDetailsRepository;

    @Autowired
    public RestaurantApplication(RestaurantDetailsRepository restaurantDetailsRepository) {
        this.restaurantDetailsRepository = restaurantDetailsRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(RestaurantApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        RestaurantDetails restaurantDetails = new RestaurantDetails();
        restaurantDetails.setHomeUrl("http://namastenepal.de/nnc/");
        restaurantDetails.setRestaurantName("NAMASTE NEPAL");
        restaurantDetails.setTeleNo("037123451350");

        // restaurantDetailsRepository.save(restaurantDetails);

        Address address = new Address();
        address.setStrNo("12");
        address.setStrName("Yorckstr");
        address.setZipCode("09130");
        address.setCity("Chemnitz");
        restaurantDetails.setAddress(address);

        restaurantDetailsRepository.save(restaurantDetails);

    }
}
