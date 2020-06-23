package com.dz.io.domain.repository;

import com.dz.io.domain.model.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CategoryRepository extends CrudRepository<Category, Long> {

    Category findByName(String name);

    List<Category> findAll();
}
