package edu.upc.eetac.dsa;

import edu.upc.eetac.dsa.model.BuyedObject;
import edu.upc.eetac.dsa.model.User;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public interface IUserDAO {

    public int registerUser(String userName, String password, String name, String surname, String mail) throws SQLException;
    public User getUserById(int idUser) throws SQLException;
    public void updateUser(User user, String name, String surname, int money, HashMap<Integer, BuyedObject> buyedObjects, String mail)throws SQLException;
    public void deleteUser(User user) throws SQLException;
}
