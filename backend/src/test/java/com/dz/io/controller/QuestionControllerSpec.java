package com.dz.io.controller;

import com.dz.io.service.QuestionService;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(QuestionController.class)
public class QuestionControllerSpec {
    @MockBean
    private QuestionService questionService;

    @MockBean
    private ModelMapper modelMapper;

    @Autowired
    private MockMvc mvc;

}
