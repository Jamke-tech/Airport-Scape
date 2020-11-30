package edu.upc.eetac.dsa;

import edu.upc.eetac.dsa.model.Employee;

import java.util.List;

public interface IEmployeeDAO {

    public int addEmployee(String name, String surname, double salary);
    public Employee getEmployee(int employeeID);
    public void updateEmployee(int employeeID, String name, String surname, double salary);
    public void deleteEmployee(int employeeID);
    public List<Employee> getEmployees();
    public List <Employee> getEmployeeByDept(int deptId);
}
