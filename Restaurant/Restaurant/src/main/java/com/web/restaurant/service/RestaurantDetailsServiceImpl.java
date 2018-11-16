package com.web.restaurant.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.restaurant.model.Address;
import com.web.restaurant.model.Dish;
//import com.web.Restaurant.Model.RestauranDto;
import com.web.restaurant.model.RestaurantDetails;
import com.web.restaurant.repository.AddressRepository;
import com.web.restaurant.repository.DishRepository;
import com.web.restaurant.repository.RestaurantDetailsRepository;

@Service("RestaurantDetailService")
@Transactional
public class RestaurantDetailsServiceImpl implements RestaurantDetailService {

	@Autowired 
	private RestaurantDetailsRepository restaurantDetailsRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private DishRepository dishRepository;
	
	@Override
	public void addRrestaurantDetails() {
		// TODO Auto-generated method stub
		
		Set<RestaurantDetails> detailSet = new HashSet<RestaurantDetails>();
		RestaurantDetails restaurantDetails1 = new RestaurantDetails();
		RestaurantDetails restaurantDetails2 = new RestaurantDetails();
		
		Address address1 = new Address();
		address1.setStrNo("58");
		address1.setStrName("Reichsstrasse");
		address1.setZipCode("09112");
		address1.setCity("Chemnitz");
		
		Address address2 = new Address();
		address2.setStrNo("80");
		address2.setStrName("Heinzstrasse");
		address2.setZipCode("09130");
		address2.setCity("Chemnitz");
		
		restaurantDetails1.setHomeUrl("https://www.asia-kueche-hoang.de/");
		restaurantDetails1.setRestaurantName("ASIA KITCHEN HOANG");
		restaurantDetails1.setTeleNo("037123480095");
		detailSet.add(restaurantDetails1);
		restaurantDetails1.setAddress(address1);
		
		restaurantDetails2.setHomeUrl("https://www.sofra-dueruem-doener-chemnitz.de/");
		restaurantDetails2.setRestaurantName("SOFRA DüRüM-DÖNER");
		restaurantDetails2.setTeleNo("037135597599");
		detailSet.add(restaurantDetails2);
		restaurantDetails2.setAddress(address2);
		
		restaurantDetailsRepository.save(detailSet);
	}

	@Override
	public List<RestaurantDetails> allRestaurantDetails() {
		List<RestaurantDetails> all = restaurantDetailsRepository.findAll();
		return all;
	}

	@Override
	public List<Dish> allDishDetails() {
		List<Dish> dishes = dishRepository.findAll();
		return dishes;
	}

	@Override
	public RestaurantDetails findById(Long id) {
		return  restaurantDetailsRepository.findOne(id);
	}

	/*@Override
	public List<RestauranDto> getAllRestaurant() {
		return restaurantDetailsRepository.getAllRestaurant();
	}*/
	
	

}
