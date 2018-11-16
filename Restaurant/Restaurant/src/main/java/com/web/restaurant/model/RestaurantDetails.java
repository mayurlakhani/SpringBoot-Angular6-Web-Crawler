package com.web.restaurant.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "RestaurantDetails")
public class RestaurantDetails extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String restaurantName;

    @Column
    private String homeUrl;

    @Column
    private String teleNo;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    @JsonBackReference
    private Address address;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHomeUrl() {
        return homeUrl;
    }

    public void setHomeUrl(String homeUrl) {
        this.homeUrl = homeUrl;
    }

    public Address getAddress() {
        return address;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getTeleNo() {
        return teleNo;
    }

    public void setTeleNo(String teleNo) {
        this.teleNo = teleNo;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

}
