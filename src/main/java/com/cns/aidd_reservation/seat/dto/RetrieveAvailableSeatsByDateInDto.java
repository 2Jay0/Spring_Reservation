package com.cns.aidd_reservation.seat.dto;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RetrieveAvailableSeatsByDateInDto {
	private Date date;
	private String startTime;
	private String endTime;
	
	public RetrieveAvailableSeatsByDateInDto(Date date, String startTime, String endTime) {
		this.date = date;
		this.startTime = startTime;
		this.endTime = endTime;
	}
}