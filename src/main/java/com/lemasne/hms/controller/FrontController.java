package com.lemasne.hms.controller;

import com.lemasne.hms.interfaces.IController;
import com.lemasne.hms.interfaces.IDao;
import com.lemasne.hms.interfaces.IView;

public class FrontController {

    private IView view;
    private IDao dao;
    private IController controller;
    
    
    public FrontController(IDao dao, IView view) {
        this.view = view;
        this.dao = dao;
        
//        switch controller in terms of type of view
//        switch (view) {
//            case MainWindow:
//                this.controller = ...
//        }
//    }
}
