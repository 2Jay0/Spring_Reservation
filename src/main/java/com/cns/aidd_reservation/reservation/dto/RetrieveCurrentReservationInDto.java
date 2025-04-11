package com.cns.aidd_reservation.reservation.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RetrieveCurrentReservationInDto {
    private int employeeId;

    public RetrieveCurrentReservationInDto(int employeeId) {
        this.employeeId = employeeId;
    }
}
