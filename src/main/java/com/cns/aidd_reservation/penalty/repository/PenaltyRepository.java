package com.cns.aidd_reservation.penalty.repository;

import org.apache.ibatis.annotations.Mapper;

import com.cns.aidd_reservation.penalty.dto.RegisterPenaltyLogDto;

@Mapper
public interface PenaltyRepository {
	public int registerPenaltyLog(RegisterPenaltyLogDto registerPenaltyLogDto) throws Exception;
}
