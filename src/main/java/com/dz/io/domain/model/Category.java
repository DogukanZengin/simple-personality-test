package com.dz.io.domain.model;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public final class Category {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String display;
}
