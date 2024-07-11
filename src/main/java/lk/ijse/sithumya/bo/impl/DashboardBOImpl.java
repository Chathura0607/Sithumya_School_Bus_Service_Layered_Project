package lk.ijse.sithumya.bo.impl;

import javafx.scene.chart.XYChart;
import lk.ijse.sithumya.bo.custom.DashboardBO;
import lk.ijse.sithumya.dao.DAOFactory;
import lk.ijse.sithumya.dao.custom.QueryDAO;
import lk.ijse.sithumya.dao.custom.DashboardDAO;
import lk.ijse.sithumya.dto.TodayPaymentsDTO;
import lk.ijse.sithumya.entity.TodayPayments;

import java.sql.SQLException;
import java.util.ArrayList;

public class DashboardBOImpl implements DashboardBO {
    private DashboardDAO dashboardDAO = (DashboardDAO) DAOFactory.getDAOFactory().getDAOType(DAOFactory.DAOTypes.DASHBOARD);
    private QueryDAO queryDAO = (QueryDAO) DAOFactory.getDAOFactory().getDAOType(DAOFactory.DAOTypes.QUERY);

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
    public ArrayList<TodayPaymentsDTO> loadTodayPayments() throws SQLException {
        ArrayList<TodayPaymentsDTO> allTodayPayments = new ArrayList<>();
        ArrayList<TodayPayments> all = queryDAO.loadTodayPayments();

        for (TodayPayments todayPayments : all) {
            allTodayPayments.add(new TodayPaymentsDTO(
                    todayPayments.getPaymentId(),
                    todayPayments.getStudentName(),
                    todayPayments.getPaymentPlanName(),
                    todayPayments.getPaymentAmount()
            ));
        }
        return allTodayPayments;
    }

    @Override
    public XYChart.Series<String, Number> getChartData() throws SQLException {
        return dashboardDAO.getChartData();
    }
}
