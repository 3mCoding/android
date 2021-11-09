package com.example.threeminutescoding;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.threeminutescoding.Question.Description;
import com.example.threeminutescoding.network.RetrofitClient;
import com.example.threeminutescoding.network.ServiceApi;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DescriptionActivity extends AppCompatActivity {

    int id;
    TextView title, content;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);
        Intent intent = new Intent(this.getIntent());
        id = intent.getIntExtra("id", 1);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Log.d("mydesc", "id : " + id);
        title = findViewById(R.id.text_title);
        content = findViewById(R.id.desc_content);
        ServiceApi service = RetrofitClient.getClient().create(ServiceApi.class);
        Call<List<Description>> call = service.descriptionData(01);
        call.enqueue(new Callback<List<Description>>() {
            @Override
            public void onResponse(Call<List<Description>> call, Response<List<Description>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Description> result = response.body();
                    for (Description info : result) {
                        title.setText(info.getTitle());
                        final String contentPath = "http://18.206.61.42:3000/description/" + info.getDescPath();
                        getContent(contentPath);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Description>> call, Throwable t) {

            }
        });
    }
    private void getContent(String path){
        StringBuffer sb = new StringBuffer();

        try {
            URL url = new URL(path);

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(url.openStream()));

            String str = null;
            while ((str = reader.readLine()) != null) {
                sb.append(str + "\n");
            }

            content.setText(sb.toString());
            //Log.d("myapp", sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Toast.makeText(DescriptionActivity.this, "메인 화면 이동.", Toast.LENGTH_SHORT).show();   //토스트 메시지
        Intent intent = new Intent(DescriptionActivity.this, MainActivity.class); //지금 액티비티에서 다른 액티비티로 이동하는 인텐트 설정
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);    //인텐트 플래그 설정
        startActivity(intent);  //인텐트 이동
        finish();   //현재 액티비티 종료
    }
}
