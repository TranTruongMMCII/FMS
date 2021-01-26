package com.example.feedback_management.Dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.feedback_management.Feedback.FeedbackViewModel;
import com.example.feedback_management.R;
import com.example.feedback_management.adapter.FeedbackAdapter;
import com.example.feedback_management.model.Feedback;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

public class DeleteDialog extends DialogFragment {
    private Button btnYes;
    private Button btnCancel;
    private TextView tvDelete;
    private FeedbackViewModel feedbackViewModel;

    private ArrayList<Feedback> data;

    private FeedbackAdapter feedbackAdapter;

    public static DeleteDialog getInstance(int id, Feedback feedback, int position) {
        DeleteDialog deleteDialog = new DeleteDialog();
        Bundle args = new Bundle();
        args.putInt("id", id);
        args.putInt("position", position);
        args.putParcelable("feedback", feedback);
        deleteDialog.setArguments(args);
        return deleteDialog;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.delete_dialog, container, false);
        feedbackViewModel = ViewModelProviders.of(this).get(FeedbackViewModel.class);
        getDialog().getWindow().setBackgroundDrawableResource(R.drawable.round_button);

        btnYes = view.findViewById(R.id.btnYes);
        btnCancel = view.findViewById(R.id.btnCancel);
        tvDelete = view.findViewById(R.id.tvDelete);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd").withZone(ZoneId.systemDefault());
        LocalDate today = LocalDate.now();
        Date today_date = java.sql.Date.valueOf(dtf.format(today));

        data = new ArrayList<>();
        feedbackAdapter = new FeedbackAdapter(getContext(), data);
        feedbackAdapter.notifyDataSetChanged();

        int count = 0;
        int id = getArguments().getInt("id", 0);
        int pos = getArguments().getInt("position", 0);
        Feedback feedback = getArguments().getParcelable("feedback");

        for(int i=0;i<feedback.getModules().size();i++) {
            Date start_Feedback = java.sql.Date.valueOf(dtf.format(feedback.getModules().get(i).getFeedbackStartTime().toInstant()));
            Date end_Feedback = java.sql.Date.valueOf(dtf.format(feedback.getModules().get(i).getFeedbackEndTime().toInstant()));
            if(today_date.after(start_Feedback) && today_date.before(end_Feedback))
                count++;
        }

        if(count > 0) {
            tvDelete.setText("Feedback is being used by "+count+" module. Do you really want to delete this feedback?");
        }

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                feedbackViewModel.deleteFeedback(feedback.getFeedbackID());
                feedbackAdapter.getFeedbacks().remove(pos);
                feedbackAdapter.notifyItemRemoved(pos);

                SuccessDialog successDialog = SuccessDialog.getInstance(id);
                successDialog.show(getChildFragmentManager(), null);

            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });


//        feedbackViewModel.deleteFeedback(feedback.getFeedbackID());

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void retrieveTasks() {
        feedbackViewModel.getFeedbackList().observe(getViewLifecycleOwner(), new Observer<ArrayList<Feedback>>() {
            @Override
            public void onChanged(ArrayList<Feedback> feedbacks) {
                data.clear();
                data.addAll(feedbacks);
                feedbackAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        retrieveTasks();
    }
}
