package edu.upc.eetac.dsa.BBDD;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public interface Session<E> {
    void save(Object entity) throws SQLException;
    void close() throws SQLException;
    Object get(Object theClass, int ID) throws SQLException;
    Object getByName(Object theClass, String name)  throws SQLException;
    Object getByNameGame(Object theClass, String name)  throws SQLException;
    public void update(Object object) throws SQLException;
    public void updateComplex(Object object, String propertyCondition, Object valueCondition)throws SQLException;
    public void delete(Object object) throws SQLException;
    List<Object> findAll(Object object);
    public List<Object> findAllByName(Object theObject, String userName);
    List<Object> query(String query, Object object, HashMap params);
    public Object getByID(Object theClass, int id) throws SQLException;
}
