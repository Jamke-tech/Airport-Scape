package edu.upc.eetac.dsa.DAO;

import edu.upc.eetac.dsa.BBDD.FactorySession;
import edu.upc.eetac.dsa.BBDD.Session;
import edu.upc.eetac.dsa.model.BuyedObject;
import edu.upc.eetac.dsa.model.User;

import java.sql.SQLException;
import java.util.HashMap;

public class UserDAOImpl implements IUserDAO {

    private static IUserDAO instance;

    private UserDAOImpl() {   }

    public static IUserDAO getInstance() {
        if (instance==null) instance = new UserDAOImpl();
        return instance;
    }



    public int registerUser(User user) throws SQLException {
        Session session = null;
        int idUser = 0;
        try {
            session = FactorySession.openSession();
            int money = 1000;
            user.setMoney(money);
            user.setIdUser(idUser);
            session.save(user);
            return idUser;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            session.close();
            return idUser;
        }

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
    public int loginUser(User userToLog)
    {
        //hem de mirar si aquest usuari existeix i si existeix si la contraseña es la mateixa que tenim a la base de dades




        return 0;
    }


    public void updateUser(User user, String name, String surname, int money, HashMap<Integer,BuyedObject> buyedObjects, String mail) throws SQLException {
        //User user = this.getUser(idUser);
        user.setName(name);
        user.setSurname(surname);
        user.setMoney(money);
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
