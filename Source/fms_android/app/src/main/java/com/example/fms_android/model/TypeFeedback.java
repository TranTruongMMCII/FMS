package com.example.fms_android.model;

public class TypeFeedback {
    private Long typeID;
    private boolean isDeleted;
    private String typeName;

    public TypeFeedback(Long typeID, boolean isDeleted, String typeName) {
        this.typeID = typeID;
        this.isDeleted = isDeleted;
        this.typeName = typeName;
    }

    public TypeFeedback(boolean isDeleted, String typeName) {
        this.isDeleted = isDeleted;
        this.typeName = typeName;
    }

    public Long getTypeID() {
        return typeID;
    }

    public void setTypeID(Long typeID) {
        this.typeID = typeID;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
