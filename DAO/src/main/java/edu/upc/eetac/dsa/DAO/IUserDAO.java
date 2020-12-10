package edu.upc.eetac.dsa.DAO;

import edu.upc.eetac.dsa.model.BuyedObject;
import edu.upc.eetac.dsa.model.User;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public interface IUserDAO {

    public int registerUser(User user) throws SQLException;
    public User getUserById(int idUser) throws SQLException;
    public void updateUser(User user, String name, String surname, int money, HashMap<Integer, BuyedObject> buyedObjects, String mail)throws SQLException;
    public void deleteUser(User user) throws SQLException;
    public int loginUser(User userToLog) throws SQLException;
}
