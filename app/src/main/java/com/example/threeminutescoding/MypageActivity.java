package com.example.threeminutescoding;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MypageActivity extends AppCompatActivity {
    TextView txtTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage);
        txtTitle = findViewById(R.id.txtTitle);
        txtTitle.setText("마이페이지");
    }
}
