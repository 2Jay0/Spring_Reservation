package com.cns.aidd_reservation.admin.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ForceMoveSeatOutDto {
	private String status;
	
	public ForceMoveSeatOutDto(String status) {
		this.status = status;
	}
}