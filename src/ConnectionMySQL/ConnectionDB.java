/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package ConnectionMySQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
    private static ConnectionDB instance = null;
    private Connection connection;

    // Informasi koneksi database
    private String jdbcURL = "jdbc:mysql://localhost:3306/warungkostungu";
    private String username = "root";
    private String password = "";

    private ConnectionDB() {
        try {
            connection = DriverManager.getConnection(jdbcURL, username, password);
            System.out.println("Koneksi berhasil dibuka");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ConnectionDB getInstance() {
        if (instance == null) {
            instance = new ConnectionDB();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Koneksi berhasil ditutup");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}


