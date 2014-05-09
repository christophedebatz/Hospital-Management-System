package com.lemasne.hms.tools;

import java.awt.Toolkit;
import java.awt.Window;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class TemplateLoader {

    public static void load(String templateName) {

        if (templateName == null) {
            TemplateLoader.loadDefault();
            return;
        }
        
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if (templateName.equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException e) {
            try {
                UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                JOptionPane.showMessageDialog(null, "Cannot load " + templateName + " template.");
            }
        }
    }

    // ADD
    public static void loadDefault() {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            JOptionPane.showMessageDialog(null, "Cannot load default template.");
        }
    }

    public static void initWindowProperties(Window frame) {
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage(TemplateLoader.class.getResource("/pictures/hospital.png")));
    }
}
