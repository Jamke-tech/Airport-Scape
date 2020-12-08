package edu.upc.eetac.dsa;

import edu.upc.eetac.dsa.model.BuyedObject;
import edu.upc.eetac.dsa.model.User;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public class UserDAOImpl extends GestorGame implements IUserDAO {


    public int registerUser(String userName, String password, String name, String surname, String mail) throws SQLException {
        Session session = null;
        int idUser = 0;
        try {
            session = FactorySession.openSession();
            int money = 1000;
            HashMap<Integer, BuyedObject> buyedObjects = null;
            User user = new User(idUser, userName, password, name, surname, money, buyedObjects, mail);
            session.save(user);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        return idUser;
    }


    public User getUserById(int idUser) throws SQLException {
        Session session = null;
        User user = null;
        try {
            session = FactorySession.openSession();
            user = (User) session.get(User.class, idUser);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            session.close();
        }

        return user;
    }


    public void updateUser(User user, String name, String surname, int money, HashMap<Integer,BuyedObject> buyedObjects, String mail) throws SQLException {
        //User user = this.getUser(idUser);
        user.setName(name);
        user.setSurname(surname);
        user.setMoney(money);
        user.setBuyedObjects(buyedObjects);
        user.setMail(mail);

        Session session = null;
        try {
            session = FactorySession.openSession();
            session.update(user);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            session.close();
        }
    }


    public void deleteUser(User user) throws SQLException {
        //User user = this.getUser(idUser);
        Session session = null;
        try {
            session = FactorySession.openSession();
            session.delete(user);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            session.close();
        }

    }



}
