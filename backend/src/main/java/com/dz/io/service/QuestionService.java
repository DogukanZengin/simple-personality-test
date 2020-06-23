package com.dz.io.service;

import com.dz.io.domain.model.Category;
import com.dz.io.domain.model.Question;
import com.dz.io.domain.repository.CategoryRepository;
import com.dz.io.domain.repository.QuestionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    private final QuestionRepository repository;
    private final CategoryRepository categoryRepository;

    public QuestionService(QuestionRepository repository,
                           CategoryRepository categoryRepository) {
        this.repository = repository;
        this.categoryRepository = categoryRepository;
    }

    public List<Question> retrieve(){
        return repository.findAll();
    }

    public List<Category> retrieveCategories(){
        return categoryRepository.findAll();
    }
}
