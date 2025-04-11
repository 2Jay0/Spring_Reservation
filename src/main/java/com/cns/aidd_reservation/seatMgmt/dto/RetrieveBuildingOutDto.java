package com.cns.aidd_reservation.seatMgmt.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RetrieveBuildingOutDto {
	private int buildingId;
	private String buildingName;
	
	@Builder
	public RetrieveBuildingOutDto(int buildingId, String buildingName) {
		super();
		this.buildingId = buildingId;
		this.buildingName = buildingName;
	}
	
}
