package com.sirius.threeminutescoding.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sirius.threeminutescoding.R;
import com.sirius.threeminutescoding.user.StudentList;

import java.util.List;
import java.util.zip.Inflater;

public class StudentAdapter extends BaseAdapter {
    Context context;
    List<StudentList> studentList;
    public StudentAdapter(Context context, List<StudentList> studentList ) {
        this.context = context;
        this.studentList = studentList;
    }

    @Override
    public int getCount() {
        return studentList.size();
    }

    @Override
    public Object getItem(int position) {
        return studentList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = View.inflate(context, R.layout.item_student_list, null);

        TextView txtStudentNumber = view.findViewById(R.id.txt_student_number);
        TextView txtStudentName = view.findViewById(R.id.txt_student_name);
        TextView txtStudentStep = view.findViewById(R.id.txt_student_step);

        txtStudentNumber.setText(studentList.get(position).getStuNum());
        txtStudentName.setText(studentList.get(position).getUserName());
        txtStudentStep.setText(studentList.get(position).getUserStep()+"번");


        return view;
    }
}
