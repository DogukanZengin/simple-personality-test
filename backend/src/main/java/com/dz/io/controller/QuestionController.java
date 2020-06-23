package com.dz.io.controller;

import com.dz.io.domain.dto.CategoryQuestionsDto;
import com.dz.io.domain.model.Question;
import com.dz.io.service.QuestionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/questions")
public class QuestionController {

    private final QuestionService service;

    public QuestionController(QuestionService service) {
        this.service = service;
    }

    @GetMapping
    ResponseEntity<List<Question>> retrieveQuestions(){
        return ResponseEntity.status(HttpStatus.OK).body(service.retrieve());
    }

    @GetMapping(path = "/category/{categoryId}")
    ResponseEntity<List<CategoryQuestionsDto>> retrieveQuestionsByCategory(final @PathVariable("categoryId") Long categoryId){
        return null;
    }

}
