package com.example.feedback_management.FeedbackCreate;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.feedback_management.api.APIUtility;
import com.example.feedback_management.model.Question;
import com.example.feedback_management.model.Topic;
import com.example.feedback_management.service.FeedbackService;
import com.example.feedback_management.service.QuestionService;
import com.example.feedback_management.service.TopicService;
import com.example.feedback_management.service.TypeFeedbackService;
import com.example.feedback_management.model.TypeFeedback;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeedbackCreateViewModel extends AndroidViewModel {

    private MutableLiveData<ArrayList<TypeFeedback>> typeFeedbackList;
    private TypeFeedbackService typeFeedbackService;

    private MutableLiveData<ArrayList<Question>> questionList;
    private QuestionService questionService;

    private MutableLiveData<ArrayList<Topic>> topicList;
    private TopicService topicService;

    private FeedbackService feedbackService;

    public FeedbackCreateViewModel(@NonNull Application application) {
        super(application);
        typeFeedbackService = APIUtility.getTypeFeedbackService();
        questionService = APIUtility.getQuestionService();
        topicService = APIUtility.getTopicService();
        feedbackService = APIUtility.getFeedbackService();
    }

    public MutableLiveData<ArrayList<TypeFeedback>> getTypeFeedbackList() {
        if (typeFeedbackList == null) {
            typeFeedbackList = new MutableLiveData<ArrayList<TypeFeedback>>();
        }
        loadTypeFeedbacks();
        return typeFeedbackList;
    }

    public MutableLiveData<ArrayList<Question>> getQuestionList() {
        if(questionList == null) {
            questionList = new MutableLiveData<ArrayList<Question>>();
        }
        loadQuestions();
        return questionList;
    }

    public MutableLiveData<ArrayList<Topic>> getTopicList() {
        if(topicList == null) {
            topicList = new MutableLiveData<ArrayList<Topic>>();
        }
        loadTopics();
        return topicList;
    }

    private void loadTypeFeedbacks() {
        Call<ArrayList<TypeFeedback>> call = typeFeedbackService.getTypeFeedbacks();
        call.enqueue(new Callback<ArrayList<TypeFeedback>>() {
            @Override
            public void onResponse(Call<ArrayList<TypeFeedback>> call, Response<ArrayList<TypeFeedback>> response) {
                typeFeedbackList.setValue(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<TypeFeedback>> call, Throwable t) {
                Toast.makeText(getApplication().getBaseContext(), "Fail" + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void loadQuestions() {
        Call<ArrayList<Question>> call = questionService.getQuestions();
        call.enqueue(new Callback<ArrayList<Question>>() {
            @Override
            public void onResponse(Call<ArrayList<Question>> call, Response<ArrayList<Question>> response) {
                questionList.setValue(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<Question>> call, Throwable t) {
                Toast.makeText(getApplication().getBaseContext(), "Fail" + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void loadTopics() {
        Call<ArrayList<Topic>> call = topicService.getTopics();
        call.enqueue(new Callback<ArrayList<Topic>>() {
            @Override
            public void onResponse(Call<ArrayList<Topic>> call, Response<ArrayList<Topic>> response) {
                topicList.setValue(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<Topic>> call, Throwable t) {
                Toast.makeText(getApplication().getBaseContext(), "Fail" + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void deleteFeedbackQuestion(Long feedbackid, Long questionid) {
        Call<ResponseBody> call = feedbackService.deleteFeedbackQuestion(feedbackid, questionid);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    public void insertFeedbackQuestion(Long feedbackid, Long questionid) {
        Call<ResponseBody> call = feedbackService.insertFeedbackQuestion(feedbackid, questionid);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

    }

    public void updateFeedback(Long id, Long typeid, String title) {
        Call<ResponseBody> call = feedbackService.updateFeedbackQuestion(id, typeid, title);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

}