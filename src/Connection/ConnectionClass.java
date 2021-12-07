package Connection;

import java.sql.Connection;
import java.sql.DriverManager;


public class ConnectionClass {
public Connection con;
    public Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_management", "root", "aman");

        } catch (Exception e) {
            System.out.println(e);
        }

        return con;
    }


}




