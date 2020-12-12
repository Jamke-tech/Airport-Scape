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

/*    @After
    public void tearDown(){
        this.UserDAOImpl.getInstance().clear();
    }*/
/*    @Test
    public void testRegisterUser() throws SQLException {
        int idUser = 0;
        String userName = "mmv888";
        String password = "mmv";
        String name ="Miguel";
        String surname = "Marin";
        int money = 1000;
        String mail = "miguel_8171099@gmail.com";
        User user = new User(idUser, userName, password, name, surname, money,  mail);
        idUser = Impl.registerUser(user);
        Assert.assertEquals(5, idUser);
    }

    @Test
    public void testUpdateUser() throws SQLException {
        int error;
        int idUser = 1;
        String userName = "mmv";
        String password = "mmv";
        String name ="Miguel";
        String surname = "Marin Vicente";
        int money = 1000;
        String mail = "miguel_8171099@hotmail.com";
        User user = new User(idUser, userName, password, name, surname, money,  mail);
        error = Impl.updateUser(user);
        Assert.assertEquals(0, error);
    }*/

/*    @Test
    public void testDeleteUser() {
        Impl.deleteUser(user);
    }*/
    @Test
    public void getUserById() throws SQLException {
        int id = 0;
        User user = Impl.getUserById(id);
        Assert.assertEquals("mmv", user.getUserName());
    }

}
