package com.example.threeminutescoding;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class Frag2 extends Fragment {
    ListView listview;
    String[] question = {"2104 김유나", "2105 김유진", "2107 김하진", "2109 심이진", "2213 이민지", "2111 유예영", "2207 김하늘", "2201 가연우", "2204 김세린" };
    ArrayAdapter<String> ar;
    ArrayList<String> choice;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.frag2, container, false);
        listview = v.findViewById(R.id.listview);
        choice = new ArrayList<>();
        ar = new ArrayAdapter<String>(getContext(), android.R.layout.simple_expandable_list_item_1, question);

        listview.setAdapter(ar);
        return v;
    }
}
