package com.example.fms_android.utility;

import com.example.fms_android.api.APIClient;
import com.example.fms_android.service.AssignmentService;
import com.example.fms_android.service.UserService;

public class APIUtility {

    private static String baseURL = "http://10.0.2.2:8080/";

    public static UserService getUserService(){
        return APIClient.getClient(baseURL).create(UserService.class);
    }
    public static AssignmentService getAssignmentService(){
        return APIClient.getClient(baseURL).create(AssignmentService.class);
    }
}
