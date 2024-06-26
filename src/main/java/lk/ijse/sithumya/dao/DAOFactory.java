package lk.ijse.sithumya.dao;

import lk.ijse.sithumya.dao.impl.BusDAOImpl;
import lk.ijse.sithumya.dao.impl.DashboardDAOImpl;
import lk.ijse.sithumya.dao.impl.GuardianDAOImpl;
import lk.ijse.sithumya.dao.impl.UserDAOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;

    public enum DAOTypes {
        USER, DASHBOARD, BUS, GUARDIAN
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

            default:
                return null;
        }
    }
}
