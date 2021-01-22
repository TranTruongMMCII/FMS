package com.example.fms_android.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.fms_android.R;
import com.example.fms_android.model.Answer;
import com.example.fms_android.model.ClassOfTruong;
import com.example.fms_android.model.ModuleOfTruong;
import com.example.fms_android.viewmodel.AdminStatisticsViewModel;
import com.github.mikephil.charting.charts.PieChart;

import java.util.ArrayList;
import java.util.Date;

public class AdminStatisticsFragment extends DialogFragment {

    private Spinner spinnerClassName;
    private Spinner spinnerModuleName;
    private TextView txtClassName;
    private TextView txtModuleName;
    private PieChart pieChartStatisticsAdmin;
    private Button btnShowDetail, btnShowOverview;

    private View view;
    private AdminStatisticsViewModel adminStatisticsViewModel;

    public static final AdminStatisticsFragment getInstance(){
        return new AdminStatisticsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        adminStatisticsViewModel = ViewModelProviders.of(this).get(AdminStatisticsViewModel.class);
        view = inflater.inflate(R.layout.feedback_statics, container, false);

        spinnerClassName = view.findViewById(R.id.spinnerClassName);
        spinnerModuleName = view.findViewById(R.id.spinnerModuleName);
        txtClassName = view.findViewById(R.id.txtClassName);
        txtModuleName = view.findViewById(R.id.txtModuleName);
        pieChartStatisticsAdmin = view.findViewById(R.id.pieChartStatisticsAdmin);
        btnShowDetail = view.findViewById(R.id.btnShowDetail);
        btnShowOverview = view.findViewById(R.id.btnShowOverview);

        ArrayList<ClassOfTruong> classOfTruongs = new ArrayList<>();
        classOfTruongs.add(new ClassOfTruong(1, 10, "Class 01", new Date(2021, 0, 1), new Date(2021, 1, 1), false));
        classOfTruongs.add(new ClassOfTruong(2, 10, "Class 02", new Date(2021, 0, 1), new Date(2021, 1, 1), false));
        classOfTruongs.add(new ClassOfTruong(3, 10, "Class 03", new Date(2021, 0, 1), new Date(2021, 1, 1), false));
        classOfTruongs.add(new ClassOfTruong(4, 10, "Class 04", new Date(2021, 0, 1), new Date(2021, 1, 1), false));
        classOfTruongs.add(new ClassOfTruong(5, 10, "Class 05", new Date(2021, 0, 1), new Date(2021, 1, 1), false));
        classOfTruongs.add(new ClassOfTruong(6, 10, "Class 06", new Date(2021, 0, 1), new Date(2021, 1, 1), false));

        ArrayList<ModuleOfTruong> moduleOfTruongs = new ArrayList<>();
        moduleOfTruongs.add(new ModuleOfTruong(1, 1, "abc", "Module 1", new Date(2021, 0, 1), new Date(2021, 0, 1), new Date(2021, 0, 1), new Date(2021, 0, 1), false));
        moduleOfTruongs.add(new ModuleOfTruong(2, 1, "abc", "Module 2", new Date(2021, 0, 1), new Date(2021, 0, 1), new Date(2021, 0, 1), new Date(2021, 0, 1), false));
        moduleOfTruongs.add(new ModuleOfTruong(3, 2, "abc", "Module 3", new Date(2021, 0, 1), new Date(2021, 0, 1), new Date(2021, 0, 1), new Date(2021, 0, 1), false));
        moduleOfTruongs.add(new ModuleOfTruong(4, 2, "abc", "Module 4", new Date(2021, 0, 1), new Date(2021, 0, 1), new Date(2021, 0, 1), new Date(2021, 0, 1), false));
        moduleOfTruongs.add(new ModuleOfTruong(5, 3, "abc", "Module 5", new Date(2021, 0, 1), new Date(2021, 0, 1), new Date(2021, 0, 1), new Date(2021, 0, 1), false));
        moduleOfTruongs.add(new ModuleOfTruong(6, 4, "abc", "Module 6", new Date(2021, 0, 1), new Date(2021, 0, 1), new Date(2021, 0, 1), new Date(2021, 0, 1), false));

        ArrayList<String> className = new ArrayList<>();
        className.add(getActivity().getString(R.string.choose_item));
        for (ClassOfTruong var : classOfTruongs
             ) {
            className.add(var.getClassName());
        }
        spinnerClassName.setAdapter(new ArrayAdapter<String>(getActivity(),
                R.layout.support_simple_spinner_dropdown_item,
                className));

        ArrayList<String> moduleName = new ArrayList<>();
        moduleName.add(getActivity().getString(R.string.choose_item));
        for (ModuleOfTruong var : moduleOfTruongs
             ) {
            moduleName.add(var.getModuleName());
        }
        spinnerModuleName.setAdapter(new ArrayAdapter<String>(getActivity(), R.layout.support_simple_spinner_dropdown_item, moduleName));

        ArrayList<String> answerKey = new ArrayList<>();
        answerKey.add(getActivity().getString(R.string.strongly_disagree));
        answerKey.add(getActivity().getString(R.string.disagree));
        answerKey.add(getActivity().getString(R.string.undecided));
        answerKey.add(getActivity().getString(R.string.agree));
        answerKey.add(getActivity().getString(R.string.strong_agree));

        ArrayList<Answer> answerOfTruongs = new ArrayList<>();
        answerOfTruongs.add(new Answer(1, 1, 1, 0, "abc"));
        answerOfTruongs.add(new Answer(1, 2, 2, 1, "abc"));
        answerOfTruongs.add(new Answer(1, 3, 3, 2, "abc"));
        answerOfTruongs.add(new Answer(1, 4, 4, 3, "abc"));
        answerOfTruongs.add(new Answer(1, 5, 5, 4, "abc"));
        answerOfTruongs.add(new Answer(1, 6, 6, 0, "abc"));
        answerOfTruongs.add(new Answer(2, 1, 1, 1, "abc"));
        answerOfTruongs.add(new Answer(2, 2, 2, 2, "abc"));
        answerOfTruongs.add(new Answer(2, 3, 3, 3, "abc"));
        answerOfTruongs.add(new Answer(2, 4, 4, 4, "abc"));
        answerOfTruongs.add(new Answer(2, 5, 5, 0, "abc"));
        answerOfTruongs.add(new Answer(2, 6, 6, 1, "abc"));
        answerOfTruongs.add(new Answer(3, 1, 1, 2, "abc"));
        answerOfTruongs.add(new Answer(3, 2, 1, 3, "abc"));
        answerOfTruongs.add(new Answer(3, 3, 1, 4, "abc"));
        answerOfTruongs.add(new Answer(3, 4, 1, 0, "abc"));

        if(spinnerClassName.getSelectedItemPosition() != 0 && spinnerModuleName.getSelectedItemPosition() != 0){
            
        }

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        //Make dialog full screen with transparent background
        if (dialog != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            dialog.getWindow().setLayout(width, height);
        }
    }
}