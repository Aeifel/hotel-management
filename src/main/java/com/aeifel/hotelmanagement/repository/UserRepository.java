package com.aeifel.hotelmanagement.repository;

//import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aeifel.hotelmanagement.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	User findByEmail(String email);

	User findByUserId(Integer userId);

}
