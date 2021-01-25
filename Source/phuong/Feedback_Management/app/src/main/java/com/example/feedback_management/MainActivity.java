package com.example.feedback_management;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.feedback_management.Feedback.FeedbackFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnFeedback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnFeedback = findViewById(R.id.btnFeedback);

        btnFeedback.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        FragmentManager fm = getSupportFragmentManager();
        FeedbackFragment fb = FeedbackFragment.getnstance();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.activity_main_container, fb, "feedbackFragment");
        transaction.commit();
        btnFeedback.setVisibility(View.INVISIBLE);

    }
}