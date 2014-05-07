package com.lemasne.hms.view.forms.model;

import java.util.List;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

public class ComboboxModel<T> extends AbstractListModel implements ComboBoxModel {

    private final List<T> elements;
    private final Class entityClass;
    private T selectedItem;
    
    public ComboboxModel(Class entityClass, List<T> elements) {
        super();
        this.entityClass = entityClass;
        this.elements = elements;
    }
    
    @Override
    public void setSelectedItem(Object newElement) {
        for (T t : elements) {
            if (((T)newElement).equals(t)) {
                this.selectedItem = t;
                break;
            }
        }
    }

    @Override
    public Object getSelectedItem() {
        return this.selectedItem;
    }

    @Override
    public int getSize() {
        return this.elements.size();
    }

    @Override
    public Object getElementAt(int index) {
        return (T) this.elements.get(index);
    }
}
