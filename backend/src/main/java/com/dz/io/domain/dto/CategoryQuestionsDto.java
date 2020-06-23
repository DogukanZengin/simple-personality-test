package com.dz.io.domain.dto;

import com.dz.io.domain.model.Question;
import lombok.Data;

import java.util.List;

@Data
public class CategoryQuestionsDto {
    private final CategoryDto categoryDto;
    private final List<Question> questions;
}
