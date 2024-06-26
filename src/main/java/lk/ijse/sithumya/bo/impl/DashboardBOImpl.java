package lk.ijse.sithumya.bo.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.sithumya.bo.custom.DashboardBO;
import lk.ijse.sithumya.dao.DAOFactory;
import lk.ijse.sithumya.dao.custom.DashboardDAO;
import lk.ijse.sithumya.dto.TodayPaymentsDTO;
import lk.ijse.sithumya.dto.tm.TodayPaymentsTm;

import java.sql.SQLException;
import java.util.List;

public class DashboardBOImpl implements DashboardBO {
    private DashboardDAO dashboardDAO = (DashboardDAO) DAOFactory.getDAOFactory().getDAOType(DAOFactory.DAOTypes.DASHBOARD);

    @Override
    public int getTotalStudentsCount() throws SQLException, ClassNotFoundException {
        return dashboardDAO.getTotalStudentsCount();
    }

    @Override
    public int getTotalBusCount() throws SQLException, ClassNotFoundException {
        return dashboardDAO.getTotalBusCount();
    }

    @Override
    public int getTotalDriversCount() throws SQLException, ClassNotFoundException {
        return dashboardDAO.getTotalDriversCount();
    }

    @Override
    public String getUserName() throws SQLException, ClassNotFoundException {
        return dashboardDAO.getUserName();
    }

    @Override
    public List<TodayPaymentsDTO> loadTodayPayments() throws SQLException {
        return dashboardDAO.loadTodayPayments();
    }

    /*@Override
    public ObservableList<TodayPaymentsTm> loadTodayPayments() throws SQLException {
        List<TodayPaymentsDTO> paymentList = dashboardDAO.loadTodayPayments();
        ObservableList<TodayPaymentsTm> obList = FXCollections.observableArrayList();

        for (TodayPaymentsDTO todayPayment : paymentList) {
            TodayPaymentsTm todayPaymentTm = new TodayPaymentsTm(
                    todayPayment.getPaymentId(),
                    todayPayment.getStudentName(),
                    todayPayment.getPaymentPlanName(),
                    todayPayment.getPaymentAmount()
            );
            obList.add(todayPaymentTm);
        }
        return obList;
    }*/
}
