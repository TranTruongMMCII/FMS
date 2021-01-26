package com.example.feedback_management.Dialog;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.feedback_management.Feedback.FeedbackFragment;
import com.example.feedback_management.R;

public class SuccessDialog extends DialogFragment {
    private Button btnOK;
    private TextView tvAdd;
    public static SuccessDialog getInstance(int id) {
        SuccessDialog successDialog = new SuccessDialog();
        Bundle args = new Bundle();
        args.putInt("id", id);
        successDialog.setArguments(args);
        return successDialog;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.success_dialog, container, false);

        getDialog().getWindow().setBackgroundDrawableResource(R.drawable.round_button);

        btnOK = view.findViewById(R.id.btnYes);
        tvAdd = view.findViewById(R.id.tvAdd);

        SharedPreferences sharedPreferences = getContext().getSharedPreferences("fragment", 0);
        String text = sharedPreferences.getString("fragment_component", "DEFAULT");

        if(text.equals("edit_fragment")||text.equals("view_fragment")) {
            tvAdd.setText("Edit Success");
        }
        if(text.equals("delete_fragment")) {
            tvAdd.setText("Delete Success");
        }

        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FeedbackFragment feedbackFragment = new FeedbackFragment();

                int id = getArguments().getInt("id", 0);

                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(id, feedbackFragment, "findThisFragment")
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .addToBackStack(null)
                        .commit();
                dismiss();
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}





















