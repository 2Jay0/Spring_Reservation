package com.cns.aidd_reservation.reservation.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RetrieveTotalTimeOutDto {
    private int totalTime;

    @Builder
    public RetrieveTotalTimeOutDto(int totalTime) {
        super();
        this.totalTime = totalTime;
    }
}
