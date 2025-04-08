package com.cns.aidd_reservation.penalty.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class InsertPenaltyLogDto {
	private int reservationId;
	private int employeeId;
	private String reason;
	private LocalDateTime givenAt;

	@Builder
	public InsertPenaltyLogDto(int reservationId, int employeeId, String reason, LocalDateTime givenAt) {
		super();
		this.reservationId = reservationId;
		this.employeeId = employeeId;
		this.reason = reason;
		this.givenAt = givenAt;
	}
}
