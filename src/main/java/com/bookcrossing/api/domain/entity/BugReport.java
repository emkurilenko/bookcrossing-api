package com.bookcrossing.api.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import javax.persistence.Entity;
import javax.persistence.Table;


@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bug_report", schema = "bookcrossing_service")
public class BugReport extends BookCrossingBaseEntity<Long> {

    private ZonedDateTime createdDate;

    private String message;

    private String email;
}
