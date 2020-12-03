package edu.upc.eetac.dsa;

import edu.upc.eetac.dsa.model.BuyedObject;
import edu.upc.eetac.dsa.model.User;

import java.util.HashMap;
import java.util.List;

public interface IUserDAO {

    public int registerUser(String userName, String password, String name, String surname, String mail);
    public User getUser(String userName, String password);
    public void updateUser(int idUser, String userName, String password, String name, String surname, String mail);
    public void deleteUser(String userName, String password);
}
