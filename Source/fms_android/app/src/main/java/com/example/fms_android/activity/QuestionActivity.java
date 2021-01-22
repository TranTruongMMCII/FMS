package com.example.fms_android.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.fms_android.AppExecutors;
import com.example.fms_android.Constants;
import com.example.fms_android.R;
import com.example.fms_android.fragment.QuestionFragment;
import com.example.fms_android.model.Question;
import com.example.fms_android.service.QuestionService;
import com.example.fms_android.utility.APIUtility;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuestionActivity extends AppCompatActivity {

    private EditText txtContent;
    private Button btnSaveQ, btnBackQ;

    private long questionID;
    private Intent intent;
    private QuestionService questionService;

    QuestionFragment questionFragment;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initViews();

        questionService = APIUtility.getQuestionService();

        intent = getIntent();


        /// *********SPINNER**********///
        spinner = findViewById(R.id.spinnerQuestion_AddTopic);
        questionFragment = new QuestionFragment();


        if (intent != null && intent.hasExtra(Constants.UPDATE_USER_ID)) {

            questionID = intent.getLongExtra(Constants.UPDATE_USER_ID, -1);

            Call<Question> questionCall = questionService.getQuestionById(questionID);
            questionCall.enqueue(new Callback<Question>() {
                @Override
                public void onResponse(Call<Question> call, Response<Question> response) {
                    Question question = response.body();
                    populateUI(question);
                }

                @Override
                public void onFailure(Call<Question> call, Throwable t) {
                    Toast.makeText(getApplication().getBaseContext(), "QuestionViewModel" + t.getMessage(), Toast.LENGTH_LONG).show();

                }
            });
        }

    }

    private void populateUI(Question question) {
        if (question == null) {
            return;
        }
        txtContent.setText(question.getQuestionContent());

    }

    private void initViews() {

        txtContent = findViewById(R.id.txtContent);
        btnSaveQ = findViewById(R.id.btnSaveQ);
        btnBackQ = findViewById(R.id.btnBackQ);

        btnBackQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                QuestionFragment questionFragment = QuestionFragment.getInstance();
                questionFragment.show(getSupportFragmentManager(), "QuestionFragment");
            }
        });

        btnSaveQ.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(txtContent.getText())) {
                    txtContent.setError("Please enter the question");

                }
                else
                {
                    onSaveButtonClicked();
                }


            }


        });
    }

    private void onSaveButtonClicked() {

        final Question question = new Question(txtContent.getText().toString());
        AppExecutors.getInstance().getNetworkIO().execute(new Runnable() {
            @Override
            public void run() {
                if (!intent.hasExtra(Constants.UPDATE_USER_ID)) {
                    Call<Question> questionCall = questionService.postQuestion(question);
                    questionCall.enqueue(new Callback<Question>() {
                        @Override
                        public void onResponse(Call<Question> call, Response<Question> response) {
                            Toast.makeText(getApplication().getBaseContext(), "Add successfully! ", Toast.LENGTH_LONG).show();
                            ;

                        }

                        @Override
                        public void onFailure(Call<Question> call, Throwable t) {
                            Toast.makeText(getApplication().getBaseContext(), "Failed to add: " + t.getMessage(), Toast.LENGTH_LONG).show();
                            ;

                        }

                    });
                } else {
                    Call<Void> questionCall = questionService.updateQuestion(questionID, question);
                    questionCall.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            Toast.makeText(getApplication().getBaseContext(), "Update successfully! ", Toast.LENGTH_LONG).show();
                            ;

                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            Toast.makeText(getApplication().getBaseContext(), "Failed to update: " + t.getMessage(), Toast.LENGTH_LONG).show();
                            ;

                        }
                    });
                }
                finish();
                QuestionFragment questionFragment = QuestionFragment.getInstance();
                questionFragment.show(getSupportFragmentManager(), "QuestionFragment");
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }


}