package com.lemasne.hms.controller;

import com.lemasne.hms.controller.dto.ControllerDTO;
import com.lemasne.hms.interfaces.IController;
import com.lemasne.hms.interfaces.IView;
import com.lemasne.hms.model.ChambreModel;
import com.lemasne.hms.model.DocteurModel;
import com.lemasne.hms.model.HospitalisationModel;
import com.lemasne.hms.model.InfirmierModel;
import com.lemasne.hms.model.MaladeModel;
import com.lemasne.hms.model.ServiceModel;
import com.lemasne.hms.model.SoigneModel;
import com.lemasne.hms.settings.Config;
import com.lemasne.hms.settings.Constants;
import com.lemasne.hms.tools.TemplateLoader;
import com.lemasne.hms.view.ConnectDialogView;
import com.lemasne.hms.view.FrontView;
import com.lemasne.hms.view.TabView;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class FrontController implements IController, ActionListener, ChangeListener, ItemListener {
    
    private final String name = null;
    private ControllerDTO dto;
    private FrontView view;
    private ConnectDialogController connectDialogCtrl;
    
    private IView chambreView;
    private IView serviceView;
    private IView infirmierView;
    private IView docteurView;
    private IView maladeView;
    private IView hospitalisationView;
    private IView soigneView;
    
    private IController currentCtrl;
    private IController chambreCtrl;
    private IController serviceCtrl;
    private IController infirmierCtrl;
    private IController docteurCtrl;
    private IController maladeCtrl;
    private IController hospitalisationCtrl;
    private IController soigneCtrl;

    
    public FrontController(boolean forceConnect) 
    {
        this.initChambre();
        this.initService();
        this.initInfirmier();
        this.initDocteur();
        this.initMalade();
        this.initHospitalisation();
        this.initSoigne();
        
        this.initFront(forceConnect);
    }
  
    private void initChambre() {
        this.chambreView = new TabView(Constants.CHAMBRE);
        this.chambreCtrl = new ChambreController(new ChambreModel(), this.chambreView, this.view);
        this.chambreCtrl.loadTable();
    }
    
    private void initService() {
        this.serviceView = new TabView(Constants.SERVICE);
        this.serviceCtrl = new ServiceController(new ServiceModel(), this.serviceView, this.view);
    }
    
    private void initInfirmier() {
        this.infirmierView = new TabView(Constants.INFIRMIER);
        this.infirmierCtrl = new InfirmierController(new InfirmierModel(), this.infirmierView);
    }
    
    private void initDocteur() {
        this.docteurView = new TabView(Constants.DOCTEUR);
        this.docteurCtrl = new DocteurController(new DocteurModel(), this.docteurView);
    }
    
    private void initMalade() {
        this.maladeView = new TabView(Constants.MALADE);
        this.maladeCtrl = new MaladeController(new MaladeModel(), this.maladeView);
    }
    
    private void initHospitalisation() {
        this.hospitalisationView = new TabView(Constants.HOSPITALISATION);
        this.hospitalisationCtrl = new HospitalisationController(new HospitalisationModel(), this.hospitalisationView);
    }
    
    private void initSoigne() {
        this.soigneView = new TabView(Constants.SOIGNE);
        this.soigneCtrl = new SoigneController(new SoigneModel(), this.soigneView);
    }
        
    private void initFront(boolean forceConnect) {
        List<Component> views = new ArrayList<>();
        views.add((Component) this.chambreView);
        views.add((Component) this.serviceView);
        views.add((Component) this.infirmierView);
        views.add((Component) this.docteurView);
        views.add((Component) this.maladeView);
        views.add((Component) this.hospitalisationView);
        views.add((Component) this.soigneView);
        
        this.view = new FrontView(views, this, Config.getInstance().get("connectAtStartup").equals("true") || forceConnect);
        this.view.setVisible(true);
        this.view.setItemListener(this);
        this.view.setActionListener(this);
        this.dto = new ControllerDTO();
        this.currentCtrl = chambreCtrl;
        
        TemplateLoader.initWindowProperties(this.view);
    }
    
    
    @Override
    public void actionPerformed(ActionEvent event) {
        switch (event.getActionCommand()) {
            case "connect":
                ConnectDialogView connectDialogView = new ConnectDialogView(this.view, true);
                this.connectDialogCtrl = new ConnectDialogController(connectDialogView, this);
            break;
            case "close":
                this.view.dispose();
                System.exit(0);
            break;
            default:
                System.out.println("Other action...");
            break;
        }
    }
    
    @Override
    public void itemStateChanged(ItemEvent event) {
        if (event.getItem().toString().contains("join")) {
            this.dto.setEnabledJoins(event.getStateChange() == ItemEvent.SELECTED);
            this.currentCtrl.setControllerDto(this.dto);
            this.currentCtrl.loadTable();
        }
    }

    @Override
    public void stateChanged(ChangeEvent event) {
        JTabbedPane tabs = (JTabbedPane) event.getSource();
        switch (tabs.getTitleAt(tabs.getSelectedIndex()).toLowerCase()) {
            case Constants.SERVICE:
                this.currentCtrl = this.serviceCtrl;
            break;
            case Constants.INFIRMIER:
                this.currentCtrl = this.infirmierCtrl;
            break;
            case Constants.MALADE:
                this.currentCtrl = this.maladeCtrl;
            break;
            case Constants.DOCTEUR:
                this.currentCtrl = this.docteurCtrl;
            break;
            case Constants.HOSPITALISATION:
                this.currentCtrl = this.hospitalisationCtrl;
            break;
            case Constants.SOIGNE:
                this.currentCtrl = this.soigneCtrl;
            break;
                
            default:
                this.currentCtrl = this.chambreCtrl;
            break;
        }
        
        this.currentCtrl.setControllerDto(this.dto);
        this.currentCtrl.loadTable();
    }

    @Override
    public void loadTable() {
        this.chambreCtrl.loadTable();
    }

    @Override
    public IController setControllerDto(ControllerDTO controllerDTO) {
        this.dto = controllerDTO;
        return this;
    }

    @Override
    public void executeDTORequest() {
        if (this.dto != null) {
            switch (this.dto.getCtrlRequest()) {
                case "launch_connexion":
                    this.view.dispose();
                    new FrontController(true);
                break;
            }
        }
    }

    @Override
    public String getName() {
        return this.name;
    }
}
