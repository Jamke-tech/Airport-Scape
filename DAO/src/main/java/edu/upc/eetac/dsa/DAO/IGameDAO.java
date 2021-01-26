package edu.upc.eetac.dsa.DAO;

import edu.upc.eetac.dsa.model.Game;
import edu.upc.eetac.dsa.model.Map;

import java.sql.SQLException;
import java.util.List;

public interface IGameDAO {
    public int saveGame(Game game) throws SQLException;
    public Game getGameSaved(String name) throws SQLException;
    public int getIdMap (String name) throws SQLException;
    public Map getStringMap (int id) throws SQLException;
    public int winGame(Game game) throws SQLException;
    public List<Game> getListUserGames(String userName) throws SQLException;
}
