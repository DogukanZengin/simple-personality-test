package com.dz.io.parse;

import com.dz.io.domain.model.*;
import com.dz.io.domain.repository.CategoryRepository;
import com.dz.io.domain.repository.QuestionRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

/**
 * Component for reading json data and persisting to Database
 * It runs with the application start up and fills the Database with Entities
 */
@Component
public final class JsonToEntityParser {

    private final CategoryRepository categoryRepository;
    private final QuestionRepository questionRepository;

    public JsonToEntityParser(CategoryRepository categoryRepository,
                              QuestionRepository questionRepository) {
        this.categoryRepository = categoryRepository;
        this.questionRepository = questionRepository;
    }

    @PostConstruct
    public void init() throws IOException {
        map(read("personality-test.json"));
    }

    public JsonNode read(final String uri) throws IOException{
        File resource = new ClassPathResource(uri).getFile();
        String data = new String(Files.readAllBytes(resource.toPath()));
        return new ObjectMapper().readTree(data);
    }

    private void map(final JsonNode json){
        createCategories(json.get("categories"));
        createQuestions(json.get("questions"));
    }

    public void createCategories(final JsonNode categories){
        categories.elements().forEachRemaining(jsonNode ->{
            Category category = new Category();
            category.setName(jsonNode.asText());
            category.setDisplay(jsonNode.textValue().toUpperCase().replaceAll("_"," "));
            categoryRepository.save(category);
        });
    }

    private void createQuestions(final JsonNode questions){
        questions.elements().forEachRemaining(jQuestion -> questionRepository.save(createQuestion(jQuestion)));
    }

    private Question createQuestion(JsonNode jQuestion){
        Question question = new Question();
        question.setText(jQuestion.get("question").asText());
        question.setCategory(categoryRepository.findByName(jQuestion.get("category").asText()));

        JsonNode jQuestionType = jQuestion.get("question_type");

        question.setType(Type.getType(jQuestionType.get("type").asText()));
        switch (question.getType()){
            case SINGLE_CHOICE:
                question.setOptions(new ArrayList<>());
                jQuestionType.get("options").elements().forEachRemaining(jOption -> question.getOptions().add(createOption(jOption)));
                break;
            case NUMBER_RANGE:
                question.setRange(createRange(jQuestionType.get("range")));
                break;
            case SINGLE_CHOICE_CONDITIONAL:
                question.setOptions(new ArrayList<>());
                String optionWithFollowUp = checkCondition(jQuestionType.get("condition"));
                jQuestionType.get("options").elements().forEachRemaining(jOption ->{
                    Option option = createOption(jOption);
                    if(option.getText().equals(optionWithFollowUp)){
                        option.setFollowUp(createQuestion(jQuestionType.get("condition").get("if_positive")));
                    }
                    question.getOptions().add(option);
                });
                break;
        }
        return question;
    }

    private Option createOption(JsonNode jOption){
        Option option = new Option();
        option.setText(jOption.asText());
        return option;
    }

    private Range createRange(JsonNode jRange){
        Range range = new Range();
        range.setMin(jRange.get("from").asText());
        range.setMax(jRange.get("to").asText());
        return range;
    }

    private String checkCondition(JsonNode condition){
        return condition.get("predicate").get("exactEquals").get(1).asText();
    }
}
