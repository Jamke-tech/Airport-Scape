package edu.upc.eetac.dsa;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
//ESTO ES UNA PRUEBAAAAAAAAAAAAAAAAAAAAAAAA
<<<<<<< HEAD
//ESTOOOO NO SE COÑO ES
//HOLA CHICOSSSS
=======
//ESTOOOO NO SE que COÑO ES
>>>>>>> 977b5b65596d9263b40534ce35d1b37a02b30921
public class FactorySession {
    public static Session openSession() {


        Connection conn = getConnection();

        Session session = new SessionImpl(conn);

        return session;
    }



    private static Connection getConnection() {
        Connection conn = null;
        try {
            conn =
                    DriverManager.getConnection("jdbc:mysql://localhost/test?" +
                            "user=minty&password=greatsqldb");

        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return conn;
    }
}
