package com.aeifel.hotelmanagement.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aeifel.hotelmanagement.model.Booking;
import com.aeifel.hotelmanagement.model.Room;
import com.aeifel.hotelmanagement.repository.BookingRepository;
import com.aeifel.hotelmanagement.repository.RoomRepository;

@RestController
@RequestMapping("/booking")
public class BookingController {
	@Autowired
	BookingRepository bookingRepository;
	@Autowired
	RoomRepository roomRepository;

	@PostMapping("/checkAvailability")
	public String checkAvailability(@RequestBody Booking request) {
		try {
            Date formattedCheckIn = request.getCheckInDate();
            Date formattedCheckOut = request.getCheckOutDate();

            List<Booking> numberBookedInInterval = bookingRepository.findBookingsInDateInterval(
                    request.getHotel().getHotelId(), formattedCheckIn, formattedCheckOut);

            Room room = roomRepository.findRoomByHotelIdAndRoomType(request.getHotel().getHotelId(), request.getRoomType());
            int totalRooms = room.getNumberAvailable();
            int booked = 0;
            for (Booking booking : numberBookedInInterval) {
                booked += booking.getNumRoomsBooked();
            }
            int leftUnbooked = totalRooms - booked;
            
            return String.valueOf(leftUnbooked);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Internal server error. Please try again.");
        }
	}
	
	@PostMapping("/book")
	public Booking bookRoom(@RequestBody Booking request) {
		try {
			
            Date formattedCheckIn = request.getCheckInDate();
            Date formattedCheckOut = request.getCheckOutDate();

            List<Booking> numberBookedInInterval = bookingRepository.findBookingsInDateInterval(
                    request.getHotel().getHotelId(), formattedCheckIn, formattedCheckOut);

            Room room = roomRepository.findRoomByHotelIdAndRoomType(request.getHotel().getHotelId(), request.getRoomType());
            double price = room.getPrice();

            int differenceInDays = (int) ((formattedCheckOut.getTime() - formattedCheckIn.getTime()) / (1000 * 60 * 60 * 24));
            int totalCost = differenceInDays * (int)price * request.getNumRoomsBooked();

            int booked = 0;
            for (Booking booking : numberBookedInInterval) {
                booked += booking.getNumRoomsBooked();
            }
            int startRoomNum = room.getStartRoomNum() + booked;

            Booking newBooking = new Booking();
            newBooking.getUser().setUserId(request.getUser().getUserId());
            newBooking.getHotel().setHotelId(request.getHotel().getHotelId());
            newBooking.setCost((double)totalCost);
            newBooking.setRoomType(request.getRoomType());
            newBooking.setNumRoomsBooked(request.getNumRoomsBooked());
            newBooking.setStartRoomNum(startRoomNum);
            newBooking.setCheckInDate(request.getCheckInDate());
            newBooking.setCheckOutDate(request.getCheckOutDate());

            bookingRepository.save(newBooking);

            return newBooking;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Internal server error. Please try again.");
        }
	}
}
