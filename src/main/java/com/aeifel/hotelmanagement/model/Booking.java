package com.aeifel.hotelmanagement.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "booking_t")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Booking {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer bookingId;

	@ManyToOne
	@JoinColumn(name = "userId")
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "hotelId")
	private Hotel hotel;
	
	private Double cost;
	private String roomType;
	private Integer numRoomsBooked;
	private Integer startRoomNum;
	private Date checkInDate;
	private Date checkOutDate;
}
