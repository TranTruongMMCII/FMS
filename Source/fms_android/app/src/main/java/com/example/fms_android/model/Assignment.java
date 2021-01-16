package com.example.fms_android.model;

public class Assignment {
    private long classId;
//    private Module moduleId;
    private long moduleId;
    private String trainerId;
    private String registrationCode;

//    public Assignment(long classId, Module moduleId, String trainerId, String registrationCode) {
//        this.classId = classId;
//        this.moduleId = moduleId;
//        this.trainerId = trainerId;
//        this.registrationCode = registrationCode;
//    }
//
//    public Assignment(long classId, Module moduleId, String trainerId) {
//        this.classId = classId;
//        this.moduleId = moduleId;
//        this.trainerId = trainerId;
//    }


    public Assignment(long classId, long moduleId, String trainerId) {
        this.classId = classId;
        this.moduleId = moduleId;
        this.trainerId = trainerId;
    }

    public Assignment(long classId, long moduleId, String trainerId, String registrationCode) {
        this.classId = classId;
        this.moduleId = moduleId;
        this.trainerId = trainerId;
        this.registrationCode = registrationCode;
    }

    public Assignment() {
    }

    public void setModuleId(long moduleId) {
        this.moduleId = moduleId;
    }

    public long getClassId() {
        return classId;
    }

    public void setClassId(long classId) {
        this.classId = classId;
    }

//    public Module getModuleId() {
//        return moduleId;
//    }
//
//    public void setModuleId(Module moduleId) {
//        this.moduleId = moduleId;
//    }


    public long getModuleId() {
        return moduleId;
    }

    public String getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(String trainerId) {
        this.trainerId = trainerId;
    }

    public String getRegistrationCode() {
        return registrationCode;
    }

    public void setRegistrationCode(String registrationCode) {
        this.registrationCode = registrationCode;
    }
}
