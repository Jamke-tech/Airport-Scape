package edu.upc.eetac.dsa;

import edu.upc.eetac.dsa.model.BuyedObject;
import edu.upc.eetac.dsa.model.Game;
import edu.upc.eetac.dsa.model.User;
import edu.upc.eetac.dsa.util.QueryHelper;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.HashMap;

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
    @Test
    public void testRegisterUser() throws SQLException {
        int idUser = 0;
        String userName = "mmv";
        String password = "mmv";
        String name ="Miguel";
        String surname = "Marin";
        int money = 1000;
        HashMap<Integer, BuyedObject > buyedObjects = null;
        String mail = "miguel_8171099@gmail.com";
        User user = new User(idUser, userName, password, name, surname, money, buyedObjects,  mail);
        idUser = Impl.registerUser(user);
        Assert.assertEquals(1, idUser);
    }

/*    @Test
    public void testGetUserById() {
        Assert.assertEquals());
    }

    @Test
    public void testUpdateUser() {
        Assert.assertEquals();
    }

    @Test
    public void testDeleteUser() {
        Assert.assertEquals();
    }*/

}
