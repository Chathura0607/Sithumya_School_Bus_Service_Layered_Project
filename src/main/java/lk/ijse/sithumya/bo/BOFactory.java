package lk.ijse.sithumya.bo;

import lk.ijse.sithumya.bo.impl.UserBOImpl;

public class BOFactory {
    private static BOFactory boFactory;

    public enum BOTypes {
        USER
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
            default:
                return null;
        }
    }
}
