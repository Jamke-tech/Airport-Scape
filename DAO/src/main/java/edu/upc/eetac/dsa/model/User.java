package edu.upc.eetac.dsa.model;

import java.util.HashMap;

public class User {

    public int idUser;
    public String userName;
    public String password;
    public String name;
    public String surname;
    public int money;
    public String mail;

    public User(int idUser, String userName, String password, String name, String surname, int money, String mail) {
        this.idUser = idUser;
        this.userName = userName;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.money = money;
        this.mail = mail;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getNameOfUser() {
        return userName;


    }

    public String getPass() {
        return password;
    }
}



