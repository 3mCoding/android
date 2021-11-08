package com.example.threeminutescoding;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

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
    String[] items = {"전체", "학번 : 오름차순", "학번 : 내림차순", "많이 푼 사람"};
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.tab_student_list, container, false);
        listview = v.findViewById(R.id.listview);
        studentSpinner(v);

        return v;
    }
    private void detailsData(int order) {
        ServiceApi service = RetrofitClient.getClient().create(ServiceApi.class);
        Call<List<StudentList>> call = service.studentListData(order);
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
    void studentSpinner(View v){
        Spinner spinner = v.findViewById(R.id.grade);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, items);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(view.getContext(), items[position], Toast.LENGTH_LONG).show();
                num.clear();
                name.clear();
                step.clear();
                Log.d("myapp", String.valueOf(position));
                detailsData(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
}
