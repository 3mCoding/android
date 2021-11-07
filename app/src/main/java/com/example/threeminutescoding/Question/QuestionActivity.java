package com.example.threeminutescoding.Question;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.threeminutescoding.AnswerActivity;
import com.example.threeminutescoding.R;
import com.example.threeminutescoding.Submit;
import com.example.threeminutescoding.SubmitAdapter;
import com.example.threeminutescoding.network.RetrofitClient;
import com.example.threeminutescoding.network.ServiceApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuestionActivity extends AppCompatActivity {

    ImageButton arrowQuestion, arrowResult, arrowAnswer, arrowSubmit;
    TextView txtProblem, txtResult, txtAnswer, txtTitle;
    Button btnSubmit, btnDescription;
    private ServiceApi service;
    int step;
    ListView list_submit;
    ArrayList<Submit> nList;
    public static EditText[] mEdit = new EditText[5];
    public static ArrayList<EditText> nEdit = new ArrayList<>();
    SubmitAdapter ar;
    int answerNum, init = 0;
    String[] items = {"java", "c", "c++"};
    int answerNum;
    String answerAll = "";

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
        questionSpinner();
        getData("java");
        btnSwitch();
    }

    void btnSwitch() {
        arrowQuestion = findViewById(R.id.arrowQuestion);
        arrowResult = findViewById(R.id.arrowResult);
        arrowAnswer = findViewById(R.id.arrowAnswer);
        arrowSubmit = findViewById(R.id.arrowSubmit);

        btnSubmit = findViewById(R.id.btnSubmit);
        btnDescription = findViewById(R.id.btnDescription);

        list_submit = findViewById(R.id.list_submit);
        nList = new ArrayList<>();

        arrowQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (txtProblem.getVisibility() == View.VISIBLE) {
                    arrowQuestion.setBackgroundResource(R.drawable.arrow_right);
                    txtProblem.setVisibility(View.GONE);
                } else {
                    arrowQuestion.setBackgroundResource(R.drawable.arrow_down_black);
                    txtProblem.setVisibility(View.VISIBLE);
                }

            }
        });
        arrowResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (txtResult.getVisibility() == View.VISIBLE) {
                    arrowResult.setBackgroundResource(R.drawable.arrow_right);
                    txtResult.setVisibility(View.GONE);
                } else {
                    arrowResult.setBackgroundResource(R.drawable.arrow_down_black);
                    txtResult.setVisibility(View.VISIBLE);
                }

            }
        });
        arrowAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (txtAnswer.getVisibility() == View.VISIBLE) {
                    arrowAnswer.setBackgroundResource(R.drawable.arrow_right);
                    txtAnswer.setVisibility(View.GONE);
                } else {
                    arrowAnswer.setBackgroundResource(R.drawable.arrow_down_black);
                    txtAnswer.setVisibility(View.VISIBLE);
                }

            }
        });
        arrowSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (list_submit.getVisibility() == View.VISIBLE) {
                    arrowSubmit.setBackgroundResource(R.drawable.arrow_right);
                    list_submit.setVisibility(View.GONE);
                } else {
                    arrowSubmit.setBackgroundResource(R.drawable.arrow_down_black);
                    list_submit.setVisibility(View.VISIBLE);
                }
            }
        });


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(QuestionActivity.this, "제출되었습니다.", Toast.LENGTH_SHORT).show();

                for(int i=0; i<5; i++) {
                    answerAll += nList.get(i).edtAnswer + "*";
                }
                Log.d("answerCheck", "전체 답 : " + answerAll);
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

    void getData(String type) {
        service = RetrofitClient.getClient().create(ServiceApi.class);
        Call<List<Question>> call = service.questionData(type, step);
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
                        answerNum = info.getAnswerNum();
                        Log.d("myapp", String.valueOf(info.getAnswerNum()));
                    }
                } else {
                    Log.d("myapp", "question - else err");
                    Log.d("myapp", response.errorBody().toString());
                }
                if(init != 1){
                    for (int i = 0; i < answerNum; i++) {

                        Submit submit = new Submit(i + 1);
                        nList.add(submit);
                    }
                    ar = new SubmitAdapter(getApplicationContext(), nList);
                    list_submit.setAdapter(ar);
                    init++;
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

    void questionSpinner(){
        Spinner spinner = findViewById(R.id.language);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, items
        );

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(view.getContext(), items[position], Toast.LENGTH_LONG).show();
                getData(items[position]);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
}