package com.web.restaurant.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;

@Entity
@Table(name = "Address")
public class Address extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column
	private String strNo;

	@Column
	private String strName;

	@Column
	private String zipCode;

	@Column
	private String city;
	
    @OneToOne(mappedBy = "address", fetch = FetchType.LAZY)
	@JsonManagedReference
	private RestaurantDetails restaurant;

	public String getStrNo() {
		return strNo;
	}

	public void setStrNo(String strNo) {
		this.strNo = strNo;
	}

	public String getStrName() {
		return strName;
	}

	public void setStrName(String strName) {
		this.strName = strName;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public RestaurantDetails getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(RestaurantDetails restaurant) {
		this.restaurant = restaurant;
	}

}