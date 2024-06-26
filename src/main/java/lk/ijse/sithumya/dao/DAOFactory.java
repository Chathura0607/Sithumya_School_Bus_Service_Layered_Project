package lk.ijse.sithumya.dao;

import lk.ijse.sithumya.dao.impl.UserDAOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;

    public enum DAOTypes {
        USER
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
            default:
                return null;
        }
    }
}
