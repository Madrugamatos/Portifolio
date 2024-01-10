package conection;

import models.controls.ControleDeContas;
import models.controls.ControlerFactory;

import java.sql.*;

public class Conection {

   private static Connection connection = null;

    public static Connection getConnection() {

        String url = "jdbc:mysql://localhost:3306/contas";//?useSSL=false
        String user = "developer";
        String password = "12345678";


        if (connection == null) {
            try {
                connection = DriverManager.getConnection(url, user, password);

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return connection;
    }

    public static void closeConnection(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void closeStatement(Statement statement){
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static void closeResultser(ResultSet resultSet){
        try {
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }



}
