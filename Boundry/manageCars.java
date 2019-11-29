package Boundry;

import java.awt.Color;
import java.awt.EventQueue;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import Entities.CarRegistration;
import Entities.UserInfo;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.toedter.calendar.JDateChooser;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;



public class manageCars {

	private JFrame frame;
	private JTable table;
	
	
	private ResultSet rs = null;
	private java.sql.Statement stmt = null;
	private PreparedStatement pstmt = null;

	private DefaultTableModel tm = new DefaultTableModel();
	private DBHelper sd = new DBHelper();

	CarRegistration newCarRegistration = new CarRegistration();
    UserInfo user;
	private ListSelectionListener listener;
	private JTextField carRegisNotextField;
	private JTextField modeltextField;
	private JTextField maketextField;
	private JTextField pricetextField;
	
	JDateChooser fromdateChooser;
	JDateChooser todateChooser ;
	private JTextField IDtextField;
	
	private int ID;
	private String CarRegistrationNo;
	private String model;
	private String make;
	private Date fromDate;
	private Date toDate;
	private float price;
	

	
	// carRegisNotextField, modeltextField , maketextField, fromDate, toDate pricetextField

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args, UserInfo user) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					manageCars window = new manageCars(user);
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
	public manageCars(UserInfo user) {
		this.user = user;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 788, 488);
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
				CarRegistration ts = getcar(currId);
				
				IDtextField.setText(String.valueOf(ts.getID())); 
				carRegisNotextField.setText((String.valueOf(ts.getCarRegistrationNo())));
				modeltextField.setText(ts.getModel());
				maketextField.setText(ts.getMake());
				fromdateChooser.setDate(((ts.getFromDate())));
				todateChooser.setDate((ts.getToDate())); 
				pricetextField.setText(String.valueOf(ts.getPrice())); 
				
				
				
			}
			
		};
		
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 68, 617, 209);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel lblLicencePlate = new JLabel("Licence Plate");
		lblLicencePlate.setForeground(new Color(0, 0, 128));
		lblLicencePlate.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblLicencePlate.setBounds(10, 304, 86, 14);
		frame.getContentPane().add(lblLicencePlate);
		
		JLabel lblModel = new JLabel("Model");
		lblModel.setForeground(new Color(0, 0, 128));
		lblModel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblModel.setBounds(116, 304, 46, 14);
		frame.getContentPane().add(lblModel);
		
		JLabel lblMake = new JLabel("Make");
		lblMake.setForeground(new Color(0, 0, 128));
		lblMake.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblMake.setBounds(204, 304, 46, 14);
		frame.getContentPane().add(lblMake);
		
		JLabel lblDateFrom = new JLabel("From");
		lblDateFrom.setForeground(new Color(0, 0, 128));
		lblDateFrom.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDateFrom.setBounds(317, 303, 66, 14);
		frame.getContentPane().add(lblDateFrom);
		
		JLabel lblTo = new JLabel("To");
		lblTo.setForeground(new Color(0, 0, 128));
		lblTo.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTo.setBounds(418, 304, 46, 14);
		frame.getContentPane().add(lblTo);
		
		JLabel lblPrice = new JLabel("Price");
		lblPrice.setForeground(new Color(0, 0, 128));
		lblPrice.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPrice.setBounds(529, 303, 46, 14);
		frame.getContentPane().add(lblPrice);
		
		carRegisNotextField = new JTextField();
		carRegisNotextField.setBounds(10, 328, 86, 20);
		frame.getContentPane().add(carRegisNotextField);
		carRegisNotextField.setColumns(10);
		
		modeltextField = new JTextField();
		modeltextField.setBounds(116, 328, 76, 20);
		frame.getContentPane().add(modeltextField);
		modeltextField.setColumns(10);
		
		maketextField = new JTextField();
		maketextField.setBounds(202, 328, 86, 20);
		frame.getContentPane().add(maketextField);
		maketextField.setColumns(10);
		
		pricetextField = new JTextField();
		pricetextField.setBounds(523, 328, 86, 20);
		frame.getContentPane().add(pricetextField);
		pricetextField.setColumns(10);
		
		fromdateChooser = new JDateChooser();
		fromdateChooser.setBounds(309, 328, 95, 20);
		frame.getContentPane().add(fromdateChooser);
		
		todateChooser = new JDateChooser();
		todateChooser.setBounds(418, 328, 95, 20);
		frame.getContentPane().add(todateChooser);
		
		IDtextField = new JTextField();
		IDtextField.setBounds(619, 328, 38, 20);
		frame.getContentPane().add(IDtextField);
		IDtextField.setColumns(10);
		
		JButton btnAddCar = new JButton("Add Car");
		btnAddCar.setForeground(new Color(0, 0, 128));
		btnAddCar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnAddCar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				/*
				 * java.sql.Date FromDate = new
				 * java.sql.Date(fromdateChooser.getDate().getTime()); java.sql.Date toDate =
				 * new java.sql.Date(todateChooser.getDate().getTime());
				 * 
				 */
				
				
				
				CarRegistration cr = new CarRegistration();
				
				try {
					ID = Integer.valueOf(IDtextField.getText());
					CarRegistrationNo = carRegisNotextField.getText();
					model = modeltextField.getText();
					make = maketextField.getText();
					java.sql.Date fromDate = new java.sql.Date(fromdateChooser.getDate().getTime());
					java.sql.Date toDate = new java.sql.Date(todateChooser.getDate().getTime());
	                price = Float.valueOf(pricetextField.getText()).floatValue();
					
					

	                System.out.print(ID);
	                System.out.print(CarRegistrationNo);
	                System.out.print(model);
	                System.out.print(make);
	                System.out.print(fromDate);
	                System.out.print(toDate);
	                System.out.print(price);
	                
	                
	                
					insertCarRegister( user.getCustomerID(), user.getCustomerName(),   CarRegistrationNo, model, make, fromDate, toDate, price);
					
					
			/*		  cr.setID(Integer.parseInt(IDtextField.getText()));
			          cr.setCarRegistrationNo((carRegisNotextField.getText()));
			          cr.setModel((modeltextField.getText()));
			          cr.setMake((maketextField.getText()));
			          cr.setFromDate(FromDate);
			          cr.setToDate(toDate);
			          cr.setPrice(Float.parseFloat(pricetextField.getText()));
*/
			          
			     //  ManageInventory.main(null);
				} catch(Exception ex) {
					System.out.println("Error in inserting " + ex.getMessage());
					JOptionPane jop = new JOptionPane();
					jop.showMessageDialog(null," Empty Fields .");
			}
				
		
		updateTable();
 	
				
				
			}
		});
		btnAddCar.setBounds(22, 392, 111, 23);
		frame.getContentPane().add(btnAddCar);
		
		JButton btnDeleteCar = new JButton("Delete  Car");
		btnDeleteCar.setForeground(new Color(0, 0, 128));
		btnDeleteCar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnDeleteCar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				deleteCarInventory(Integer.parseInt(IDtextField.getText()));
				updateTable();
			
			
			}
		});
		btnDeleteCar.setBounds(156, 392, 109, 23);
		frame.getContentPane().add(btnDeleteCar);
		
		JButton btnUpdateCar = new JButton("Update Car");
		btnUpdateCar.setForeground(new Color(0, 0, 128));
		btnUpdateCar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnUpdateCar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				java.sql.Date FromDate = new java.sql.Date(fromdateChooser.getDate().getTime());
				java.sql.Date toDate = new java.sql.Date(todateChooser.getDate().getTime());
				

				CarRegistration cr = new CarRegistration();
	                cr.setID(Integer.parseInt(IDtextField.getText()));
					cr.setCarRegistrationNo((carRegisNotextField.getText()));
					cr.setModel(modeltextField.getText());
					cr.setMake((maketextField.getText()));
					cr.setFromDate(FromDate);
					cr.setToDate(toDate);
					cr.setPrice(Float.parseFloat(pricetextField.getText()));
									

					
					updateCarInventory(cr);
					
					updateTable();
				
				
			}
		});
		btnUpdateCar.setBounds(297, 392, 110, 23);
		frame.getContentPane().add(btnUpdateCar);
		
		JLabel lblId = new JLabel("ID");
		lblId.setForeground(new Color(0, 0, 128));
		lblId.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblId.setBounds(619, 305, 32, 14);
		frame.getContentPane().add(lblId);
		
		JLabel lblRentalCarInformation = new JLabel("Rental car information");
		lblRentalCarInformation.setForeground(new Color(0, 0, 128));
		lblRentalCarInformation.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblRentalCarInformation.setBounds(22, 22, 191, 28);
		frame.getContentPane().add(lblRentalCarInformation);
		
		updateTable();
	}

	private void updateTable()	{
	 	table.getSelectionModel().removeListSelectionListener(listener);
		tm = new DefaultTableModel();
		
		//textFieldItem, textFieldType, textFieldQuantity , textFieldPrice , textFieldCategory , textFieldUnitPrice

		//Add the columns
		tm.addColumn("ID");
		tm.addColumn("RegNo");
		tm.addColumn("Model");
		tm.addColumn("Make");
		tm.addColumn("FromDate");
		tm.addColumn("ToDate");
		tm.addColumn("Price");
		
				
		//Add the rows
		ArrayList<CarRegistration> sl = new ArrayList<CarRegistration>();
		
		//Populate the arraylist with the getShoes
		sl = listCarRegistration();
		
		for (CarRegistration s : sl)	{
			tm.addRow(s.getVectoradmin());
		}
		
		table.setModel(tm);
		table.getSelectionModel().addListSelectionListener(listener);
	}


	
	public ArrayList<CarRegistration> listCarRegistration() 
	{
		ArrayList<CarRegistration> s1 = new ArrayList<CarRegistration>();

		String sql = "SELECT * FROM carregistration order by carregistration.ID";
		try {
			// connect to the database
			sd.connectDB();
			stmt = sd.getConnection().createStatement();
			rs = ((java.sql.Statement) stmt).executeQuery(sql);

			while (rs.next())
			{     
				// CarRegistrationNo, model , make, fromDate, toDate

				 
				CarRegistration s = new CarRegistration();
				
				//Get the right type (string) from the right column ("itemId");
				s.setID((rs.getInt("ID")));
				s.setCarRegistrationNo((rs.getString("CarRegistrationNo")));
				s.setModel((rs.getString("model")));
				s.setMake((rs.getString("make")));
				s.setFromDate((rs.getDate("fromDate")));
				s.setToDate((rs.getDate("toDate")));
				s.setPrice((rs.getFloat("price")));
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

	
	public void insertCarRegister(  int customerID,  String customerName, String CarRegistrationNo, String model,
			String make, Date fromDate, Date toDate, Float price) {
		// TODO Auto-generated method stub

		String insertSql = "INSERT INTO carregistration ( customerID,  customerName, CarRegistrationNo,model,make,fromDate,toDate,price ) "
				+ "values (?,?,?,?,?,?,?,?)";

		try {
			sd.connectDB();

			// create statement
			pstmt = sd.getConnection().prepareStatement(insertSql);

			// declare the parameter starting at 1
			pstmt.setInt(1,customerID);
			pstmt.setString(2, customerName);
			pstmt.setString(3, CarRegistrationNo);
			pstmt.setString(4, model);
			pstmt.setString(5, make);
			pstmt.setDate(6, fromDate);
			pstmt.setDate(7, toDate);
			pstmt.setFloat(8, price);

			pstmt.executeUpdate();

			sd.disconnectDB();
		} catch (SQLException sx) {
			System.out.println("Error inserting data into the database");
			System.out.println(sx.getMessage());
			System.out.println(sx.getErrorCode());
			System.out.println(sx.getSQLState());
		}
	}

	//method to get inventory 
		public CarRegistration getcar(int ID) {
				
			CarRegistration ap = new CarRegistration();
				
				String sql = "SELECT * FROM carregistration  WHERE ID  = ?";
				
				try {
					
					//Connect to the database
					sd.connectDB();
					
					//Create the statement
					pstmt = sd.getConnection().prepareStatement(sql);
					
					//Declare the parameter (starting at 1)
					pstmt.setInt(1,ID);
					
					rs = pstmt.executeQuery();
					
					while (rs.next())	{
						
						//Get the right type (string) from the right column ("");									
						ap.setID((rs.getInt("ID")));
						ap.setCarRegistrationNo((rs.getString("CarRegistrationNo")));
						ap.setModel((rs.getString("model")));
						ap.setMake((rs.getString("make")));
						ap.setFromDate((rs.getDate("fromDate")));
						ap.setToDate((rs.getDate("toDate")));
						ap.setPrice((rs.getFloat("price")));				
					} 
					
					sd.disconnectDB();
						
					} catch (SQLException sx) {
						System.out.println("Error Connecting to Database");
						System.out.println(sx.getMessage());
						System.out.println(sx.getErrorCode());
						System.out.println(sx.getSQLState());
						
					}
					return ap;			
				}
		
		
		public void updateCarInventory(CarRegistration su)	{		

			String updateSql = "UPDATE carregistration  SET " + 
					"CarRegistrationNo = ?, " +
					"model = ?, " +
					"make = ?, " +
					"fromDate = ?, " +
					"toDate = ?, " +
					"price = ? " +
					
					"WHERE ID = ?";
			
			//itemId , Item , Type , Quantity , Price , Category , Unitprice
			
			try {
					sd.connectDB();
					
					pstmt = sd.getConnection().prepareStatement(updateSql);
			
					
					//pstmt.setInt(1, su.getVGID());  				
					pstmt.setString(1, su.getCarRegistrationNo());
					pstmt.setString(2, su.getModel());
					pstmt.setString(3, su.getMake());
					pstmt.setDate(4, su.getFromDate());
					pstmt.setDate(5, su.getToDate());
					pstmt.setFloat(6, su.getPrice());
					pstmt.setInt(7, su.getID());  				
					
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
			public void deleteCarInventory(int id) {
				
				String sql = "DELETE FROM carregistration  WHERE ID = ?";
				
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
