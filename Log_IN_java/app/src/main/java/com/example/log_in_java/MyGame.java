package com.example.log_in_java;

import com.example.log_in_java.models.User;

public class MyGame {

    private static MyGame gameinstance;

    //Attributes
    public String namePartida;
    public int level;
    public int suspicious;
    public int money;
    public String nameUser;


    //------------Singleton--------------//

    public MyGame(){}

    public static synchronized MyGame getInstance(){
        if(gameinstance == null) {
            gameinstance = new MyGame();
        }
        return gameinstance;
    }

    public MyGame(String namePartida, int level, int suspicious, int money, String nameUser) {
        this.level = level;
        this.suspicious = suspicious;
        this.money = money;
        this.nameUser = nameUser;
        this.namePartida = namePartida;
    }

    public String getNamePartida() {
        return namePartida;
    }

    public void setNamePartida(String namePartida) {
        this.namePartida = namePartida;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
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

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }
}
