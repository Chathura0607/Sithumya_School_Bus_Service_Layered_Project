package lk.ijse.sithumya.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface CurdDAO <T> extends SuperDAO{
    ArrayList<T> getAll() throws SQLException, ClassNotFoundException;

    boolean save(T entity) throws SQLException, ClassNotFoundException;

    T search(String id) throws SQLException, ClassNotFoundException;

    boolean delete(String id) throws SQLException, ClassNotFoundException;

    boolean update(T entity) throws SQLException, ClassNotFoundException;

    String generateNewId() throws SQLException, ClassNotFoundException;

    List<String> getAllIds() throws SQLException, ClassNotFoundException;
}