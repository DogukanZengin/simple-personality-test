package com.dz.io.domain.repository;

import com.dz.io.domain.model.Question;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface QuestionRepository extends CrudRepository<Question, Long> {

    List<Question> findAll();
}
