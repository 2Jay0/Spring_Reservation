package com.cns.aidd_reservation.reservation.dto;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RetrieveSeatAvailableOutDto {
	private int id;
	private int employeeId;
	private int seatId;
	private Date date;
	private String startTime;
	private String endTime;
	private String status;
	private String checkInAt;
	private String createdAt;
	private int extendedFromReservationId;
	
	public RetrieveSeatAvailableOutDto(int id, int seatId, Date date, String startTime, String endTime
			, String status, String checkInAt, String createdAt, int extendedFromReservationId) {
		this.id = id;
		this.seatId = seatId;
		this.date = date;
		this.startTime = startTime;
		this.endTime = endTime;
		this.status = status;
		this.checkInAt = checkInAt;
		this.createdAt = createdAt;
		this.extendedFromReservationId = extendedFromReservationId;
	}
}
