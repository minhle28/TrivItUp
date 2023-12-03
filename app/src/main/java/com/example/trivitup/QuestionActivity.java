package com.example.trivitup;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.trivitup.databinding.ActivityQuestionBinding;

import java.util.ArrayList;

public class QuestionActivity extends AppCompatActivity {
    ActivityQuestionBinding binding;
    ArrayList<Question> questions;
    int index = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        binding = ActivityQuestionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        questions = new ArrayList<>();
        questions.add(new Question("What is earth?","Planet","Sun","Car","Human","Planet"));
        questions.add(new Question("What is Moon?","Satellite","Sun","Car","Human","Satellite"));
        setNextQuestion();
    }
    void setNextQuestion(){
        if(index < questions.size()){
            Question question = questions.get(index);
            binding.question.setText(question.getQuestion());
            binding.option1.setText(question.getOption1());
            binding.option2.setText(question.getOption2());
            binding.option3.setText(question.getOption3());
            binding.option4.setText(question.getOption4());
        }
    }
}