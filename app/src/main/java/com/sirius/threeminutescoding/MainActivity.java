package com.sirius.threeminutescoding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.viewpager.widget.ViewPager;

import com.sirius.threeminutescoding.Question.QuestionActivity;
import com.sirius.threeminutescoding.Question.QuestionList;
import com.sirius.threeminutescoding.adapter.QuestionStepAdapter;
import com.sirius.threeminutescoding.network.RetrofitClient;
import com.sirius.threeminutescoding.network.ServiceApi;
import com.sirius.threeminutescoding.user.StudentList;
import com.sirius.threeminutescoding.user.UserInfo;
import com.google.android.material.tabs.TabLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    Button btnProblem;
    ImageButton btnUser;

    ListView listQuestionStep;
    ListView listStudent;

    List<StudentList> studentLists;
    List<QuestionList> questionLists;
    int step;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listQuestionStep = findViewById(R.id.list_question_step);
        listStudent = findViewById(R.id.list_student);

        btnProblem = findViewById(R.id.btnProblem);
        btnUser = findViewById(R.id.btnUser);

        getQuestionStepList();
        getStudentList();

        step = UserInfo.getStep();

        findViewById(R.id.btn_question_step).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), QuestionStepAllActivity.class);
                intent.putExtra("question_list", (ArrayList<QuestionList>) questionLists);
                startActivity(intent);
            }
        });

        findViewById(R.id.btn_question_level).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 난이도별 문제 액티비티
            }
        });

        findViewById(R.id.btn_student).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 학생 리스트 액티비티
            }
        });

        btnUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getApplicationContext(), MypageActivity.class);
                startActivity(in);
            }
        });

        btnProblem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dialogFragment = new QuestionRecommendationDialog();
                dialogFragment.show(getSupportFragmentManager(), "level");
            }
        });

    }
    public void getQuestionStepList() {
        ServiceApi service = RetrofitClient.getClient().create(ServiceApi.class);
        Call<List<QuestionList>> call = service.questionListData();
        call.enqueue(new Callback<List<QuestionList>>() {
            @Override
            public void onResponse(Call<List<QuestionList>> call, Response<List<QuestionList>> response) {
                Log.d("MainActivity", "getQuestionStepList 성공");
                if (response.isSuccessful() && response.body() != null) {
                    List<QuestionList> result = response.body();
                    questionLists = result;

                    QuestionStepAdapter adapter = null;
                    // 서버에 저장된 문제 갯수에 대한 제한 걸기
                    if(step-1 < questionLists.size()-2) {
                        adapter = new QuestionStepAdapter(getApplicationContext(), questionLists.subList(step-1, step+2));
                    }
                    else {
                        adapter = new QuestionStepAdapter(getApplicationContext(), questionLists.subList(step-1, questionLists.size()));
                    }

                    listQuestionStep.setAdapter(adapter);
                }

            }

            @Override
            public void onFailure(Call<List<QuestionList>> call, Throwable t) {
                Log.d("MainActivity", "getQuestionStepList 실패");
            }
        });
    }
    public void getStudentList() {
        ServiceApi service = RetrofitClient.getClient().create(ServiceApi.class);
        Call<List<StudentList>> call = service.studentListData();
        call.enqueue(new Callback<List<StudentList>>() {
            @Override
            public void onResponse(Call<List<StudentList>> call, Response<List<StudentList>> response) {
                Log.d("MainActivity", "getStudentList 성공");
                if (response.isSuccessful() && response.body() != null) {
                    List<StudentList> result = response.body();
                    studentLists = result;

                    QuestionStepAdapter adapter = null;
                    if(questionLists.size() < 3) {
                        adapter = new QuestionStepAdapter(getApplicationContext(), questionLists.subList(0, questionLists.size()));
                    }
                    else {
                        adapter = new QuestionStepAdapter(getApplicationContext(), questionLists.subList(0, 3));
                    }
                    listStudent.setAdapter(adapter);
                }

            }

            @Override
            public void onFailure(Call<List<StudentList>> call, Throwable t) {
                Log.d("MainActivity", "getStudentList 실패");
            }
        });
    }
}
