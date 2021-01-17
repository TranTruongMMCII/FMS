package com.example.fms_android.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fms_android.R;
import com.example.fms_android.model.Assignment;
import com.example.fms_android.model.Module;

import java.util.ArrayList;

public class AssignmentAdapter extends RecyclerView.Adapter<AssignmentAdapter.MyViewHolder>{

    private Context context;
    private ArrayList<Assignment> assignments;

    public AssignmentAdapter(Context context, ArrayList<Assignment> assignments) {
        this.context = context;
        this.assignments = assignments;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.assignment_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Assignment assignment = assignments.get(position);
        holder.tvAssignmentID.setText(Integer.toString(position+1));
        holder.tvModuleName.setText(assignment.getModuleId().getModuleName());
        holder.tvClassName.setText(assignment.getClassId().getClassName());
        holder.tvTrainerName.setText(assignment.getTrainerId().getUserName());
        holder.tvCode.setText("sdkjsjcb");
    }

    @Override
    public int getItemCount() {
        if (assignments != null)
            return assignments.size();
        return 0;
    }

    public ArrayList<Assignment> getAssignments(){
        return assignments;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvAssignmentID, tvModuleName, tvClassName, tvTrainerName, tvCode;
        ImageView ivEditAssign, ivDeleteAssign;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvAssignmentID = itemView.findViewById(R.id.tvAssignmentID);
            tvModuleName = itemView.findViewById(R.id.tvModuleName);
            tvClassName = itemView.findViewById(R.id.tvClassName);
            tvTrainerName = itemView.findViewById(R.id.tvTrainerName);
            tvCode = itemView.findViewById(R.id.tvCode);
            ivDeleteAssign = itemView.findViewById(R.id.ivDeleteAssign);
            ivEditAssign = itemView.findViewById(R.id.ivEditAssign);
        }
    }
}
