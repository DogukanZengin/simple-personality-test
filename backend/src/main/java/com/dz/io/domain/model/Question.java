package com.dz.io.domain.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public final class Question {

    @Id
    @GeneratedValue
    private Long id;

    private String text;

    @OneToOne
    private Category category;

    @OneToMany(cascade = CascadeType.ALL)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    List<Option> options;

    Type type;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "question_range")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    Range range;
}
