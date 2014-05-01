package com.lemasne.hms.controller.dto;


public class ControllerDTO {
    
    private boolean enableJoins;

    public ControllerDTO(boolean enableJoins) {
        this.enableJoins = enableJoins;
    }

    public ControllerDTO() {
        this.enableJoins = false;
    }
    
    public boolean isEnableJoins() {
        return enableJoins;
    }

    public void setEnabledJoins(boolean enableJoins) {
        this.enableJoins = enableJoins;
    }
    
}
