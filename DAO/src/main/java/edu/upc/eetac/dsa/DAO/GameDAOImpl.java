package edu.upc.eetac.dsa.DAO;

import edu.upc.eetac.dsa.BBDD.FactorySession;
import edu.upc.eetac.dsa.BBDD.Session;
import edu.upc.eetac.dsa.model.*;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

public class GameDAOImpl implements IGameDAO {
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
        catch (SQLIntegrityConstraintViolationException e){
            session.update(game);

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
            game = (Game) session.getByNameGame(game, name);
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
            Game actualGame = (Game) session.getByNameGame(new Game(), name);
            idMap = actualGame.getIdMap();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            return idMap;
        }
    }


    public Map getStringMap (int id) throws SQLException {
        Session session = null;
        Map map = null;
        try {
            session = FactorySession.openSession();
            map = (Map) session.getByID(new Map(), id);
            //stringMap = map.getStringMap();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            return map;
        }
    }
    public int winGame(Game game) throws SQLException {
        // Hemos de añadir el dinero obtenido al usuario
        Session session = null;
        int error =-1;
        try {
            session = FactorySession.openSession();
            String userName = game.getUserName();
            User user = (User) session.getByName(new User(), userName);
            int userMoney = user.getMoney();//Buscamos el dinero que tiene el usuario
            int finalMoney = userMoney + game.getMoney() ;
            int wins = user.getWins() + 1;
            User updateuser = new User (user.getId(),user.getUserName(),user.getPassword(),user.getName(),user.getSurname(),finalMoney, user.getMail(), wins);
            session.update(updateuser);//Sumamos el dinero al usuario y lo modificamos
            error = 0;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return error;

    }

    public List<Game> getListUserGames(String userName) throws SQLException {

        Session session = null;
        List<Game> listaGames = new ArrayList<Game>();
        try {
            session = FactorySession.openSession();
            listaGames = session.findAllByUserName(new Game(),userName);
        }
        catch (Exception e) {
            e.printStackTrace();
            listaGames = null;
        }
        finally {
            session.close();
            return listaGames;
        }
    }

}
