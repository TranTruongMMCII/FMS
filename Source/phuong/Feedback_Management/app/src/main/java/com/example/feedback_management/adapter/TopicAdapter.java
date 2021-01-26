package com.example.feedback_management.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.feedback_management.FeedbackCreate.FeedbackCreateFragment;
import com.example.feedback_management.R;
import com.example.feedback_management.model.Feedback;
import com.example.feedback_management.model.Feedback_Question;
import com.example.feedback_management.model.Question;
import com.example.feedback_management.model.Topic;
import com.example.feedback_management.service.QuestionService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TopicAdapter extends RecyclerView.Adapter<TopicAdapter.MyViewHolder>  {

    private Context context;
    private ArrayList<Topic> topics;
    private Topic topic;
    private ArrayList<Question> checkedQuetions = new ArrayList<>();
    private OnItemClickListener listener;
    private Feedback feedback;
    private ArrayList<Integer> count_check = new ArrayList<>();


    private QuestionService questionService;

    public static TopicAdapter getInstance() { return new TopicAdapter(); }

    public TopicAdapter() {
        super();
    }

    public TopicAdapter(Context context, ArrayList<Topic> topics, Feedback feedback) {
        this.context = context;
        this.topics = topics;
        this.feedback = feedback;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.topic_question_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Topic topic = topics.get(position);
        holder.tvTopicTitle.setText(topic.getTopicName());

        QuestionAdapter questionAdapter = new QuestionAdapter(context, topic.getQuestions());
        holder.rvQuestion.setLayoutManager(new LinearLayoutManager(context));
        holder.rvQuestion.setAdapter(questionAdapter);

        questionAdapter.setOnItemClickListener(new QuestionAdapter.OnItemClickListener() {
            @Override
            public void onItemCheck(Question question) {
                checkedQuetions.add(question);
                count_check.add(Integer.parseInt(topic.getTopicID().toString()));
                saveCheckedQuestion(checkedQuetions, count_check);
//                saveCountCheck(count_check);

                if(listener != null && position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(topics.get(position));
                }

            }

            @Override
            public void onItemUncheck(Question question) {
                checkedQuetions.remove(question);
//                saveCheckedQuestion(checkedQuetions);

                for(int i=0;i<count_check.size();i++) {
                    if(count_check.get(i) == Integer.parseInt(topic.getTopicID().toString())) {
                        count_check.remove(i);
//                        saveCountCheck(count_check);
                        saveCheckedQuestion(checkedQuetions, count_check);
                        break;
                    }
                }

                if(listener != null && position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(topics.get(position));
                }

            }
        });

        ArrayList<Question> checked_questionID = new ArrayList<>();

        if(feedback != null) {
            List<Feedback_Question> feedback_questions = feedback.getFeedback_Questions();

            for(int i=0;i<feedback_questions.size();i++) {
                checked_questionID.add(feedback.getFeedback_Questions().get(i).getFeedback_QuestionKey().getQuestion_feedback_question());
            }

            SharedPreferences appSharedPrefs = PreferenceManager
                    .getDefaultSharedPreferences(context);
            SharedPreferences.Editor prefsEditor = appSharedPrefs.edit();
            Gson gson = new Gson();
            String json = gson.toJson(feedback_questions);
            prefsEditor.putString("checked_question", json);
            prefsEditor.commit();
        }


    }

    @Override
    public int getItemCount() {
        if(topics == null)
            return 0;
        return topics.size();
    }

    public ArrayList<Topic> getTopics() {
        return topics;
    }

    public void setTopics(ArrayList<Topic> topics) {
        this.topics = topics;
    }

    public ArrayList<Question> getCheckedQuetions() {
        return checkedQuetions;
    }

    public void setCheckedQuetions(ArrayList<Question> checkedQuetions) {
        this.checkedQuetions = checkedQuetions;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvTopicTitle;
        private RecyclerView rvQuestion;
        private CheckBox chkQuestionitem;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTopicTitle = itemView.findViewById(R.id.tvTitle);
            rvQuestion = itemView.findViewById(R.id.rvTopic_Question);

        }
    }

    public interface OnItemClickListener {
        void onItemClick(Topic topic);
    }

    public void setOnItemClickListener(TopicAdapter.OnItemClickListener listener) {
        this.listener = listener;
    }


    private void saveCheckedQuestion(ArrayList<Question> checkedQuetions, ArrayList<Integer> count) {
        SharedPreferences appSharedPrefs_review_question = PreferenceManager
                .getDefaultSharedPreferences(context);
        SharedPreferences.Editor prefsEditor_review = appSharedPrefs_review_question.edit();
        Gson gson_review = new Gson();

        String json_review_question = gson_review.toJson(checkedQuetions);
        prefsEditor_review.putString("checked_question_set", json_review_question);

        String json_review_count = gson_review.toJson(count);
        prefsEditor_review.putString("count", json_review_count);

        prefsEditor_review.commit();
    }

//    private void saveCountCheck(ArrayList<Integer> count) {
//        SharedPreferences appSharedPrefs_review = PreferenceManager
//                .getDefaultSharedPreferences(context);
//        SharedPreferences.Editor prefsEditor_review = appSharedPrefs_review.edit();
//        Gson gson_review = new Gson();
//        prefsEditor_review.commit();
//    }


}
