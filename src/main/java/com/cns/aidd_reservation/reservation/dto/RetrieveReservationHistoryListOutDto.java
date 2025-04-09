package com.cns.aidd_reservation.reservation.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RetrieveReservationHistoryListOutDto {
	private String seatName;
	private String buildingName;
	private int floor;
	private int seatId;
	private String date;
	private String startTime;
	private String endTime;
	private String status;
	private int reservationId;
	
	@Builder
	public RetrieveReservationHistoryListOutDto(String seatName, String buildingName, int floor,
			int seatId, String date, String startTime, String endTime, String status, int reservationId) {
		super();
		this.seatName = seatName;
		this.buildingName = buildingName;
		this.floor = floor;
		this.seatId = seatId;
		this.date = date;
		this.startTime = startTime;
		this.endTime = endTime;
		this.status = status;
		this.reservationId = reservationId;
	}
}
