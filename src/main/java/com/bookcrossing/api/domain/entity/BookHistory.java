package com.bookcrossing.api.domain.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Entity
@Table(name = "book_history", schema = "bookcrossing_service")
public class BookHistory extends BookCrossingBaseEntity<Long> {

    private ZonedDateTime creationDate;

    @OneToOne
    private User user;

    @OneToOne
    private Book book;

    @Enumerated(EnumType.STRING)
    private BookStatus status;

}
