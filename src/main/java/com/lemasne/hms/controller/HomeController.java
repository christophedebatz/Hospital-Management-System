package com.lemasne.hms.controller;

import com.lemasne.hms.interfaces.IController;
import com.lemasne.hms.interfaces.IModel;
import com.lemasne.hms.interfaces.IView;


public class HomeController implements IController {

    private static HomeController instance;
    private IModel model;
    private IView view;
    
    private HomeController(IModel model, IView view) {
        if (model == null || view == null) {
            throw new IllegalArgumentException("Model or view or both are null.");
        }
        this.model = model;
        this.view = view;
    }
    
    public static synchronized IController getInstance(IModel model, IView view) {
        if (instance == null) {
            instance = new HomeController(model, view);
        }
        return instance;
    }
    
    public IController setView(IView view) {
        this.view = view;
        return this;
    }
    
   public IController setModel(IModel model) {
       this.model = model;
       return this;
   }
}
