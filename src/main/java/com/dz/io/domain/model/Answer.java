package com.dz.io.domain.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Data
@Entity
public final class Answer {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    private User user;

    @OneToOne
    private Question question;

    @OneToOne
    private Option selected;
}
