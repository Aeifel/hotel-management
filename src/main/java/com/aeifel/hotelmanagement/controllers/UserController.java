package com.aeifel.hotelmanagement.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aeifel.hotelmanagement.model.User;
import com.aeifel.hotelmanagement.repository.UserRepository;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserRepository userRepository;
	
	@PostMapping("/login")
	private String userLogin(@RequestBody User user) {
		User user2 = userRepository.findByEmail(user.getEmail());
		if(user2==null) return "No User Found";
		
		if(!user2.getPassword().equals(user.getPassword())) return "Wrong Password! Please Try Again..";
		
		return "User Login Successfull !";
	}
	
	@PostMapping("/signup")
	private String userSignUp(@RequestBody User user) {
		User u =  userRepository.findByEmail(user.getEmail());
		if(u!=null) return "User Already Exists";
		
		userRepository.save(user);
		return "User Saved Successfully with user id : " + user.getUserId();
	}
	
	@PostMapping("/getDetails")
	private String getUserDetails(@RequestBody User user) {
		User u =  userRepository.findByUserId(user.getUserId());
		if(u==null) return "User Does not exist!";
		
		return u.toString();
	}

}
