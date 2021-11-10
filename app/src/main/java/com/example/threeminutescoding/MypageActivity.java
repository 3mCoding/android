package com.sirius.threeminutescoding;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.sirius.threeminutescoding.user.UserInfo;

public class MypageActivity extends AppCompatActivity {
    TextView txtTitle;
    static TextView txtName;
    TextView txtEmail;
    TextView txtRank;
    TextView txtProblem;
    TextView txtDate;
    TextView txtInfoModi;
    TextView txtVersion;
    TextView txtProblemReco;
    static String name;
    String email;
    String date;
    static String stuNum;
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

        stuNum = String.valueOf(UserInfo.getStudent_num());
        name = UserInfo.getName();
        date = UserInfo.getJoinData();

        problem = UserInfo.getStep()-1;
        rank = UserInfo.getRank();
        email = UserInfo.getEmail();
        txtName.setText( stuNum + " " + name);
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
