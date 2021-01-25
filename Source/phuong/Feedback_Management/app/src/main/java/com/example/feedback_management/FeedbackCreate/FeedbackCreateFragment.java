package com.example.feedback_management.FeedbackCreate;

import androidx.lifecycle.Observer;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.feedback_management.AppExecutors;
import com.example.feedback_management.Feedback.FeedbackFragment;
import com.example.feedback_management.Feedback.FeedbackViewModel;
import com.example.feedback_management.FeedbackReview.FeedbackReviewFragment;
import com.example.feedback_management.FeedbackReview.FeedbackReviewViewModel;
import com.example.feedback_management.R;
import com.example.feedback_management.adapter.QuestionAdapter;
import com.example.feedback_management.adapter.TopicAdapter;
import com.example.feedback_management.model.Feedback;
import com.example.feedback_management.model.Question;
import com.example.feedback_management.model.Topic;
import com.example.feedback_management.model.TypeFeedback;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class FeedbackCreateFragment extends Fragment {

    public static FeedbackCreateFragment getnstance() {
        return new FeedbackCreateFragment();
    }

    private View view;
    private Button btnReview;
    private Button btnBack;
    private Spinner spinnerTypeFeedback;
    private FeedbackViewModel feedbackViewModel;
    private FeedbackCreateViewModel feedbackCreateViewModel;
    private FeedbackReviewViewModel feedbackReviewViewModel;
    private RecyclerView rvQuestionList;
    private RecyclerView rvTopicList;
    private TextView tvFeedbackTitle;
    private EditText etFeedbackTitle;

    private ArrayList<Question> questionData;

    private ArrayList<Question> checkedQuetions = new ArrayList<>();
    private QuestionAdapter questionAdapter;

    private ArrayList<Topic> topicData;
    private TopicAdapter topicAdapter;

    Bundle bundle = new Bundle();

    private ArrayList<Question> getCheckedQuestion = new ArrayList<>();
    private ArrayList<Integer> count = new ArrayList<>();

    private int pos = 0;
    private long typeid = 0;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.feedback_create_fragment, container, false);
        feedbackViewModel = ViewModelProviders.of(this).get(FeedbackViewModel.class);
        feedbackCreateViewModel = ViewModelProviders.of(this).get(FeedbackCreateViewModel.class);
        feedbackReviewViewModel = ViewModelProviders.of(this).get(FeedbackReviewViewModel.class);
        btnReview = view.findViewById(R.id.btnReview);
        btnBack = view.findViewById(R.id.btnBack);
        spinnerTypeFeedback = view.findViewById(R.id.spinnerFeedbackType);
        tvFeedbackTitle = view.findViewById(R.id.tvFeedbackTitle);
        etFeedbackTitle = view.findViewById(R.id.etFeedbackTitle);

        rvTopicList = view.findViewById(R.id.rvFeedbackItem);
        rvTopicList.setLayoutManager(new LinearLayoutManager(getContext()));
        topicData = new ArrayList<>();

        topicAdapter = new TopicAdapter(getContext(), topicData, null);

        rvTopicList.setAdapter(topicAdapter);

        SharedPreferences sharedPreferences = getContext().getSharedPreferences("fragment", 0);
        String text = sharedPreferences.getString("fragment_component", "DEFAULT");

        bundle = this.getArguments();
        if(bundle!=null) {
            Feedback getCheckedFeedback = (Feedback) bundle.getParcelable("selected_feedback");

            if(getCheckedFeedback != null) {

                topicAdapter = new TopicAdapter(getContext(), topicData, getCheckedFeedback);

                rvTopicList.setAdapter(topicAdapter);

                etFeedbackTitle.setText(getCheckedFeedback.getTitle());

                feedbackCreateViewModel.getTypeFeedbackList().observe(getViewLifecycleOwner(), new Observer<ArrayList<TypeFeedback>>() {
                    @Override
                    public void onChanged(ArrayList<TypeFeedback> typeFeedbacks) {
                        ArrayList<String> getTypeName = new ArrayList<String>();
                        for(int i=0;i<typeFeedbacks.size();i++) {
                            getTypeName.add(typeFeedbacks.get(i).getTypeName());
                        }
                        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, getTypeName);
                        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinnerTypeFeedback.setAdapter(spinnerAdapter);

                        spinnerTypeFeedback.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                typeid = typeFeedbacks.get(position).getTypeID();
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });

                        for(int i=0;i<getTypeName.size();i++) {
                            if(getCheckedFeedback.getFeedback_typeID().getTypeName().equals(getTypeName.get(i))) {
                                pos = i;
                                break;
                            }
                        }

                        spinnerTypeFeedback.setSelection(pos);
                    }
                });

                if(text.equals("view_fragment")) {

                    btnReview.setText("SAVE");

                    feedbackCreateViewModel.getTopicList().observe(getViewLifecycleOwner(), new Observer<ArrayList<Topic>>() {
                        @Override
                        public void onChanged(ArrayList<Topic> topics) {

                            topicAdapter.setOnItemClickListener(new TopicAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(Topic topic) {

                                    getCheckedQuestion(topics, getCheckedFeedback);

                                }
                            });

                            getCheckedQuestion(topics, getCheckedFeedback);

                            btnBack.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    AppExecutors.getInstance().getDiskIO().execute(new Runnable() {
                                        @Override
                                        public void run() {
                                            FeedbackFragment feedbackFragment = new FeedbackFragment();
                                            getActivity().getSupportFragmentManager().beginTransaction()
                                                    .replace(((ViewGroup)getView().getParent()).getId(), feedbackFragment, "findThisFragment")
                                                    .addToBackStack(null)
                                                    .commit();
                                        }
                                    });
                                }
                            });
                        }
                    });

                    }
                    if(text.equals("edit_fragment"))  {
                        topicAdapter.setOnItemClickListener(new TopicAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(Topic topic) {
                                SharedPreferences appSharedPrefs = PreferenceManager.getDefaultSharedPreferences(getContext());

                                Gson gson = new Gson();
                                String json = appSharedPrefs.getString("count", "");

                                Type type = new TypeToken<ArrayList<Integer>>() { }.getType();
                                count = gson.fromJson(json, type);

                            }
                        });


                        btnReview.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            if(count.size()<topicData.size()) {
                                Toast.makeText(getContext(), "Xin hãy chọn ít nhất 1 câu hỏi", Toast.LENGTH_LONG).show();
                                return;
                            }

                            FeedbackReviewFragment fb = new FeedbackReviewFragment();
                            if(!etFeedbackTitle.getText().toString().equals("") && etFeedbackTitle.getText().length() > 0) {
                                Bundle bundle_review = new Bundle();
                                bundle_review.putString("feedbackTitle", etFeedbackTitle.getText().toString());
                                bundle_review.putLong("typeid", typeid);
                                bundle_review.putParcelable("selected_feedback_review", getCheckedFeedback);
                                fb.setArguments(bundle_review);

                                getActivity().getSupportFragmentManager().beginTransaction()
                                        .replace(((ViewGroup)getView().getParent()).getId(), fb, "findThisFragment")
                                        .addToBackStack(null)
                                        .commit();

                            }
                        }
                    });

                    btnBack.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            AppExecutors.getInstance().getDiskIO().execute(new Runnable() {
                                @Override
                                public void run() {
                                    FeedbackFragment fb = new FeedbackFragment();
                                    getActivity().getSupportFragmentManager().beginTransaction()
                                            .replace(((ViewGroup)getView().getParent()).getId(), fb, "findThisFragment")
                                            .addToBackStack(null)
                                            .commit();
                                }
                            });
                        }
                    });
                }

            }

        }
        if(text.equals("create_fragment")) {
            feedbackCreateViewModel.getTypeFeedbackList().observe(getViewLifecycleOwner(), new Observer<ArrayList<TypeFeedback>>() {
                @Override
                public void onChanged(ArrayList<TypeFeedback> typeFeedbacks) {
                    ArrayList<String> getTypeName = new ArrayList<String>();
                    for(int i=0;i<typeFeedbacks.size();i++) {
                        getTypeName.add(typeFeedbacks.get(i).getTypeName());
                    }
                    ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, getTypeName);
                    spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerTypeFeedback.setAdapter(spinnerAdapter);

                    spinnerTypeFeedback.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            typeid = typeFeedbacks.get(position).getTypeID();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });

                }
            });

            topicAdapter.setOnItemClickListener(new TopicAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(Topic topic) {
                    SharedPreferences appSharedPrefs = PreferenceManager.getDefaultSharedPreferences(getContext());

                    Gson gson = new Gson();
                    String json = appSharedPrefs.getString("count", "");

                    Type type = new TypeToken<ArrayList<Integer>>() { }.getType();
                    count = gson.fromJson(json, type);

                }
            });

            btnReview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(count.size()<topicData.size()) {
                        Toast.makeText(getContext(), "Xin hãy chọn ít nhất 1 câu hỏi", Toast.LENGTH_LONG).show();
                        return;
                    }

                    FeedbackReviewFragment fb = new FeedbackReviewFragment();

                    Bundle bundle_review = new Bundle();
                    bundle_review.putString("feedbackTitle", etFeedbackTitle.getText().toString());
                    bundle_review.putLong("typeid", typeid);
                    fb.setArguments(bundle_review);

                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(((ViewGroup)getView().getParent()).getId(), fb, "findThisFragment")
                            .addToBackStack(null)
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
                            .commit();
                }
            });

        }

        return view;
    }

    private void retrieveTasks() {
        feedbackCreateViewModel.getTopicList().observe(getViewLifecycleOwner(), new Observer<ArrayList<Topic>>() {
            @Override
            public void onChanged(ArrayList<Topic> topics) {
                topicData.clear();
                topicData.addAll(topics);
                topicAdapter.notifyDataSetChanged();
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        retrieveTasks();
    }

    private void getCheckedQuestion(ArrayList<Topic> topics, Feedback getCheckedFeedback) {
        topicAdapter.setOnItemClickListener(new TopicAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Topic topic) {
                SharedPreferences appSharedPrefs = PreferenceManager.getDefaultSharedPreferences(getContext());

                Gson gson = new Gson();
                String json = appSharedPrefs.getString("count", "");

                Type type = new TypeToken<ArrayList<Integer>>() { }.getType();
                count = gson.fromJson(json, type);

            }
        });

        SharedPreferences appSharedPrefs = PreferenceManager.getDefaultSharedPreferences(getContext());

        Gson gson = new Gson();
        String json = appSharedPrefs.getString("checked_question_set", "");

        Type type = new TypeToken<ArrayList<Question>>() { }.getType();

        getCheckedQuestion = gson.fromJson(json, type);

        ArrayList<Long> getAllQuestionsID = new ArrayList<>();
        ArrayList<Long> getCheckedQuestionID = new ArrayList<>();

        for(int i=0;i<topics.size();i++) {
            for(int j=0;j<topics.get(i).getQuestions().size();j++) {
                getAllQuestionsID.add(topics.get(i).getQuestions().get(j).getQuestionID());
            }
        }

        for(int i=0;i<getCheckedQuestion.size();i++) {
            getCheckedQuestionID.add(getCheckedQuestion.get(i).getQuestionID());
        }


        getAllQuestionsID.removeAll(getCheckedQuestionID);

        ArrayList<Long> getUncheckedQuestionID = new ArrayList<>(getAllQuestionsID);

        btnReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AppExecutors.getInstance().getDiskIO().execute(new Runnable() {
                    @Override
                    public void run() {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if(count.size()<topicData.size()) {
                                    Toast.makeText(getContext(), "Xin hãy chọn ít nhất 1 câu hỏi", Toast.LENGTH_LONG).show();
                                    return;
                                }
                                else {
                                    try {

                                        for(int i=0;i<getUncheckedQuestionID.size();i++) {
                                            feedbackReviewViewModel.deleteFeedbackQuestion(getCheckedFeedback.getFeedbackID(), getUncheckedQuestionID.get(i));
                                        }

                                        for(int i=0;i<getCheckedQuestionID.size();i++) {
                                            feedbackReviewViewModel.insertFeedbackQuestion(getCheckedFeedback.getFeedbackID(), getCheckedQuestionID.get(i));
                                        }


                                        feedbackReviewViewModel.updateFeedback(getCheckedFeedback.getFeedbackID(), typeid, etFeedbackTitle.getText().toString());

                                        FeedbackFragment feedbackFragment = new FeedbackFragment();


                                        getActivity().getSupportFragmentManager().beginTransaction()
                                                .replace(((ViewGroup)getView().getParent()).getId(), feedbackFragment, "findThisFragment")
                                                .addToBackStack(null)
                                                .commit();

                                    }
                                    catch (Exception e) { }
                                }
                            }
                        });

                    }
                });


            }
        });



    }

}