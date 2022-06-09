package com.sirius.threeminutescoding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.viewpager.widget.ViewPager;

import com.sirius.threeminutescoding.Question.QuestionActivity;
import com.sirius.threeminutescoding.user.UserInfo;
import com.google.android.material.tabs.TabLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    FragmentAdapter adapter;
    Button btnProblem;
    ImageButton btnUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
        adapter = new FragmentAdapter(getSupportFragmentManager());
        btnProblem = findViewById(R.id.btnProblem);
        btnUser = findViewById(R.id.btnUser);

        btnUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getApplicationContext(), MypageActivity.class);
                startActivity(in);
            }
        });

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setText("문제 목록");
        tabLayout.getTabAt(1).setText("문제 푼 친구");

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

//        btnProblem.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getApplicationContext(), QuestionActivity.class);
//                int step = UserInfo.getStep();
//                //Log.d("myapp", "main - getStep() : " + step);
//                intent.putExtra("step", step);
//                startActivity(intent);
//            }
//        });
        btnProblem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dialogFragment = new QuestionRecommendationDialog();
                dialogFragment.show(getSupportFragmentManager(), "level");
            }
        });

    }
}
