package com.web.restaurant.service;

import java.util.List;

import org.springframework.stereotype.Service;


import com.web.restaurant.model.Dish;
//import com.web.Restaurant.Model.RestauranDto;
import com.web.restaurant.model.RestaurantDetails;

@Service
public interface RestaurantDetailService {
	void addRrestaurantDetails();
	List<RestaurantDetails> allRestaurantDetails();
	List<Dish> allDishDetails();
	RestaurantDetails findById(Long id);
}
