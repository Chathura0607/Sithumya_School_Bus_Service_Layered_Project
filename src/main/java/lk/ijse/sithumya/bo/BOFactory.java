package lk.ijse.sithumya.bo;

import lk.ijse.sithumya.bo.impl.*;

public class BOFactory {
    private static BOFactory boFactory;

    public enum BOTypes {
        USER, DASHBOARD, BUS, GUARDIAN, DRIVER, STUDENT, STATION, STOCK, PLAN, PAYMENT, MAINTENANCE, GUARDIANSHIP, FEE, SALARY, RECORD
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

            case DRIVER:
                return new DriverBOImpl();

            case STUDENT:
                return new StudentBOImpl();

            case STATION:
                return new FillingStationBOImpl();

            case STOCK:
                return new StockBOImpl();

            case PLAN:
                return new PaymentPlanBOImpl();

            case PAYMENT:
                return new PaymentBOImpl();

            case MAINTENANCE:
                return new MaintenanceBOImpl();

            case GUARDIANSHIP:
                return new GuardianshipBOImpl();

            case FEE:
                return new FeeBOImpl();

            case SALARY:
                return new SalaryBOImpl();

            case RECORD:
                return new FuelingRecordBOImpl();

            default:
                return null;
        }
    }
}
