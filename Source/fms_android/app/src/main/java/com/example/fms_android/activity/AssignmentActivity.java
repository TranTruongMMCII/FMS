package com.example.fms_android.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

import com.example.fms_android.R;
import com.example.fms_android.service.AssignmentService;
import com.example.fms_android.service.UserService;
import com.example.fms_android.utility.APIUtility;

public class AssignmentActivity extends AppCompatActivity {

    private Spinner spnClass, spnModule, spnTrainer;
    private Button btnSave, btnBack;
    private Intent intent;
    private AssignmentService assignmentService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initViews();

        assignmentService = APIUtility.getAssignmentService();

        intent = getIntent();

    }

    private void initViews(){
        spnModule = findViewById(R.id.spnModule_AddAssign);
        spnClass = findViewById(R.id.spnClass_AddAssign);
        spnTrainer = findViewById(R.id.spnTrainer_addAssign);
        btnBack = findViewById(R.id.btn_back_add_assign);
        btnSave = findViewById(R.id.btn_save_add_assign);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}