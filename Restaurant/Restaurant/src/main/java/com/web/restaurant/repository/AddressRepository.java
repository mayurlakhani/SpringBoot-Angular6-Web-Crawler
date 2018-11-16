package com.web.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web.restaurant.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long>{

}
