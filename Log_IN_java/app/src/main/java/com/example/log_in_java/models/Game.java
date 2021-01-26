package com.example.log_in_java.models;

public class Game {

    private static Game gameinstance;

    public int id;
    public String name;
    public int idMap;
    public int time;
    public boolean win;
    public int idUser;

    public static synchronized Game getInstance(){
        if(gameinstance == null) {
            gameinstance = new Game();
        }
        return gameinstance;
    }


    public Game(int id, String name, int idMap, int time, boolean win, int idUser, int suspicious, boolean ticket) {
        this.id = id;
        this.name = name;
        this.idMap = idMap;
        this.time = time;
        this.win = win;
        this.idUser = idUser;

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

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public boolean isWin() {
        return win;
    }

    public void setWin(boolean win) {
        this.win = win;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }


}
