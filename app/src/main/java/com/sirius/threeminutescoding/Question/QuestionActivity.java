package com.sirius.threeminutescoding.Question;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.sirius.threeminutescoding.DescriptionActivity;
import com.sirius.threeminutescoding.R;
import com.sirius.threeminutescoding.Submit;
import com.sirius.threeminutescoding.SubmitAdapter;
import com.sirius.threeminutescoding.network.RetrofitClient;
import com.sirius.threeminutescoding.network.ServiceApi;
import com.sirius.threeminutescoding.user.StepResponse;
import com.sirius.threeminutescoding.user.UserInfo;

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
    SubmitAdapter ar;
    int answerNum, init = 0, id;
    String[] items = {"java", "c", "c++"};
    String answerAll = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        Intent intent = new Intent(this.getIntent());
        step = intent.getIntExtra("step", 1);
        //Log.d("myapp", "question num : " + String.valueOf(num));

        txtTitle = findViewById(R.id.text_title);
        txtProblem = findViewById(R.id.txtProblem);
        txtResult = findViewById(R.id.txtResult);
        txtAnswer = findViewById(R.id.txtAnswer);
        txtTitle.setText("오늘의 문제 - " + step + "번");
//        if(step > UserInfo.getStep()){
//            Toast.makeText(QuestionActivity.this, "현재 단계에서 풀 수 없는 문제입니다.", Toast.LENGTH_LONG).show();
//            Intent intent2 = new Intent(QuestionActivity.this, MainActivity.class);
//            startActivity(intent2);
//            finish();
//        }
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

                answerAll = "";
                for(int i=0; i<answerNum; i++) {
                    answerAll += nList.get(i).edtAnswer + ".";
                }
                Log.d("myapp", "전체 답 : " + answerAll);

                Log.d("myapp__", "UserInfo.getStep()");
                Log.d("myapp__", String.valueOf(UserInfo.getStep()));

                Log.d("myapp__", "step");
                Log.d("myapp__", String.valueOf(step));
                if(answerAll.contains("..") || answerAll.contains("null")){
                    Toast.makeText(QuestionActivity.this, "모든 문항을 입력해주세요.", Toast.LENGTH_SHORT).show();
                }
                else {
                    checkAnswer(new answerData(id, answerAll));
                    if(UserInfo.getStep() == step)
                        stepUpdate();
                }
            }
        });

        btnDescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnDescription.setVisibility(View.GONE);
                btnSubmit.setVisibility(View.VISIBLE);
                Intent intent = new Intent(QuestionActivity.this, DescriptionActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
                finish();
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
                        id = info.getId();
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

    void checkAnswer(answerData data){
        Log.d("myapp", "check");
        service.questionAnswer(data).enqueue(new Callback<AnswerResponse>() {
            @Override
            public void onResponse(Call<AnswerResponse> call, Response<AnswerResponse> response) {
                AnswerResponse result = response.body();
                Toast.makeText(getApplicationContext(), result.getMessage(), Toast.LENGTH_LONG).show();
                if (result.getCode() == 200) {
                    btnDescription.setVisibility(View.VISIBLE);
                    btnSubmit.setVisibility(View.GONE);
                }
            }
            @Override
            public void onFailure(Call<AnswerResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "인터넷 연결이 필요합니다.", Toast.LENGTH_SHORT).show();
                Log.e("로그인 에러 발생",t.getMessage());
                t.printStackTrace();
            }
        });
    }
    void stepUpdate(){
        ServiceApi service = RetrofitClient.getClient().create(ServiceApi.class);
        final int stepP = step + 1;
        Log.d("myapp__", String.valueOf(stepP));
        Call<StepResponse> call = service.stepData(UserInfo.getEmail(), stepP);
        call.enqueue(new Callback<StepResponse>() {
            @Override
            public void onResponse(Call<StepResponse> call, Response<StepResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    UserInfo.setStep(stepP);
                    Log.d("myapp_", String.valueOf(UserInfo.getStep()));
                }
            }

            @Override
            public void onFailure(Call<StepResponse> call, Throwable t) {
                Log.e("myapp_",t.getMessage());
                Log.d("myapp_", "실패");
            }
        });
    }
}