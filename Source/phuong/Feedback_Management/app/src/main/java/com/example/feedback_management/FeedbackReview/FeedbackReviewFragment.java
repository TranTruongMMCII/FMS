package com.example.feedback_management.FeedbackReview;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.preference.PreferenceManager;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.feedback_management.AppExecutors;
import com.example.feedback_management.Dialog.SuccessDialog;
import com.example.feedback_management.Feedback.FeedbackFragment;
import com.example.feedback_management.FeedbackCreate.FeedbackCreateFragment;
import com.example.feedback_management.FeedbackCreate.FeedbackCreateViewModel;
import com.example.feedback_management.R;
import com.example.feedback_management.adapter.QuestionAdapter;
import com.example.feedback_management.adapter.TopicAdapter;
import com.example.feedback_management.model.Feedback;
import com.example.feedback_management.model.Feedback_Question;
import com.example.feedback_management.model.Question;
import com.example.feedback_management.model.Topic;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class FeedbackReviewFragment extends Fragment {

    private FeedbackReviewViewModel mViewModel;
    private Button btnBack;
    private Button btnSave;

    private FeedbackCreateViewModel feedbackCreateViewModel;
    private FeedbackReviewViewModel feedbackReviewViewModel;
    public static FeedbackReviewFragment newInstance() {
        return new FeedbackReviewFragment();
    }

    private View view;
    private ArrayList<Topic> topicData;
    private ArrayList<Question> questionData;
    private TopicAdapter topicAdapter;
    private QuestionAdapter questionAdapter;
    private RecyclerView rvQuestionCheckedList;
    private TextView tvFeedbackTitle;
    private TextView tvAdminID;
    private TextView tvCheckedQuestion;
    private TextView tvFeedbackReview;

    private ArrayList<Question> getCheckedQuestion = new ArrayList<>();

    Bundle bundle = new Bundle();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.feedback_review_fragment, container, false);
        feedbackReviewViewModel = ViewModelProviders.of(this).get(FeedbackReviewViewModel.class);
        btnBack = view.findViewById(R.id.btnBack);
        btnSave = view.findViewById(R.id.btnSave);
        tvFeedbackTitle = view.findViewById(R.id.tvFeedbackTitle);
        tvAdminID = view.findViewById(R.id.tvAdminID);
        tvCheckedQuestion = view.findViewById(R.id.tvCheckedQuestion);
        tvFeedbackReview = view.findViewById(R.id.tvFeedbackReview);

        topicData = new ArrayList<>();

        bundle = this.getArguments();
        if(bundle != null) {
            String getFeedbackTitle = bundle.getString("feedbackTitle");
            tvFeedbackTitle.setText(getFeedbackTitle);
            tvFeedbackTitle.setTextColor(Color.RED);
            Feedback getCheckedFeedback = (Feedback) bundle.getParcelable("selected_feedback_review");

            if(getCheckedFeedback != null) {
                btnBack.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        FeedbackCreateFragment fb = new FeedbackCreateFragment();
                        Bundle bundle = new Bundle();
                        bundle.putParcelable("selected_feedback", getCheckedFeedback);
                        fb.setArguments(bundle);
                        getActivity().getSupportFragmentManager().beginTransaction()
                                .replace(((ViewGroup)getView().getParent()).getId(), fb, "findThisFragment")
                                .addToBackStack(null)
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                                .commit();
                    }
                });
            }
            else {
                btnBack.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        FeedbackCreateFragment fb = new FeedbackCreateFragment();
                        getActivity().getSupportFragmentManager().beginTransaction()
                                .replace(((ViewGroup)getView().getParent()).getId(), fb, "findThisFragment")
                                .addToBackStack(null)
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                                .commit();
                    }
                });

            }

        }

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(FeedbackReviewViewModel.class);

    }

    private void retrieveTasks() {
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("fragment", 0);
        String text = sharedPreferences.getString("fragment_component", "DEFAULT");
        feedbackReviewViewModel.getTopicList().observe(getViewLifecycleOwner(), new Observer<ArrayList<Topic>>() {
            @Override
            public void onChanged(ArrayList<Topic> topics) {

                topicData.clear();
                topicData.addAll(topics);

                SharedPreferences appSharedPrefs = PreferenceManager.getDefaultSharedPreferences(getContext());

                Gson gson = new Gson();
                String json = appSharedPrefs.getString("checked_question_set", "");

                Type type = new TypeToken<ArrayList<Question>>() { }.getType();

                getCheckedQuestion = gson.fromJson(json, type);


                ArrayList<Long> getAllQuestionsID = new ArrayList<>();
                ArrayList<Long> getCheckedQuestionID = new ArrayList<>();

                for(int i=0;i<topicData.size();i++) {
                    for(int j=0;j<topicData.get(i).getQuestions().size();j++) {
                        getAllQuestionsID.add(topicData.get(i).getQuestions().get(j).getQuestionID());
                    }
                }

                for(int i=0;i<getCheckedQuestion.size();i++) {
                    getCheckedQuestionID.add(getCheckedQuestion.get(i).getQuestionID());
                }

                getAllQuestionsID.removeAll(getCheckedQuestionID);

                ArrayList<Long> getUncheckedQuestionID = new ArrayList<>(getAllQuestionsID);

                Bundle bundle = getArguments();

                if(bundle != null) {
                    Feedback getCheckedFeedback = (Feedback) bundle.getParcelable("selected_feedback_review");
                    if(getCheckedFeedback != null) {

                        long typeid = bundle.getLong("typeid");

                        String title = tvFeedbackTitle.getText().toString();


                        if(text.equals("view_fragment"))
                        {
                            tvFeedbackReview.setText("Detail Feedback");

                            tvAdminID.setText(getCheckedFeedback.getAdminID().getUserName());
                            tvAdminID.setTextColor(Color.RED);

                            loadCheckedQuestions_view(topics, getCheckedFeedback);

                            btnSave.setText("EDIT");
                            btnSave.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    FeedbackCreateFragment fb = new FeedbackCreateFragment();
                                    Bundle bundle = new Bundle();
                                    bundle.putParcelable("selected_feedback", getCheckedFeedback);
                                    bundle.putString("save", "SAVE");
                                    bundle.putString("fragment","view_fragment");
                                    fb.setArguments(bundle);
                                    getActivity().getSupportFragmentManager().beginTransaction()
                                            .replace(((ViewGroup)getView().getParent()).getId(), fb, "findThisFragment")
                                            .addToBackStack(null)
                                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                                            .commit();
                                }
                            });

                            btnBack.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    FeedbackFragment fb = new FeedbackFragment();
                                    getActivity().getSupportFragmentManager().beginTransaction()
                                            .replace(((ViewGroup)getView().getParent()).getId(), fb, "findThisFragment")
                                            .addToBackStack(null)
                                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                                            .commit();

                                }
                            });


                        }
                        if(text.equals("edit_fragment")) {

                            loadCheckedQuestions(topics);

                            tvFeedbackReview.setText("Review Edit Feedback");

                            Long feedbackId = getCheckedFeedback.getFeedbackID();



                            tvAdminID.setText(getCheckedFeedback.getAdminID().getUserName());
                            tvAdminID.setTextColor(Color.RED);

                            btnSave.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    AppExecutors.getInstance().getDiskIO().execute(new Runnable() {
                                        @Override
                                        public void run() {
                                            try {
                                                for(int i=0;i<getUncheckedQuestionID.size();i++) {
                                                    feedbackReviewViewModel.deleteFeedbackQuestion(feedbackId, getUncheckedQuestionID.get(i));
                                                }

                                                for(int i=0;i<getCheckedQuestionID.size();i++) {
                                                    feedbackReviewViewModel.insertFeedbackQuestion(feedbackId, getCheckedQuestionID.get(i));
                                                }

                                                feedbackReviewViewModel.updateFeedback(getCheckedFeedback.getFeedbackID(), typeid, title);

                                                SuccessDialog successDialog = SuccessDialog.getInstance(((ViewGroup)getView().getParent()).getId());
                                                successDialog.show(getChildFragmentManager(), null);

                                            }
                                            catch (Exception e) { }
                                        }
                                    });
                                }
                            });
                        }


                    }

                    if(text.equals("create_fragment")) {
                        loadCheckedQuestions(topics);

                        tvAdminID.setText("admin");
                        tvAdminID.setTextColor(Color.RED);

                        long typeid = bundle.getLong("typeid");

                        String title = tvFeedbackTitle.getText().toString();

                        String admin = "admin";

                        feedbackReviewViewModel.insertFeedback(title, admin, typeid);

                        btnSave.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                feedbackReviewViewModel.getLastFeedback().observe(getViewLifecycleOwner(), new Observer<Feedback>() {
                                    @Override
                                    public void onChanged(Feedback feedback) {
                                        Long feedbackId = feedback.getFeedbackID();

                                        for(int i=0;i<getCheckedQuestion.size();i++) {
                                            feedbackReviewViewModel.insertFeedbackQuestion(feedbackId, getCheckedQuestion.get(i).getQuestionID());
                                        }

                                        SuccessDialog successDialog = SuccessDialog.getInstance(((ViewGroup)getView().getParent()).getId());
                                        successDialog.show(getChildFragmentManager(), null);

                                    }
                                });


                            }
                        });

                        btnBack.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                feedbackReviewViewModel.getLastFeedback().observe(getViewLifecycleOwner(), new Observer<Feedback>() {
                                    @Override
                                    public void onChanged(Feedback feedback) {
                                        Long feedbackId = feedback.getFeedbackID();
                                        feedbackReviewViewModel.deleteFeedback(feedbackId);

                                        FeedbackCreateFragment fb = new FeedbackCreateFragment();
                                        Bundle bundle = new Bundle();
                                        bundle.putParcelable("selected_feedback", getCheckedFeedback);
                                        fb.setArguments(bundle);
                                        getActivity().getSupportFragmentManager().beginTransaction()
                                                .replace(((ViewGroup)getView().getParent()).getId(), fb, "findThisFragment")
                                                .addToBackStack(null)
                                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                                                .commit();
                                    }
                                });
                            }
                        });


                    }


                }

            }
        });
    }

    private void loadCheckedQuestions(ArrayList<Topic> topics) {
        String tmp = "";
        for(int i=0;i<topics.size();i++) {
            tmp+="<b>"+topics.get(i).getTopicName()+"</b>"+"<br>";
            for(int k=0;k<topics.get(i).getQuestions().size();k++) {
                for(int j=0;j<getCheckedQuestion.size();j++) {
                    if(getCheckedQuestion.get(j).getQuestionID().equals(topics.get(i).getQuestions().get(k).getQuestionID())) {
                        tmp+="-"+getCheckedQuestion.get(j).getQuesionContent()+"<br>";
                        break;
                    }
                }
            }
        }

        tvCheckedQuestion.setText(Html.fromHtml(tmp));

    }

    private void loadCheckedQuestions_view(ArrayList<Topic> topics, Feedback getCheckedFeedback) {
        String tmp = "";
        for(int i=0;i<topics.size();i++) {
            tmp+="<b>"+topics.get(i).getTopicName()+"</b>"+"<br>";
            for(int k=0;k<topics.get(i).getQuestions().size();k++) {
                for(int j=0;j<getCheckedFeedback.getFeedback_Questions().size();j++) {
                    Long getChckQuestionId = getCheckedFeedback.getFeedback_Questions().get(j).getFeedback_QuestionKey().getQuestion_feedback_question().getQuestionID();
                    Long getQuestionId = topics.get(i).getQuestions().get(k).getQuestionID();
                    String getChckQuestionContent = getCheckedFeedback.getFeedback_Questions().get(j).getFeedback_QuestionKey().getQuestion_feedback_question().getQuesionContent();
                    if(getChckQuestionId.equals(getQuestionId)) {
                        tmp+="-"+getChckQuestionContent+"<br>";
                        break;
                    }

                }
            }
        }

        tvCheckedQuestion.setText(Html.fromHtml(tmp));
    }

    @Override
    public void onResume() {
        super.onResume();
        retrieveTasks();
    }

}