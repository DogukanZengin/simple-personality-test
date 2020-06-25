package com.dz.io.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class AnswersDto {

    @JsonProperty("username")
    String userName;
    @JsonProperty("answers")
    List<AnswerDto> answers;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<AnswerDto> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswerDto> answers) {
        this.answers = answers;
    }
}
