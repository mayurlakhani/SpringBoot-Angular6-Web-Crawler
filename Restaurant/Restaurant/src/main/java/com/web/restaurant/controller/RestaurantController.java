package com.web.restaurant.controller;

//import org.hibernate.annotations.common.util.impl.LoggerFactory;

import java.io.IOException;
import java.util.List;

import com.web.restaurant.repository.CategoryRepository;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.restaurant.model.RestaurantDetails;
import com.web.restaurant.service.ParserService;
import com.web.restaurant.service.RestaurantDetailService;
import com.web.restaurant.utill.CustomErrorType;

import ch.qos.logback.classic.Logger;

import com.web.restaurant.enums.CrawlerType;
import com.web.restaurant.model.*;

@RestController
@RequestMapping("/restaurant")
@CrossOrigin(origins = "http://localhost:4200")
public class RestaurantController {
    public static final Logger logger = (Logger) LoggerFactory.getLogger(RestaurantController.class);

    @Autowired
    private RestaurantDetailService restaurantDetailService;
    @Autowired
    private ParserService parserService;
    @Autowired
    private CategoryRepository repository;

    @GetMapping("/add")
    public ResponseEntity myMethod() throws IOException {
        restaurantDetailService.addRrestaurantDetails();
        parserService.parse(CrawlerType.INSERT);
        return new ResponseEntity("Inserted!", HttpStatus.CREATED);
    }

    @GetMapping("/update")
    public ResponseEntity crawledURL() throws IOException {
        parserService.parse(CrawlerType.UPDATE);
        return new ResponseEntity("Updated!", HttpStatus.OK);
    }

    @GetMapping(value = "/allRestaurants")
    public ResponseEntity<List<RestaurantDetails>> allRestaurantDetails() {
        List<RestaurantDetails> restaurantDetails = restaurantDetailService.allRestaurantDetails();
        //if (restaurantDetails.isEmpty())
        if (restaurantDetails == null) {
            return new ResponseEntity(new CustomErrorType("list is empty"),
                    HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<RestaurantDetails>>(restaurantDetails, HttpStatus.FOUND);
    }

    @GetMapping(value = "/allCategories")
    public ResponseEntity<List<Category>> allCategories() {
        List<Category> categoryList = repository.findAll();
        //if (restaurantDetails.isEmpty())
        if (categoryList == null) {
            return new ResponseEntity(new CustomErrorType("list is empty"),
                    HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Category>>(categoryList, HttpStatus.FOUND);
    }

    @GetMapping(value = "/alldishDetails")
    public ResponseEntity<List<Dish>> allDishDetails() {
        System.out.println("GET ALL DISHES!!!!!!!!!!!!!");
        List<Dish> dishDetails = restaurantDetailService.allDishDetails();
        //if (restaurantDetails.isEmpty())
        if (dishDetails == null) {
            return new ResponseEntity(new CustomErrorType("list is empty"),
                    HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Dish>>(dishDetails, HttpStatus.OK);
    }

    @GetMapping(value = "/allRestaurants/{id}")
    public ResponseEntity<?> RestaurentById(@PathVariable("id") Long id) {
        logger.info("Fetching allRestaurants with id {}", id);
        RestaurantDetails restaurantDetails = restaurantDetailService.findById(id);
        if (restaurantDetails == null) {
            logger.error("restaurantDetails with id {} not found.", id);
            return new ResponseEntity(new CustomErrorType("id with id " + id
                    + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<RestaurantDetails>(restaurantDetails, HttpStatus.OK);
    }

	/*@GetMapping()
	public List<RestauranDto> getAllRestaurant() {
		return restaurantDetailService.getAllRestaurant();
	}*/
}
