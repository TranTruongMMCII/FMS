package com.example.feedback_management.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.example.feedback_management.Feedback.FeedbackViewModel;
import com.example.feedback_management.R;
import com.example.feedback_management.model.Feedback;

import java.util.ArrayList;

public class FeedbackAdapter extends RecyclerView.Adapter<FeedbackAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<Feedback> feedbacks;
    private OnDeleteClickListener deleteClickListener;
    private OnEditClickListener editClickListener;
    private OnViewClickListener viewClickListener;

    public FeedbackAdapter(Context context, ArrayList<Feedback> feedbacks) {
        this.context = context;
        this.feedbacks = feedbacks;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.feedback_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Feedback feedback = feedbacks.get(position);
        holder.tvFeedbackID.setText(""+feedback.getFeedbackID());
        holder.tvTitle.setText(feedback.getTitle());
        holder.tvAdminID.setText(feedback.getAdminID().getUserName());
    }

    @Override
    public int getItemCount() {
        if (feedbacks == null) {
            return 0;
        }
        return feedbacks.size();
    }

    public ArrayList<Feedback> getFeedbacks() {
        return feedbacks;
    }

    public void setFeedbacks(ArrayList<Feedback> feedbacks) {
        this.feedbacks = feedbacks;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvFeedbackID;
        TextView tvTitle;
        TextView tvAdminID;

        ImageButton btnView;
        ImageButton btnEdit;
        ImageButton btnDelete;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvFeedbackID = itemView.findViewById(R.id.tvFeedbackId);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvAdminID = itemView.findViewById(R.id.tvAdminId);

            btnView = itemView.findViewById(R.id.btnView);
            btnView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getBindingAdapterPosition();
                    if(viewClickListener != null && position != RecyclerView.NO_POSITION) {
                        viewClickListener.onViewClick(feedbacks.get(position));
                    }
                    notifyItemChanged(position);
                }
            });
            btnEdit = itemView.findViewById(R.id.btnEdit);
            btnEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getBindingAdapterPosition();
                    if(editClickListener != null && position != RecyclerView.NO_POSITION) {
                        editClickListener.onEditClick(feedbacks.get(position));
                    }
                    notifyItemChanged(position);
                }
            });
            btnDelete = itemView.findViewById(R.id.btnDelete);
            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getBindingAdapterPosition();
                    if(deleteClickListener != null && position != RecyclerView.NO_POSITION) {
                        deleteClickListener.onDeleteClick(feedbacks.get(position));
                        feedbacks.remove(position);
                        notifyItemRemoved(position);
                    }
                }
            });



        }
    }

    public interface OnDeleteClickListener {
        void onDeleteClick(Feedback feedback);
    }

    public void setOnDeleteClickListener(FeedbackAdapter.OnDeleteClickListener deleteClickListener) {
        this.deleteClickListener = deleteClickListener;
    }

    public interface OnEditClickListener {
        void onEditClick(Feedback feedback);
    }

    public void setOnEditClickListener(FeedbackAdapter.OnEditClickListener editClickListener) {
        this.editClickListener = editClickListener;
    }

    public interface OnViewClickListener {
        void onViewClick(Feedback feedback);
    }

    public void setOnViewClickListener(FeedbackAdapter.OnViewClickListener viewClickListener) {
        this.viewClickListener = viewClickListener;
    }

}
