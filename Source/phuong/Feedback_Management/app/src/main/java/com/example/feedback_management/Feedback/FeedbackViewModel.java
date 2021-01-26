package com.example.feedback_management.Feedback;

import android.app.Application;
import android.os.AsyncTask;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.feedback_management.api.APIUtility;
import com.example.feedback_management.model.Question;
import com.example.feedback_management.service.FeedbackService;
import com.example.feedback_management.model.Feedback;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeedbackViewModel extends AndroidViewModel {

    private MutableLiveData<ArrayList<Feedback>> feedbackList;
    private MutableLiveData<Feedback> feedback;
    private FeedbackService feedbackService;

    public FeedbackViewModel(@NonNull Application application) {
        super(application);

        feedbackService = APIUtility.getFeedbackService();
    }

    public MutableLiveData<ArrayList<Feedback>> getFeedbackList() {
        if (feedbackList == null) {
            feedbackList = new MutableLiveData<ArrayList<Feedback>>();
        }

        loadFeedbacks();

        return feedbackList;
    }

    public MutableLiveData<Feedback> getFeedbackById(Long id) {
        if(feedback == null) {
            feedback = new MutableLiveData<Feedback>();
        }
        loadFeedbackById(id);

        return  feedback;
    }

    private void loadFeedbacks() {
        Call<ArrayList<Feedback>> call = feedbackService.getFeedbacks();
        call.enqueue(new Callback<ArrayList<Feedback>>() {
            @Override
            public void onResponse(Call<ArrayList<Feedback>> call, Response<ArrayList<Feedback>> response) {
                feedbackList.setValue(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<Feedback>> call, Throwable t) {
                Toast.makeText(getApplication().getBaseContext(), "FeedbackViewModel" + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void loadFeedbackById(Long id) {
        Call<Feedback> call =  feedbackService.getFeedback(id);
        call.enqueue(new Callback<Feedback>() {
            @Override
            public void onResponse(Call<Feedback> call, Response<Feedback> response) {
                feedback.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Feedback> call, Throwable t) {

            }
        });
    }

    public void deleteFeedback(Long id) {
        new DeleteFeedbackAsyncTask(feedbackService).execute(id);
    }

    private static class DeleteFeedbackAsyncTask extends AsyncTask<Long, Void, Void> {

        private FeedbackService feedbackService;
        private DeleteFeedbackAsyncTask(FeedbackService feedbackService) { this.feedbackService = feedbackService; }

        @Override
        protected Void doInBackground(Long... longs) {
            Call<ResponseBody> call = feedbackService.deleteFeedback(longs[0]);
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {

                }
            });
            return null;
        }
    }
    









}