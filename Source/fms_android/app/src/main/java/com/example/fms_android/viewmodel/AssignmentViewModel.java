package com.example.fms_android.viewmodel;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.fms_android.model.Assignment;
import com.example.fms_android.service.AssignmentService;
import com.example.fms_android.utility.APIUtility;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AssignmentViewModel extends AndroidViewModel {
    // TODO: Implement the ViewModel

    private MutableLiveData<ArrayList<Assignment>> assignmentList;
    private AssignmentService assignmentService;

    public AssignmentViewModel (@NonNull Application application){
        super(application);
        assignmentService = APIUtility.getAssignmentService();
    }

    public MutableLiveData<ArrayList<Assignment>> getAssignmentList(){
        if (assignmentList == null){
            assignmentList = new MutableLiveData<ArrayList<Assignment>>();
        }
        loadAssignment();
        return assignmentList;
    }

    public void loadAssignment(){
        Call<ArrayList<Assignment>> call = assignmentService.getAssignments();
        call.enqueue(new Callback<ArrayList<Assignment>>() {
            @Override
            public void onResponse(Call<ArrayList<Assignment>> call, Response<ArrayList<Assignment>> response) {
                assignmentList.setValue(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<Assignment>> call, Throwable t) {
                Toast.makeText(getApplication().getBaseContext(), "AssignmentViewModel " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }

    public void deleteAssignment(long classId, long moduleId, String trainerId){
        Call<Void> call = assignmentService.delete(classId, moduleId, trainerId);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                loadAssignment();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(getApplication().getBaseContext(), "AssignmentViewModel " + t.getMessage(), Toast.LENGTH_LONG).show();;
            }
        });

    }


}