package com.dz.io.domain;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Data
@Entity
public final class Category {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    private Category category;
}
