package com.dz.io.domain.dto;

import com.dz.io.domain.model.Option;
import com.dz.io.domain.model.Question;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AnswerDto {
    @JsonProperty("question")
    Question question;
    @JsonProperty("answer")
    Option answer;

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Option getAnswer() {
        return answer;
    }

    public void setAnswer(Option answer) {
        this.answer = answer;
    }
}
