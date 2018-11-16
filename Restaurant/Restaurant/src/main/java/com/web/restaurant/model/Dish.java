package com.web.restaurant.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Dish")
public class Dish extends BaseEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long dishId;

	@Column
	private String dishName;

	@Lob
	@Column
	private String dishIngredients;

	@Column
	private String dishPrice;

	@ManyToOne(optional = false)
	@JoinColumn(name = "category_id")
	@JsonManagedReference
	private Category category;

	public Long getDishId() {
		return dishId;
	}

	public void setDishId(Long dishId) {
		this.dishId = dishId;
	}

	public String getDishName() {
		return dishName;
	}

	public void setDishName(String dishName) {
		this.dishName = dishName;
	}

	public String getDishIngredients() {
		return dishIngredients;
	}

	public void setDishIngredients(String dishIngredients) {
		this.dishIngredients = dishIngredients;
	}

	public String getDishPrice() {
		return dishPrice;
	}

	public void setDishPrice(String dishPrice) {
		this.dishPrice = dishPrice;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

}
