package com.example.threeminutescoding;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.threeminutescoding.Question.QuestionActivity;
import com.example.threeminutescoding.Question.QuestionList;
import com.example.threeminutescoding.network.RetrofitClient;
import com.example.threeminutescoding.network.ServiceApi;
import com.example.threeminutescoding.user.UserInfo;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TabQuestionList extends Fragment {
    ListView listview;
    //intent로 question activity 넘어가기 위해 몇번째 문제인지 필요함.
    ArrayList<Integer> getStep = new ArrayList<>();
    //데이터 저장 시 no번 - title 식으로 저장
    ArrayList<String> getData = new ArrayList<>();
    QuestionListAdapter adapter;
    int cnt = 0;
    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.tab_question_list, container, false);

        listview = v.findViewById(R.id.list_view);
        detailsData();
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(), QuestionActivity.class);
                //리스트 누르면 해당 position 반환하는데, 포지션에 해당하는 getStep을 step 변수에 저장
                int step = getStep.get(position);
                //Log.d("myapp", "list : " + step);
                intent.putExtra("step", step);
                startActivity(intent);
            }
        });
        return v;
    }
    private void detailsData() {
        ServiceApi service = RetrofitClient.getClient().create(ServiceApi.class);
        Call<List<QuestionList>> call = service.questionListData();
        call.enqueue(new Callback<List<QuestionList>>() {

            @Override
            public void onResponse(Call<List<QuestionList>> call, Response<List<QuestionList>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<QuestionList> result = response.body();
                    for (QuestionList info : result) {
                        getStep.add(info.getNo());
                        getData.add(info.getTitle());
                    }
                    adapter = new QuestionListAdapter(getContext(), getData, getStep);
                    listview.setAdapter(adapter);
                }

            }

            @Override
            public void onFailure(Call<List<QuestionList>> call, Throwable t) {

            }
        });
    }
}
