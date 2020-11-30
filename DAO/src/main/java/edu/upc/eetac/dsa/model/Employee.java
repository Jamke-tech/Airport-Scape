package edu.upc.eetac.dsa.model;

public class Employee {

    private String name;
    private String surname;
    private double salary;

    public Employee(String  name, String surname, double salary) {
        this.name = name;
        this.surname = surname;
        this.salary = salary;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
