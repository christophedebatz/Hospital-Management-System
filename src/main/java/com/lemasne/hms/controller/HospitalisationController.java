package com.lemasne.hms.controller;

import com.lemasne.hms.interfaces.IDao;
import com.lemasne.hms.interfaces.IFormView;
import com.lemasne.hms.interfaces.IModel;
import com.lemasne.hms.interfaces.IView;
import com.lemasne.hms.model.ChambreModel;
import com.lemasne.hms.model.MaladeModel;
import com.lemasne.hms.model.ServiceModel;
import com.lemasne.hms.model.entities.Chambre;
import com.lemasne.hms.model.entities.Hospitalisation;
import com.lemasne.hms.model.entities.Malade;
import com.lemasne.hms.model.entities.Service;
import com.lemasne.hms.tools.Helpers;
import com.lemasne.hms.tools.TemplateLoader;
import com.lemasne.hms.view.forms.HospitalisationFormView;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class HospitalisationController extends AbstractController<Hospitalisation> implements ItemListener {

    private final IView parent;
    private final HospitalisationFormView formView;

    public HospitalisationController(IModel model, IView view, IView parent) {
        super(Hospitalisation.class, model, view);
        this.parent = parent;

        this.formView = new HospitalisationFormView((JFrame) this.parent, true);
        this.formView.setActionListener(this);
        this.formView.setItemListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        
        TemplateLoader.load(FrontController.currentSkin);
        SwingUtilities.updateComponentTreeUI(this.formView);
        
        switch (event.getActionCommand()) {

            case "add":
                this.formView.setFormType(IFormView.FormType.ADD_FEATURE);

                this.formView.getChambreCombo().setModel(new ChambreModel().getComboBoxModel());
                this.formView.getMaladeCombo().setModel(new MaladeModel().getComboBoxModel());
                this.formView.getServiceCombo().setModel(new ServiceModel().getComboBoxModel());

                this.formView.setVisible(true);
                break;

            case "update":
                this.formView.setFormType(IFormView.FormType.UPDATE_FEATURE);

                // get selected jtable selected row
                IDao dao = this.getModel().getDao();
                String[] values = Helpers.getKeyValues(this.view, dao);

                List<Hospitalisation> hospis = dao.getListWith(
                        dao.findById((Object[]) values)
                );

                Hospitalisation hospi;
                if (!hospis.isEmpty()) { // if result not null
                    hospi = hospis.get(0);

                    this.formView.setNbLits(hospi.getLit()); // nb lits

                    // retrieve associated malade name (with found directeur no)
                    IModel maladeModel = new MaladeModel();
                    List<Malade> malades = maladeModel.getDao().getListWith(
                            maladeModel.getDao().findAllByFieldsNames(
                                    new String[]{"numero"},
                                    new Object[]{hospi.getNo_malade()}
                            )
                    );

                    // fill directeur combobox
                    this.formView.getMaladeCombo().setModel(maladeModel.getComboBoxModel());

                    if (!malades.isEmpty()) {
                        this.formView.getMaladeCombo().getModel().setSelectedItem((Malade) malades.get(0));
                    }

                    // retrieve associated service name (with found directeur no)
                    IModel serviceModel = new ServiceModel();
                    List<Service> services = serviceModel.getDao().getListWith(
                            serviceModel.getDao().findAllByFieldsNames(
                                    new String[]{"code"},
                                    new Object[]{hospi.getCode_service()}
                            )
                    );

                    // fill directeur combobox
                    this.formView.getServiceCombo().setModel(serviceModel.getComboBoxModel());

                    if (!services.isEmpty()) {
                        this.formView.getServiceCombo().getModel().setSelectedItem((Service) services.get(0));
                    }

                    // retrieve associated malade name (with found directeur no)
                    IModel chambreModel = new ChambreModel();
                    List<Chambre> chambres = chambreModel.getDao().getListWith(
                            chambreModel.getDao().findAllByFieldsNames(
                                    new String[]{"no_chambre"},
                                    new Object[]{hospi.getNo_chambre()}
                            )
                    );

                    // fill chambres combobox
                    this.formView.getChambreCombo().setModel(((ChambreModel) chambreModel).getChambreByService((Service) services.get(0)));

                    if (!chambres.isEmpty()) {
                        this.formView.getChambreCombo().getModel().setSelectedItem((Chambre) chambres.get(0));
                    }

                    this.formView.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog((Component) this.parent, "Cette donnée semble inexistante ou déjà supprimée/éditée.");
                    this.view.dispose();
                }
                break;

            case "validate":

                if (this.formView.getChambreCombo().getSelectedItem() == null) {
                    JOptionPane.showMessageDialog((JFrame) this.parent, "Vous devez d'abord créer une chambre avant de continuer.");
                    return;
                }

                if (this.formView.getServiceCombo().getSelectedItem() == null
                        || this.formView.getMaladeCombo().getSelectedItem() == null) {
                    JOptionPane.showMessageDialog((JFrame) this.parent, "Formulaire incomplet. Veuillez recommencer.");
                    return;
                }

                final String no_malade = String.valueOf(((Malade) this.formView.getMaladeCombo().getSelectedItem()).getNumero());
                final String code_service = ((Service) this.formView.getServiceCombo().getSelectedItem()).getCode();
                final String no_chambre = String.valueOf(((Chambre) this.formView.getChambreCombo().getSelectedItem()).getNo_chambre());
                final String lit = String.valueOf(this.formView.getNbLits());

                // add something
                if (this.formView.getFormType().equals(IFormView.FormType.ADD_FEATURE)) {
                    Helpers.addToDatabase(this.model.getDao(), no_malade, code_service, no_chambre, lit);
                } // update something
                else {
                    String[] keyValues = Helpers.getKeyValues(this.view, this.getModel().getDao());

                    Map<String, Object> params = new HashMap<>();
                    params.put("no_malade", no_malade);
                    params.put("code_service", code_service);
                    params.put("no_chambre", no_chambre);
                    params.put("lit", lit);

                    if (!this.model.getDao().updateById(params, (Object[]) keyValues)) {
                        JOptionPane.showMessageDialog((JFrame) this.parent, "Impossible de mettre à jour les données. Erreur: 47, Update AbstractDao");
                        return;
                    }
                }

                this.loadTable();
                this.formView.dispose();

                break;

            case "remove":
                Helpers.removeFromDatabase(this.view, this);
                break;

            default:
                this.formView.dispose();
                break;
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        JComboBox comboBox = (JComboBox) e.getSource();
        if (comboBox == this.formView.getServiceCombo()) {
            this.formView.getChambreCombo().setModel(
                    ((ChambreModel) new ChambreModel()).getChambreByService(
                            (Service) this.formView.getServiceCombo().getSelectedItem()
                    )
            );
        }
    }
}
