package edu.upc.eetac.dsa.DAO;

import edu.upc.eetac.dsa.BBDD.FactorySession;
import edu.upc.eetac.dsa.BBDD.Session;
import edu.upc.eetac.dsa.model.BuyedObject;
import edu.upc.eetac.dsa.model.Objects;
import edu.upc.eetac.dsa.model.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ObjectDAOImpl implements IObjectDAO {
    private static IObjectDAO instance;


    private ObjectDAOImpl() {   }

    public static IObjectDAO getInstance() {
        if (instance==null) instance = new ObjectDAOImpl();
        return instance;

    }


    @Override
    public int buyObjectForUser(User user, int idObject ) throws SQLException {
        // Hemos de añadir el objecto en la tabla BuyedObject
        Session session = null;
        int error =-1;
        try {
            int priceObject = getPriceByID(idObject);//Buscams el precio del objeto con ID
            int userMoney=user.getMoney();//Buscamos el dinero que tiene el usuario
            int finalMoney = userMoney-priceObject;
            if(finalMoney>0) {
                session = FactorySession.openSession();
                User updateuser = new User (user.getId(),user.getUserName(),user.getPassword(),user.getName(),user.getSurname(),finalMoney, user.getMail());
                session.update(updateuser);//modificamos el dinero restante al usuario
                BuyedObject newBuyedObject = new BuyedObject(0, idObject, user.getUserName());
                session.save(newBuyedObject);
                error = 0;
            }
            else
            {
                error=6;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return error;

    }
    public int getPriceByID (int idObject)throws SQLException{
        Session session = null;
        Objects object = new Objects();
        try {
            session = FactorySession.openSession();
            object = (Objects) session.getByID(object,idObject);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            session.close();
        }

        return object.getPrice();
    }

    public void deleteBuyedObject(BuyedObject buyedObject) throws SQLException {
        Session session = null;
        try {
            session = FactorySession.openSession();
            session.delete(buyedObject);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            session.close();
        }
    }

    public List<Objects> getListBuyedObjects(String userName) throws SQLException {
        //Tiene que entrar en la tabla de Buyed Object i seleccionar todos los objetos i con cada id sacar el objetos que es
        Session session = null;
        List<Objects> listObjects = new ArrayList<Objects>();
        try {
            session = FactorySession.openSession();
            List<BuyedObject> compras = session.findAllByName(new BuyedObject(),userName); // nos retorna toda la lista de objetos comprados por el user con nickname X

            for (BuyedObject objectBuyed : compras)//Recorremos toda la lista i lo pasamos a objectoes normales para enviar a laweb o Android
            {
                Objects ObjectToAdd = new Objects();
                int idObject = objectBuyed.getIdObject();
                ObjectToAdd = (Objects) session.getByID(ObjectToAdd,idObject);
                listObjects.add(ObjectToAdd);

            }

        }
        catch (Exception e) {
            e.printStackTrace();
            listObjects = null;
        }
        finally {
            session.close();
            return listObjects;
        }


    }
}
