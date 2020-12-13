package com.example.log_in_java.models;


import java.io.Serializable;
import java.util.HashMap;

public class User implements Serializable {

    private static User userinstance;

    //Attributes
    public int id;
    public String userName;
    public String password;
    public String name;
    public String surname;
    public int money;
    public String mail;


    //------------Singleton--------------//

    private User(){}

    public static synchronized User getInstance(){
        if(userinstance == null) {
            userinstance = new User();
        }
        return userinstance;
    }


    //----------Constructors----------//

    public User(int id, String userName, String password, String name, String surname, int money, String mail) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.money = money;
        this.mail = mail;
    }

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    //------------Methods----------------//

    public void setId(int id) {
        this.id = id;
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

    public int getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getMoney() {
        return money;
    }

    public String getMail() {
        return mail;
    }

    public void codeInstance(){
        setId(0);
        setUserName(null);
        setPassword(null);
        setName(null);
        setSurname(null);
        setMoney(0);
        setMail(null);


    }
}
