package com.example.alexandru.grile;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alexandru.grile.model.Answer;
import com.example.alexandru.grile.model.Question;
import com.example.alexandru.grile.model.TestMaker;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Test extends AppCompatActivity {

    private TestMaker test = new TestMaker();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        Intent intent = getIntent();

        String filePathName = intent.getStringExtra("filepath");

        String resp = readFromFile(filePathName);
        String[] fullQuestions = resp.split("\n(?=\\p{L})");

        for (String q : fullQuestions) {
            String[] individualQuestion = q.split("\n");
            Question question = new Question(individualQuestion[0]);

            for (int i = 1; i < individualQuestion.length; i++) {
                Answer answer = null;
                if (individualQuestion[i].charAt(individualQuestion[i].length() - 2) == 'T' || individualQuestion[i].charAt(individualQuestion[i].length() - 1) == 'T') {
                    answer = new Answer(individualQuestion[i].substring(1, individualQuestion[i].length() - 2).toString(), true);
                } else {
                    answer = new Answer(individualQuestion[i].substring(1, individualQuestion[i].length() - 1).toString());
                }
                question.addAnswer(answer);
            }
            test.getQuestions().add(question);
        }

        LinearLayout layout = (LinearLayout) findViewById(R.id.test_layout);
        generateDynamicQuestions(test.getQuestions(), layout);


    }

    private void generateDynamicQuestions(List<Question> questions, LinearLayout layout) {
        for (Question q : questions) {
            TextView textView = new TextView(this);

            textView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            textView.setText(q.getText());

            layout.addView(textView);
            generateDynamicAnswers(q, q.getAnswers(), layout);
        }
    }

    private void generateDynamicAnswers(Question question, List<Answer> answers, LinearLayout layout) {
        for (Answer a : answers) {
            CheckBox checkBox = new CheckBox(this);

            checkBox.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            checkBox.setText(a.getText());

            a.setCheckBox(checkBox);

            checkBoxListener(question, checkBox);

            layout.addView(checkBox);
        }
    }

    private void checkBoxListener(final Question question, CheckBox checkBox) {

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                question.validateGoodResponse();
                test.statusTestComplete();
                if (test.isTestPassed()) {
                    Toast.makeText(Test.this,
                            "Felicitari, iei examenul!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private String readFromFile(String pathName) {
        String text = "";

        FileInputStream fis;

        try {
            fis = new FileInputStream(pathName);
            int size = fis.available();
            byte[] buffer = new byte[size];
            fis.read(buffer);
            fis.close();
            text = new String(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return text;
    }

}
