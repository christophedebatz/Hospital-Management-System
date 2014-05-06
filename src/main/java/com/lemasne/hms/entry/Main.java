package com.lemasne.hms.entry;

import com.lemasne.hms.controller.FrontController;
import com.lemasne.hms.tools.TemplateLoader;

public class Main {

    public static void main(String[] argv) {

        TemplateLoader.load("Nimbus");
        new FrontController(false);
        
    }
}
