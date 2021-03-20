package com.bookcrossing.api.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "base_task", schema = "bookcrossing_service")
public class BaseTask extends BaseNamedEntity<Long> {

    @Enumerated(EnumType.STRING)
    private Status status;

    private String description;

    public enum Status {
        RUNNING,
        FINISHED,
        FAILED
    }
}
