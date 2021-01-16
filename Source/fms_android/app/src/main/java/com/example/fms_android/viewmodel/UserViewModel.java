package com.example.fms_android.viewmodel;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.fms_android.utility.APIUtility;
import com.example.fms_android.service.UserService;
import com.example.fms_android.model.User;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserViewModel extends AndroidViewModel {

    private MutableLiveData<ArrayList<User>> userList;
    private UserService userService;


    public UserViewModel(@NonNull Application application) {
        super(application);

        userService = APIUtility.getUserService();
    }

    public MutableLiveData<ArrayList<User>> getUserList() {
        if(userList == null){
            userList = new MutableLiveData<ArrayList<User>>();
        }

        loadUsers();

        return userList;
    }

    private void loadUsers() {
        Call<ArrayList<User>> call = userService.getUsers();
        call.enqueue(new Callback<ArrayList<User>>() {
            @Override
            public void onResponse(Call<ArrayList<User>> call, Response<ArrayList<User>> response) {
                userList.setValue(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<User>> call, Throwable t) {
                Toast.makeText(getApplication().getBaseContext(), "UserViewModel " + t.getMessage(), Toast.LENGTH_LONG).show();;
            }
        });
    }

    public void deleteUser(long key) {
        Call<Void> call = userService.delete(key);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                loadUsers();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(getApplication().getBaseContext(), "UserViewModel " + t.getMessage(), Toast.LENGTH_LONG).show();;
            }
        });
    }
}
