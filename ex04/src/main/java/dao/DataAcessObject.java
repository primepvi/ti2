package dao;

import java.sql.*;

public class DataAcessObject {
    protected Connection connection;

    public DataAcessObject() {
        this.connection = null;
    }

    public boolean connect() {
        String driverName = "org.postgresql.Driver";
        String serverName = "localhost";
        String databaseName = "ti2cc-02";
        int databasePort = 5432;

        String databaseUrl = "jdbc:postgresql://" + serverName + ":" + databasePort + "/" + databaseName;
        String databaseUsername = "docker";
        String databasePassword = "docker";

        boolean isDatabaseConnected = false;

        try {
            Class.forName(driverName);
            connection = DriverManager.getConnection(databaseUrl, databaseUsername, databasePassword);
            isDatabaseConnected = connection == null;
            System.out.println("> Conexão com o banco de dados efetuada com sucesos.");
        } catch (Exception error) {
            System.err.println(error.getMessage());
        }

        return isDatabaseConnected;
    }

    public boolean close() {
        boolean isDatabaseClosed = false;

        try {
            connection.close();
            isDatabaseClosed = true;
        } catch (SQLException exception) {
            System.err.println(exception.getMessage());
        }

        return isDatabaseClosed;
    }


}
