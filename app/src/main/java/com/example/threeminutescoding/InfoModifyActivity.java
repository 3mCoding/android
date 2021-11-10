package com.example.threeminutescoding;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.threeminutescoding.network.RetrofitClient;
import com.example.threeminutescoding.network.ServiceApi;
import com.example.threeminutescoding.user.LoginResponse;
import com.example.threeminutescoding.user.StepResponse;
import com.example.threeminutescoding.user.UpdateResponse;
import com.example.threeminutescoding.user.UserInfo;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
                infoChange();
            }
        });
    }
    void infoChange(){
        int stuNum;
        String name;

        if(edtNumber.getText().toString().length() == 0){
            stuNum = -1;
            name = edtName.getText().toString();
            UserInfo.setName(name);
        }else if(edtName.getText().toString().length() == 0){
            name = "x";
            stuNum = Integer.parseInt(edtNumber.getText().toString());
            UserInfo.setStudent_num(stuNum);
        }
        else{
            stuNum = Integer.parseInt(edtNumber.getText().toString());
            name = edtName.getText().toString();
            UserInfo.setName(name);
            UserInfo.setStudent_num(stuNum);
        }

        Log.d("myapp00", String.valueOf(stuNum));
        Log.d("myapp00", name);
        ServiceApi service = RetrofitClient.getClient().create(ServiceApi.class);
        Call<UpdateResponse> call = service.userUpdate(UserInfo.getEmail(), stuNum, name);
        call.enqueue(new Callback<UpdateResponse>() {
            @Override
            public void onResponse(Call<UpdateResponse> call, Response<UpdateResponse> response) {
                UpdateResponse result = response.body();
                if (response.isSuccessful() && response.body() != null) {
                    edtName.setText("");
                    edtNumber.setText("");
                    Toast.makeText(getApplicationContext(), result.getMessage(), Toast.LENGTH_SHORT).show();
                    Toast.makeText(getApplicationContext(), "앱 다시 실행 시 정보가 바뀝니다.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UpdateResponse> call, Throwable t) {
                Log.e("myapp_",t.getMessage());
                Log.d("myapp_", "실패");
            }
        });
    }
}
