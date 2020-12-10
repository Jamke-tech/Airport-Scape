package edu.upc.eetac.dsa;

import edu.upc.eetac.dsa.model.BuyedObject;
import edu.upc.eetac.dsa.model.User;

import java.sql.SQLException;
import java.util.HashMap;

public interface IObjectDAO {
    public BuyedObject getBuyedObjectById(BuyedObject buyedObject) throws SQLException;
    public void deleteBuyedObject(BuyedObject buyedObject) throws SQLException;
    //public List<BuyedObject> getListBuyedObjects();
}
