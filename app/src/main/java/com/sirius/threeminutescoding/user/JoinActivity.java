package com.sirius.threeminutescoding.user;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.sirius.threeminutescoding.R;
import com.sirius.threeminutescoding.network.RetrofitClient;
import com.sirius.threeminutescoding.network.ServiceApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JoinActivity extends AppCompatActivity {

    private ServiceApi service;
    private ProgressBar mProgressView;
    private EditText viewName;
    private EditText viewStuNum;
    private EditText viewPw;
    private EditText viewCheckPw;
    private EditText viewEmail;
    private Button JoinBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        service = RetrofitClient.getClient().create(ServiceApi.class);
        mProgressView = (ProgressBar) findViewById(R.id.join_progress);

        viewName = findViewById(R.id.join_name);
        viewEmail = findViewById(R.id.join_email);
        viewStuNum = findViewById(R.id.join_student_num);
        viewPw = findViewById(R.id.join_password);
        viewCheckPw = findViewById(R.id.join_password_check);
        JoinBtn = findViewById(R.id.join_submit);

        JoinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptJoin();
            }
        });
    }

    private void attemptJoin() {
        viewName.setError(null);
        viewStuNum.setError(null);
        viewPw.setError(null);
        viewCheckPw.setError(null);
        viewEmail.setError(null);

        String inputName = viewName.getText().toString();
        String inputStuNum = viewStuNum.getText().toString();
        String inputPw = viewPw.getText().toString();
        String inputCheckPw = viewCheckPw.getText().toString();
        String inputEmail = viewEmail.getText().toString();

        boolean cancel = false;
        View focusView = null;

        if (inputName.isEmpty()) {
            viewName.setError("이름을 입력해주세요.");
            focusView = viewName;
            cancel = true;
        }
        if (inputStuNum.isEmpty()) {
            viewStuNum.setError("학번을 입력해주세요.");
            focusView = viewStuNum;
            cancel = true;
        }
        if (inputEmail.isEmpty()) {
            viewEmail.setError("이메일을 입력해주세요.");
            focusView = viewEmail;
            cancel = true;
        } else if (!isEmailValid(inputEmail)) {
            viewEmail.setError("미림 이메일로만 가입할 수 있습니다.");
            focusView = viewEmail;
            cancel = true;
        }
        if (inputPw.isEmpty()) {
            viewPw.setError("비밀번호를 입력해주세요.");
            focusView = viewPw;
            cancel = true;
        } else if (!isPasswordValid(inputPw)) {
            viewPw.setError("8자 이상의 비밀번호를 입력하세요");
            focusView = viewPw;
            cancel = true;
        }

        if (!inputPw.equals(inputCheckPw)) {
            viewCheckPw.setError("비밀번호가 일치하지 않습니다.");
            focusView = viewPw;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            startJoin(new JoinData(inputStuNum, inputName, inputEmail, inputPw));
            showProgress(true);

        }

    }
    private void startJoin(JoinData data) {
        service.userJoin(data).enqueue(new Callback<JoinResponse>() {
            @Override
            public void onResponse(Call<JoinResponse> call, Response<JoinResponse> response) {
                JoinResponse result = response.body();
                Toast.makeText(JoinActivity.this, result.getMessage(), Toast.LENGTH_SHORT).show();
                showProgress(false);
                if (result.getCode() == 200) {
                    //액티비티 종료
                    finish();

                }
            }

            @Override
            public void onFailure(Call<JoinResponse> call, Throwable t) {
                Toast.makeText(JoinActivity.this, "회원가입 에러 발생", Toast.LENGTH_SHORT).show();
                showProgress(false);
                Log.e("회원가입 에러 발생", t.getMessage());
                t.printStackTrace();
            }

        });
    }

    private boolean isEmailValid(String email) {
        return email.contains("@e-mirim.hs.kr");
    }

    private boolean isPasswordValid(String password) {
        return password.length() >= 8;
    }
    private void showProgress(boolean show) {
        mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
    }
}