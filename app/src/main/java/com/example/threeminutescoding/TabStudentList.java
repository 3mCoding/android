package com.example.threeminutescoding;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.threeminutescoding.Question.QuestionList;
import com.example.threeminutescoding.network.RetrofitClient;
import com.example.threeminutescoding.network.ServiceApi;
import com.example.threeminutescoding.user.StudentList;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TabStudentList extends Fragment {
    ListView listview;
    UserAdapter adapter;
    ArrayList<String> num = new ArrayList<>();
    ArrayList<String> name = new ArrayList<>();
    ArrayList<Integer> step = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.tab_student_list, container, false);
        listview = v.findViewById(R.id.listview);

        detailsData();

        return v;
    }
    private void detailsData() {
        ServiceApi service = RetrofitClient.getClient().create(ServiceApi.class);
        Call<List<StudentList>> call = service.studentListData();
        call.enqueue(new Callback<List<StudentList>>() {

            @Override
            public void onResponse(Call<List<StudentList>> call, Response<List<StudentList>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<StudentList> result = response.body();
                    for (StudentList info : result) {
                        num.add(info.getStuNum());
                        name.add(info.getUserName());
                        step.add(info.getUserStep());
                    }
                }
                adapter = new UserAdapter(getContext(), num, name, step);
                listview.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<StudentList>> call, Throwable t) {

            }
        });
    }
}
