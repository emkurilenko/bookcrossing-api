package com.bookcrossing.api.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Entity
@Table(name = "genre", schema = "bookcrossing_service")
public class Genre extends BaseNamedEntity<Long> {

//    @ManyToMany
//    @JoinTable(
//            name = "book_genre",
//            schema = "bookcrossing_service",
//            joinColumns = @JoinColumn(name = "genre_id"),
//            inverseJoinColumns = @JoinColumn(name = "book_id"))
//    private List<Book> books;

}
