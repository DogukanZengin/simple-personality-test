package com.dz.io.domain.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Option {
    @Id
    @GeneratedValue
    private Long id;

    private String text;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "option_followup")
    private Question followUp;
}
