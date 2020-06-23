package com.dz.io.domain.repository;

import com.dz.io.domain.model.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {

    Category findByName(String name);
}
