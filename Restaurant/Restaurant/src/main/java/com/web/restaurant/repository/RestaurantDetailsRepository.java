package com.web.restaurant.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

//import com.web.Restaurant.Model.RestauranDto;
import com.web.restaurant.model.RestaurantDetails;

@Repository
public interface RestaurantDetailsRepository extends JpaRepository<RestaurantDetails, Long>{

	RestaurantDetails findByRestaurantName(String restaurantName);
	List<RestaurantDetails> findByRestaurantNameNot(String restaurantName);
	
	/*@Query(nativeQuery = true, value="SELECT new com.web.Restaurant.Model.RestauranDto(rd.restaurant_name, ca.cat_name, di.dish_Name, "
			+ "di.dish_Ingredients, di.dish_Price) FROM Restaurant_Details rd "
			+ "inner join Category ca on rd.id = ca.RESTAURANT_ID inner join Dish di "
			+ "on ca.id = di.category_id " )
	List<RestauranDto> getAllRestaurant();*/

}
