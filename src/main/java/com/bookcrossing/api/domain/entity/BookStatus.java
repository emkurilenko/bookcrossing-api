package com.bookcrossing.api.domain.entity;

public enum BookStatus {
    AVAILABLE, //доступная
//    ABSENT,
    RESERVED, //for user who create book
    CANCELED,
    BOOKED,
    TAKE_AWAY,
    TAKEN_AWAY, //for current user
    UNKNOWN
}
