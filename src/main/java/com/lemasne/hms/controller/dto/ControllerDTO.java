package com.lemasne.hms.controller.dto;


public class ControllerDTO {
    
    private boolean enableJoins;
    private String ctrlRequest;
    private Object ctrlObject;

    public ControllerDTO(boolean enableJoins) {
        this.enableJoins = enableJoins;
    }
    
    public ControllerDTO(String ctrlRequest) {
        this.ctrlRequest = ctrlRequest;
    }

    public ControllerDTO() {
        this.enableJoins = false;
        this.ctrlRequest = null;
    }

    public Object getCtrlObject() {
        return ctrlObject;
    }

    public void setCtrlObject(Object ctrlObject) {
        this.ctrlObject = ctrlObject;
    }
    
    public String getCtrlRequest() {
        return ctrlRequest;
    }

    public void setCtrlRequest(String ctrlRequest) {
        this.ctrlRequest = ctrlRequest;
    }
    
    public boolean isEnableJoins() {
        return enableJoins;
    }

    public void setEnabledJoins(boolean enableJoins) {
        this.enableJoins = enableJoins;
    }
}
