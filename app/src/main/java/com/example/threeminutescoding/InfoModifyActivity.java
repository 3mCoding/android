package com.example.threeminutescoding;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class InfoModifyActivity extends AppCompatActivity {
    TextView text_title;
    Button btnSubmit;
    EditText edtNumber, edtName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_modify);
        text_title = findViewById(R.id.text_title);
        text_title.setText("개인정보 수정");

        btnSubmit = findViewById(R.id.btnSubmit);
        edtNumber = findViewById(R.id.edtNumber);
        edtName = findViewById(R.id.edtName);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtName.setText("");
                edtNumber.setText("");
                Toast.makeText(getApplicationContext(), "수정되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
