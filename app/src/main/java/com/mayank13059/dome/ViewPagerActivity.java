package com.mayank13059.dome;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;

import java.util.List;
import java.util.UUID;

public class ViewPagerActivity extends FragmentActivity {

    private ViewPager mViewPager;
    private List<Task> mTasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewPager = new ViewPager(this);
        mViewPager.setId(R.id.activity_view_pager);

        SQLDB dbEntry = new SQLDB(getApplicationContext());
        dbEntry.open();
        mTasks = dbEntry.readAll();
        dbEntry.close();

        FragmentManager fragmentManager = getSupportFragmentManager();

        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                Task task = mTasks.get(position);
                return TaskDisplayFragment.newInstance(position);
            }

            @Override
            public int getCount() {
                int s = mTasks.size();
                Log.e("PagerAdapter", "Counting is "+s);
                return mTasks.size();
            }
        });

        Integer taskID = (Integer)getIntent().getSerializableExtra(TaskDisplayFragment.EXTRA_TASK_ID);

        Log.e("PagerAdapter", "Here");

        mViewPager.setCurrentItem(taskID);
    }
}
