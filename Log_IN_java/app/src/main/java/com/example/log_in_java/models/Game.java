package com.example.log_in_java.models;

public class Game {

    private static Game gameinstance;

    public int id;
    public String name;
    public int idMap;
    public boolean win;
    public String userName;
    public int suspicious;
    public int money;

    public static synchronized Game getInstance(){
        if(gameinstance == null) {
            gameinstance = new Game();
        }
        return gameinstance;
    }


    public Game(int id, String name, int idMap, boolean win, String userName, int suspicious) {
        this.id = id;
        this.name = name;
        this.idMap = idMap;
        this.win = win;
        this.userName = userName;
        this.suspicious = suspicious;
    }


    public Game() {}


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIdMap() {
        return idMap;
    }

    public void setIdMap(int idMap) {
        this.idMap = idMap;
    }


    public boolean isWin() {
        return win;
    }

    public void setWin(boolean win) {
        this.win = win;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getSuspicious() {
        return suspicious;
    }

    public void setSuspicious(int suspicious) {
        this.suspicious = suspicious;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
