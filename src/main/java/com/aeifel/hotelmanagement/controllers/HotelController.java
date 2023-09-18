package com.aeifel.hotelmanagement.controllers;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aeifel.hotelmanagement.model.Hotel;
//import com.aeifel.hotelmanagement.validator.HotelValidator;
import com.aeifel.hotelmanagement.repository.HotelRepository;

@RestController
@RequestMapping("/hotel")
public class HotelController {
	@Autowired
	HotelRepository hotelRepository;
	
	@PostMapping("/create")
	public String createHotels(@RequestBody Hotel hotel) {
		Hotel hotel2 = hotelRepository.findByName(hotel.getName());
		if(hotel2!=null) return "Hotel Already Exists!";
		
		hotelRepository.save(hotel);
		return "Hotel Created Successfully with id: " + hotel.getHotelId();
	}
	
	@PostMapping("/search")
	public String fetchHotels(@RequestParam String query) {
		Hotel hotel2 = hotelRepository.findByName(query);
		if(hotel2!=null) return "Hotel Exists with id - " + hotel2.getHotelId();
		
		return "Hotel Does Not Exist !";
	}
	
	@PostMapping("/getDetails")
	public String getHotelDetails(@RequestBody Hotel hotel) {
		Hotel hotel2 = hotelRepository.findById(hotel.getHotelId()).orElse(null);
		if(hotel2==null) return "Hotel does not exist !!";
		
		return hotel2.toString();
	}

}
