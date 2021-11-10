package com.sirius.threeminutescoding;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sirius.threeminutescoding.Question.RecommendResponse;
import com.sirius.threeminutescoding.network.RetrofitClient;
import com.sirius.threeminutescoding.network.ServiceApi;
import com.sirius.threeminutescoding.user.UserInfo;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
                Log.d("myapp00", "버튼 누름");
                setData();
            }
        });
    }
    void setData(){
        String content = edtReco.getText().toString();
        ServiceApi service = RetrofitClient.getClient().create(ServiceApi.class);
        Call<RecommendResponse> call = service.recData(content, UserInfo.getStudent_num());
        call.enqueue(new Callback<RecommendResponse>() {
            @Override
            public void onResponse(Call<RecommendResponse> call, Response<RecommendResponse> response) {
                RecommendResponse result = response.body();
                if (response.isSuccessful() && response.body() != null) {
                    edtReco.setText("");
                    Toast.makeText(getApplicationContext(), result.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RecommendResponse> call, Throwable t) {
                Log.e("myapp_",t.getMessage());
                Log.d("myapp_", "실패");
            }
        });
    }
}
