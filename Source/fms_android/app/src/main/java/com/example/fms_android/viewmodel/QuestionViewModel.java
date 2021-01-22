package com.example.fms_android.viewmodel;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.fms_android.model.Question;
import com.example.fms_android.service.QuestionService;
import com.example.fms_android.utility.APIUtility;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuestionViewModel extends AndroidViewModel {

    private MutableLiveData<ArrayList<Question>> questionList;
    private QuestionService questionService;


    public QuestionViewModel(@NonNull Application application) {
        super(application);
        questionService = APIUtility.getQuestionService();
    }

    public MutableLiveData<ArrayList<Question>> getQuestionList(){

        if(questionList == null)
        {
            questionList = new MutableLiveData<ArrayList<Question>>();

        }
        loadQuestions();
        return questionList;
    }

    private void loadQuestions(){
        Call<ArrayList<Question>> call = questionService.getQuestions();
        call.enqueue(new Callback<ArrayList<Question>>() {
            @Override
            public void onResponse(Call<ArrayList<Question>> call, Response<ArrayList<Question>> response) {
                questionList.setValue(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<Question>> call, Throwable t) {
                Toast.makeText(getApplication().getBaseContext(), "QuestionViewModel" + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void deleteQuestion(long key){
        Call<Void> call = questionService.delete(key);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                loadQuestions();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(getApplication().getBaseContext(), "QuestionViewModel" + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

}
