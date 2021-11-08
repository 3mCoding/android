package com.example.threeminutescoding;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;

public class QuestionListAdapter extends BaseAdapter {
    Context mContext;
    ArrayList<String> desc;
    ArrayList<Integer> no;
    @Override
    public int getCount() {
        return desc.size();
    }
    public QuestionListAdapter(Context mContext, ArrayList<String> q, ArrayList<Integer> no) {
        this.mContext = mContext;
        this.desc = q;
        this.no = no;
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
        return convertView;
    }
}
