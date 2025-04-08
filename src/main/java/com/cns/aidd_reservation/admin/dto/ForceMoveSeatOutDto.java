package com.cns.aidd_reservation.admin.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ForceMoveSeatOutDto {
	private String status;
	
	@Builder
	public ForceMoveSeatOutDto(String status) {
		super();
		this.status = status;
	}
}