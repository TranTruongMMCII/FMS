package com.example.fms_android.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fms_android.R;
import com.example.fms_android.model.QuestionOfTruong;

import java.util.ArrayList;

public class TrainingModuleFeedbackAdapter extends RecyclerView.Adapter<TrainingModuleFeedbackAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<QuestionOfTruong> questionOfTruongs;
    private ArrayList<Integer> answer;
    private ArrayList<String> listAnswer;
    private int countItems;
    private RadioButton radioButton;

    public TrainingModuleFeedbackAdapter(Context context, ArrayList<QuestionOfTruong> questionOfTruongs) {
        this.context = context;
        this.questionOfTruongs = questionOfTruongs;
        this.countItems = 0;
        this.answer = new ArrayList<>();
        this.listAnswer = new ArrayList<>();

        for(int i = 0; i < questionOfTruongs.size(); ++i){
            answer.add(-1);
            listAnswer.add("empty");
        }
        this.radioButton = new RadioButton(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.anwser_of_feedback_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        if(position %2 == 1)
        {
            holder.itemView.setBackgroundColor(Color.parseColor("#99CCFF"));
        }
        else
        {
            holder.itemView.setBackgroundColor(Color.parseColor("#FFFAF8FD"));
        }

        QuestionOfTruong questionOfTruong = questionOfTruongs.get(position);
        holder.txtQuestion.setText(questionOfTruong.getQuestionContent());

        holder.radioGroup.setOnCheckedChangeListener((radioGroup, i) -> {
            radioButton = holder.itemView.findViewById(holder.radioGroup.getCheckedRadioButtonId());
            answer.set(position, radioGroup.getCheckedRadioButtonId());
            listAnswer.set(position, radioButton.getText().toString());
        });
    }

    public ArrayList<QuestionOfTruong> getTasks(){
        return questionOfTruongs;
    }

    public void setTasks(ArrayList<QuestionOfTruong> tasks){
        this.questionOfTruongs = tasks;
        notifyDataSetChanged();
    }

    public ArrayList<Integer> getAnswer() {
        return answer;
    }

    public int getItemChecked(){
        countItems = 0;
        for (int i:answer
             ) {
            countItems += (i == -1 ? 0 : 1);
        }
        return countItems;
    }

    public ArrayList<String> getListAnswer() {
        return listAnswer;
    }

    @Override
    public int getItemCount() {
        return this.questionOfTruongs == null ? 0 : this.questionOfTruongs.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private RadioButton radioButtonStronglyDisagree, radioButtonDisagree, radioButtonUnDecided, radioButtonAgree, radioButtonStrongAgree;
        private TextView txtQuestion;
        private RadioGroup radioGroup;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtQuestion = itemView.findViewById(R.id.txtQuestion);
            radioButtonAgree = itemView.findViewById(R.id.radioButtonAgree);
            radioButtonStrongAgree = itemView.findViewById(R.id.radioButtonStrongAgree);
            radioButtonDisagree = itemView.findViewById(R.id.radioButtonStrongAgree);
            radioButtonUnDecided = itemView.findViewById(R.id.radioButtonUnDecided);
            radioButtonStronglyDisagree = itemView.findViewById(R.id.radioButtonStronglyDisagree);
            radioGroup = itemView.findViewById(R.id.radioGroup2);
        }
    }
}
