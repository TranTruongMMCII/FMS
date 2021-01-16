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
import com.example.fms_android.activity.UserActivity;
import com.example.fms_android.model.User;

import java.util.ArrayList;
import java.util.Arrays;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<User> users;

    public UserAdapter(Context context, ArrayList<User> users) {
        this.context = context;
        this.users = users;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.user_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        User user = users.get(position);
        holder.txtUserID.setText("UserID: " + user.getId());
        holder.txtFirstName.setText("First name: " + user.getFirstName());
        holder.txtLastName.setText("Last name: " + user.getLastName());
        holder.txtEmail.setText("Email: " + user.getEmail());
    }

    @Override
    public int getItemCount() {
        if(users == null){
            return 0;
        }
        return users.size();
    }

    public ArrayList<User> getTasks(){
        return users;
    }

    public void setTasks(ArrayList<User> users){
        this.users = users;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txtUserID;
        TextView txtFirstName;
        TextView txtLastName;
        TextView txtEmail;
        ImageView imageViewEdit;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            txtUserID = itemView.findViewById(R.id.txtUserID);
            txtFirstName = itemView.findViewById(R.id.txtFirstName);
            txtLastName = itemView.findViewById(R.id.txtLastName);
            txtEmail = itemView.findViewById(R.id.txtEmail);
            imageViewEdit = itemView.findViewById(R.id.imageViewEdit);

            imageViewEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    long elementID = users.get(getAdapterPosition()).getId();
                    Intent intent = new Intent(context, UserActivity.class);
                    intent.putExtra(Constants.UPDATE_USER_ID, elementID);
                    context.startActivity(intent);
                }
            });
        }
    }
}
