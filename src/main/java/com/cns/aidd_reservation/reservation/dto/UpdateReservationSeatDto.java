package com.cns.aidd_reservation.reservation.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateReservationSeatDto {
	private int reservationId;
	private int seatId;
	private String endTime;
	
	public UpdateReservationSeatDto(int reservationId, int seatId, String endTime) {
		this.reservationId = reservationId;
		this.seatId = seatId;
		this.endTime = endTime;
	}

}
