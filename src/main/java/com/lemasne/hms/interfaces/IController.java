package com.lemasne.hms.interfaces;

import com.lemasne.hms.controller.dto.ControllerDTO;
import java.awt.event.ActionEvent;

public interface IController {
    public void actionPerformed(ActionEvent event);
    public void setControllerDto(ControllerDTO controllerDTO);

    public void loadTable();
}
