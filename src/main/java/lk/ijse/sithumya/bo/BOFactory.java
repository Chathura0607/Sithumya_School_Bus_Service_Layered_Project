package lk.ijse.sithumya.bo;

import lk.ijse.sithumya.bo.impl.BusBOImpl;
import lk.ijse.sithumya.bo.impl.DashboardBOImpl;
import lk.ijse.sithumya.bo.impl.GuardianBOImpl;
import lk.ijse.sithumya.bo.impl.UserBOImpl;

public class BOFactory {
    private static BOFactory boFactory;

    public enum BOTypes {
        USER, DASHBOARD, BUS, GUARDIAN
    }

    private BOFactory() {
    }

    public static BOFactory getBOFactory() {
        return (boFactory==null) ? boFactory = new BOFactory():boFactory;
    }

    public SuperBO getBOType(BOTypes types) {
        switch (types) {
            case USER:
                return new UserBOImpl();

            case DASHBOARD:
                return new DashboardBOImpl();

            case BUS:
                return new BusBOImpl();

            case GUARDIAN:
                return new GuardianBOImpl();

            default:
                return null;
        }
    }
}
