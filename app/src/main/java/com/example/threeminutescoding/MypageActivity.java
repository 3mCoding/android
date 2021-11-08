package com.example.threeminutescoding;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.threeminutescoding.user.UserInfo;

public class MypageActivity extends AppCompatActivity {
    TextView txtTitle;
    TextView txtName, txtEmail, txtRank, txtProblem, txtDate, txtInfoModi, txtVersion, txtProblemReco;
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
        txtInfoModi = findViewById(R.id.txtInfoModi);
        txtVersion = findViewById(R.id.txtVersion);
        txtProblemReco = findViewById(R.id.txtProblemReco);

        name = UserInfo.getName();
        date = UserInfo.getJoinData();

        problem = UserInfo.getStep();
        rank = UserInfo.getRank();
        email = UserInfo.getEmail();

        txtName.setText(name);
        txtDate.setText("가입한 날짜 : " + date);
        txtRank.setText("순위 : " + rank + "위");
        txtProblem.setText("푼 문제 수 : " +problem);
        txtEmail.setText(email);


        txtInfoModi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getApplicationContext(), InfoModifyActivity.class);
                startActivity(in);
            }
        });

        txtVersion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "앱 버전 : 1.0", Toast.LENGTH_SHORT).show();
            }
        });

        txtProblemReco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getApplicationContext(), ProblemRecoActivity.class);
                startActivity(in);
            }
        });

    }
}
