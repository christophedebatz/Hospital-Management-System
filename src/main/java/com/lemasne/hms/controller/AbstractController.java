package com.lemasne.hms.controller;

import com.lemasne.hms.controller.dto.ControllerDTO;
import com.lemasne.hms.interfaces.IController;
import com.lemasne.hms.interfaces.IModel;
import com.lemasne.hms.interfaces.IView;
import com.lemasne.hms.tools.Helpers;

abstract class AbstractController<T> implements IController {

    protected final Class entityClass;
    protected ControllerDTO dto;
    protected IModel model;
    protected IView view;

    protected AbstractController(Class entityClass, IModel model, IView view) {
        this.entityClass = entityClass;
        
        if (model == null || view == null) {
            throw new IllegalArgumentException("Model or view or both are null.");
        }
        
        this.model = model;
        this.view = view;
        this.view.setVisible(true);
    }
    
    @Override
    public void loadTable() {
        if (this.dto != null && this.dto.isEnableJoins()) {
            this.view.getTable().setModel(this.model.getJoinedTableModel());
        } else {
            this.view.getTable().setModel(this.model.getTableModel());
        }
        
        Helpers.updateRowHeights(this.view.getTable());
    }
    
    @Override
    public void setControllerDto(ControllerDTO controllerDTO) {
        this.dto = controllerDTO;
    }
}
