package com.dz.io.service;

import com.dz.io.domain.model.Question;
import com.dz.io.domain.repository.QuestionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    private final QuestionRepository repository;

    public QuestionService(QuestionRepository repository) {
        this.repository = repository;
    }

    public List<Question> retrieve(){
        return repository.findAll();
    }
}
