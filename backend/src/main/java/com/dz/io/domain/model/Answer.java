package com.dz.io.domain.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public final class Answer {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    private User user;

    @OneToOne
    private Question question;

    @OneToOne
    private Option selected;

    private String rangeValue;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Option getSelected() {
        return selected;
    }

    public void setSelected(Option selected) {
        this.selected = selected;
    }

    public String getRangeValue() {
        return rangeValue;
    }

    public void setRangeValue(String rangeValue) {
        this.rangeValue = rangeValue;
    }
}
