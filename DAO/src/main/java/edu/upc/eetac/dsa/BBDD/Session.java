package edu.upc.eetac.dsa.BBDD;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public interface Session<E> {
    void save(Object entity);
    void close() throws SQLException;
    Object get(Object theClass, int ID);
    Object getByName(Object theClass, String name);
    public void update(Object object) throws IllegalAccessException;
    public void updateComplex(Object object, String propertyCondition, Object valueCondition);
    public void delete(Object object) throws IllegalAccessException;
    List<Object> findAll(Class theClass);
    List<Object> findAll(Class theClass, HashMap params);
    List<Object> query(String query, Class theClass, HashMap params);
}
