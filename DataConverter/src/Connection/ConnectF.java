package Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectF {

	public static Connection getConnection() {
	     try {
	    	 Class.forName("org.gjt.mm.mysql.Driver"); 
	 		String rurl = "jdbc:mysql://localhost:3306/coupauf";	
	 		return DriverManager.getConnection(rurl+"?characterEncoding=ISO8859_1&useOldUTF8Behavior=true&autoReconnect=true&useSSL=false","root", "142536//");

	     } catch (SQLException | ClassNotFoundException e) {
	         throw new RuntimeException(e);
	     }
	 }	
}
