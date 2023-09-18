package com.aeifel.hotelmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aeifel.hotelmanagement.model.Room;

public interface RoomRepository extends JpaRepository<Room, Integer> {

	Room findRoomByHotelIdAndRoomType(Integer hotelId, String roomType);

}
