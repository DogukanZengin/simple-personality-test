package com.dz.io.domain.model;

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
    List<Option> options;

    Type type;

    @OneToOne
    @JoinTable(name = "question_range")
    Range range;
}
