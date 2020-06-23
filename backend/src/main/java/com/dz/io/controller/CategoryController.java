package com.dz.io.controller;

import com.dz.io.domain.model.Category;
import com.dz.io.service.QuestionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final QuestionService service;

    public CategoryController(QuestionService service) {
        this.service = service;
    }

    @GetMapping
    ResponseEntity<List<Category>> retrieveCategories(){
        return ResponseEntity.status(HttpStatus.OK).body(service.retrieveCategories());
    }
}
