package com.dz.io.domain.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Range {

    @Id
    @GeneratedValue
    private Long id;

    String min;
    String max;
}
