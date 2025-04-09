package com.cns.aidd_reservation.reservation.domain;

public enum ReservationStatus {
	PENDING("PENDING", "승인대기"),
    RESERVED("RESERVED", "예약확정"),
    IN_USE("IN_USE", "체크인완료"),
	CANCELED("CANCELED","취소"),
	NO_SHOW("NO_SHOW", "체크인안함"),
    COMPLETED("COMPLETED", "완료"),
    FORCED_CANCEL("FORCED_CANCEL", "강제반납"),
	FAIL("FAIL","실패");

    private final String code;
    private final String value;

    ReservationStatus(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public String getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }
}
