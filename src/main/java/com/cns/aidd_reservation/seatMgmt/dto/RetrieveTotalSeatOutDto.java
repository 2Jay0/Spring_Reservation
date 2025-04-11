package com.cns.aidd_reservation.seatMgmt.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RetrieveTotalSeatOutDto {
    private int seatId;
    private String seatName;
    private String status;

    @Builder
    public RetrieveTotalSeatOutDto(int seatId, String seatName, String status) {
        super();
        this.seatId = seatId;
        this.seatName = seatName;
        this.status = status;
    }
}
