package lk.ijse.sithumya.dao;

import lk.ijse.sithumya.dao.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;

    public enum DAOTypes {
        USER, DASHBOARD, BUS, GUARDIAN, DRIVER, STUDENT, STATION, STOCK, PLAN, PAYMENT, MAINTENANCE, GUARDIANSHIP, FEE, SALARY, RECORD
    }

    private DAOFactory() {
    }

    public static DAOFactory getDAOFactory() {
        return (daoFactory==null) ? daoFactory = new DAOFactory():daoFactory;
    }

    public SuperDAO getDAOType(DAOTypes types) {
        switch (types) {
            case USER:
                return new UserDAOImpl();

            case DASHBOARD:
                return new DashboardDAOImpl();

            case BUS:
                return new BusDAOImpl();

            case GUARDIAN:
                return new GuardianDAOImpl();

            case DRIVER:
                return new DriverDAOImpl();

            case STUDENT:
                return new StudentDAOImpl();

            case STATION:
                return new FillingStationDAOImpl();

            case STOCK:
                return new StockDAOImpl();

            case PLAN:
                return new PaymentPlanDAOImpl();

            case PAYMENT:
                return new PaymentDAOImpl();

            case MAINTENANCE:
                return new MaintenanceDAOImpl();

            case GUARDIANSHIP:
                return new GuardianshipDAOImpl();

            case FEE:
                return new FeeDAOImpl();

            case SALARY:
                return new SalaryDAOImpl();

            case RECORD:
                return new FuelingRecordDAOImpl();

            default:
                return null;
        }
    }
}
