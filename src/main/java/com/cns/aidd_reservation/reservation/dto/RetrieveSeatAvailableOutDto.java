package com.cns.aidd_reservation.reservation.dto;

import java.time.LocalDateTime;
import java.util.Date;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RetrieveSeatAvailableOutDto {
	private int id;
	private int employeeId;
	private int seatId;
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	private String status;
	private LocalDateTime checkInAt;
	private LocalDateTime createdAt;
	private int extendedFromReservationId;
	
	@Builder
	public RetrieveSeatAvailableOutDto(int id, int seatId, LocalDateTime startTime, LocalDateTime endTime
			, String status, LocalDateTime checkInAt, LocalDateTime createdAt, int extendedFromReservationId) {
		super();
		this.id = id;
		this.seatId = seatId;
		this.startTime = startTime;
		this.endTime = endTime;
		this.status = status;
		this.checkInAt = checkInAt;
		this.createdAt = createdAt;
		this.extendedFromReservationId = extendedFromReservationId;
	}
}
