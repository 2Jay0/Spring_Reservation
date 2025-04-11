package com.cns.aidd_reservation.seatMgmt.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.cns.aidd_reservation.seatMgmt.dto.RetrieveAvailableSeatsByDateInDto;
import com.cns.aidd_reservation.seatMgmt.dto.RetrieveAvailableSeatsByDateOutDto;
import com.cns.aidd_reservation.seatMgmt.dto.RetrieveBuildingInDto;
import com.cns.aidd_reservation.seatMgmt.dto.RetrieveBuildingOutDto;
import com.cns.aidd_reservation.seatMgmt.dto.RetrieveFloorInDto;
import com.cns.aidd_reservation.seatMgmt.dto.RetrieveFloorOutDto;
import com.cns.aidd_reservation.seatMgmt.dto.RetrieveTotalSeatInDto;
import com.cns.aidd_reservation.seatMgmt.dto.RetrieveTotalSeatOutDto;

@Mapper
public interface SeatMgmtRepository {
	public List<RetrieveAvailableSeatsByDateOutDto> retrieveAvailableSeatsByDate(RetrieveAvailableSeatsByDateInDto retrieveAvailableSeatsByDateInDto) throws Exception;
	public List<RetrieveTotalSeatOutDto> retrieveTotalSeat(RetrieveTotalSeatInDto retrieveTotalSeatInDto) throws Exception;
	public List<RetrieveBuildingOutDto> retrieveBuilding(RetrieveBuildingInDto retrieveBuildingInDto) throws Exception;
	public List<RetrieveFloorOutDto> retrieveFloor(RetrieveFloorInDto retrieveFloorInDto) throws Exception;
}
