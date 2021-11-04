package com.example.threeminutescoding.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.threeminutescoding.MainActivity;
import com.example.threeminutescoding.R;
import com.example.threeminutescoding.network.RetrofitClient;
import com.example.threeminutescoding.network.ServiceApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    ServiceApi service;
    private ProgressBar mProgressView;
    private EditText viewEmail;
    private EditText viewPw;
    private Button loginBtn;
    private Button intentJoin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        service = RetrofitClient.getClient().create(ServiceApi.class);
        mProgressView = findViewById(R.id.login_progress);

        viewEmail = findViewById(R.id.login_email);
        viewPw = findViewById(R.id.login_password);
        loginBtn = findViewById(R.id.login_submit);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptLogin();
            }
        });
        intentJoin = findViewById(R.id.login_into_join);
        intentJoin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), JoinActivity.class);
                startActivity(intent);
            }
        });
    }
    private void attemptLogin(){
        viewEmail.setError(null);
        viewPw.setError(null);

        String inputEmail = viewEmail.getText().toString();
        String inputPw = viewPw.getText().toString();

        boolean cancel = false;

        View focusView = null;

        if (inputPw.isEmpty()) {
            viewPw.setError("비밀번호를 입력해주세요.");
            focusView = viewPw;
            cancel = true;
        } else if (!isPasswordValid(inputPw)) {
            viewPw.setError("8자 이상의 비밀번호를 입력해주세요.");
            focusView = viewPw;
            cancel = true;
        }

        if (inputEmail.isEmpty()) {
            viewEmail.setError("학교 이메일을 입력해주세요.");
            focusView = viewEmail;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            startLogin(new LoginData(inputEmail, inputPw));
            showProgress(true);
        }
    }
    private void startLogin(LoginData data) {
        service.userLogin(data).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse result = response.body();
                Toast.makeText(LoginActivity.this, result.getMessage(), Toast.LENGTH_SHORT).show();
                showProgress(false);

                if (result.getCode() == 200) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    //액티비티 종료
                    finish();

                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "인터넷 연결이 필요합니다.", Toast.LENGTH_SHORT).show();
                Log.e("로그인 에러 발생",t.getMessage());
                t.printStackTrace();
                showProgress(false);
            }
        });

    }
    private void showProgress(boolean show) {
        mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
    }
    private boolean isPasswordValid(String password) {
        return password.length() >= 8;
    }
}