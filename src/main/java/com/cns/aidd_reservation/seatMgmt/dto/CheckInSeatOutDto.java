package com.cns.aidd_reservation.seatMgmt.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CheckInSeatOutDto {
	private boolean successYn;
	
	@Builder
	public CheckInSeatOutDto(boolean successYn) {
		super();
		this.successYn = successYn;
	}

}
