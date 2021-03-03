package com.bookcrossing.api.domain.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@MappedSuperclass
public class BaseNamedEntity<ID extends Serializable> extends BookCrossingBaseEntity<ID> {

    @Column(name = "name", nullable = false)
    private String name;
}
