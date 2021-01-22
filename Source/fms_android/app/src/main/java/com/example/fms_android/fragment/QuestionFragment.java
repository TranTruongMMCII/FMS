package com.example.fms_android.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import com.example.fms_android.R;
import com.example.fms_android.activity.QuestionActivity;
import com.example.fms_android.adapter.QuestionAdapter;
import com.example.fms_android.model.Question;
import com.example.fms_android.viewmodel.QuestionViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class QuestionFragment extends DialogFragment {
    private QuestionViewModel questionViewModel ;
    private RecyclerView recyclerView;
    private ArrayList<Question> data;
    private QuestionAdapter questionAdapter;
    private Spinner spinner;
    private FloatingActionButton fabAddQ;
    private View view;


    public static QuestionFragment getInstance()
    {
        return new QuestionFragment();
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        //Make dialog full screen with transparent background
        if (dialog != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            dialog.getWindow().setLayout(width, height);
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        questionViewModel = ViewModelProviders.of(this).get(QuestionViewModel.class);

        view = inflater.inflate(R.layout.fragment_question, container, false);
        recyclerView = view.findViewById(R.id.recyclerViewQuestion);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        fabAddQ = view.findViewById(R.id.fadAddQ);
        fabAddQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), QuestionActivity.class));
            }
        });



        // data
        data = new ArrayList<>();
        questionAdapter = new QuestionAdapter(getContext(), data);

        recyclerView.setAdapter(questionAdapter);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                List<Question> tasks = questionAdapter.getTasks();
                Question question = tasks.get(position);
                questionViewModel.deleteQuestion(question.getQuestionID());
                retrieveTasks();
            }
        }).attachToRecyclerView(recyclerView);

        return view;
    }

    private void retrieveTasks() {
        questionViewModel.getQuestionList().observe(getViewLifecycleOwner(), new Observer<ArrayList<Question>>() {
            @Override
            public void onChanged(ArrayList<Question> questions) {
                data.clear();
                data.addAll(questions);
                questionAdapter.notifyDataSetChanged();
            }
        });
    }
    // Spinner
    String arr[] = {"Topic 1", "Topic 2"};

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onResume() {
        super.onResume();
        retrieveTasks();
    }


}