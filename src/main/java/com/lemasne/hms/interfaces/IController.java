package com.lemasne.hms.interfaces;

import com.lemasne.hms.controller.dto.ControllerDTO;
import java.awt.event.ActionEvent;

public interface IController {
    public IModel getModel();
    public void actionPerformed(ActionEvent event);
    public IController setControllerDto(ControllerDTO controllerDTO);
    public void executeDTORequest();
    public void loadTable();
    public String getName();
}
