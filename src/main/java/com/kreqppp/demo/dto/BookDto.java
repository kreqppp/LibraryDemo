package com.kreqppp.demo.dto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@Getter
@Setter
public class BookDto {

    @NotNull
    private Long id;

    @NotNull
    private String title;

    private AuthorDto authorDto;

    private String genre;

    @JsonFormat(pattern = "yyyy")
    private Date year;
}
