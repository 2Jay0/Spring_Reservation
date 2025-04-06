package com.cns.aidd_reservation.admin.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ForceCancelSeatOutDto {
	private String status;
	private boolean successYn;
	
	public ForceCancelSeatOutDto(String status, boolean successYn) {
		this.status = status;
		this.successYn = successYn;
	}
}
