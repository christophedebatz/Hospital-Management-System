package com.lemasne.hms.controller;

import com.lemasne.hms.interfaces.IController;
import com.lemasne.hms.interfaces.IView;
import com.lemasne.hms.model.ChambreModel;
import com.lemasne.hms.model.ServiceModel;
import com.lemasne.hms.tools.TemplateLoader;
import com.lemasne.hms.view.FrontView;
import com.lemasne.hms.view.TabView;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class FrontController implements IController, ActionListener, ChangeListener {

    private FrontView view;
    private IView chambreView;
    private IView serviceView;
            
    private IController chambreCtrl;
    private IController serviceCtrl;

    
    public FrontController() 
    {
        this.initChambre();
        this.initService();
        
        this.initFront();
    }
    
    private void initChambre() {
        this.chambreView = new TabView("Chambre");
        this.chambreCtrl = new ChambreController(new ChambreModel(), this.chambreView);
        this.chambreCtrl.loadTable();
    }
    
    private void initService() {
        this.serviceView = new TabView("Service");
        this.serviceCtrl = new ServiceController(new ServiceModel(), this.serviceView);
    }
    
    private void initFront() {
        List<Component> views = new ArrayList<>();
        views.add((Component) this.chambreView);
        views.add((Component) this.serviceView);
        
        this.view = new FrontView(views, this);
        this.view.setVisible(true);
        
        TemplateLoader.initWindowProperties(this.view);
    }
    
    @Override
    public void actionPerformed(ActionEvent event) {
        switch (event.getActionCommand()) {
           
            default:
                System.out.println("Other action...");
                break;
        }
    }

    @Override
    public void stateChanged(ChangeEvent event) {
        JTabbedPane tabs = (JTabbedPane) event.getSource();
        switch (tabs.getTitleAt(tabs.getSelectedIndex()).toLowerCase()) {
            case "service":
                this.serviceCtrl.loadTable();
            break;
            default:
                this.chambreCtrl.loadTable();
            break;
        }
    }

    @Override
    public void loadTable() {
        this.chambreCtrl.loadTable();
    }
}
