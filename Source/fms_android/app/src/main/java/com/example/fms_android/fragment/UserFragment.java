package com.example.fms_android.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fms_android.R;
import com.example.fms_android.activity.UserActivity;
import com.example.fms_android.adapter.UserAdapter;
import com.example.fms_android.model.User;
import com.example.fms_android.viewmodel.UserViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class UserFragment extends DialogFragment {
    private UserViewModel userViewModel;
    private RecyclerView recyclerView;
    private ArrayList<User> data;
    private UserAdapter userAdapter;
    private FloatingActionButton fabAdd;
    private View view;

    public static UserFragment getInstance(){
        return new UserFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        System.out.println("hello");
        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        view = inflater.inflate(R.layout.fragment_user, container,false);

        recyclerView = view.findViewById(R.id.rvAssignment);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        fabAdd = view.findViewById(R.id.fabAddAssign);
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), UserActivity.class));
            }
        });

        data = new ArrayList<>();
        userAdapter = new UserAdapter(getContext(), data);

        recyclerView.setAdapter(userAdapter);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                List<User> tasks = userAdapter.getTasks();
                User user = tasks.get(position);
                userViewModel.deleteUser(user.getId());
                retrieveTasks();
            }
        }).attachToRecyclerView(recyclerView);

        return view;
    }

    private void retrieveTasks() {
        userViewModel.getUserList().observe(getViewLifecycleOwner(), new Observer<ArrayList<User>>() {
            @Override
            public void onChanged(ArrayList<User> users) {
                data.clear();
                data.addAll(users);
                userAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        retrieveTasks();
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
}
