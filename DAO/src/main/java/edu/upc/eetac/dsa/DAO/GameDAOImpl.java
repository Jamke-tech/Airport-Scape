package edu.upc.eetac.dsa.DAO;

import edu.upc.eetac.dsa.BBDD.FactorySession;
import edu.upc.eetac.dsa.BBDD.Session;
import edu.upc.eetac.dsa.model.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GameDAOImpl {
    private static IGameDAO instance;

    private GameDAOImpl() {   }

    public static IGameDAO getInstance() {
        if (instance==null) instance = (IGameDAO) new GameDAOImpl();
        return instance;

    }
    public int saveGame(Game game) throws SQLException {
        Session session = null;
        int error =-1;
        try {
            session = FactorySession.openSession();
            session.save(game);
            error=0;

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally {
            if(session!=null) {
                session.close();
            }
            return error;
        }
    }
    public Game getGameSaved(String name) throws SQLException {
        Session session = null;
        Game game = new Game();
        try {
            session = FactorySession.openSession();
            game = (Game) session.getByName(game, name);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            session.close();
        }

        return game;
    }
    public int getIdMap (String name) throws SQLException {
        Session session = null;
        int idMap = -1;
        try {
            session = FactorySession.openSession();
            Game actualGame = (Game) session.getByName(new Game(), name);
            idMap = actualGame.getIdMap();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            return idMap;
        }
    }
    public String getStringMapByGameName (String name) throws SQLException {
        Session session = null;
        String stringMap = null;
        try {
            session = FactorySession.openSession();
            Game actualGame = (Game) session.getByName(new Game(), name);
            int idMap = actualGame.getIdMap();
            Map map = (Map) session.getByID(new Map(), idMap);
            stringMap = map.getStringMap();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            return stringMap;
        }
    }
}
