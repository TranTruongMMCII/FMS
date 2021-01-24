package com.example.fms_android.activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fms_android.Constants;
import com.example.fms_android.MainActivity;
import com.example.fms_android.R;
import com.example.fms_android.adapter.TrainingModuleFeedbackAdapter;
import com.example.fms_android.fragment.TraineeFeedbackFragment;
import com.example.fms_android.model.Answer;
import com.example.fms_android.model.QuestionOfTruong;
import com.example.fms_android.service.AnswerService;
import com.example.fms_android.utility.APIUtility;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeedbackOfTraineeActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TrainingModuleFeedbackAdapter trainingModuleFeedbackAdapter;
    private View view;
    private ArrayList<QuestionOfTruong> questionOfTruongs;
    private Button btnSubmit;
    private Bundle bundle;
    private int classID;
    private int moduleID;
    private AnswerService answerService;
    private TextView txtModuleName, txtClassName, txtTraineeName;
    private EditText txtGeneralComment;

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

        answerService = APIUtility.getAnswerService();

        bundle = getIntent().getExtras();
        classID = Integer.parseInt(bundle.getString("classID"));
        moduleID = Integer.parseInt(bundle.getString("moduleID"));

        recyclerView = findViewById(R.id.recyclerViewListQuestion);
        btnSubmit = findViewById(R.id.btnSubmit);
        txtClassName = findViewById(R.id.txtClassName);
        txtModuleName = findViewById(R.id.txtModuleName);
        txtTraineeName = findViewById(R.id.txtTraineeName);
        txtGeneralComment = findViewById(R.id.txtGeneralComment);

        txtClassName.setText(bundle.getString("className"));
        txtModuleName.setText(bundle.getString("moduleName"));
        txtTraineeName.setText(bundle.getString("traineeName"));

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        questionOfTruongs = new ArrayList<>();
        questionOfTruongs.add(new QuestionOfTruong(1, 1, "Khóa học này có phù hợp với bạn không?", 0));
//        questionOfTruongs.add(new QuestionOfTruong(2, 1, "Khóa học này có cần thay đổi gì không?", 0));
//        questionOfTruongs.add(new QuestionOfTruong(3, 1, "Khóa học này có đủ thông tin với bạn không?", 0));
//        questionOfTruongs.add(new QuestionOfTruong(4, 1, "Khóa học này có đủ thông tin với bạn không?", 0));
//        questionOfTruongs.add(new QuestionOfTruong(5, 1, "Khóa học này có đủ thông tin với bạn không?", 0));


        trainingModuleFeedbackAdapter = new TrainingModuleFeedbackAdapter(getApplicationContext(), questionOfTruongs);
        recyclerView.setAdapter(trainingModuleFeedbackAdapter);

        btnSubmit.setOnClickListener(view -> {
            if (TextUtils.isEmpty(txtGeneralComment.getText())){
                txtGeneralComment.setText(getString(R.string.empty_string));
            }
            Builder builder = new Builder(this);
            builder.setTitle(getString(R.string.announcement));
            if(trainingModuleFeedbackAdapter.getItemChecked() != questionOfTruongs.size()){
                builder.setMessage(getString(R.string.complete_feedback));
                builder.setIcon(R.drawable.baseline_priority_high_black_18dp);
                builder.setPositiveButton(getString(R.string.ok), (dialogInterface, i) -> {

                });
                builder.setCancelable(false);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
            else {
                builder.setMessage(getString(R.string.sure_to_submit_feedback));
                builder.setIcon(R.drawable.baseline_priority_high_black_18dp);
                builder.setPositiveButton(getString(R.string.ok), (dialogInterface, i) -> {
                    ArrayList<Integer> answer = trainingModuleFeedbackAdapter.getAnswer();
                    for (int j = 0; j < answer.size(); ++j){
                        int value = 0;
                        if (answer.get(j) == 2131231009){
                            value = 0;
                        }
                        else if (answer.get(j) == 2131231007){
                            value = 1;
                        }
                        else if (answer.get(j) == 2131231010){
                            value = 2;
                        }
                        else if (answer.get(j) == 2131231006){
                            value = 3;
                        }
                        else {
                            value = 4;
                        }

                        Answer answer1 = new Answer(classID, moduleID, questionOfTruongs.get(j).getQuestionID(), value, "TraineeID", txtGeneralComment.getText().toString());
                        System.out.println(answer1.getClassID() +" " + answer1.getModuleID() + " " + answer1.getQuestionID() +" " + answer1.getTraineeID() + " " + answer1.getValue() + " " + answer1.getComment());
                        Call<Answer> answerCall = answerService.postAnswer(answer1);
                        answerCall.enqueue(new Callback<Answer>() {
                            @Override
                            public void onResponse(Call<Answer> call, Response<Answer> response) {
//                                Toast.makeText(getApplication().getBaseContext(), getString(R.string.add_successfully), Toast.LENGTH_LONG).show();;
                            }

                            @Override
                            public void onFailure(Call<Answer> call, Throwable t) {
//                                Toast.makeText(getApplication().getBaseContext(), getString(R.string.failed_to_add) + t.getMessage(), Toast.LENGTH_LONG).show();;
                               return;
                            }
                        });
                    }
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
                    builder1.setTitle(getString(R.string.announcement));
                    builder1.setMessage(getString(R.string.submit_successful));
                    builder1.setIcon(R.drawable.baseline_done_black_18dp);
                    builder1.setPositiveButton(getString(R.string.ok), (dialogInterface1, i1) -> {
                        Constants.IS_COMPLETED = true;
                        Constants.CLASS_ID = String.valueOf(classID);
                        Constants.MODULE_ID = String.valueOf(moduleID);

//                        Intent intent = new Intent(this, MainActivity.class);
//                        startActivity(intent);
                        this.finish();
                        TraineeFeedbackFragment fragment = TraineeFeedbackFragment.getInstance();
                        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//                        fragmentTransaction.replace(R.id.activity_feedback, fragment, "TraineeFeedbackFragment");
//                        fragmentTransaction.commit();
//                        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.feedback_fragment);
                        fragmentTransaction.detach(fragment)
                                .attach(fragment)
                                .commit();
//                        fragment.show(getSupportFragmentManager(), "TraineeFeedbackFragment");

//                        getSupportFragmentManager().beginTransaction()
//                                .show(TraineeFeedbackFragment.getInstance())
//                                .commit();
                    });
                    builder1.setCancelable(false);
                    AlertDialog alertDialog = builder1.create();
                    alertDialog.show();
                });
                builder.setNegativeButton(getString(R.string.cancel), (dialogInterface, i) -> {

                });
                builder.setCancelable(false);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
    }
}
