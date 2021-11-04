package com.example.threeminutescoding;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class UserAdapter extends BaseAdapter {
    Context mContext;
    ArrayList<User> mData;

    public UserAdapter(Context mContext, ArrayList<User> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }


    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int i) {
        return mData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null) {
            view = View.inflate(mContext, R.layout.item_user_list, null);
        }
        TextView number = view.findViewById(R.id.userNumber);
        TextView name = view.findViewById(R.id.userName);
        TextView problem = view.findViewById(R.id.userProblem);

        number.setText(mData.get(i).number);
        name.setText(mData.get(i).name);
        problem.setText(Integer.toString(mData.get(i).problem));

        return view;
    }
}
