package edu.upc.eetac.dsa.DAO;

import edu.upc.eetac.dsa.model.Game;

import java.sql.SQLException;

public interface IGameDAO {
    public int saveGame(Game game) throws SQLException;
    public Game getGameSaved(String name) throws SQLException;
    public int getIdMap (String name) throws SQLException;
    public String getStringMap (int id) throws SQLException;
    public int winGame(Game game, int money) throws SQLException;
}
