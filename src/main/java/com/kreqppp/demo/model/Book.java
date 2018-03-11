package com.kreqppp.demo.model;

import lombok.AllArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="book")
@AllArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "title")
    private String title;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "author_id")
    private Author author;

    @Column(name = "genre")
    private String genre;

    @Column(name = "year")
    private Date year;
}
