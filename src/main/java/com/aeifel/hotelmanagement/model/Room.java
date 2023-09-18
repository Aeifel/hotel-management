package com.aeifel.hotelmanagement.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "room_t")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Room {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer roomNo;

	@OneToOne
	@JoinColumn(name = "hotelId")
	private Hotel hotel;
	
	private String roomType;
	private Double price;
	private Integer numberAvailable;
	private Integer startRoomNum;
	private String image;
}
