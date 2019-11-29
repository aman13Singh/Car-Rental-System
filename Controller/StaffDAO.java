package Controller;

import java.awt.Color;
import java.awt.EventQueue;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import Boundry.DBHelper;
import Entities.CarRegistration;
import Entities.UserInfo;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class StaffDAO {

	private JFrame frame;
	private JTable table;
	private JTextField nametextField;
	private JTextField addresstextField;
	private JTextField phonnumbertextField;
	private JTextField passwordtextField;

	
	private ResultSet rs = null;
	private java.sql.Statement stmt = null;
	private PreparedStatement pstmt = null;

	private DefaultTableModel tm = new DefaultTableModel();
	private DBHelper sd = new DBHelper();

	CarRegistration newCarRegistration = new CarRegistration();
    UserInfo user;
	private ListSelectionListener listener;
	private JTextField customerIDtextField;

	
	private int customerId;
	private String customerName;
	private String address;
	private String number;
	private String password;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StaffDAO window = new StaffDAO();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public StaffDAO() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setForeground(new Color(0, 0, 128));
		frame.getContentPane().setFont(new Font("SansSerif", Font.BOLD, 13));
		frame.setBounds(100, 100, 709, 539);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		
		Color color = new Color(85, 96, 128);

		/**
		 * define listener for table
		 */
		listener = new ListSelectionListener() {
			
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				
				//get the value of the selected row's id (column 0)
				int currId = (int) table.getValueAt(table.getSelectedRow(),0);
				
				//Grab the corresponding shoe from the database
				UserInfo ts = getcar(currId);
				
				customerIDtextField.setText(String.valueOf(ts.getCustomerID())); 
				nametextField.setText(((ts.getCustomerName())));
				addresstextField.setText(ts.getAddress());
				phonnumbertextField.setText(ts.getNumber());
				passwordtextField.setText(((ts.getPassword())));
				
				
				
				
			}
			
		};
		
		
		
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(43, 65, 620, 208);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel lblName = new JLabel("Name");
		lblName.setForeground(new Color(0, 0, 128));
		lblName.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblName.setBounds(43, 316, 75, 14);
		frame.getContentPane().add(lblName);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setForeground(new Color(0, 0, 128));
		lblAddress.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblAddress.setBounds(148, 316, 75, 14);
		frame.getContentPane().add(lblAddress);
		
		JLabel lblPhoneNumber = new JLabel("Phone Number");
		lblPhoneNumber.setForeground(new Color(0, 0, 128));
		lblPhoneNumber.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPhoneNumber.setBounds(253, 316, 98, 14);
		frame.getContentPane().add(lblPhoneNumber);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setForeground(new Color(0, 0, 128));
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPassword.setBounds(373, 316, 86, 14);
		frame.getContentPane().add(lblPassword);
		
		nametextField = new JTextField();
		nametextField.setBounds(41, 339, 86, 20);
		frame.getContentPane().add(nametextField);
		nametextField.setColumns(10);
		
		addresstextField = new JTextField();
		addresstextField.setBounds(149, 339, 86, 20);
		frame.getContentPane().add(addresstextField);
		addresstextField.setColumns(10);
		
		phonnumbertextField = new JTextField();
		phonnumbertextField.setBounds(253, 339, 86, 20);
		frame.getContentPane().add(phonnumbertextField);
		phonnumbertextField.setColumns(10);
		
		passwordtextField = new JTextField();
		passwordtextField.setBounds(373, 339, 86, 20);
		frame.getContentPane().add(passwordtextField);
		passwordtextField.setColumns(10);
		
		JLabel lblId = new JLabel("ID");
		lblId.setForeground(new Color(0, 0, 128));
		lblId.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblId.setBounds(493, 316, 61, 14);
		frame.getContentPane().add(lblId);
		
		customerIDtextField = new JTextField();
		customerIDtextField.setBounds(489, 341, 86, 20);
		frame.getContentPane().add(customerIDtextField);
		customerIDtextField.setColumns(10);
		
		JButton btnAddCustomer = new JButton("Add Customer");
		btnAddCustomer.setForeground(new Color(0, 0, 128));
		btnAddCustomer.setFont(new Font("SansSerif", Font.BOLD, 13));
		btnAddCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
							
				UserInfo cr = new UserInfo();
				
				try {
					customerId = Integer.valueOf(customerIDtextField.getText());
					customerName = nametextField.getText();
					address = addresstextField.getText();
					number = phonnumbertextField.getText();
	                password = passwordtextField.getText();
					
					

	                System.out.print(customerId);
	                System.out.print(customerName);
	                System.out.print(address);
	                System.out.print(number);
	                System.out.print(password);
	               
	                
	                
	                insertCustomer( customerId, customerName,   address, number, password);
			
			          
			     //  ManageInventory.main(null);
				} catch(Exception ex) {
					System.out.println("Error in inserting " + ex.getMessage());
					JOptionPane jop = new JOptionPane();
					jop.showMessageDialog(null," Empty Fields .");
			}
				
		
		   updateTable();
 	
				
				
			}
		});
		btnAddCustomer.setBounds(81, 402, 122, 23);
		frame.getContentPane().add(btnAddCustomer);
		
		JButton btnRemove = new JButton("Remove");
		btnRemove.setForeground(new Color(0, 0, 128));
		btnRemove.setFont(new Font("SansSerif", Font.BOLD, 13));
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				deleteCustomer(Integer.parseInt(customerIDtextField.getText()));
				updateTable();
				
			}
		});
		btnRemove.setBounds(239, 402, 89, 23);
		frame.getContentPane().add(btnRemove);
		
		JButton btnDeleteCustomer = new JButton("Update Customer");
		btnDeleteCustomer.setForeground(new Color(0, 0, 128));
		btnDeleteCustomer.setFont(new Font("SansSerif", Font.BOLD, 13));
		btnDeleteCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
			
				UserInfo cr = new UserInfo();

				cr.setCustomerID(Integer.parseInt(customerIDtextField.getText()));
				cr.setCustomerName((nametextField.getText()));
				cr.setAddress(addresstextField.getText());
				cr.setNumber((phonnumbertextField.getText()));
				cr.setPassword((passwordtextField.getText()));
								

				
				updateCustomerInfo(cr);
				
				updateTable();				
				
			}
		});
		btnDeleteCustomer.setBounds(380, 402, 141, 23);
		frame.getContentPane().add(btnDeleteCustomer);
		
		JLabel lblUserInformation = new JLabel("User Information");
		lblUserInformation.setForeground(new Color(0, 0, 128));
		lblUserInformation.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblUserInformation.setBounds(43, 27, 240, 27);
		frame.getContentPane().add(lblUserInformation);
	
	
		updateTable();
		
	}
	
	

	private void updateTable()	{
	 	table.getSelectionModel().removeListSelectionListener(listener);
		tm = new DefaultTableModel();
		
		//textFieldItem, textFieldType, textFieldQuantity , textFieldPrice , textFieldCategory , textFieldUnitPrice

		//Add the columns
		tm.addColumn("customerID");
		tm.addColumn("customerName");
		tm.addColumn("address");
		tm.addColumn("number");
		tm.addColumn("password");
		
				
		//Add the rows
		ArrayList<UserInfo> sl = new ArrayList<UserInfo>();
		
		//Populate the arraylist with the getShoes
		sl = listCustomer();
		
		for (UserInfo s : sl)	{
			tm.addRow(s.getVectorCustmor());
		}
		
		table.setModel(tm);
		table.getSelectionModel().addListSelectionListener(listener);
	}


	
	public ArrayList<UserInfo> listCustomer() 
	{
		ArrayList<UserInfo> s1 = new ArrayList<UserInfo>();

		String sql = "SELECT * FROM user_info order by user_info.customerID";
		try {
			// connect to the database
			sd.connectDB();
			stmt = sd.getConnection().createStatement();
			rs = ((java.sql.Statement) stmt).executeQuery(sql);

			while (rs.next())
			{     
				// CarRegistrationNo, model , make, fromDate, toDate

				 
				UserInfo s = new UserInfo();
				
				//Get the right type (string) from the right column ("itemId");
				s.setCustomerID((rs.getInt("customerID")));
				s.setCustomerName((rs.getString("customerName")));
				s.setAddress((rs.getString("address")));
				s.setNumber((rs.getString("number")));
				s.setPassword((rs.getString("password")));
				
				s1.add(s);
				
				 // System.out.println(s1);
			}

			sd.disconnectDB();
		} catch (SQLException sx) {
			System.out.println("Error fetching data from the database");
			System.out.println(sx.getMessage());
			System.out.println(sx.getErrorCode());
			System.out.println(sx.getSQLState());
		}

		return s1;
	}

	
	//method to get inventory 
	public UserInfo getcar(int customerID) {
			
		UserInfo s = new UserInfo();
			
			String sql = "SELECT * FROM user_info  WHERE customerID  = ?";
			
			try {
				
				//Connect to the database
				sd.connectDB();
				
				//Create the statement
				pstmt = sd.getConnection().prepareStatement(sql);
				
				//Declare the parameter (starting at 1)
				pstmt.setInt(1,customerID);
				
				rs = pstmt.executeQuery();
				
				while (rs.next())	{
					
					/*//Get the right type (string) from the right column ("");									
					ap.setCustomerID((rs.getInt("ID")));
					ap.setCustomerName((rs.getString("CarRegistrationNo")));
					ap.setAddress((rs.getString("model")));
					ap.setNumber((rs.getString("make")));
					ap.setPassword((rs.getString("fromDate")));*/
				
					
					s.setCustomerID((rs.getInt("customerID")));
					s.setCustomerName((rs.getString("customerName")));
					s.setAddress((rs.getString("address")));
					s.setNumber((rs.getString("number")));
					s.setPassword((rs.getString("password")));
					
					
				} 
				
				sd.disconnectDB();
					
				} catch (SQLException sx) {
					System.out.println("Error Connecting to Database");
					System.out.println(sx.getMessage());
					System.out.println(sx.getErrorCode());
					System.out.println(sx.getSQLState());
					
				}
				return s;			
			}

	public void insertCustomer(  int customerID,  String customerName, String address, String number, String password) {
		// TODO Auto-generated method stub

		String insertSql = "INSERT INTO user_Info ( customerID,  customerName, address, number,  password ) "
				+ "values (?,?,?,?,?)";

		try {
			sd.connectDB();

			// create statement
			pstmt = sd.getConnection().prepareStatement(insertSql);

			// declare the parameter starting at 1
			pstmt.setInt(1,customerID);
			pstmt.setString(2, customerName);
			pstmt.setString(3, address);
			pstmt.setString(4, number);
			pstmt.setString(5, password);
		

			pstmt.executeUpdate();

			sd.disconnectDB();
		} catch (SQLException sx) {
			System.out.println("Error inserting data into the database");
			System.out.println(sx.getMessage());
			System.out.println(sx.getErrorCode());
			System.out.println(sx.getSQLState());
		}
	
	
	}

	
	public void updateCustomerInfo(UserInfo su)	{		

		String updateSql = "UPDATE user_Info  SET " + 
				"customerName = ?, " +
				"address = ?, " +
				"number = ?, " +
				"password = ? " +
				
				"WHERE customerID = ?";
		
		//itemId , Item , Type , Quantity , Price , Category , Unitprice
		
		try {
				sd.connectDB();
				
				pstmt = sd.getConnection().prepareStatement(updateSql);
		
				
				//pstmt.setInt(1, su.getVGID());  				
				pstmt.setString(1, su.getCustomerName());
				pstmt.setString(2, su.getAddress());
				pstmt.setString(3, su.getNumber());
				pstmt.setString(4, su.getPassword());
				pstmt.setInt(5, su.getCustomerID());  				
				
				pstmt.executeUpdate();
				
				sd.disconnectDB();
				
		} catch (SQLException sx) {
			System.out.println("Error Connecting to Database");
			System.out.println(sx.getMessage());
			System.out.println(sx.getErrorCode());
			System.out.println(sx.getSQLState());
			
		}	
		
	}
		
		
	//method to delete inventory
	public void deleteCustomer(int id) {
			
			String sql = "DELETE FROM user_info  WHERE customerID = ?";
			
			try {
				
				//Connect to the database
				sd.connectDB();
				
				//Create the statement
				pstmt = sd.getConnection().prepareStatement(sql);
				
				//Declare the parameter (starting at 1)
				pstmt.setInt(1,id);
				
				//Delete Data
				pstmt.executeUpdate();
				
				sd.disconnectDB();
					
				} catch (SQLException sx) {
					System.out.println("Error Connecting to Database");
					System.out.println(sx.getMessage());
					System.out.println(sx.getErrorCode());
					System.out.println(sx.getSQLState());
					
				}		
			}
}

