package com.bookcrossing.api.domain.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Entity
@Table(name = "user", schema = "bookcrossing_service")
public class User extends BookCrossingBaseEntity<Long> {

    private String login;

    private String password;

    private String email;

    private String firstName;

    private String lastName;

    private String hobbies;

    private LocalDate dateOfBirth;

    private Gender gender;

    @OneToOne
    @JoinColumn(name = "photo_id", referencedColumnName = "id")
    private File photo;
}
