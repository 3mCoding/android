package com.sirius.threeminutescoding;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.sirius.threeminutescoding.Question.QuestionActivity;
import com.sirius.threeminutescoding.Question.QuestionList;
import com.sirius.threeminutescoding.adapter.QuestionStepAdapter;
import com.sirius.threeminutescoding.user.UserInfo;

import java.util.ArrayList;
import java.util.List;

public class QuestionStepAllActivity extends AppCompatActivity {
    ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_step_all);
        listview = findViewById(R.id.listview);

        ArrayList<QuestionList> questionList = (ArrayList<QuestionList>) getIntent().getSerializableExtra("question_list");

        QuestionStepAdapter adapter = null;
        adapter = new QuestionStepAdapter(getApplicationContext(), questionList);

        listview.setAdapter(adapter);
        listview.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), QuestionActivity.class);
                intent.putExtra("step", position+1);
                startActivity(intent);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        TextView textTitle = findViewById(R.id.text_title);
        textTitle.setText("단계별 문제 목록");


    }
}