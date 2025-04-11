package com.cns.aidd_reservation.seatMgmt.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RetrieveFloorOutDto {
	private int floor;
	
	@Builder
	public RetrieveFloorOutDto(int floor) {
		super();
		this.floor = floor;
	}
}
