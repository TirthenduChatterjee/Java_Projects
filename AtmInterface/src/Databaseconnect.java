import java.sql.Connection;
import java.sql.DriverManager;


public class Databaseconnect {
Connection con ;
public Connection connect(){
    String username = "root";
    String password = "Hello@babai#251023";
    String database = "ATM";
    String url = "jdbc:mysql://localhost:3306/"+database;
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection(url, username, password);
    } catch (Exception e) {
        System.out.println(e);
    }
    return con;
}    
}
