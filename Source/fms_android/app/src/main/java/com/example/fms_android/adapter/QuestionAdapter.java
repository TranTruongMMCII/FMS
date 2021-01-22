package com.example.fms_android.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fms_android.Constants;
import com.example.fms_android.R;
import com.example.fms_android.activity.QuestionEditActivity;
import com.example.fms_android.activity.UserActivity;
import com.example.fms_android.model.Question;

import java.util.ArrayList;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<Question> questions;

    public QuestionAdapter(Context context, ArrayList<Question> questions) {
        this.context = context;
        this.questions = questions;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.question_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Question question = questions.get(position);
        holder.txtTopicID.setText("Topic Id: "  + question.getTopicID());
        holder.txtTopicName.setText("Topic Name: "  + question.getNameTopic());
        holder.txtQuestionID.setText("Question Id: "  + question.getQuestionID());
        holder.txtQuestionContent.setText("Question Content: "  + question.getQuestionContent());



    }

    @Override
    public int getItemCount() {
        if(questions == null){
            return 0;
        }
        return questions.size();
    }

    public ArrayList<Question> getTasks(){
        return questions;
    }

    public void setTasks(ArrayList<Question> questions){
        this.questions = questions;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txtTopicID;
        TextView txtTopicName;
        TextView txtQuestionID;
        TextView txtQuestionContent;
        ImageView imageEdit;
        ImageView imageDelete;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            txtTopicID = itemView.findViewById(R.id.tvQuestionID);
            txtTopicName = itemView.findViewById(R.id.tvTopicName);
            txtQuestionID = itemView.findViewById(R.id.tvQuestionID);
            txtQuestionContent = itemView.findViewById(R.id.tvQuestionContent);
            imageEdit = itemView.findViewById(R.id.ivEditQues);
            imageDelete = itemView.findViewById(R.id.ivDeleteQues);


            imageEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    long elementID = questions.get(getAdapterPosition()).getQuestionID();
                    Intent intent = new Intent(context, QuestionEditActivity.class);
                    intent.putExtra(Constants.UPDATE_USER_ID, elementID);
                    context.startActivity(intent);
                }
            });

            imageDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    long elementID = questions.get(getAdapterPosition()).getQuestionID();
                    Intent intent = new Intent(context, UserActivity.class);
                    intent.putExtra(Constants.UPDATE_USER_ID, elementID);
                    context.startActivity(intent);

                }
            });
        }
    }
}
