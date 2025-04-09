package com.cns.aidd_reservation.seatMgmt.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.cns.aidd_reservation.seatMgmt.dto.RetrieveAvailableSeatsByDateInDto;
import com.cns.aidd_reservation.seatMgmt.dto.RetrieveAvailableSeatsByDateOutDto;
import com.cns.aidd_reservation.seatMgmt.dto.RetrieveSeatInDto;
import com.cns.aidd_reservation.seatMgmt.dto.RetrieveSeatOutDto;

@Mapper
public interface SeatMgmtRepository {
	public List<RetrieveAvailableSeatsByDateOutDto> retrieveAvailableSeatsByDate(RetrieveAvailableSeatsByDateInDto retrieveAvailableSeatsByDateInDto) throws Exception;
	public RetrieveSeatOutDto retrieveSeat(RetrieveSeatInDto retrieveSeatInDto) throws Exception;
}
