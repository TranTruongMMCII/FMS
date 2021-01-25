package com.example.feedback_management.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.feedback_management.Feedback.FeedbackFragment;
import com.example.feedback_management.FeedbackCreate.FeedbackCreateFragment;
import com.example.feedback_management.R;
import com.example.feedback_management.model.Feedback;
import com.example.feedback_management.model.Feedback_Question;
import com.example.feedback_management.model.Question;
import com.example.feedback_management.service.FeedbackService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.MyViewHolder>  {

    private Context context;
    ArrayList<Question> questions;
    ArrayList<Question> checkedQuetions = new ArrayList<>();
    private OnItemClickListener listener;
    private FeedbackService feedbackService;

    public static QuestionAdapter getInstance() { return new QuestionAdapter(); }

    @NonNull
    @Override
    public QuestionAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.question_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionAdapter.MyViewHolder holder, int position) {


        if(questions != null && questions.size() > 0) {
            Question question = questions.get(position);
            holder.chkQuestionitem.setText(question.getQuesionContent());

            SharedPreferences sharedPreferences = context.getSharedPreferences("fragment", 0);
            String text = sharedPreferences.getString("fragment_component", "DEFAULT");

            if(text.equals("create_fragment")) {
                holder.chkQuestionitem.setChecked(false);
            }
            else {
                SharedPreferences appSharedPrefs = PreferenceManager
                        .getDefaultSharedPreferences(context);
                Gson gson = new Gson();
                String json = appSharedPrefs.getString("checked_question", "");

                Type type = new TypeToken<ArrayList<Feedback_Question>>() { }.getType();

                ArrayList<Feedback_Question> getCheckedQuestionID = gson.fromJson(json, type);

                for(int i=0;i<getCheckedQuestionID.size();i++) {
                    if(question.getQuestionID().equals(getCheckedQuestionID.get(i).getFeedback_QuestionKey().getQuestion_feedback_question().getQuestionID())) {
                        holder.chkQuestionitem.setChecked(true);
                    }
                }
            }



        }



    }

    @Override
    public int getItemCount() {
        if(questions == null) {
            return 0;
        }
        return questions.size();
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
        notifyDataSetChanged();
    }

    public QuestionAdapter() {
        super();
    }

    public ArrayList<Question> getCheckedQuetions() {
        return checkedQuetions;
    }

    public void setCheckedQuetions(ArrayList<Question> checkedQuetions) {
        this.checkedQuetions = checkedQuetions;
    }

    public QuestionAdapter(Context context, ArrayList<Question> questions) {
        this.context = context;
        this.questions = questions;
    }

    public QuestionAdapter(ArrayList<Question> checkedQuetions, OnItemClickListener onItemClickListener) {
        this.checkedQuetions = checkedQuetions;
        this.listener = onItemClickListener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        CheckBox chkQuestionitem;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            chkQuestionitem = itemView.findViewById(R.id.chkQuestionItem);

            chkQuestionitem.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        int position = getBindingAdapterPosition();
                        if(listener != null && position != RecyclerView.NO_POSITION) {
                            listener.onItemCheck(questions.get(position));

                        }
                    }
                    else {
                        int position = getBindingAdapterPosition();
                        if(listener != null && position != RecyclerView.NO_POSITION) {
                            listener.onItemUncheck(questions.get(position));
                        }
                    }

//                    Toast.makeText(context, "cac", Toast.LENGTH_LONG).show();
                }
            });



        }

    }

    public interface OnItemClickListener {
        void onItemCheck(Question question);
        void onItemUncheck(Question question);
    }

    public void setOnItemClickListener(QuestionAdapter.OnItemClickListener listener) {
        this.listener = listener;
    }


}
