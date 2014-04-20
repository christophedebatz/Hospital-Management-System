package com.lemasne.hms.entry;

import com.lemasne.hms.interfaces.IController;
import com.lemasne.hms.interfaces.IDao;
import com.lemasne.hms.model.entities.Chambre;
import com.lemasne.hms.model.dao.ChambreDao;
import java.util.List;


public class Main {
    public static void main (String[] argv) {
//        MainWindow mWin = new MainWindow();
//        mWin.setVisible(true);
        
        IDao cdao = new ChambreDao();
        List<Chambre> chambres = ChambreDao.getListWith(cdao.findAll());
        
        for (Chambre chambre : chambres) {
            System.out.println(chambre.getCode_service());
        }
        
        IController controller;
    }
}
