package com.example.threeminutescoding;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.threeminutescoding.Question.QuestionActivity;

public class MainActivity extends AppCompatActivity {

    Button gotoSolve = findViewById(R.id.goto_solve);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gotoSolve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(R.layout.activity_question);
            }
        });
    }
}
