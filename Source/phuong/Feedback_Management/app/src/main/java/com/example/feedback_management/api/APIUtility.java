package com.example.feedback_management.api;

import com.example.feedback_management.api.APIClient;
import com.example.feedback_management.service.FeedbackService;
import com.example.feedback_management.service.QuestionService;
import com.example.feedback_management.service.TopicService;
import com.example.feedback_management.service.TypeFeedbackService;

public class APIUtility {
    private static String baseURL = "http://10.0.2.2:8080/";

    public static FeedbackService getFeedbackService() {
        return APIClient.getClient(baseURL).create(FeedbackService.class);
    }

    public static TypeFeedbackService getTypeFeedbackService() {
        return APIClient.getClient(baseURL).create(TypeFeedbackService.class);
    }

    public static QuestionService getQuestionService() {
        return APIClient.getClient(baseURL).create(QuestionService.class);
    }

    public static TopicService getTopicService() {
        return APIClient.getClient(baseURL).create(TopicService.class);
    }
}
