package com.cns.aidd_reservation.penalty.dto;

import com.cns.aidd_reservation.admin.dto.ForceMoveSeatOutDto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RegisterPenaltyLogDto {
	private int reservationId;
	private int employeeId;
	private String reason;
	private String givenAt;
	
	public RegisterPenaltyLogDto(int reservationId, int employeeId, String reason, String givenAt) {
		this.reservationId = reservationId;
		this.employeeId = employeeId;
		this.reason = reason;
		this.givenAt = givenAt;
	}
}
