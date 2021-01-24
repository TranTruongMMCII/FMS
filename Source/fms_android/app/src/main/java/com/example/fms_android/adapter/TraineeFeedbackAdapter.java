package com.example.fms_android.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fms_android.R;
import com.example.fms_android.activity.FeedbackOfTraineeActivity;
import com.example.fms_android.model.FeedbackOfTruong;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class TraineeFeedbackAdapter extends RecyclerView.Adapter<TraineeFeedbackAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<FeedbackOfTruong> feedbackOfTruongs;

    public TraineeFeedbackAdapter(Context context, ArrayList<FeedbackOfTruong> feedbackOfTruongs) {
        this.context = context;
        this.feedbackOfTruongs = feedbackOfTruongs;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.feedback_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        FeedbackOfTruong feedbackOfTruong = feedbackOfTruongs.get(position);
        holder.txtFeedbackTitle.setText(feedbackOfTruong.getFeedbackTitle());
        holder.txtClassID.setText(feedbackOfTruong.getClassID());
        holder.txtClassName.setText(feedbackOfTruong.getClassName());
        holder.txtModuleID.setText(feedbackOfTruong.getModuleID());
        holder.txtModuleName.setText(feedbackOfTruong.getModuleName());
        holder.txtEndtime.setText(feedbackOfTruong.getEndTime());
        holder.txtStatus.setText(feedbackOfTruong.getStatus());

        if(feedbackOfTruong.getStatus().equals(holder.itemView.getContext().getString(R.string.complete))){
            holder.fabFeedback.setVisibility(View.INVISIBLE);
        }
        else{
            holder.fabFeedback.setVisibility(View.VISIBLE);
        }
    }

    public ArrayList<FeedbackOfTruong> getTasks(){
        return feedbackOfTruongs;
    }

    public void setTasks(ArrayList<FeedbackOfTruong> feedbackOfTruongs){
        this.feedbackOfTruongs = feedbackOfTruongs;
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return feedbackOfTruongs == null? 0 : feedbackOfTruongs.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtFeedbackTitle, txtClassID, txtClassName, txtModuleID, txtModuleName, txtEndtime, txtStatus;
        FloatingActionButton fabFeedback;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            txtFeedbackTitle = itemView.findViewById(R.id.txtFeedbackTitle);
            txtClassID = itemView.findViewById(R.id.txtClassID);
            txtClassName = itemView.findViewById(R.id.txtClassName);
            txtModuleID = itemView.findViewById(R.id.txtModuleID);
            txtModuleName = itemView.findViewById(R.id.txtModuleName);
            txtEndtime = itemView.findViewById(R.id.txtEndTime);
            txtStatus = itemView.findViewById(R.id.txtStatus);
            fabFeedback = itemView.findViewById(R.id.fabFeedback);

            fabFeedback.setOnClickListener(view -> {
                Bundle bundle = new Bundle();
                bundle.putString("classID", txtClassID.getText().toString());
                bundle.putString("moduleID", txtModuleID.getText().toString());
                bundle.putString("className", txtClassName.getText().toString());
                bundle.putString("moduleName", txtModuleName.getText().toString());
                bundle.putString("traineeName", "TraineeName");
                Intent intent = new Intent(context, FeedbackOfTraineeActivity.class);
                intent.putExtras(bundle);
                context.startActivity(intent);
            });

        }
    }
}
