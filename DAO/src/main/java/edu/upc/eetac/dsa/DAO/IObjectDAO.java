package edu.upc.eetac.dsa.DAO;

import edu.upc.eetac.dsa.model.BuyedObject;
import edu.upc.eetac.dsa.model.Objects;
import edu.upc.eetac.dsa.model.User;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public interface IObjectDAO {
    public int buyObjectForUser (User user,int idObject) throws SQLException;
    public void deleteBuyedObject(BuyedObject buyedObject) throws SQLException;
    public List<Objects> getListBuyedObjects (String Username) throws SQLException;
}
