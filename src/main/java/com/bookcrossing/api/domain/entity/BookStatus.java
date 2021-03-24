package com.bookcrossing.api.domain.entity;

public enum BookStatus {
    AVAILABLE,
    ABSENT,
    RESERVED, //for user who create book
    CANCELED,
    BOOKED,
    TAKE_AWAY,
    TAKEN_AWAY
}
