package net.ibxnjadev.ucn.parallel.helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqlConnection implements net.ibxnjadev.ucn.parallel.helper.Connection<Connection> {

    private java.sql.Connection connection;

    private static final String AUTO_RECONNECT_PROPERTY = "?autoReconnect=true&useUnicode=yes";
    private static final String CLASS_NAME = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://{host}:{port}/";

    private final String host;
    private final int port;
    private final String user;
    private final String password;

    public SqlConnection(String host,
                         int port,
                         String user,
                         String password) {
        this.host = host;
        this.port = port;
        this.user = user;
        this.password = password;
    }


    @Override
    public void connect() {

        try {
            Class.forName(CLASS_NAME);

            String url = URL.replace("{port}", String.valueOf(port))
                    .replace("{host}", host);

            url = url + AUTO_RECONNECT_PROPERTY;

            connection = DriverManager.getConnection(
                    url,
                    user,
                    password
            );

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public java.sql.Connection get() {
        return connection;
    }

}
