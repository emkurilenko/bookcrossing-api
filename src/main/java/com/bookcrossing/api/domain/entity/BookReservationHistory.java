package com.bookcrossing.api.domain.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Entity
@Table(name = "book_reservation_history", schema = "bookcrossing_service")
public class BookReservationHistory extends BookCrossingBaseEntity<Long> {

    private ZonedDateTime createdDate;

    private ZonedDateTime expiredAt;

    @OneToOne
    private User user;

    @OneToOne
    private Book book;

    @Enumerated(EnumType.STRING)
    private BookStatus status;

}
