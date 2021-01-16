package com.example.fms_android.fragment;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.fms_android.R;
import com.example.fms_android.activity.AssignmentActivity;
import com.example.fms_android.activity.UserActivity;
import com.example.fms_android.adapter.AssignmentAdapter;
import com.example.fms_android.adapter.UserAdapter;
import com.example.fms_android.model.Assignment;
import com.example.fms_android.model.User;
import com.example.fms_android.viewmodel.AssignmentViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class AssignmentFragment extends Fragment {
    private AssignmentViewModel assignmentViewModel;
    private RecyclerView recyclerView;
    private ArrayList<Assignment> data;
    private AssignmentAdapter assignmentAdapter;
    private FloatingActionButton fabAdd;
    private View view;
    private EditText etSearch;
    private Button btnsearch;

    public static AssignmentFragment getInstance() {
        return new AssignmentFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        assignmentViewModel = ViewModelProviders.of(this).get(AssignmentViewModel.class);
        view = inflater.inflate(R.layout.fragment_assignment, container, false);
        recyclerView = view.findViewById(R.id.rvAssignment);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        fabAdd = view.findViewById(R.id.fabAddAssign);
        etSearch = view.findViewById(R.id.etSearchAssign);
        btnsearch = view.findViewById(R.id.btnSearchAssign);

        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), AssignmentActivity.class));
            }
        });

        data = new ArrayList<>();
        assignmentAdapter = new AssignmentAdapter(getContext(), data);

        recyclerView.setAdapter(assignmentAdapter);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                List<Assignment> tasks = assignmentAdapter.getAssignments();
                Assignment assignment = tasks.get(position);
//                assignmentViewModel.deleteAssignment(assignment.getClassId(), assignment.getModuleId().getModuleID(), assignment.getTrainerId());
                retrieveTasks();
            }
        }).attachToRecyclerView(recyclerView);

        return view;
    }

    private void retrieveTasks() {
        assignmentViewModel.getAssignmentList().observe(getViewLifecycleOwner(), assignments ->  {
            data.clear();
            data.addAll(assignments);
            assignmentAdapter.notifyDataSetChanged();
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        retrieveTasks();
    }

//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        assignmentViewModel = new ViewModelProvider(this).get(AssignmentViewModel.class);
//        // TODO: Use the ViewModel
//    }

}