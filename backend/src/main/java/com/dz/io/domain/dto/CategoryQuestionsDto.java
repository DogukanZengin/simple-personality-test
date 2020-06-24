package com.dz.io.domain.dto;

import com.dz.io.domain.model.Question;

import java.util.List;


public class CategoryQuestionsDto {
    private CategoryDto categoryDto;
    private List<Question> questions;

    public CategoryDto getCategoryDto() {
        return categoryDto;
    }

    public void setCategoryDto(CategoryDto categoryDto) {
        this.categoryDto = categoryDto;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
