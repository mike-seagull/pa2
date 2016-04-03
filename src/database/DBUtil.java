package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {
	
	static
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
public static Connection getConnection() throws SQLException
{
	Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/world?useSSL=true", "root","ruchinarayan");
	return conn;
}

public static void closeResultSet(ResultSet rs)
{
	if(rs != null)
	{
		try {
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

public static void closeStatement(Statement stmt)
{
	if(stmt != null)
	{
		try {
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		stmt = null;
	}
}

public static void closeConnection(Connection con)
{
	if(con != null)
	{
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		con = null;
	}
}

}
