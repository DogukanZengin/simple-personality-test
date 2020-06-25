package com.dz.io.service;

import com.dz.io.domain.dto.AnswerDto;
import com.dz.io.domain.dto.AnswersDto;
import com.dz.io.domain.model.Answer;
import com.dz.io.domain.model.User;
import com.dz.io.domain.repository.AnswerRepository;
import com.dz.io.domain.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class AnswerService {

    private final AnswerRepository repository;
    private final UserRepository userRepository;

    public AnswerService(AnswerRepository repository,
                         UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    public void saveAnswers(AnswersDto dto){
        User user = new User();
        user.setEmail(dto.getUserName());
        user = userRepository.save(user);

        User finalUser = user;
        dto.getAnswers().forEach(answerDto -> createAnswer(answerDto, finalUser));
    }

    private void createAnswer(AnswerDto answerDto, User user){
        Answer answer = new Answer();
        answer.setUser(user);
        answer.setQuestion(answerDto.getQuestion());
        answer.setSelected(answerDto.getAnswer());
        answer.setRangeValue(answerDto.getRange());
        repository.save(answer);
    }
}
