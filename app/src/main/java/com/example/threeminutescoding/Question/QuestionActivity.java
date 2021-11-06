package com.example.threeminutescoding.Question;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.threeminutescoding.AnswerActivity;
import com.example.threeminutescoding.R;
import com.example.threeminutescoding.network.RetrofitClient;
import com.example.threeminutescoding.network.ServiceApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuestionActivity extends AppCompatActivity {

    ImageButton arrowQuestion, arrowResult, arrowAnswer;
    TextView txtProblem, txtResult, txtAnswer, txtTitle;
    Button btnSubmit, btnDescription;
    private ServiceApi service;
    int step;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        Intent intent = new Intent(this.getIntent());
        step = intent.getIntExtra("step", 1);
        //Log.d("myapp", "question num : " + String.valueOf(num));

        txtTitle = findViewById(R.id.txtTitle);
        txtProblem = findViewById(R.id.txtProblem);
        txtResult = findViewById(R.id.txtResult);
        txtAnswer = findViewById(R.id.txtAnswer);
        txtTitle.setText("오늘의 문제 - " + step + "번");
        getData();
        btnSwitch();
    }

    void btnSwitch(){
        arrowQuestion = findViewById(R.id.arrowQuestion);
        arrowResult = findViewById(R.id.arrowResult);
        arrowAnswer = findViewById(R.id.arrowAnswer);

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
                Intent in = new Intent(QuestionActivity.this, AnswerActivity.class);
                startActivity(in);
            }
        });
    }

    void getData(){
        service = RetrofitClient.getClient().create(ServiceApi.class);

        Call<List<Question>> call = service.questionData("java",step);
        call.enqueue(new Callback<List<Question>>() {
            @Override
            public void onResponse(Call<List<Question>> call, Response<List<Question>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Question> result = response.body();

                    for (Question info : result) {
                        Log.d("myapp", String.valueOf(info.getCode()));
                        txtProblem.setText(info.getQuestion());
                        txtResult.setText(info.getPrint());
                        txtAnswer.setText(info.getCode());
                    }
                } else {
                    Log.d("myapp", "question - else err");
                    Log.d("myapp", response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<List<Question>> call, Throwable t) {
                Log.d("myapp", "question - Failure error");
                Log.e("myapp", "에러 : " + t.getMessage());
                Toast.makeText(getApplicationContext(), "인터넷 연결이 필요합니다.", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void makeEdit(String code){
        int length = code.length();
        int cnt = 0;
        for(int i = 0; i < length; i++){
            if(code.substring(i, i+1).equals('_') == false) continue;
            cnt++;
        }

    }
}