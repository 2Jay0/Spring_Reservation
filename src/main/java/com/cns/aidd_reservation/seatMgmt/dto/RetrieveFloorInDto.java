package com.cns.aidd_reservation.seatMgmt.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RetrieveFloorInDto {
	private int buildingId;
	
	@Builder
	public RetrieveFloorInDto(int buildingId) {
		super();
		this.buildingId = buildingId;
	}
}
