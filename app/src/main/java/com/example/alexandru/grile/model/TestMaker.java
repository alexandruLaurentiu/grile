package com.example.alexandru.grile.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexandru on 13/05/2018.
 */

public class TestMaker {

    private List<Question> questions = new ArrayList<>();
    private boolean testPassed;

    public void statusTestComplete(){
        int passCounter = 0;

        for(Question question : questions){
            if(question.isPassed()){
                passCounter++;
            }
        }

        if(passCounter == questions.size()){
            testPassed = true;
        }
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public boolean isTestPassed() {
        return testPassed;
    }

    public void setTestPassed(boolean testPassed) {
        this.testPassed = testPassed;
    }
}
