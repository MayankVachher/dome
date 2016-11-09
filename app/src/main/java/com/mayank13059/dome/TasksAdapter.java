package com.mayank13059.dome;


import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Mayank Vachher (2013059) on 09/11/16.
 */

public class TasksAdapter extends RecyclerView.Adapter<TasksAdapter.MyViewHolder> {

    private List<Task> taskList;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView titleHolder, statusHolder;

        public MyViewHolder(View view) {
            super(view);

            titleHolder = (TextView) view.findViewById(R.id.task_list_row_title);
            statusHolder = (TextView) view.findViewById(R.id.task_list_row_status);
        }
    }

    public TasksAdapter(List<Task> taskList) {
        this.taskList = taskList;
//        Log.e("Adapter", taskList.toString());
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.task_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Task task = taskList.get(position);
        holder.titleHolder.setText(task.getTitle());

        if(task.getStatus() == 1) {
            holder.statusHolder.setText("Done");
        }
        else {
            holder.statusHolder.setText("Not Done");
        }
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }
}
