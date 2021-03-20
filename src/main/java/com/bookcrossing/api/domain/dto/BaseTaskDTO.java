package com.bookcrossing.api.domain.dto;

import com.bookcrossing.api.domain.entity.BaseTask;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class BaseTaskDTO extends BaseNamedEntityDTO<Long> {
    private BaseTask.Status status;

    private String description;
}
