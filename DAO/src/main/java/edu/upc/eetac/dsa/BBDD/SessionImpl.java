package edu.upc.eetac.dsa.BBDD;

import edu.upc.eetac.dsa.model.BuyedObject;
import edu.upc.eetac.dsa.model.Objects;
import edu.upc.eetac.dsa.util.ObjectHelper;
import edu.upc.eetac.dsa.util.QueryHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class SessionImpl implements Session {
    private final Connection conn;

    public SessionImpl(Connection conn) {
        this.conn = conn;
    }

    public void save(Object entity)  throws SQLException{

        String insertQuery = QueryHelper.createQueryINSERT(entity);

        PreparedStatement pstm = null;
        try {
            pstm = conn.prepareStatement(insertQuery);
            String field;
            int i = 1;
            while (i < ObjectHelper.getFields(entity).length) {
                field = ObjectHelper.getFields(entity)[i];
                pstm.setObject(i++, ObjectHelper.getter(entity, field));
            }

            pstm.executeQuery();
        }
        catch (IllegalAccessException e)
        {
            e.printStackTrace();
        }




    }

    public void close() throws SQLException {
        //FALTA POR CORREGIR
        conn.close();
    }

    public Object get(Object theObject, int ID) {
        //FALTA POR CORREGIR
        String selectQuery = QueryHelper.createQuerySELECT(theObject);
        PreparedStatement pstm = null;
        try {
            pstm = conn.prepareStatement(selectQuery);
            pstm.setObject(1, ID);
            pstm.executeQuery();
            ResultSet rs = pstm.getResultSet();
            if (rs.next()){

                for (int i=1;i<=rs.getMetaData().getColumnCount();i++)
                    ObjectHelper.setter(theObject,rs.getMetaData().getColumnName(i),rs.getObject(i));
            }
            return theObject;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Object getByName(Object theObject, String name) throws SQLException {
        String selectQuery = QueryHelper.createQuerySELECTName(theObject);
        PreparedStatement pstm = null;
        try {
            pstm = conn.prepareStatement(selectQuery);
            pstm.setObject(1, name);
            pstm.executeQuery();
            ResultSet rs = pstm.getResultSet();
            if (rs.next()){
                for (int i=1;i<=rs.getMetaData().getColumnCount();i++)
                    ObjectHelper.setter(theObject,rs.getMetaData().getColumnName(i),rs.getObject(i));
            }
            return theObject;

        }  catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }
    public Object getByID(Object theObject, int id) throws SQLException {
        String selectQuery = QueryHelper.createQuerySELECT(theObject);
        PreparedStatement pstm = null;
        try {
            pstm = conn.prepareStatement(selectQuery);
            pstm.setObject(1, id);
            pstm.executeQuery();
            ResultSet rs = pstm.getResultSet();
            if (rs.next()){
                for (int i=1;i<=rs.getMetaData().getColumnCount();i++)
                    ObjectHelper.setter(theObject,rs.getMetaData().getColumnName(i),rs.getObject(i));
            }
            return theObject;

        }  catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void update(Object object) throws SQLException {
        String updateQuery = QueryHelper.createQueryUPDATE(object);

        PreparedStatement pstm = null;

        try {
            pstm = conn.prepareStatement(updateQuery);
            String field;
            int i =1;
            while (i<ObjectHelper.getFields(object).length){
                field = ObjectHelper.getFields(object)[i];
                pstm.setObject(i++, ObjectHelper.getter(object, field));
            }
            pstm.setObject(i++, ObjectHelper.getter(object, ObjectHelper.getFields(object)[0]));
            pstm.executeQuery();

        }  catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public void updateComplex(Object object, String propertyCondition, Object valueCondition) throws SQLException{
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

    public void delete(Object object) throws SQLException {
        //FALTA POR CORREGIR
        String deleteQuery = QueryHelper.createQueryDELETE(object);
        PreparedStatement pstm = null;

        try {
            int ID = ObjectHelper.getId(object);
            pstm = conn.prepareStatement(deleteQuery);
            pstm.setObject(1, "ID");
            pstm.setObject(2, ID);
            pstm.executeQuery();

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    public List<Object> findAll(Object theObject) {
        String selectQuery = QueryHelper.createQuerySELECTAll(theObject);
        PreparedStatement pstm = null;
        List<Object> ListObject = new ArrayList<Object>();
        try {
            pstm = conn.prepareStatement(selectQuery);
            pstm.setObject(1, 0);
            pstm.executeQuery();
            ResultSet rs = pstm.getResultSet();
            while (rs.next()) {
                Object o = new Object();
                for (int i=1;i<=rs.getMetaData().getColumnCount();i++)
                    ObjectHelper.setter(o,rs.getMetaData().getColumnName(i),rs.getObject(i));
                ListObject.add(o);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return ListObject;
    }

    @Override
    public List<Object> findAllByName(Object theObject, String userName) {
        String selectQuery = QueryHelper.createQuerySELECTAllBynickName(theObject);
        PreparedStatement pstm = null;
        List<Object> ListObject = new ArrayList<Object>();
        try {
            pstm = conn.prepareStatement(selectQuery);
            pstm.setObject(1, userName);
            pstm.executeQuery();
            ResultSet rs = pstm.getResultSet();
            Class nuestraClasse = theObject.getClass();
            while (rs.next()) {
                BuyedObject object = new BuyedObject();// parche pq si entra algo que no es object mal !!
                for (int i=1;i<=rs.getMetaData().getColumnCount();i++)
                    ObjectHelper.setter(object,rs.getMetaData().getColumnName(i),rs.getObject(i));
                ListObject.add(object);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return ListObject;
    }

    public List<Object> query(String query, Object theObject, HashMap params) {


        return null;
    }
}
