package com.bookcrossing.api.domain.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "book", schema = "bookcrossing_service")
public class Book extends BaseNamedEntity<Long> {

    private ZonedDateTime updateAt;

    private String code;

    @ManyToMany
    @JoinTable(
            name = "book_genre",
            schema = "bookcrossing_service",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private List<Genre> genres;

    @ManyToMany
    @JoinTable(
            name = "book_author",
            schema = "bookcrossing_service",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    private List<Author> authors;

    @OneToMany
    @JoinTable(
            name = "book_file",
            schema = "bookcrossing_service",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "file_id"))
    private List<File> pictures = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.ALL)
    private Location location;

    @OneToMany(cascade = {CascadeType.REMOVE}, mappedBy = "book")
//    @JoinTable(
//            name = "book_history",
//            schema = "bookcrossing_service",
//            joinColumns = @JoinColumn(name = "book_id"),
//            inverseJoinColumns = @JoinColumn(name = "id"))
    private List<BookHistory> bookHistories;

}
