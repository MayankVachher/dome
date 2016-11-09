package com.mayank13059.dome;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.UUID;

public class TaskDisplay extends SingleFragmentActivity {

    protected Fragment createFragment() {
        Integer task_pos = (Integer) getIntent()
                .getSerializableExtra(TaskDisplayFragment.EXTRA_TASK_ID);
        return TaskDisplayFragment.newInstance(task_pos);
    }
}
