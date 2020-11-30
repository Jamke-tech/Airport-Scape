package edu.upc.eetac.dsa;

import edu.upc.eetac.dsa.model.Employee;

import java.util.HashMap;
import java.util.List;

public class EmployeeDAOImpl implements IEmployeeDAO {


    public int addEmployee(String name, String surname, double salary) {
        Session session = null;
        int employeeID = 0;
        try {
            session = FactorySession.openSession();
            Employee employee = new Employee(name, surname, salary);
            session.save(employee);
        }
        catch (Exception e) {
            // LOG
        }
        finally {
            session.close();
        }

        return employeeID;
    }


    public Employee getEmployee(int employeeID) {
        Session session = null;
        Employee employee = null;
        try {
            session = FactorySession.openSession();
            employee = (Employee)session.get(Employee.class, employeeID);
        }
        catch (Exception e) {
            // LOG
        }
        finally {
            session.close();
        }

        return employee;
    }


    public void updateEmployee(int employeeID, String name, String surname, double salary) {
        Employee employee = this.getEmployee(employeeID);
        employee.setName(name);
        employee.setSurname(surname);
        employee.setSalary(salary);

        Session session = null;
        try {
            session = FactorySession.openSession();
            session.update(Employee.class);
        }
        catch (Exception e) {
            // LOG
        }
        finally {
            session.close();
        }
    }


    public void deleteEmployee(int employeeID) {
        Employee employee = this.getEmployee(employeeID);
        Session session = null;
        try {
            session = FactorySession.openSession();
            session.delete(Employee.class);
        }
        catch (Exception e) {
            // LOG
        }
        finally {
            session.close();
        }

    }


    public List<Employee> getEmployees() {
        Session session = null;
        List<Employee> employeeList=null;
        try {
            session = FactorySession.openSession();
            employeeList = session.findAll(Employee.class);
        }
        catch (Exception e) {
            // LOG
        }
        finally {
            session.close();
        }
        return employeeList;
    }


    public List<Employee> getEmployeeByDept(int deptID) {

        Session session = null;
        List<Employee> employeeList=null;
        try {
            session = FactorySession.openSession();

            HashMap<String, Integer> params = new HashMap<String, Integer>();
            params.put("deptID", deptID);

            employeeList = session.findAll(Employee.class, params);
        }
        catch (Exception e) {
            // LOG
        }
        finally {
            session.close();
        }
        return employeeList;
    }


}
