package com.bookcrossing.api.domain.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Data
@NoArgsConstructor
public class UserDTO extends BaseEntityDTO<Long> implements BaseDTO<Long> {
    private String login;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private String hobbies;
    private UUID photo;
}
