package com.cns.aidd_reservation.reservation.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RetrieveCurrentReservationOutDto {
    private String buildingName;
    private int floor;
    private int seatId;
    private String seatName;
    private String startDate;
    private String endDate;
    private int remainTime;

    @Builder
    public RetrieveCurrentReservationOutDto(String buildingName, int floor, int seatId, String seatName, String startDate, String endDate, int remainTime) {
        super();
        this.buildingName = buildingName;
        this.floor = floor;
        this.seatId = seatId;
        this.seatName = seatName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.remainTime = remainTime;
    }
}
