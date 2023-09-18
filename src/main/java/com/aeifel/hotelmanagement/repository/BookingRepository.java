package com.aeifel.hotelmanagement.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aeifel.hotelmanagement.model.Booking;

public interface BookingRepository extends JpaRepository<Booking, Integer> {

	List<Booking> findBookingsInDateInterval(Integer hotelId, Date formattedCheckIn, Date formattedCheckOut);

}
