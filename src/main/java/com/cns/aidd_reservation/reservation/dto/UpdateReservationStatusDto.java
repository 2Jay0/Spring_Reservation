package com.cns.aidd_reservation.reservation.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateReservationStatusDto {
	private int reservationId;
	private String status;
	
	@Builder
	public UpdateReservationStatusDto(int reservationId, String status) {
		super();
		this.reservationId = reservationId;
		this.status = status;
	}

}
