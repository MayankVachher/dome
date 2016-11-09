package com.mayank13059.dome;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.UUID;


public class TaskDisplayFragment extends Fragment {


    public static final String EXTRA_TASK_ID = "TASK_ID";

    Task task;

    private OnFragmentInteractionListener mListener;

    public TaskDisplayFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public static TaskDisplayFragment newInstance(Integer taskID) {
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_TASK_ID, taskID);

        TaskDisplayFragment fragment = new TaskDisplayFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Integer taskID = (Integer) getArguments().getSerializable(EXTRA_TASK_ID);

        SQLDB dbEntry = new SQLDB(getActivity());
        dbEntry.open();
        task = dbEntry.readAll().get(taskID);
        dbEntry.close();

        Log.e("Fragment", "Hola hola hola");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_task_dispay, container, false);
    }


    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
