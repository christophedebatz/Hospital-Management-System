package com.lemasne.hms.controller;

import com.lemasne.hms.interfaces.IDao;
import com.lemasne.hms.interfaces.IFormView;
import com.lemasne.hms.interfaces.IModel;
import com.lemasne.hms.interfaces.IView;
import com.lemasne.hms.model.dao.MaladeDao;
import com.lemasne.hms.model.entities.Malade;
import com.lemasne.hms.tools.Helpers;
import com.lemasne.hms.tools.TemplateLoader;
import com.lemasne.hms.view.forms.MaladeFormView;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;


public class MaladeController extends AbstractController<Malade> {
    
    private final MaladeFormView formView;
    private final IView parent;
    
    public MaladeController(IModel model, IView view, IView parent) {
        super(Malade.class, model, view);
        this.parent = parent;

        this.formView = new MaladeFormView((JFrame) this.parent, true);
        this.formView.setActionListener(this);
    }

    
    @Override
    public void actionPerformed(ActionEvent event) {
        
        TemplateLoader.load(FrontController.currentSkin);
        SwingUtilities.updateComponentTreeUI(this.formView);
        
        switch (event.getActionCommand()) {
            
            case "add":
                this.formView.setFormType(IFormView.FormType.ADD_FEATURE);
                this.formView.setNumero(
                        String.valueOf(
                                ((MaladeDao)this.model.getDao()).getMaxIdentifierIncrement()+1)
                );
                this.formView.setVisible(true);
                break;
                
            case "update":
                
                this.formView.setFormType(IFormView.FormType.UPDATE_FEATURE);
                
                // get selected jtable selected row
                IDao dao = this.getModel().getDao();
                String[] values = Helpers.getKeyValues(this.view, dao);

                // retrieve chamber
                List<Malade> malades = dao.getListWith(
                        dao.findById((Object[]) values)
                );

                Malade malade;
                if (!malades.isEmpty()) { // if result not null
                    malade = malades.get(0);
                    
                    this.formView.setNumero(String.valueOf(malade.getNumero()));
                    this.formView.setNom(malade.getNom());
                    this.formView.setPrenom(malade.getPrenom());
                    this.formView.setTelephone(malade.getTel());
                    this.formView.setAdresse(malade.getAdresse());
                    this.formView.setMutuelle(malade.getMutuelle());
                    
                    this.formView.setVisible(true);
                }
                
                else {
                    JOptionPane.showMessageDialog((Component) this.parent, "Cette donnée semble inexistante ou déjà supprimée/éditée.");
                    this.view.dispose();
                }
                break;
                
            case "validate":
                
                if (this.formView.getNumero().equals("")
                        || this.formView.getNom().equals("")
                        || this.formView.getPrenom().equals("")
                        || this.formView.getAdresse().equals("")
                        || this.formView.getTelephone().equals("")
                        || this.formView.getMutuelle().equals("")) {
                    JOptionPane.showMessageDialog((JFrame) this.parent, "Le formulaire n'a pas été correctement rempli.");
                    return;
                }

                final String numero = this.formView.getNumero();
                final String nom = this.formView.getNom();
                final String prenom = this.formView.getPrenom();
                final String adresse = this.formView.getAdresse();
                final String telephone = Helpers.formatTelephone(this.formView.getTelephone(), null);
                final String mutuelle = this.formView.getMutuelle().substring(0, Math.min(this.formView.getMutuelle().length(), 6)).toUpperCase();
                
                // adding something
                if (this.formView.getFormType() == IFormView.FormType.ADD_FEATURE) {
                    Helpers.addToDatabase(this.model.getDao(), numero, nom, prenom, adresse, telephone, mutuelle);
                   
                    // editing
                } else {
                    String[] keyValues = Helpers.getKeyValues(this.view, this.getModel().getDao());
                    
                    Map<String, Object> params = new HashMap<>();
                    params.put("numero", numero);
                    params.put("nom", nom);
                    params.put("prenom", prenom);
                    params.put("adresse", adresse);
                    params.put("tel", telephone);
                    params.put("mutuelle", mutuelle);
                    
                    if (!this.model.getDao().updateById(params, (Object[]) keyValues)) {
                        JOptionPane.showMessageDialog((JFrame)this.parent, "Impossible de mettre à jour les données. Erreur: 47, Update AbstractDao");
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
}
