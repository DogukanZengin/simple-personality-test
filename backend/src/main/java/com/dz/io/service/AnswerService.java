package com.dz.io.service;

import com.dz.io.domain.dto.AnswersDto;
import com.dz.io.domain.repository.AnswerRepository;
import org.springframework.stereotype.Service;

@Service
public class AnswerService {

    private final AnswerRepository repository;

    public AnswerService(AnswerRepository repository) {
        this.repository = repository;
    }

    public void saveAnswers(AnswersDto dto){

    }
}
