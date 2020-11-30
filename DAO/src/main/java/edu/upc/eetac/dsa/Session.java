package edu.upc.eetac.dsa;

import java.util.HashMap;
import java.util.List;

public interface Session<E> {
    void save(Object entity);
    void close();
    Object get(Class theClass, int ID);
    void update(Object object);
    void delete(Object object);
    List<Object> findAll(Class theClass);
    List<Object> findAll(Class theClass, HashMap params);
    List<Object> query(String query, Class theClass, HashMap params);
}
