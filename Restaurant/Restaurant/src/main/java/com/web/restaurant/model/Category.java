package com.web.restaurant.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Category")
public class Category extends BaseEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "CatName")
	private String catName;

	@ManyToOne(optional = false)
	@JoinColumn(name = "RESTAURANT_ID")
	private RestaurantDetails RestaurantId;

	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonBackReference
	private Set<Dish> dishList = new HashSet<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCatName() {
		return catName;
	}

	public void setCatName(String catName) {
		this.catName = catName;
	}

	public RestaurantDetails getRestaurantId() {
		return RestaurantId;
	}

	public void setRestaurantId(RestaurantDetails restaurantId) {
		RestaurantId = restaurantId;
	}

	public Set<Dish> getDishList() {
		return dishList;
	}

	public void setDishList(Set<Dish> dishList) {
		this.dishList = dishList;
	}

	
}
