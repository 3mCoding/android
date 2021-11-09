package com.example.threeminutescoding;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.threeminutescoding.user.UserInfo;

import java.util.ArrayList;

public class QuestionListAdapter extends BaseAdapter {
    Context mContext;
    ArrayList<String> desc;
    ArrayList<Integer> no;
    ArrayList<Integer> isSolve;
    @Override
    public int getCount() {
        return desc.size();
    }
    public QuestionListAdapter(Context mContext, ArrayList<String> q, ArrayList<Integer> no, ArrayList<Integer> solve) {
        this.mContext = mContext;
        this.desc = q;
        this.no = no;
        isSolve = solve;
    }
    @Override
    public Object getItem(int position) {
        return desc.get(position );
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = View.inflate(mContext, R.layout.tab_question_list_item, null);
        TextView textDesc = convertView.findViewById(R.id.desc_question);
        Log.d("myapp", String.valueOf(no.get(position)) + "번 - " + desc.get(position));
        textDesc.setText(String.valueOf(no.get(position)) + "번 - " + desc.get(position));
        ImageView imgView = convertView.findViewById(R.id.is_solve);
        int step = UserInfo.getStep();
        if(position == step-1) {
            textDesc.setTextColor(Color.parseColor("#6E9172"));
            textDesc.setTypeface(null, Typeface.BOLD);
        }
        if(isSolve.get(position) == 1) {
            imgView.setVisibility(View.VISIBLE);
        }


        return convertView;
    }
}
