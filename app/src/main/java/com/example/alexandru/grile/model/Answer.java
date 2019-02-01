package com.example.alexandru.grile.model;

import android.widget.CheckBox;

/**
 * Created by Alexandru on 10/05/2018.
 */

public class Answer extends TextModel {

    private CheckBox checkBox;
    private boolean correctAnswer;

    public Answer(String text) {
        super(text);
    }

    public Answer(String text, boolean correctAnswer) {
        super(text);
        this.correctAnswer = correctAnswer;
    }

    public CheckBox getCheckBox() {
        return checkBox;
    }

    public void setCheckBox(CheckBox checkBox) {
        this.checkBox = checkBox;
    }

    public boolean isCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(boolean correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
}
