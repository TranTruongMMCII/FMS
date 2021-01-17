package com.example.fms_android.model;

public class Assignment {
    private Class classId;
    private Module moduleId;
    private Trainer trainerId;
    private String registrationCode;

    public Assignment(Class classId, Module moduleId, Trainer trainerId, String registrationCode) {
        this.classId = classId;
        this.moduleId = moduleId;
        this.trainerId = trainerId;
        this.registrationCode = registrationCode;
    }

    public Assignment(Class classId, Module moduleId, Trainer trainerId) {
        this.classId = classId;
        this.moduleId = moduleId;
        this.trainerId = trainerId;
    }

    public Assignment() {
    }

    public Class getClassId() {
        return classId;
    }

    public void setClassId(Class classId) {
        this.classId = classId;
    }

    public Module getModuleId() {
        return this.moduleId;
    }

    public void setModuleId(Module moduleId) {
        this.moduleId = moduleId;
    }

    public Trainer getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(Trainer trainerId) {
        this.trainerId = trainerId;
    }

    public String getRegistrationCode() {
        return registrationCode;
    }

    public void setRegistrationCode(String registrationCode) {
        this.registrationCode = registrationCode;
    }
}
