package com.sirius.threeminutescoding;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sirius.threeminutescoding.user.UserInfo;

import java.util.ArrayList;

public class UserAdapter extends BaseAdapter {
    Context mContext;
    ArrayList<String> stuNum;
    ArrayList<String> name;
    ArrayList<Integer> step;

    public UserAdapter(Context mContext, ArrayList<String> num, ArrayList<String> name, ArrayList<Integer> step) {
        this.mContext = mContext;
        this.stuNum = num;
        this.name = name;
        this.step = step;
    }

    @Override
    public int getCount() {
        return step.size();
    }

    @Override
    public Object getItem(int i) {
        return step.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null) {
            view = View.inflate(mContext, R.layout.tab_student_list_item, null);
        }
        TextView viewNumber = view.findViewById(R.id.userNumber);
        TextView viewName = view.findViewById(R.id.userName);
        TextView viewStep = view.findViewById(R.id.userProblem);

        viewNumber.setText(stuNum.get(i));
        viewName.setText(name.get(i));
        viewStep.setText(String.valueOf(step.get(i)) + "번");
        String userName = UserInfo.getName();
        if(userName.equals(stuNum.get(i) + " " + name.get(i))) {
            viewNumber.setTextColor(Color.parseColor("#6E9172"));
            viewNumber.setTypeface(null, Typeface.BOLD);
            viewName.setTextColor(Color.parseColor("#6E9172"));
            viewName.setTypeface(null, Typeface.BOLD);
            viewStep.setTextColor(Color.parseColor("#6E9172"));
            viewStep.setTypeface(null, Typeface.BOLD);
        }



        return view;
    }
}
