package com.lemasne.hms.controller;

import com.lemasne.hms.interfaces.IController;
import com.lemasne.hms.interfaces.IDao;
import com.lemasne.hms.interfaces.IView;

public class ControllerFactory {

    private IView view;
    private IDao dao;
    private IController controller;
    
    
    public ControllerFactory(IDao dao, IView view) {
        this.view = view;
        this.dao = dao;
    }
    
    public IController getController(String controllerType) {
        switch (controllerType) {
            case "homeController":
                return new HomeController();
                        
            default:
                return null;
        }
    }
}
