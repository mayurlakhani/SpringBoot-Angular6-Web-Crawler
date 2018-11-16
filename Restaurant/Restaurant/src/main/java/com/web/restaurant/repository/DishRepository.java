package com.web.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.web.restaurant.model.Dish;

@Repository
public interface DishRepository extends JpaRepository<Dish, Long>{

	@Modifying
	@Query("delete from Dish")
	@Transactional
	void deleteAllDishes();
	

}
