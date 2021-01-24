package com.example.fms_android.viewmodel;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.fms_android.R;
import com.example.fms_android.model.Answer;
import com.example.fms_android.service.AnswerService;
import com.example.fms_android.utility.APIUtility;

import java.lang.reflect.Array;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AnswerViewModel extends AndroidViewModel {
    
    private MutableLiveData<ArrayList<Answer>> answerList;
    private AnswerService answerService;
    
    public AnswerViewModel(@NonNull Application application) {
        super(application);
        answerService = APIUtility.getAnswerService();
    }

    public MutableLiveData<ArrayList<Answer>> getAnswerList() {
        
        if(answerList == null){
            answerList = new MutableLiveData<ArrayList<Answer>>();
        }
        loadAnswer();
        return answerList;
    }

    private void loadAnswer() {
        Call<ArrayList<Answer>>  call = answerService.getAnswers();
        call.enqueue(new Callback<ArrayList<Answer>>() {
            @Override
            public void onResponse(Call<ArrayList<Answer>> call, Response<ArrayList<Answer>> response) {
                answerList.setValue(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<Answer>> call, Throwable t) {
                Toast.makeText(getApplication().getBaseContext(), getApplication().getBaseContext().getString(R.string.answerViewModel) + t.getMessage(), Toast.LENGTH_LONG).show();;
            }
        });
    }

    private void deleteAnswer(long classID, long moduleID, String traineeID, long questionID){
        Call<Void> call = answerService.deleteAnswer(classID, moduleID, traineeID, questionID);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                loadAnswer();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(getApplication().getBaseContext(), getApplication().getBaseContext().getString(R.string.answerViewModel) + t.getMessage(), Toast.LENGTH_LONG).show();;
            }
        });
    }
}
