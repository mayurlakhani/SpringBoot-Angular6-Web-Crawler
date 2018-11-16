package com.web.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.web.restaurant.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{
	
	@Modifying
	@Query("delete from Category")
	@Transactional
	public void deleteAllCategories();

}
