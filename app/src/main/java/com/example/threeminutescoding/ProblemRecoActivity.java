package com.example.threeminutescoding;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ProblemRecoActivity extends AppCompatActivity {
    TextView txtTitle;
    Button btnSubmit;
    EditText edtReco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problem_reco);

        txtTitle = findViewById(R.id.text_title);
        txtTitle.setText("문제 추천");

        btnSubmit = findViewById(R.id.btnSubmit);
        edtReco = findViewById(R.id.edtReco);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtReco.setText("");
                Toast.makeText(getApplicationContext(), "제출되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
