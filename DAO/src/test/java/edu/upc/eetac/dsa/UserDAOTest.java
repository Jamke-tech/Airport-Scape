package edu.upc.eetac.dsa;

import edu.upc.eetac.dsa.DAO.IUserDAO;
import edu.upc.eetac.dsa.DAO.UserDAOImpl;
import edu.upc.eetac.dsa.model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

public class UserDAOTest {

    IUserDAO Impl;

    @Before
    public void setUp() throws Exception{
        this.Impl = UserDAOImpl.getInstance();
    }


   @Test
    public void testRegisterUser() throws SQLException {
        int idUser = 0;
        String userName = "jamke";
        String password = "jamke";
        String name ="Jaume";
        String surname = "Tabernero";
        int money = 1000;
        String mail = "jaume_taber10@hotmail.com";
        User user = new User(idUser, userName, password, name, surname, money,  mail);
        int error = Impl.registerUser(user);
        Assert.assertEquals(-1, error);
    }

    @Test
    public void testUpdateUser() throws SQLException {
        int error;
        int idUser = 1;
        String userName = "mmv888";
        String password = "mmv888";
        String name = "Miguel";
        String surname = "Marin Vicente";
        int money = 1000;
        String mail = "miguel_8171099@hotmail.com";
        User user = new User(idUser, userName, password, name, surname, money, mail);
        error = Impl.updateUser(user);
        Assert.assertEquals(0, error);
    }
    @Test
    public void testloginUser() throws SQLException {
        int error;
        String userName = "jamke";
        String password = "jamke";
        User user = new User(0, userName, password, null, null, 0, null);
        error = Impl.loginUser(user);
        Assert.assertEquals(0, error);//Password okey
    }




/*    @Test
    public void testDeleteUser() {
        Impl.deleteUser(user);
    }*/
   @Test
    public void getUserByNickname() throws SQLException {
        String nickname = "mmv888";
        User user = Impl.getUserByNickname(nickname);
        Assert.assertEquals("mmv888", user.getUserName());
    }

}
