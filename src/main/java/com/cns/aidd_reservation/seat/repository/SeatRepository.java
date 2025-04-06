package com.cns.aidd_reservation.seat.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.cns.aidd_reservation.seat.dto.RetrieveAvailableSeatsByDateInDto;
import com.cns.aidd_reservation.seat.dto.RetrieveAvailableSeatsByDateOutDto;
import com.cns.aidd_reservation.seat.dto.RetrieveSeatInDto;
import com.cns.aidd_reservation.seat.dto.RetrieveSeatOutDto;

@Mapper
public interface SeatRepository {
	public List<RetrieveAvailableSeatsByDateOutDto> retrieveAvailableSeatsByDate(RetrieveAvailableSeatsByDateInDto retrieveAvailableSeatsByDateInDto) throws Exception;
	public RetrieveSeatOutDto retrieveSeat(RetrieveSeatInDto retrieveSeatInDto) throws Exception;
}
