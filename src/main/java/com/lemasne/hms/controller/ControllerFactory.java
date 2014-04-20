package com.lemasne.hms.controller;

import com.lemasne.hms.interfaces.IController;
import com.lemasne.hms.interfaces.IModel;
import com.lemasne.hms.interfaces.IView;

public class ControllerFactory {

    private IView view;
    private IModel model;
    private static IController controllerInstance;
    
    
    public ControllerFactory(IModel model, IView view) {
        this.view = view;
        this.model = model;
    }
    
    public synchronized IController getController(String controllerType) {
        switch (controllerType) {
            case "homeController":
                return HomeController.getInstance(this.model, this.view);
                        
            default:
                return null;
        }
    }
}
