package Controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Boundry.DBHelper;

public class RegistrationDAO {

	

	DBHelper helper = new DBHelper();
	private ResultSet rs = null;
	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	
	//default constructor
	public RegistrationDAO() {	
		
	}


	/**
	  * @return list of customerName
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
		 * @param customerName, name of the user
		 * @param address, addreess of user
		 * @param number, number of user
		 * @param password, password of user
		 */
		  public void insertNewUser(String customerName, String address, String number, String password)
		  { // TODO Auto-generated method stub

			  String insertSql = "INSERT INTO user_Info (customerName,address,number,password) " +
				  				"values (?,?,?,?)";
		  
			  try { 
				  helper.connectDB();
		  
				  // create statement
				  pstmt = helper.getConnection().prepareStatement(insertSql);
		  
				  // declare the parameter starting at 1 
				  pstmt.setString(1,customerName);			
				  pstmt.setString(2, address);
				  pstmt.setString(3, number);
				  pstmt.setString(4, password);
					  
				  pstmt.executeUpdate();
		  
				  helper.disconnectDB(); 
			  } catch (SQLException sx)
			  {
				  System.out.println("Error inserting data into the database");
				  System.out.println(sx.getMessage()); 
				  System.out.println(sx.getErrorCode());
				  System.out.println(sx.getSQLState());
			  }
		  }
		  
		/**
		  * @return list of specific user's details
		  */
		public ArrayList<String> getUserProfile(String customerName)
		{
		  ArrayList<String> s1 = new ArrayList<String>();
		  
		  String sql = "SELECT * FROM user_Info where customerName = ?";
		  
		  try {
			  helper.connectDB();
		  
			  //create statement 
			  pstmt = helper.getConnection().prepareStatement(sql);
		  	  
			  pstmt.setString(1, customerName);
			  rs = pstmt.executeQuery(); 
			  while(rs.next())
			  {
				  s1.add("customerID: " + rs.getInt("customerID") + " ,customerName: " + rs.getString("customerName") + " ,address: " + rs.getString("address") + " ,number: " + rs.getString("number") + " ,password: " + rs.getString("password"));
			  }	  
			  helper.disconnectDB();
		  }catch(SQLException sx)
		  {
			  System.out.println("Error fetching data from the database");
			  System.out.println(sx.getMessage());
			  System.out.println(sx.getErrorCode());
			  System.out.println(sx.getSQLState()); 
		  }
		  
		  return s1;
		  
		}

	
	
}
