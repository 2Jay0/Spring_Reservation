package com.cns.aidd_reservation.seatMgmt.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RetrieveAvailableSeatOutDto {
	private String buildingName;
	private String floor;
	private String seatName;
	private int seatId;
	
	@Builder
	public RetrieveAvailableSeatOutDto(String buildingName, String floor, String seatName, int seatId) {
		super();
		this.buildingName = buildingName;
		this.floor = floor;
		this.seatName = seatName;
		this.seatId = seatId;
	}
}
