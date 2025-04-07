package com.cns.aidd_reservation.reservation.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ExtendReservationTimeInDto {
	private int reservationId;
	private String extendTime;
	
	@Builder
	public ExtendReservationTimeInDto(int reservationId, String extendTime) {
		super();
		this.reservationId = reservationId;
		this.extendTime = extendTime;
	}
}
