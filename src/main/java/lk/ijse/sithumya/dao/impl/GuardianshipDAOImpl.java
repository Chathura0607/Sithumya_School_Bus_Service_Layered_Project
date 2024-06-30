package lk.ijse.sithumya.dao.impl;

import lk.ijse.sithumya.dao.custom.GuardianshipDAO;
import lk.ijse.sithumya.entity.Guardianship;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GuardianshipDAOImpl implements GuardianshipDAO {
    @Override
    public ArrayList<Guardianship> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(Guardianship entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Guardianship search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Guardianship entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        return "";
    }

    @Override
    public List<String> getAllIds() throws SQLException, ClassNotFoundException {
        return List.of();
    }
}
