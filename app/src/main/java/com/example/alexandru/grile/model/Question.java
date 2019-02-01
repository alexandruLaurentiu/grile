package com.example.alexandru.grile.model;

import android.graphics.Color;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexandru on 10/05/2018.
 */

public class Question extends TextModel {

    private List<Answer> answers = new ArrayList<>();
    private boolean passed;

    public Question(String text) {
        super(text);
    }


    private void goodResponse() {
        int numberOfGoodAnswers = 0;
        int responses = 0;
        int potentiallyCorrect = 0;

        for (Answer a : answers) {
            if (a.isCorrectAnswer()) {
                numberOfGoodAnswers += 1;
            }
        }

        for (Answer a : answers) {
            if (a.getCheckBox().isChecked()) {
                responses += 1;
            }
        }
        for (Answer a : answers) {
            if (a.isCorrectAnswer() && a.getCheckBox().isChecked()) {
                potentiallyCorrect += 1;
            }
        }

        if (responses == numberOfGoodAnswers && responses == potentiallyCorrect) {
            passed = true;
        } else {
            passed = false;
        }
    }

    public void validateGoodResponse() {
        goodResponse();

        if (passed) {
            for (Answer a : answers) {
                if (a.getCheckBox().isChecked()) {
                    a.getCheckBox().setBackgroundColor(Color.GREEN);
                }else if(!a.getCheckBox().isChecked()){
                    a.getCheckBox().setBackgroundColor(Color.WHITE);
                }
            }
        } else if (!passed) {
            for (Answer a : answers) {
                if(a.getCheckBox().isChecked()) {
                    a.getCheckBox().setBackgroundColor(Color.RED);
                }else if(!a.getCheckBox().isChecked()){
                    a.getCheckBox().setBackgroundColor(Color.WHITE);
                }
            }
        }
    }

    public void addAnswer(Answer answer){
       answers.add(answer);
    }


    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public boolean isPassed() {
        return passed;
    }

    public void setPassed(boolean passed) {
        this.passed = passed;
    }
}
