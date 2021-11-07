package com.example.threeminutescoding;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class SubmitAdapter extends BaseAdapter {
    Context mContext;
    ArrayList<Submit> mData;

    public SubmitAdapter(Context mContext, ArrayList<Submit> mData) {
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
            view = View.inflate(mContext, R.layout.item_submit_list, null);
        }
        TextView txtNumber = view.findViewById(R.id.txtNumber);
        txtNumber.setText(Integer.toString(mData.get(i).number));
        return view;
    }
}
