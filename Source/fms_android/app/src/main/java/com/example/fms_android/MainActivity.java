package com.example.fms_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.fms_android.fragment.AdminStatisticsFragment;
import com.example.fms_android.fragment.AssignmentFragment;
import com.example.fms_android.fragment.TraineeFeedbackFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getSupportFragmentManager();
//                AssignmentFragment fb = AssignmentFragment.getInstance();
//                TraineeFeedbackFragment traineeFeedbackFragment = TraineeFeedbackFragment.getInstance();
                AdminStatisticsFragment adminStatisticsFragment = AdminStatisticsFragment.getInstance();

                FragmentTransaction transaction = fm.beginTransaction();
//                transaction.replace(R.id.activity_main_container, fb, "assignmentFragment");
//                transaction.replace(R.id.activity_main_container, traineeFeedbackFragment, "TraineeFeedbackFragment");
                transaction.replace(R.id.activity_main_container, adminStatisticsFragment, "AdminStatisticsFragment");

                transaction.commit();
                button.setVisibility(View.INVISIBLE);
            }
        });

    }
}