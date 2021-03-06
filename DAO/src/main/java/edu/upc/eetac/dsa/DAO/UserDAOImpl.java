package edu.upc.eetac.dsa.DAO;

import edu.upc.eetac.dsa.BBDD.FactorySession;
import edu.upc.eetac.dsa.BBDD.Session;
import edu.upc.eetac.dsa.model.User;

import java.sql.SQLException;
import java.util.*;

public class UserDAOImpl implements IUserDAO {

    private static IUserDAO instance;

    private UserDAOImpl() {   }

    public static IUserDAO getInstance() {
        if (instance==null) instance = new UserDAOImpl();
        return instance;

    }

    public int registerUser(User user) throws SQLException {
        Session session = null;
        int error =-1;
        try {
            session = FactorySession.openSession();
                user.setWins(0);
                user.setMoney(1000);
                session.save(user);
                error=0;

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally {
            if(session!=null) {
                session.close();
            }
            return error;
        }
    }


    public int loginUser(User userToLog) throws SQLException {
        Session session = null;
        int error=3;
        try {
            User existingUser = getUserByNickname(userToLog.getUserName());
            if(existingUser!=null)//user found we must prove that the pass is the same
            {
                if(existingUser.getPassword().equals(userToLog.getPassword()))
                {
                    error = 0;
                }
                else
                {
                    error =1;
                }
            }
            else//user not found
            {
                error=4;
            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if(session!=null) {
                session.close();
            }

        }
        return error;
    }

    public int updateUser(User user) throws SQLException {
        Session session = null;
        int error=1;
        try {
            session = FactorySession.openSession();
            session.update(user);
            error =0;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            session.close();
            return error;
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

    public User getUserByNickname(String nameUser) throws SQLException{
        Session session = null;
        User user = new User();
        try {
            session = FactorySession.openSession();
            user = (User) session.getByName(user, nameUser);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            session.close();
        }

        return user;
    }

    public User getUserById( int idUser) throws SQLException{
        Session session = null;
        User user = new User();
        try {
            session = FactorySession.openSession();
            user = (User) session.getByID(user, idUser);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            session.close();
        }

        return user;
    }

    @Override
    public User getUserByEmail(String email) throws SQLException {
        Session session = null;
        User user = new User();
        try {
            session = FactorySession.openSession();
            user = (User) session.getByEmail(user, email);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            session.close();
        }

        return user;
    }

    public int getIDByNickName (String nickName) throws SQLException{
        Session session = null;
        User user = new User();
        try {
            session = FactorySession.openSession();
            user = (User) session.getByName(user, nickName);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            session.close();
        }

        return user.getId();

    }

    public List<User> getListUsers() throws SQLException{
        Session session = null;
        List<User> lista = null;
        User user = new User();
        try{
            session = FactorySession.openSession();
            lista = (List<User>) session.findAll(user);
        }
        catch (Exception e){

        }
        finally {
            session.close();
        }
        return lista;
    }


}
