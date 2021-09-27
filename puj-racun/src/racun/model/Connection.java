
package racun.model;

import java.sql.DriverManager;


public class Connection {
    private String host;
    private String user;
    private String password;
    private String db;
    java.sql.Connection connection;

    public Connection() throws Exception {
        this.host = "localhost";
        this.user = "root";
        this.password = "";
        this.db = "fsre-puj";
        this.connect();
    }

    public Connection(String host, String user, String password, String db) throws Exception {
        this.host = host;
        this.user = user;
        this.password = password;
        this.db = db;
        this.connect();
    }

    public java.sql.Connection getConnection () throws Exception {
        this.connect();
        return this.connection;
    }

    public void connect () throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        String connectionString =
                "jdbc:mysql://"+
                        this.host+"/"+
                        this.db+"?user="+
                        this.user+"&password="+
                        this.password+"&useSSL=false";
        this.connection = DriverManager.getConnection(connectionString);
    }
    public void disconnect () throws Exception {
        this.connection.close();
    }
 }
