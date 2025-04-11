package com.cns.aidd_reservation.reservation.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RetrieveTotalTimeInDto {
    private int employeeId;
    private String date;

    @Builder
    public RetrieveTotalTimeInDto(int employeeId, String date) {
        super();
        this.employeeId = employeeId;
        this.date = date;
    }
}
