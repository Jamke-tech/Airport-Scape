package edu.upc.eetac.dsa.model;

public class Game {
    public int idGame;
    public MainCharacter character;
    public Map map;
    public int time;
    public boolean win;
    public int idUser;

    public Game(int idGame, MainCharacter character, Map map, int time, boolean win, int idUser) {
        this.idGame = idGame;
        this.character = character;
        this.map = map;
        this.time = time;
        this.win = win;
        this.idUser = idUser;
    }

    public void setIdGame(int idGame) {
        this.idGame = idGame;
    }

    public void setCharacter(MainCharacter character) {
        this.character = character;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void setWin(boolean win) {
        this.win = win;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
}