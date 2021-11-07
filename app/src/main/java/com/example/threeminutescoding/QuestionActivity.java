package com.example.threeminutescoding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class QuestionActivity extends AppCompatActivity {

    ImageButton arrowQuestion, arrowResult, arrowAnswer, arrowSubmit;
    TextView txtProblem, txtResult, txtAnswer;
    Button btnSubmit, btnDescription;
    ListView list_submit;
    ArrayList<Submit> nList;
    SubmitAdapter ar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        arrowQuestion = findViewById(R.id.arrowQuestion);
        arrowResult = findViewById(R.id.arrowResult);
        arrowAnswer = findViewById(R.id.arrowAnswer);
        arrowSubmit = findViewById(R.id.arrowSubmit);

        txtProblem = findViewById(R.id.txtProblem);
        txtResult = findViewById(R.id.txtResult);
        txtAnswer = findViewById(R.id.txtAnswer);

        btnSubmit = findViewById(R.id.btnSubmit);
        btnDescription = findViewById(R.id.btnDescription);

        list_submit = findViewById(R.id.list_submit);
        nList = new ArrayList<>();

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

        arrowSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(list_submit.getVisibility() == View.VISIBLE) {
                    arrowSubmit.setBackgroundResource(R.drawable.arrow_right);
                    list_submit.setVisibility(View.GONE);
                }
                else {
                    arrowSubmit.setBackgroundResource(R.drawable.arrow_down_black);
                    list_submit.setVisibility(View.VISIBLE);
                }
            }
        });

        for(int i=0; i<5; i++) {
            Submit submit = new Submit(i+1);
            nList.add(submit);
        }

        ar = new SubmitAdapter(this, nList);
        list_submit.setAdapter(ar);


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
                Intent in = new Intent(QuestionActivity.this, AnswerActivity.class);
                startActivity(in);

            }
        });
    }
}