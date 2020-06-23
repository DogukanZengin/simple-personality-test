package com.dz.io.domain.model;

import com.fasterxml.jackson.annotation.JsonInclude;
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
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Question followUp;
}
