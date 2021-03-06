package com.dz.io.domain.dto;

import com.dz.io.domain.model.Option;
import com.dz.io.domain.model.Question;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AnswerDto {
    @JsonProperty("question")
    Question question;
    @JsonProperty("answer")
    Option answer;
    @JsonProperty("range")
    String range;

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

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }
}
