package com.sirius.threeminutescoding.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sirius.threeminutescoding.Question.QuestionActivity;
import com.sirius.threeminutescoding.Question.QuestionList;
import com.sirius.threeminutescoding.QuestionListAdapter;
import com.sirius.threeminutescoding.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class QuestionStepAdapter extends BaseAdapter {
    List<QuestionList> questionList;
    Context mContext;

    public QuestionStepAdapter(Context context, List<QuestionList> questionList) {
        this.mContext = context;
        this.questionList = questionList;
    }

    @Override
    public int getCount() {
        return questionList.size();
    }

    @Override
    public Object getItem(int position) {
        return questionList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, final View convertView, ViewGroup parent) {
        View view = View.inflate(mContext, R.layout.item_question_step_list, null);
        TextView txtQuestionNumber = view.findViewById(R.id.txt_question_number);
        TextView txtQuestionTitle = view.findViewById(R.id.txt_question_title);

        txtQuestionNumber.setText(questionList.get(position).getNo()+"");
        txtQuestionTitle.setText(questionList.get(position).getTitle());

        final int currentPosition = position+1;

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), QuestionActivity.class);
                intent.putExtra("step", currentPosition+1);
                mContext.startActivity(intent);
            }
        });

        return view;
    }
}
