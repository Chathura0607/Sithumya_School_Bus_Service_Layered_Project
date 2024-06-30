package lk.ijse.sithumya.bo.impl;

import lk.ijse.sithumya.bo.custom.MaintenanceBO;
import lk.ijse.sithumya.dao.DAOFactory;
import lk.ijse.sithumya.dao.custom.MaintenanceDAO;
import lk.ijse.sithumya.dto.BusDTO;
import lk.ijse.sithumya.dto.MaintenanceDTO;
import lk.ijse.sithumya.entity.Bus;
import lk.ijse.sithumya.entity.Maintenance;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MaintenanceBOImpl implements MaintenanceBO {
    MaintenanceDAO maintenanceDAO = (MaintenanceDAO) DAOFactory.getDAOFactory().getDAOType(DAOFactory.DAOTypes.MAINTENANCE);

    @Override
    public List<String> getAllMaintenanceIds() throws SQLException, ClassNotFoundException {
        return maintenanceDAO.getAllIds();
    }

    @Override
    public ArrayList<MaintenanceDTO> getAllMaintenance() throws SQLException, ClassNotFoundException {
        ArrayList<MaintenanceDTO> allMaintenance = new ArrayList<>();
        ArrayList<Maintenance> all = maintenanceDAO.getAll();

        for (Maintenance maintenance : all) {
            allMaintenance.add(new MaintenanceDTO(
                    maintenance.getMaintenanceId(),
                    maintenance.getBusId(),
                    maintenance.getDescription(),
                    maintenance.getCost(),
                    maintenance.getDate()
            ));
        }
        return allMaintenance;
    }
}
