package edu.upc.eetac.dsa.DAO;

import edu.upc.eetac.dsa.BBDD.FactorySession;
import edu.upc.eetac.dsa.BBDD.Session;
import edu.upc.eetac.dsa.model.User;

import java.sql.SQLException;

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
       // int retorno = -400;
        try {
            session = FactorySession.openSession();
/*            User existingUser = (User) session.getByName(user,user.getNameOfUser());
            if(existingUser!=null)
            {
                retorno = -403;//nickname used
            }*/
          //  else{//iniciamos un nuevo usuario i lo ponemos en la base de datos
                int money = 1000;
                user.setMoney(money);
                user.setId(idUser);
                session.save(user);
                //retorno = idUser;
           // }
        }
        catch (Exception e) {
            e.printStackTrace();
           // retorno = -400;
        }
        finally {
            if(session!=null) {
                session.close();
            }

        }
        return idUser;

    }
/*    public int registerUser(User user) throws SQLException {
        Session session = null;
        int idUser = 0;
        //int retorno = -400;
        try {
            session = FactorySession.openSession();
*//*            User existingUser = (User) session.getByName(user,user.getNameOfUser());
            if(existingUser!=null)
            {
                retorno = -403;//nickname used
            }*//*
           // else{//iniciamos un nuevo usuario i lo ponemos en la base de datos
                int money = 1000;
                user.setMoney(money);
                user.setId(idUser);
                session.save(user);
               // retorno = idUser;
           // }
        }
        catch (Exception e) {
            e.printStackTrace();
           // retorno = -400;
        }
        finally {
            if(session!=null) {
                session.close();
            }

        }
        return idUser;

    }*/

    public int loginUser(User userToLog) throws SQLException {
        Session session = null;
        int error=3;
        try {
            session = FactorySession.openSession();
            User existingUser = (User) session.getByName(userToLog,userToLog.getUserName());
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

    public User getUserByNickname( String nameUser) throws SQLException{
        Session session = null;
        User user = null;
        try {
            session = FactorySession.openSession();
            user = (User) session.getByName(User.class, nameUser);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            session.close();
        }

        return user;
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



}
