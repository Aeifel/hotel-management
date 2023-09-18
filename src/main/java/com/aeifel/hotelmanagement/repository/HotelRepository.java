package com.aeifel.hotelmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aeifel.hotelmanagement.model.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, Integer> {

	Hotel findByName(String name);

}
