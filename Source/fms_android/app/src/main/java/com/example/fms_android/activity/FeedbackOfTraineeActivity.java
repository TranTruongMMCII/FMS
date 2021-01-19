package com.example.fms_android.activity;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fms_android.R;
import com.example.fms_android.adapter.TrainingModuleFeedbackAdapter;
import com.example.fms_android.model.QuestionOfTruong;

import java.util.ArrayList;

public class FeedbackOfTraineeActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TrainingModuleFeedbackAdapter trainingModuleFeedbackAdapter;
    private View view;
    private ArrayList<QuestionOfTruong> questionOfTruongs;
    private Button btnSubmit;

    public static FeedbackOfTraineeActivity getInstance(){
        return new FeedbackOfTraineeActivity();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_feedback);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.recyclerViewListQuestion);
        btnSubmit = findViewById(R.id.btnSubmit);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        questionOfTruongs = new ArrayList<>();
        questionOfTruongs.add(new QuestionOfTruong(1, 1, "Khóa học này có phù hợp với bạn không?", 0));
        questionOfTruongs.add(new QuestionOfTruong(2, 1, "Khóa học này có cần thay đổi gì không?", 0));
        questionOfTruongs.add(new QuestionOfTruong(3, 1, "Khóa học này có đủ thông tin với bạn không?", 0));
        questionOfTruongs.add(new QuestionOfTruong(4, 1, "Khóa học này có đủ thông tin với bạn không?", 0));

        trainingModuleFeedbackAdapter = new TrainingModuleFeedbackAdapter(getApplicationContext(), questionOfTruongs);
        recyclerView.setAdapter(trainingModuleFeedbackAdapter);

        btnSubmit.setOnClickListener(view -> {
            if(trainingModuleFeedbackAdapter.getItemChecked() != questionOfTruongs.size()){
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle(getString(R.string.announcement));
                builder.setMessage(getString(R.string.complete_feedback));
                builder.setIcon(R.drawable.baseline_priority_high_black_18dp);
                builder.setPositiveButton(getString(R.string.ok), (dialogInterface, i) -> {

                });
                builder.setCancelable(false);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
            else {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle(getString(R.string.announcement));
                builder.setMessage(getString(R.string.sure_to_submit_feedback));
                builder.setIcon(R.drawable.baseline_priority_high_black_18dp);
                builder.setPositiveButton(getString(R.string.ok), (dialogInterface, i) -> {
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
                    builder.setTitle(getString(R.string.announcement));
                    builder.setMessage(getString(R.string.submit_successful));
                    builder.setIcon(R.drawable.baseline_done_black_18dp);
                    builder.setPositiveButton(getString(R.string.ok), (dialogInterface1, i1) -> {
                       this.finish();
                    });
                    builder.setCancelable(false);
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                });
                builder.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.setCancelable(false);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
    }
}
