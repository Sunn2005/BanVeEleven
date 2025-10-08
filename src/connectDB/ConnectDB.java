package connectDB;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
	public static Connection con = null; 
	private static ConnectDB instance = new ConnectDB(); 
	public static ConnectDB getInstance() { return instance; }

	public static void connect() { 
		String url = "jdbc:sqlserver://localhost:1433;databaseName=GaEleven";
		String user = "sa"; 
		String password = "sapassword"; 
		try { 
			con = DriverManager.getConnection(url, user, password); 
			if (con != null)
				System.out.print(new Date(0).toString() + ":" + " Connect successfully\n");
		} catch (SQLException e) { 
			e.printStackTrace();
		}
		
	}
	
	public void disconnect() {
		if (con == null) return;
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() { 
		if (con == null) connect();
		return con; 
	}

}
