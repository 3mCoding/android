package com.example.threeminutescoding;

import android.os.Bundle;
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

import java.util.ArrayList;

public class Frag2 extends Fragment {
    ListView listview;
    String[] number = {"2104", "2105", "2107", "2109", "2213"};
    String[] name = {"김유나", "김유진", "김하진", "심이진", "이민지" };
    int[] problem = {7,8,9,3,5};
    UserAdapter ar;
    ArrayList<User> choice;
    User user;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.frag2, container, false);
        listview = v.findViewById(R.id.listview);
        choice = new ArrayList<>();

        for(int i=0; i<number.length; i++) {
            user = new User(number[i], name[i], problem[i]);
            choice.add(user);
        }
        ar = new UserAdapter(getContext(), choice);
        listview.setAdapter(ar);


        return v;
    }
}
