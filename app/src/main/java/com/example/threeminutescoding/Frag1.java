package com.example.threeminutescoding;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class Frag1 extends Fragment {
    ListView listview;
    String[] question = {"13번 - for문", "14번 - while문", "15번 - break, continue", "16번 - 함수(기본)", "17번 - 함수(심화)" };
    ArrayAdapter<String> ar;
    ArrayList<String> choice;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.frag1, container, false);

        listview = v.findViewById(R.id.listview);
        choice = new ArrayList<>();
        ar = new ArrayAdapter<String>(getContext(), android.R.layout.simple_expandable_list_item_1, question);

        listview.setAdapter(ar);




        return v;
    }
}
