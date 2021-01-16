package com.example.fms_android.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fms_android.AppExecutors;
import com.example.fms_android.Constants;
import com.example.fms_android.R;
import com.example.fms_android.fragment.UserFragment;
import com.example.fms_android.model.User;
import com.example.fms_android.service.UserService;
import com.example.fms_android.utility.APIUtility;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserActivity extends AppCompatActivity {

    private EditText txtFirstName, txtLastName, txtEmail;
    private Button btnSave;

    private long userID;
    private Intent intent;
    private UserService userService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initViews();

        userService = APIUtility.getUserService();

        intent = getIntent();

        if(intent != null && intent.hasExtra(Constants.UPDATE_USER_ID)){
            btnSave.setText("Update");

            userID = intent.getLongExtra(Constants.UPDATE_USER_ID, -1);

            Call<User> userCall = userService.getUserById(userID);
            userCall.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    User user= response.body();
                    populateUI(user);
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    Toast.makeText(getApplication().getBaseContext(), "UserViewModel " + t.getMessage(), Toast.LENGTH_LONG).show();;
                }
            });
        }

    }

    private void populateUI(User user) {
        if(user == null){
            return;
        }

        txtFirstName.setText(user.getFirstName());
        txtLastName.setText(user.getLastName());
        txtEmail.setText(user.getEmail());
    }

    private void initViews() {

        txtFirstName = findViewById(R.id.txtFirstName);
        txtLastName = findViewById(R.id.txtLastName);
        txtEmail = findViewById(R.id.txtEmail);
        btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSaveButtonClicked();
            }
        });
    }

    private void onSaveButtonClicked() {
        final User user = new User(txtFirstName.getText().toString(), txtLastName.getText().toString(), txtEmail.getText().toString());

        AppExecutors.getInstance().getNetworkIO().execute(new Runnable() {
            @Override
            public void run() {
                if(!intent.hasExtra(Constants.UPDATE_USER_ID)){
                    Call<User> userCall = userService.postUser(user);
                    userCall.enqueue(new Callback<User>() {
                        @Override
                        public void onResponse(Call<User> call, Response<User> response) {
                            Toast.makeText(getApplication().getBaseContext(), "Add successfully! ", Toast.LENGTH_LONG).show();;
                        }

                        @Override
                        public void onFailure(Call<User> call, Throwable t) {
                            Toast.makeText(getApplication().getBaseContext(), "Failed to add: " + t.getMessage(), Toast.LENGTH_LONG).show();;
                        }
                    });
                    finish();
                }
                else{
                    Call<Void> userCall = userService.updateUser(userID, user);
                    userCall.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            Toast.makeText(getApplication().getBaseContext(), "Update successfully! ", Toast.LENGTH_LONG).show();;

                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            Toast.makeText(getApplication().getBaseContext(), "Failed to update: " + t.getMessage(), Toast.LENGTH_LONG).show();;

                        }
                    });
                    finish();
                }
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