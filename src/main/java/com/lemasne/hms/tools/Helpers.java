package com.lemasne.hms.tools;

import com.lemasne.hms.interfaces.IController;
import com.lemasne.hms.interfaces.IDao;
import com.lemasne.hms.interfaces.IView;
import java.awt.Component;
import java.util.Arrays;
import javax.swing.JOptionPane;
import javax.swing.JTable;

public class Helpers {

    public static void updateRowHeights(JTable table) {
        try {
            for (int row = 0; row < table.getRowCount(); row++) {
                int rowHeight = table.getRowHeight();

                for (int column = 0; column < table.getColumnCount(); column++) {
                    Component comp = table.prepareRenderer(table.getCellRenderer(row, column), row, column);
                    rowHeight = Math.max(rowHeight, comp.getPreferredSize().height);
                }

                table.setRowHeight(row, rowHeight);
            }
        } catch (ClassCastException e) {
            System.err.println("Dynamic resizer for table was not able to run.");
        }
    }

    public static String[] getKeyValues(IView view, IDao dao) {
        int[] rowsToRemove = view.getTable().getSelectedRows();
        String[] values = new String[dao.getKeysNames().length * rowsToRemove.length];
        int i = 0;

        for (int selectedRow : rowsToRemove) { // browse rows to remove
            for (String keyName : dao.getKeysNames()) { // browse keys of the current entity and match them
                values[i++] = String.valueOf(
                        view.getTable().getValueAt(
                                selectedRow,
                                view.getTable().getColumn(keyName.toUpperCase().replaceAll("_", " ")).getModelIndex()
                        )
                );
            }
        }
        return values;
    }

    // ADD (modify)
    public static void removeFromDatabase(IView view, IController ctrl) {
        if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(
                view.getParent(),
                "Confirmez-vous la suppression de ces " + view.getTable().getSelectedRows().length + " enregistrement(s) ?",
                "Suppression",
                JOptionPane.YES_NO_OPTION)) {

            IDao dao = ctrl.getModel().getDao();
            String[] values = Helpers.getKeyValues(view, dao);

            int i = 0;
            boolean isFirst = true;
            String[] idValues = new String[dao.getKeysNames().length];
            for (String v : values) {
                if (!isFirst && i % dao.getKeysNames().length == 0) {
                    i = 0;
                    dao.removeById((Object[]) idValues);
                }
                idValues[i++] = v;
                isFirst = false;
            }
            dao.removeById((Object[]) idValues);

            ctrl.loadTable();
        }
    }

    // ADD
    public static boolean addToDatabase(IDao dao, Object... values) {
        return dao.insert(Arrays.asList(values));
    }

    // ADD
    public static String formatTelephone(String telephone, String sep) {
        if (telephone.length() <= 10) {
            String separator = sep;
            if (sep == null) {
                separator = " ";
            }
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < telephone.length(); i++) {
                if (i > 0 && i % 2 == 0) {
                    sb.append(separator);
                }
                sb.append(telephone.charAt(i));
            }
            return sb.toString();
        }
        return telephone;
    }
}
