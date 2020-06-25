package com.dz.io.controller;

import com.dz.io.domain.dto.AnswersDto;
import com.dz.io.service.AnswerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/answers")
public class AnswerController {

    private final AnswerService service;

    public AnswerController(AnswerService service) {
        this.service = service;
    }

    @PostMapping
    ResponseEntity<String> saveAnswers(@RequestBody AnswersDto answersDto){
        service.saveAnswers(answersDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Answers Created");
    }
}
