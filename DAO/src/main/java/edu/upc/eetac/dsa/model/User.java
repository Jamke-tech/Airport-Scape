package edu.upc.eetac.dsa.model;

import java.util.HashMap;

public class User {

    private int idUser;
    private String userName;
    private String password;
    private String name;
    private String surname;
    private int money;
    private HashMap<Integer, BuyedObject> buyedObjects;
    private String mail;

    public User(int idUser, String userName, String password, String name, String surname, int money, HashMap<Integer, BuyedObject> buyedObjects, String mail) {
        this.idUser = idUser;
        this.userName = userName;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.money = money;
        this.buyedObjects = buyedObjects;
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

    public void setBuyedObjects(HashMap<Integer, BuyedObject> buyedObjects) {
        this.buyedObjects = buyedObjects;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}



