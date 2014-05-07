package com.lemasne.hms.interfaces;

import java.awt.event.ActionListener;


public interface IFormView {
    
    public enum FormType {
        ADD_FEATURE,
        UPDATE_FEATURE
    }
    
    public void setActionListener(ActionListener listener);
    public void setVisible(boolean isVisible);
    public void dispose();
    public void setFormType(FormType formType);
    public void setEntity(Object entity);
    public FormType getFormType();
}
