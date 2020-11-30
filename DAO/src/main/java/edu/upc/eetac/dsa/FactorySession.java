package edu.upc.eetac.dsa;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
//ESTO ES UNA PRUEBAAAAAAAAAAAAAAAAAAAAAAAA
//ESTOOOO NO SE que COÑO ES
//Hola que tal

//No me sale lo de maria
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
