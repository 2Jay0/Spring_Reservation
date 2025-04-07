package com.cns.aidd_reservation.reservation.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RetrieveRemainSeatTimeOutDto {
	private long remainTime;
	
	@Builder
	public RetrieveRemainSeatTimeOutDto(long remainTime) {
		super();
		this.remainTime = remainTime;
	}
}
