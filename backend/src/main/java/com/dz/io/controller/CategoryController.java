package com.dz.io.controller;

import com.dz.io.domain.dto.CategoryDto;
import com.dz.io.domain.model.Category;
import com.dz.io.service.QuestionService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final QuestionService service;
    private final ModelMapper modelMapper;

    public CategoryController(QuestionService service, ModelMapper modelMapper) {
        this.service = service;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    ResponseEntity<List<CategoryDto>> retrieveCategories(){
        List<CategoryDto> categories = service.retrieveCategories().stream().map(this::convertToDto).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(categories);
    }

    private CategoryDto convertToDto(Category category) {
        return modelMapper.map(category, CategoryDto.class);
    }
}
