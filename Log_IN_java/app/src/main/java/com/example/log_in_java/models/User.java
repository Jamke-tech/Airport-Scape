package com.example.log_in_java.models;


import java.io.Serializable;
import java.util.HashMap;

public class User implements Serializable {
    //Singleton
    private static User userinstance;

    private int idUser;
    private String name;
    private String surname;
    private String userName;
    private String mail;
    private String password;
    private int money;
    private HashMap<Integer, BuyedObject> buyedObjects;

    //----------Constructors----------//

    public User(int idUser, String name, String surname, String userName, String mail, String password, int money, HashMap<Integer, BuyedObject> buyedObjects) {
        this.idUser = idUser;
        this.name = name;
        this.surname = surname;
        this.userName = userName;
        this.mail = mail;
        this.password = password;
        this.money = money;
        this.buyedObjects = buyedObjects;
    }

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }


    //------------Singleton--------------//

    private User(){}

    public static synchronized User getInstance(){
        if(userinstance == null) {
            userinstance = new User();
        }
        return userinstance;
    }



    //------------Methods----------------//

    ///getters///

    public static User getUserinstance() {
        return userinstance;
    }

    public int getIdUser() {
        return idUser;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getUserName() {
        return userName;
    }

    public String getMail() {
        return mail;
    }

    public String getPassword() {
        return password;
    }

    public int getMoney() {
        return money;
    }

    public HashMap<Integer, BuyedObject> getBuyedObjects() {
        return buyedObjects;
    }

    ///setters///


    public static void setUserinstance(User userinstance) {
        User.userinstance = userinstance;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void setBuyedObjects(HashMap<Integer, BuyedObject> buyedObjects) {
        this.buyedObjects = buyedObjects;
    }

    public void coseInstance(){
        setUserName(null);
        setSurname(null);
        setName(null);
        setMail(null);
        setPassword(null);
        setIdUser(0);
        //setBuyedObjects(0,null); faltaría poner este bien
        setMoney(0);

    }
}
