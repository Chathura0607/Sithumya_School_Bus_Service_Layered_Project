package lk.ijse.sithumya.bo.impl;

import lk.ijse.sithumya.bo.custom.DriverBO;
import lk.ijse.sithumya.dao.DAOFactory;
import lk.ijse.sithumya.dao.custom.DriverDAO;
import lk.ijse.sithumya.dto.DriverDTO;
import lk.ijse.sithumya.entity.Driver;
import lk.ijse.sithumya.util.SqlUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DriverBOImpl implements DriverBO {
    private DriverDAO driverDAO = (DriverDAO) DAOFactory.getDAOFactory().getDAOType(DAOFactory.DAOTypes.DRIVER);

    @Override
    public List<String> getAllDriverIds() throws SQLException, ClassNotFoundException {
        return driverDAO.getAllIds();
    }

    @Override
    public ArrayList<DriverDTO> getAllDrivers() throws SQLException, ClassNotFoundException {
        ArrayList<DriverDTO> allDrivers = new ArrayList<>();
        ArrayList<Driver> all = driverDAO.getAll();

        for (Driver driver : all) {
            allDrivers.add(new DriverDTO(
                    driver.getDriverId(),
                    driver.getName(),
                    driver.getAddress(),
                    driver.getEmail(),
                    driver.getContactNumber(),
                    driver.getSalary(),
                    driver.getPayment(),
                    driver.getPaymentDue()
            ));
        }
        return allDrivers;
    }

    @Override
    public boolean saveDriver(DriverDTO driverDTO) throws SQLException, ClassNotFoundException {
        return driverDAO.save(new Driver(driverDTO.getDriverId(), driverDTO.getName(), driverDTO.getAddress(), driverDTO.getEmail(), driverDTO.getContactNumber(), driverDTO.getSalary(), driverDTO.getPayment(), driverDTO.getPaymentDue()));
    }

    @Override
    public DriverDTO searchDriver(String driverId) throws SQLException, ClassNotFoundException {
        Driver driver = driverDAO.search(driverId);

        if (driver != null) {
            return new DriverDTO(
                    driver.getDriverId(),
                    driver.getName(),
                    driver.getAddress(),
                    driver.getEmail(),
                    driver.getContactNumber(),
                    driver.getSalary(),
                    driver.getPayment(),
                    driver.getPaymentDue()
            );
        }
        return null;
    }

    @Override
    public boolean updateDriver(DriverDTO driverDTO) throws SQLException, ClassNotFoundException {
        return driverDAO.update(new Driver(driverDTO.getDriverId(), driverDTO.getName(), driverDTO.getAddress(), driverDTO.getEmail(), driverDTO.getContactNumber(), driverDTO.getSalary(), driverDTO.getPayment(), driverDTO.getPaymentDue()));
    }

    @Override
    public boolean deleteDriver(String driverId) throws SQLException {
        return SqlUtil.sql("DELETE d, b FROM Driver d LEFT JOIN Bus b ON d.Driver_ID = b.Driver_ID WHERE d.Driver_ID = ?", driverId);
    }
}
