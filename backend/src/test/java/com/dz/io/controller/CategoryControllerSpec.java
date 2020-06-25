package com.dz.io.controller;

import com.dz.io.domain.dto.CategoryDto;
import com.dz.io.domain.model.Category;
import com.dz.io.service.QuestionService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@WebMvcTest(CategoryController.class)
public class CategoryControllerSpec {

    @MockBean
    private QuestionService questionService;

    @MockBean
    private ModelMapper modelMapper;

    @Autowired
    private MockMvc mvc;

    @Before
    public void setUp(){

    }

    @Test
    public void whenRetrieveCategories_ThenSuccess() throws Exception {
        Category dto = new Category();
        dto.setDisplay("TEST CATEGORY");
        dto.setName("test_category");
        List<Category> list = Collections.singletonList(dto);
        given(questionService.retrieveCategories()).willReturn(list);

        MockHttpServletResponse response = mvc.perform(get("/categories")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        verify(questionService).retrieveCategories();
    }
}
