package com.cns.aidd_reservation.penalty.repository;

import org.apache.ibatis.annotations.Mapper;

import com.cns.aidd_reservation.penalty.dto.InsertPenaltyLogDto;

@Mapper
public interface PenaltyRepository {
	public int insertPenaltyLog(InsertPenaltyLogDto registerPenaltyLogDto) throws Exception;
}
