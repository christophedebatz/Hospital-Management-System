package com.lemasne.hms.controller;

import com.lemasne.hms.controller.dto.ControllerDTO;
import com.lemasne.hms.interfaces.IController;
import com.lemasne.hms.interfaces.IModel;
import com.lemasne.hms.interfaces.IView;
import com.lemasne.hms.tools.Helpers;
import com.lemasne.hms.tools.TemplateLoader;
import java.awt.event.ActionListener;
import java.util.regex.PatternSyntaxException;
import javax.swing.RowFilter;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

abstract class AbstractController<T> implements IController, ActionListener, DocumentListener, ListSelectionListener {

    protected TableRowSorter<TableModel> sorter;
    protected final Class entityClass;
    protected ControllerDTO dto;
    protected IModel model;
    protected IView view;
    protected String name;

    protected AbstractController(Class entityClass, IModel model, IView view) {
        this.name = entityClass.getSimpleName().toLowerCase();
        this.dto = new ControllerDTO();
        this.entityClass = entityClass;

        if (model == null || view == null) {
            throw new IllegalArgumentException("Model or view is null.");
        }

        this.model = model;
        this.view = view;
        this.view.setVisible(true);

        this.sorter = new TableRowSorter<>();
        this.view.addSearchBoxListener(this);
        this.view.setSelectionListener(this);
        this.view.setActionListener(this);
    }

    @Override
    public IModel getModel() {
        return this.model;
    }

    @Override
    public void loadTable() {
        TableModel modelToLoad;
        if (this.dto != null && this.dto.isEnableJoins()) {
            modelToLoad = this.model.getJoinedTableModel();
        } else {
            modelToLoad = this.model.getTableModel();
        }

        this.sorter.setModel(modelToLoad);
        this.view.getTable().setModel(modelToLoad);
        this.view.getTable().setRowSorter(this.sorter);

        Helpers.updateRowHeights(this.view.getTable());
    }

    @Override
    public IController setControllerDto(ControllerDTO controllerDTO) {
        this.dto = controllerDTO;
        return this;
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        if (this.view.getSearchContent().length() == 0) {
            sorter.setRowFilter(null);
        } else {
            try {
                sorter.setRowFilter(
                        RowFilter.regexFilter("(?i)" + this.view.getSearchContent())
                );
            } catch (PatternSyntaxException pse) {
                System.err.println("Bad regex pattern");
            }
        }
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        this.changedUpdate(e);
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        this.changedUpdate(e);
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void executeDTORequest() {
        if (this.dto != null) {

        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            this.view.setEnabledRemoving(this.view.getTable().getSelectedRows().length > 0 && (this.dto == null || !this.dto.isEnableJoins()));
            this.view.setEnabledUpdating(this.view.getTable().getSelectedRows().length == 1 && (this.dto == null || !this.dto.isEnableJoins()));
        }
    }
}
