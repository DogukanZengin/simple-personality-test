package com.dz.io.domain.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Data
@Entity
public final class Question {

    @Id
    @GeneratedValue
    private Long id;

    private String text;

    @OneToOne
    private Category category;

    @OneToOne
    private QuestionType questionType;
}
