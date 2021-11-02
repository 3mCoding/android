package com.example.threeminutescoding.Question;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.example.threeminutescoding.MainActivity;
import com.example.threeminutescoding.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuestionActivity extends AppCompatActivity {
    private List<String> mGroup = new ArrayList<String>();
    private Map<String, List<String>> mChild = new HashMap<String, List<String>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        init();
        initView();
    }

    private void init() {
        mGroup.add("Vegitable");
        mGroup.add("Fruit");

        List<String> vegList = new ArrayList<String>();
        vegList.add("Potato");
        vegList.add("Brocoli");
        vegList.add("Onion");
        vegList.add("Chilli");
        vegList.add("Carrot");
        mChild.put("Vegitable", vegList);

        List<String> fruitList = new ArrayList<String>();
        fruitList.add("Strawberry");
        fruitList.add("Blueberry");
        fruitList.add("Grapes");
        fruitList.add("Banana");
        mChild.put("Fruit", fruitList);
    }

    private void initView() {

        ExpandableListView lv = (ExpandableListView) findViewById(R.id.expandable_list);
        ExpandableListAdapter adapter = new ExpandableListAdapter();
        lv.setAdapter((android.widget.ExpandableListAdapter) adapter);

        lv.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                return false;
            }
        });

        lv.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return false;
            }
        });

        lv.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
            }
        });

        lv.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
            }
        });
    }


    private class ExpandableListAdapter extends BaseExpandableListAdapter {

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return mChild.get(mGroup.get(groupPosition)).get(childPosition);
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            if (convertView == null)
                convertView = LayoutInflater.from(QuestionActivity.this).inflate(R.layout.expandable_child_list, null);
            ((TextView) convertView.findViewById(R.id.inside_content)).setText(mChild.get(mGroup.get(groupPosition)).get(childPosition));
            return convertView;
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return mChild.get(mGroup.get(groupPosition)).size();
        }

        @Override
        public Object getGroup(int groupPosition) {
            return mGroup.get(groupPosition);
        }

        @Override
        public int getGroupCount() {
            return mGroup.size();
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            if (convertView == null)
                convertView = LayoutInflater.from(QuestionActivity.this).inflate(R.layout.expandable_parent_list, null);
            ((TextView) convertView.findViewById(R.id.inside_title)).setText(mGroup.get(groupPosition));
            return convertView;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }
    }
}
