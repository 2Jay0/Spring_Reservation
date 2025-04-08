package com.cns.aidd_reservation.reservation.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateReservationSeatDto {
	private int reservationId;
	private int seatId;
	private LocalDateTime endTime;
	
	@Builder
	public UpdateReservationSeatDto(int reservationId, int seatId, LocalDateTime endTime) {
		super();
		this.reservationId = reservationId;
		this.seatId = seatId;
		this.endTime = endTime;
	}

}
