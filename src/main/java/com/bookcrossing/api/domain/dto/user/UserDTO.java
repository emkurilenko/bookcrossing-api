package com.bookcrossing.api.domain.dto.user;

import com.bookcrossing.api.domain.dto.BaseDTO;
import com.bookcrossing.api.domain.dto.BaseEntityDTO;
import com.bookcrossing.api.domain.entity.Gender;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
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
    @JsonIgnore
    private String role = "USER";
    private LocalDate dateOfBirth;
    private Gender gender;
}
