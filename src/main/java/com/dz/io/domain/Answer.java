package com.dz.io.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public final class Answer {

    @Id
    @GeneratedValue
    private Long id;
}
