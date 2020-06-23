package com.dz.io.domain.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@Entity
public final class QuestionType {
    @Id
    @GeneratedValue
    private Long id;

    private Type type;

    @OneToMany
    List<Option> options;
}
