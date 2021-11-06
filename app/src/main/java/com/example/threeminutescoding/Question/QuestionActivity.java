package com.example.threeminutescoding.Question;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.threeminutescoding.R;

public class QuestionActivity extends AppCompatActivity {

    ImageButton arrowQuestion, arrowResult, arrowAnswer;
    TextView txtProblem, txtResult, txtAnswer;
    Button btnSubmit, btnDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        btnSwitch();
    }
    void btnSwitch(){
        arrowQuestion = findViewById(R.id.arrowQuestion);
        arrowResult = findViewById(R.id.arrowResult);
        arrowAnswer = findViewById(R.id.arrowAnswer);

        txtProblem = findViewById(R.id.txtProblem);
        txtResult = findViewById(R.id.txtResult);
        txtAnswer = findViewById(R.id.txtAnswer);

        btnSubmit = findViewById(R.id.btnSubmit);
        btnDescription = findViewById(R.id.btnDescription);

        arrowQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtProblem.getVisibility() == View.VISIBLE) {
                    arrowQuestion.setBackgroundResource(R.drawable.arrow_right);
                    txtProblem.setVisibility(View.GONE);
                }
                else {
                    arrowQuestion.setBackgroundResource(R.drawable.arrow_down_black);
                    txtProblem.setVisibility(View.VISIBLE);
                }

            }
        });
        arrowResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtResult.getVisibility() == View.VISIBLE) {
                    arrowResult.setBackgroundResource(R.drawable.arrow_right);
                    txtResult.setVisibility(View.GONE);
                }
                else {
                    arrowResult.setBackgroundResource(R.drawable.arrow_down_black);
                    txtResult.setVisibility(View.VISIBLE);
                }

            }
        });
        arrowAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtAnswer.getVisibility() == View.VISIBLE) {
                    arrowAnswer.setBackgroundResource(R.drawable.arrow_right);
                    txtAnswer.setVisibility(View.GONE);
                }
                else {
                    arrowAnswer.setBackgroundResource(R.drawable.arrow_down_black);
                    txtAnswer.setVisibility(View.VISIBLE);
                }

            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(QuestionActivity.this, "제출되었습니다.", Toast.LENGTH_SHORT).show();
                btnDescription.setVisibility(View.VISIBLE);
                btnSubmit.setVisibility(View.GONE);
            }
        });

        btnDescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnDescription.setVisibility(View.GONE);
                btnSubmit.setVisibility(View.VISIBLE);
            }
        });
    }
}