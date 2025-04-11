package com.cns.aidd_reservation.seatMgmt.repository;

import java.util.List;

import com.cns.aidd_reservation.seatMgmt.dto.*;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SeatMgmtRepository {
	public List<RetrieveAvailableSeatsByDateOutDto> retrieveAvailableSeatsByDate(RetrieveAvailableSeatsByDateInDto retrieveAvailableSeatsByDateInDto) throws Exception;
	public List<RetrieveTotalSeatOutDto> retrieveTotalSeat(RetrieveTotalSeatInDto retrieveTotalSeatInDto) throws Exception;
	public List<RetrieveBuildingOutDto> retrieveBuilding(RetrieveBuildingInDto retrieveBuildingInDto) throws Exception;
	public List<RetrieveFloorOutDto> retrieveFloor(RetrieveFloorInDto retrieveFloorInDto) throws Exception;
}
