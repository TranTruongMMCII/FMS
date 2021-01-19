package com.example.fms_android.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fms_android.R;
import com.example.fms_android.adapter.TraineeFeedbackAdapter;
import com.example.fms_android.model.FeedbackOfTruong;
import com.example.fms_android.viewmodel.TraineeFeedbackViewModel;

import java.util.ArrayList;

public class TraineeFeedbackFragment extends DialogFragment {

    private TraineeFeedbackViewModel traineeFeedbackViewModel;
    private RecyclerView recyclerViewTraineeFeedback;
    private TraineeFeedbackAdapter traineeFeedbackAdapter;
    private View view;
    private ArrayList<FeedbackOfTruong> feedbackOfTruongs;
    private LinearSnapHelper linearSnapHelper;

    public static TraineeFeedbackFragment getInstance(){
        return new TraineeFeedbackFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_trainee_feedback, container, false);

        recyclerViewTraineeFeedback = view.findViewById(R.id.recyclerViewTraineeFeedback);
        recyclerViewTraineeFeedback.setHasFixedSize(true);
        recyclerViewTraineeFeedback.setLayoutManager(new LinearLayoutManager(getContext()));

        feedbackOfTruongs = new ArrayList<>();
        feedbackOfTruongs.add(new FeedbackOfTruong("FeedbackOfTruong A1", "2", "Class 2", "1", "Truyền thông và mạng máy tính", "2020", "InComplete"));
        feedbackOfTruongs.add(new FeedbackOfTruong("FeedbackOfTruong A1", "12", "Class 12", "1", "Truyền thông và mạng máy tính", "2020", "Complete"));
        feedbackOfTruongs.add(new FeedbackOfTruong("FeedbackOfTruong A1", "12", "Class 12", "1", "Truyền thông và mạng máy tính", "2020", "InComplete"));

        traineeFeedbackAdapter = new TraineeFeedbackAdapter(getContext(), feedbackOfTruongs);
        recyclerViewTraineeFeedback.setAdapter(traineeFeedbackAdapter);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        retrieveTasks();
    }

    private void retrieveTasks() {
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            dialog.getWindow().setLayout(width, height);
        }
    }
}
