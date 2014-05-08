package com.lemasne.hms.controller;

import com.lemasne.hms.interfaces.IDao;
import com.lemasne.hms.interfaces.IFormView.FormType;
import com.lemasne.hms.interfaces.IModel;
import com.lemasne.hms.interfaces.IView;
import com.lemasne.hms.model.ChambreModel;
import com.lemasne.hms.model.dao.ChambreDao;
import com.lemasne.hms.model.entities.Chambre;
import com.lemasne.hms.model.entities.Employe;
import com.lemasne.hms.model.entities.Service;
import com.lemasne.hms.tools.Helpers;
import com.lemasne.hms.view.forms.ChambreFormView;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ChambreController extends AbstractController<Chambre> implements ItemListener {

    private final IView parent;
    private final ChambreFormView formView;

    public ChambreController(IModel model, IView view, IView parent) {
        super(Chambre.class, model, view);
        this.parent = parent;

        this.formView = new ChambreFormView((JFrame) this.parent, true);
        this.formView.setItemListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        switch (event.getActionCommand()) {
            case "add":
                this.formView.setFormType(FormType.ADD_FEATURE);
                this.formView.setNumero(((ChambreDao) this.model.getDao()).getMaxIdentifierIncrement() + 1);
                this.formView.getServiceCombo().setModel(((ChambreModel) this.model).getChambreServicesComboBoxModel());
                this.formView.getSurveillantCombo().setModel(
                        ((ChambreModel) this.model).getChambreInfirmiersComboBoxModel(
                                (Service) this.formView.getServiceCombo().getSelectedItem()
                        )
                );
                this.formView.setVisible(true);
                break;
            case "validate":
                if (this.formView.getFormType().equals(FormType.ADD_FEATURE)) { // add something
                    if (!"".equals(this.formView.getNumero())) {
                        List<String> toInsert = new ArrayList<>(); // respect order
                        toInsert.add(((Service) this.formView.getServiceCombo().getSelectedItem()).getCode());
                        toInsert.add(this.formView.getNumero());
                        toInsert.add(String.valueOf(((Employe) this.formView.getSurveillantCombo().getSelectedItem()).getNumero()));
                        toInsert.add(String.valueOf(this.formView.getNbLits()));

                        this.model.getDao().insert(toInsert); // insert in db

                        this.loadTable();
                        this.formView.dispose();
                    } else {
                        JOptionPane.showMessageDialog((JFrame) this.parent, "Le numéro de la chambre est vide.");
                    }
                }
                break;
            case "remove":
                Helpers.removeFromDatabase(this.view, this);
                break;
            case "update":
                this.formView.setFormType(FormType.UPDATE_FEATURE);

                int rowToUpdate = view.getTable().getSelectedRow();
                IDao dao = this.getModel().getDao();
                String[] values = new String[dao.getKeysNames().length];
                int i = 0;

                for (String keyName : dao.getKeysNames()) { // browse keys of the current entity and match them
                    values[i++] = String.valueOf(
                            view.getTable().getModel().getValueAt(
                                    rowToUpdate,
                                    view.getTable().getColumn(keyName.toUpperCase().replaceAll("_", " ")).getModelIndex()
                            )
                    );
                }

                List<Chambre> chambres = dao.getListWith(
                        dao.findById((Object[]) values)
                );

                Chambre chambre = null;
                if (!chambres.isEmpty()) {
                    chambre = chambres.get(0);
                    this.formView.setNbLits(chambre.getNb_lits());
                    this.formView.setNumero(chambre.getNo_chambre());
                }

                this.formView.setVisible(true);
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
            this.formView.getSurveillantCombo().setModel(
                    ((ChambreModel) this.model).getChambreInfirmiersComboBoxModel(
                            (Service) this.formView.getServiceCombo().getSelectedItem()
                    )
            );
        }
    }
}
