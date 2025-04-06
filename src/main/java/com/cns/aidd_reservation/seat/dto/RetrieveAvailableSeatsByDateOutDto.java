package com.cns.aidd_reservation.seat.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RetrieveAvailableSeatsByDateOutDto {
	private String buildingName;
	private String floor;
	private String seatName;
	
	public RetrieveAvailableSeatsByDateOutDto(String buildingName, String floor, String seatName) {
		this.buildingName = buildingName;
		this.floor = floor;
		this.seatName = seatName;
	}
}
