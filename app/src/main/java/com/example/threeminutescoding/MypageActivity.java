package com.example.threeminutescoding;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.threeminutescoding.user.UserInfo;

public class MypageActivity extends AppCompatActivity {
    TextView txtTitle;
    TextView txtName, txtEmail, txtRank, txtProblem, txtDate;
    String name, email, date;
    int problem, rank;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage);
        txtTitle = findViewById(R.id.text_title);
        txtTitle.setText("마이페이지");

        txtName = findViewById(R.id.txtName);
        txtEmail = findViewById(R.id.txtEmail);
        txtRank = findViewById(R.id.txtRank);
        txtProblem = findViewById(R.id.txtProblem);
        txtDate = findViewById(R.id.txtDate);

        name = UserInfo.getName();
        date = UserInfo.getJoinData();

        problem = UserInfo.getStep();
        rank = UserInfo.getRank();

        txtName.setText(name);
        txtDate.setText("가입한 날짜 : " + date);
        txtRank.setText("순위 : " + rank + "위");
        txtProblem.setText("푼 문제 수 : " +problem);



    }
}
