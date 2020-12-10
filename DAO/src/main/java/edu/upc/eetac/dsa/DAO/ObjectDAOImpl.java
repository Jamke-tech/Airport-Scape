package edu.upc.eetac.dsa.DAO;

import edu.upc.eetac.dsa.BBDD.FactorySession;
import edu.upc.eetac.dsa.BBDD.Session;
import edu.upc.eetac.dsa.model.BuyedObject;

import java.sql.SQLException;

public class ObjectDAOImpl {
    public BuyedObject getBuyedObjectById(int idBuyedObject) throws SQLException {
        Session session = null;
        BuyedObject buyedObject = null;
        try {
            session = FactorySession.openSession();
            buyedObject = (BuyedObject) session.get(BuyedObject.class, idBuyedObject);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            session.close();
        }

        return buyedObject;
    }


    public void deleteBuyedObject(BuyedObject buyedObject) throws SQLException {
        Session session = null;
        try {
            session = FactorySession.openSession();
            session.delete(buyedObject);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            session.close();
        }
    }

/*    public List<BuyedObject> getListBuyedObjects() {

    }*/
}
