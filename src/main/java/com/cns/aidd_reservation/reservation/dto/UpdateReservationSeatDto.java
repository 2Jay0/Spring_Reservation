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
	private int extendedId;
	
	@Builder
	public UpdateReservationSeatDto(int reservationId, int seatId, LocalDateTime endTime, int extendedId) {
		super();
		this.reservationId = reservationId;
		this.seatId = seatId;
		this.endTime = endTime;
		this.extendedId = extendedId;
	}

}
