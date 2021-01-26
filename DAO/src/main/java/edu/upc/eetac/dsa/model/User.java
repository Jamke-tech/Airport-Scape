package edu.upc.eetac.dsa.model;

public class User {

    public int id;
    public String userName;
    public String password;
    public String name;
    public String surname;
    public int money;
    public String mail;
    public int wins;

    public User(int id, String userName, String password, String name, String surname, int money, String mail, int wins) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.money = money;
        this.mail = mail;
        this.wins = wins;
    }
    public User ()
    {
    }

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

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }
}




