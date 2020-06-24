package com.dz.io.domain.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;

@Entity
public class Option {
    @Id
    @GeneratedValue
    private Long id;

    private String text;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "option_followup")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Question followUp;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Question getFollowUp() {
        return followUp;
    }

    public void setFollowUp(Question followUp) {
        this.followUp = followUp;
    }
}
