package edu.upc.eetac.dsa;

import edu.upc.eetac.dsa.util.ObjectHelper;
import edu.upc.eetac.dsa.util.QueryHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;


public class SessionImpl implements Session {
    private final Connection conn;

    public SessionImpl(Connection conn) {
        this.conn = conn;
    }

    public void save(Object entity) {

        String insertQuery = QueryHelper.createQueryINSERT(entity);

        PreparedStatement pstm = null;

        try {
            pstm = conn.prepareStatement(insertQuery);
            pstm.setObject(1, 0);
            int i = 2;

            for (String field: ObjectHelper.getFields(entity)) {
                pstm.setObject(i++, ObjectHelper.getter(entity, field));
            }

            pstm.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    public void close() throws SQLException {
        //FALTA POR CORREGIR
        conn.close();
    }

    public Object get(Class theClass, int ID) {
        //FALTA POR CORREGIR
        String selectQuery = QueryHelper.createQuerySELECT(theClass);
        PreparedStatement pstm = null;
        try {
            pstm = conn.prepareStatement(selectQuery);
            pstm.setObject(1, 0);
            pstm.setObject(2, ID);
            pstm.executeQuery();
            return pstm.getResultSet();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Object object){
        //FALTA POR CORREGIR
        String updateQuery = QueryHelper.createQueryUPDATE(object);
        PreparedStatement pstm = null;
        try {
            pstm = conn.prepareStatement(updateQuery);
            pstm.setObject(1, 0);
            int i = 2;

            for (String field: ObjectHelper.getFields(object)) {
                pstm.setObject(i++, ObjectHelper.getter(object, field));
            }
            pstm.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public void updateComplex(Object object, String propertyCondition, Object valueCondition){
        //FALTA POR CORREGIR
        String updateQuery = QueryHelper.createQueryUPDATECOMPLEX(object);
        PreparedStatement pstm = null;
        try {
            pstm = conn.prepareStatement(updateQuery);
            pstm.setObject(1, 0);
            int i = 2;

            for (String field: ObjectHelper.getFields(object)) {
                pstm.setObject(i++, ObjectHelper.getter(object, field));
            }
            pstm.setObject(i, propertyCondition);
            pstm.setObject(i+1, valueCondition);
            pstm.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    public void delete(Object object, String property, Object value) {
        //FALTA POR CORREGIR
        String deleteQuery = QueryHelper.createQueryDELETE(object);
        PreparedStatement pstm = null;
        try {
            pstm = conn.prepareStatement(deleteQuery);
            pstm.setObject(1, 0);
            pstm.setObject(2, property);
            pstm.setObject(3, value);
            pstm.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<Object> findAll(Class theClass) {
        return null;
    }

    public List<Object> findAll(Class theClass, HashMap params) {
        return null;
    }

    public List<Object> query(String query, Class theClass, HashMap params) {
        return null;
    }
}
