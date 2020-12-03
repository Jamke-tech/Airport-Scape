package edu.upc.eetac.dsa;

import edu.upc.eetac.dsa.model.BuyedObject;
import edu.upc.eetac.dsa.model.User;

import java.util.HashMap;
import java.util.List;

public class UserDAOImpl implements IUserDAO {


    public int registerUser(String userName, String password, String name, String surname, String mail) {
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
            // LOG
        }
        finally {
            session.close();
        }

        return idUser;
    }


    public User getEmployee(int employeeID) {
        Session session = null;
        User user = null;
        try {
            session = FactorySession.openSession();
            user = (User) session.get(User.class, employeeID);
        }
        catch (Exception e) {
            // LOG
        }
        finally {
            session.close();
        }

        return user;
    }


    public void updateEmployee(int employeeID, String name, String surname, double salary) {
        User user = this.getEmployee(employeeID);
        user.setName(name);
        user.setSurname(surname);
        user.setSalary(salary);

        Session session = null;
        try {
            session = FactorySession.openSession();
            session.update(User.class);
        }
        catch (Exception e) {
            // LOG
        }
        finally {
            session.close();
        }
    }


    public void deleteEmployee(int employeeID) {
        User user = this.getEmployee(employeeID);
        Session session = null;
        try {
            session = FactorySession.openSession();
            session.delete(User.class);
        }
        catch (Exception e) {
            // LOG
        }
        finally {
            session.close();
        }

    }


    public List<User> getEmployees() {
        Session session = null;
        List<User> userList =null;
        try {
            session = FactorySession.openSession();
            userList = session.findAll(User.class);
        }
        catch (Exception e) {
            // LOG
        }
        finally {
            session.close();
        }
        return userList;
    }


    public List<User> getEmployeeByDept(int deptID) {

        Session session = null;
        List<User> userList =null;
        try {
            session = FactorySession.openSession();

            HashMap<String, Integer> params = new HashMap<String, Integer>();
            params.put("deptID", deptID);

            userList = session.findAll(User.class, params);
        }
        catch (Exception e) {
            // LOG
        }
        finally {
            session.close();
        }
        return userList;
    }


}
