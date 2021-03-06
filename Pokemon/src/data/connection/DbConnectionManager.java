package data.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnectionManager {
	
	private static Connection globalConnection; 

	public static Connection getConnection() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			//con = DriverManager.getConnection("jdbc:mysql://localhost:3308/shunyu_wang?user=shunyu_wang&password=eindithi&characterEncoding=UTF-8&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&autoReconnect=true");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Pokemon", "root", "A93-10-18A");
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}
	
	public static Connection getGlobalConnection() {
		if(globalConnection == null) {
			globalConnection = getConnection();
		}
		return globalConnection;
	}
	
	public static void closeGlobalConnection() {
		if(globalConnection != null) {
			try {
				globalConnection.close();
			} catch (SQLException e) {
				System.out.println("error in close global connection");
			}
		}
	}
	
}
