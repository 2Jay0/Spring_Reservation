package com.cns.aidd_reservation.reservation.repository;

import org.apache.ibatis.annotations.Mapper;

import com.cns.aidd_reservation.reservation.dto.RetrieveReservationInDto;
import com.cns.aidd_reservation.reservation.dto.RetrieveReservationOutDto;
import com.cns.aidd_reservation.reservation.dto.RetrieveSeatAvailableInDto;
import com.cns.aidd_reservation.reservation.dto.RetrieveSeatAvailableOutDto;
import com.cns.aidd_reservation.reservation.dto.UpdateReservationSeatDto;
import com.cns.aidd_reservation.reservation.dto.UpdateReservationStatusDto;
import com.cns.aidd_reservation.reservation.dto.UpdateReservationTimeDto;

@Mapper
public interface ReservationRepository {
	public RetrieveReservationOutDto retrieveReservation(RetrieveReservationInDto retrieveReservationInDto) throws Exception;
	public RetrieveSeatAvailableOutDto retrieveSeatAvailable(RetrieveSeatAvailableInDto retrieveSeatAvailableInDto) throws Exception;
	public int updateReservationStatus(UpdateReservationStatusDto dto) throws Exception;
	public int updateReservationSeat(UpdateReservationSeatDto updateReservationSeatDto) throws Exception;
	public int updateReservationTime(UpdateReservationTimeDto updateReservationTimeDto) throws Exception;

}
