package com.dz.io.parse;

import com.dz.io.domain.model.Category;
import com.dz.io.domain.repository.CategoryRepository;
import com.dz.io.domain.repository.QuestionRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
public class JsonToEntityParserSpec {

    @Mock
    private CategoryRepository categoryRepository;
    @Mock
    private QuestionRepository questionRepository;
    private JsonToEntityParser parser;
    private ObjectMapper mapper;


    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        parser = new JsonToEntityParser(categoryRepository, questionRepository);
        mapper = new ObjectMapper();
    }

    @Test
    public void whenReadFile_ThenJsonNodeReturned() throws IOException {
        JsonNode json = parser.read("personality-test.json");
        assertThat(json).isNotNull();
    }

    @Test
    public void whenCreateCategory_ThenEntitiesSaved() throws IOException {
        JsonNode categories = mapper.readTree("[\"hard_fact\"]");
        parser.createCategories(categories);
        Category category = new Category();
        category.setName("hard_fact");
        category.setDisplay("hard_fact".toUpperCase().replaceAll("_"," "));
        verify(categoryRepository).save(category);
    }
}
