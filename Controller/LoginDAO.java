package Controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Boundry.DBHelper;

public class LoginDAO {

	
	
	DBHelper helper = new DBHelper();
	private ResultSet rs = null;
	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	
	public LoginDAO() {	
		
	}

	/**
	  * @return list of usernames
	  */
		public ArrayList<String> listUserNames() 
		{
			ArrayList<String> s1 = new ArrayList<String>();

			String sql = "SELECT customerName FROM user_Info";
			try {
				// connect to the database
				helper.connectDB();
				this.stmt = helper.getConnection().createStatement();
				rs = stmt.executeQuery(sql);

				while (rs.next())
				{
					s1.add(rs.getString("customerName"));				
				}

				helper.disconnectDB();
			} catch (SQLException sx) {
				System.out.println("Error fetching data from the database");
				System.out.println(sx.getMessage());
				System.out.println(sx.getErrorCode());
				System.out.println(sx.getSQLState());
			}

			return s1;
		}

		/**
		  * 
		  * @param usernm, username for whom password is being fetched
		  * @return password for specific user
		  */
		public String getPassword(String customerName) {

			String s1 = "";
			String sql = "SELECT password FROM user_Info where customerName = '" + customerName + "'";
			try {
				// connect to the database
				helper.connectDB();
				this.stmt = helper.getConnection().createStatement();
				rs = stmt.executeQuery(sql);

				while (rs.next())
				{			
					s1 = rs.getString(1);
				}

				helper.disconnectDB();
			} catch (SQLException sx) {
				System.out.println("Error fetching data from the database");
				System.out.println(sx.getMessage());
				System.out.println(sx.getErrorCode());
				System.out.println(sx.getSQLState());
			}

			return s1;
		}

}

	
	

