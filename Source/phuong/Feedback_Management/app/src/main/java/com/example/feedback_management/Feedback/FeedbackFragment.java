package com.example.feedback_management.Feedback;

import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.feedback_management.AppExecutors;
import com.example.feedback_management.Dialog.DeleteDialog;
import com.example.feedback_management.Dialog.SuccessDialog;
import com.example.feedback_management.FeedbackCreate.FeedbackCreateFragment;
import com.example.feedback_management.FeedbackReview.FeedbackReviewFragment;
import com.example.feedback_management.R;
import com.example.feedback_management.adapter.FeedbackAdapter;
import com.example.feedback_management.adapter.QuestionAdapter;
import com.example.feedback_management.adapter.TopicAdapter;
import com.example.feedback_management.model.Feedback;
import com.example.feedback_management.model.Topic;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class FeedbackFragment extends Fragment {

    private FeedbackViewModel feedbackViewModel;
    private RecyclerView recyclerView;
    private ArrayList<Feedback> data;
    private FeedbackAdapter feedbackAdapter;
    private View view;
    private FloatingActionButton fabAdd;
    public static FeedbackFragment getnstance() {
        return new FeedbackFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        feedbackViewModel = ViewModelProviders.of(this).get(FeedbackViewModel.class);
        view = inflater.inflate(R.layout.feedback_fragment, container, false);

        recyclerView = view.findViewById(R.id.rvFeedback);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        data = new ArrayList<>();
        feedbackAdapter = new FeedbackAdapter(getContext(), data);
        feedbackAdapter.notifyDataSetChanged();

        recyclerView.setAdapter(feedbackAdapter);

        feedbackAdapter.setOnDeleteClickListener(new FeedbackAdapter.OnDeleteClickListener() {
            @Override
            public void onDeleteClick(Feedback feedback, int pos) {
                SharedPreferences sharedPreferences = getContext().getSharedPreferences("fragment", 0);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("fragment_component", "delete_fragment");
                editor.apply();

                DeleteDialog deleteDialog = DeleteDialog.getInstance(((ViewGroup)getView().getParent()).getId(), feedback, pos);
                deleteDialog.show(getChildFragmentManager(), null);
            }
        });


        feedbackAdapter.setOnViewClickListener(new FeedbackAdapter.OnViewClickListener() {
            @Override
            public void onViewClick(Feedback feedback) {
                SharedPreferences sharedPreferences = getContext().getSharedPreferences("fragment", 0);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("fragment_component", "view_fragment");
                editor.apply();

                FeedbackReviewFragment fb = new FeedbackReviewFragment();
                Bundle bundle_review = new Bundle();
                bundle_review.putString("feedbackTitle", feedback.getTitle());
                bundle_review.putLong("typeid", feedback.getFeedback_typeID().getTypeID());
                bundle_review.putParcelable("selected_feedback_review", feedback);
                bundle_review.putString("edit_fragment", "EDIT");
                fb.setArguments(bundle_review);

                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(((ViewGroup)getView().getParent()).getId(), fb, "findThisFragment")
                        .addToBackStack(null)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit();
            }
        });


        feedbackAdapter.setOnEditClickListener(new FeedbackAdapter.OnEditClickListener() {
            @Override
            public void onEditClick(Feedback feedback) {
                SharedPreferences sharedPreferences = getContext().getSharedPreferences("fragment", 0);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("fragment_component", "edit_fragment");
                editor.apply();


                FeedbackCreateFragment feedbackCreateFragment = new FeedbackCreateFragment();
                Bundle bundle = new Bundle();
                bundle.putParcelable("selected_feedback", feedback);
                feedbackCreateFragment.setArguments(bundle);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(((ViewGroup)getView().getParent()).getId(), feedbackCreateFragment, "findThisFragment")
                        .addToBackStack(null)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit();

            }
        });

        fabAdd = view.findViewById(R.id.fabAdd);

        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getContext().getSharedPreferences("fragment", 0);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("fragment_component", "create_fragment");
                editor.commit();

                FeedbackCreateFragment fb = new FeedbackCreateFragment();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(((ViewGroup)getView().getParent()).getId(), fb, "findThisFragment")
                        .addToBackStack(null)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit();
            }
        });



        return view;
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