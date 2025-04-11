package com.cns.aidd_reservation.seatMgmt.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RetrieveTotalSeatInDto {
    private int buildingId;
    private int floor;

    @Builder
    public RetrieveTotalSeatInDto(int buildingId, int floor) {
        super();
        this.buildingId = buildingId;
        this.floor = floor;
    }
}
