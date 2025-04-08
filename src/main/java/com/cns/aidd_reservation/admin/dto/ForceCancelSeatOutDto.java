package com.cns.aidd_reservation.admin.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ForceCancelSeatOutDto {
	private String status;
	private boolean successYn;
	
	@Builder
	public ForceCancelSeatOutDto(String status, boolean successYn) {
		super();
		this.status = status;
		this.successYn = successYn;
	}
}
