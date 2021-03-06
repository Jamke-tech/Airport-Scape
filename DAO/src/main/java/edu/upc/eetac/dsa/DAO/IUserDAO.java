package edu.upc.eetac.dsa.DAO;

import edu.upc.eetac.dsa.model.BuyedObject;
import edu.upc.eetac.dsa.model.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface IUserDAO {

    public int registerUser(User user) throws SQLException;
    public int updateUser(User user)throws SQLException;
    public void deleteUser(User user) throws SQLException;
    public int loginUser(User userToLog) throws SQLException;
    public User getUserByNickname( String nameUser) throws SQLException;
    public User getUserById( int idUser) throws SQLException;
    public User getUserByEmail( String email) throws SQLException;
    public int getIDByNickName (String nickName) throws SQLException;
    List<User> getListUsers() throws SQLException;
}
