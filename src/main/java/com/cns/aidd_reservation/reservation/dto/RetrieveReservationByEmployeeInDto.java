package com.cns.aidd_reservation.reservation.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class RetrieveReservationByEmployeeInDto {
	private int employeeId;
	private LocalDateTime startTime;
	private LocalDateTime endTime;

	@Builder
	public RetrieveReservationByEmployeeInDto(int employeeId, LocalDateTime startTime, LocalDateTime endTime) {
		super();
		this.employeeId = employeeId;
		this.startTime = startTime;
		this.endTime = endTime;
	}
}
