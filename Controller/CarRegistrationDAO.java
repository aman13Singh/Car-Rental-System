package Controller;

import java.awt.EventQueue;

import javax.swing.JFrame;

import Boundry.DBHelper;
import Boundry.Main;
import Entities.CarRegistration;
import Entities.UserInfo;

import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JToolBar;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import com.jgoodies.forms.factories.DefaultComponentFactory;
import com.mysql.cj.xdevapi.Statement;
import com.toedter.calendar.JDateChooser;
import java.awt.Font;
import java.awt.Color;
import com.toedter.calendar.JCalendar;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class CarRegistrationDAO {

	private JFrame frame;
	UserInfo user;
	private JTextField carRegisNotextField;
	private JTextField modeltextField;
	private JTextField maketextField;
	private String CarRegistrationNo;
	private String model;
	private String make;
	private Date fromDate;
	private Date toDate;
	private float price;
	
	
	JDateChooser fromdateChooser;
	JDateChooser todateChooser;
	
	
	// carRegisNotextField, modeltextField , maketextField, fromDate, toDate pricetextField

	private ResultSet rs = null;
	private java.sql.Statement stmt = null;
	private PreparedStatement pstmt = null;

	private DefaultTableModel tm = new DefaultTableModel();
	private DBHelper sd = new DBHelper();
	
	CarRegistration newCarRegistration = new CarRegistration();

	private JTable table;
	private ListSelectionListener listener ;
	private JTextField pricetextField;
	private JTextField IDtextField;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args, UserInfo user) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CarRegistrationDAO window = new CarRegistrationDAO(user);
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
	public CarRegistrationDAO(UserInfo user) {
		this.user = user;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setForeground(new Color(0, 0, 128));
		frame.getContentPane().setFont(new Font("Consolas", Font.BOLD, 14));
		frame.setBounds(100, 100, 1073, 430);
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
		
		
		

		JButton btnback = new JButton("<<<Back");
		btnback.setForeground(new Color(0, 0, 128));
		btnback.setFont(new Font("Consolas", Font.BOLD, 14));
		btnback.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Main.main(null, user);
				frame.dispose();

			}
		});
		btnback.setBounds(867, 352, 93, 23);
		frame.getContentPane().add(btnback);

		JLabel lblCarRegistrationNo = new JLabel("Car Registration No");
		lblCarRegistrationNo.setForeground(new Color(0, 0, 128));
		lblCarRegistrationNo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCarRegistrationNo.setBounds(10, 69, 155, 23);
		frame.getContentPane().add(lblCarRegistrationNo);

		JLabel lblModel = new JLabel("Model");
		lblModel.setForeground(new Color(0, 0, 128));
		lblModel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblModel.setBounds(108, 107, 57, 25);
		frame.getContentPane().add(lblModel);

		JLabel lblMake = new JLabel("Make");
		lblMake.setForeground(new Color(0, 0, 128));
		lblMake.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblMake.setBounds(108, 143, 46, 34);
		frame.getContentPane().add(lblMake);

		JLabel lblAvaiableDate = new JLabel("Avaiable Date");
		lblAvaiableDate.setForeground(new Color(0, 0, 128));
		lblAvaiableDate.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblAvaiableDate.setBounds(39, 220, 115, 25);
		frame.getContentPane().add(lblAvaiableDate);

		carRegisNotextField = new JTextField();
		carRegisNotextField.setBounds(206, 70, 165, 20);
		frame.getContentPane().add(carRegisNotextField);
		carRegisNotextField.setColumns(10);

		modeltextField = new JTextField();
		modeltextField.setBounds(206, 105, 165, 20);
		frame.getContentPane().add(modeltextField);
		modeltextField.setColumns(10);

		maketextField = new JTextField();
		maketextField.setText("");
		maketextField.setBounds(206, 146, 165, 20);
		frame.getContentPane().add(maketextField);
		maketextField.setColumns(10);

		JLabel lblTo = DefaultComponentFactory.getInstance().createLabel("To");
		lblTo.setForeground(new Color(0, 0, 128));
		lblTo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTo.setBounds(193, 259, 46, 14);
		frame.getContentPane().add(lblTo);

		fromdateChooser = new JDateChooser();
		fromdateChooser.setBounds(242, 220, 127, 20);
		frame.getContentPane().add(fromdateChooser);

		JLabel lblFrom = new JLabel("From");
		lblFrom.setForeground(new Color(0, 0, 128));
		lblFrom.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblFrom.setBounds(186, 220, 46, 14);
		frame.getContentPane().add(lblFrom);

		todateChooser = new JDateChooser();
		todateChooser.setBounds(242, 253, 127, 20);
		frame.getContentPane().add(todateChooser);

		JButton btnRegisterCar = new JButton("Register Car");
		btnRegisterCar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				CarRegistrationNo = carRegisNotextField.getText();
				model = modeltextField.getText();
				make = maketextField.getText();
				java.sql.Date fromDate = new java.sql.Date(fromdateChooser.getDate().getTime());
				java.sql.Date toDate = new java.sql.Date(todateChooser.getDate().getTime());
                price = Float.valueOf(pricetextField.getText()).floatValue();
				/*
				 * insertCarRegister(user.getCustomerID(), user.getCustomerName(),
				 * newCarRegistration.getCarRegistrationNo(), newCarRegistration.getModel() ,
				 * newCarRegistration.getMake(), newCarRegistration.getFromDate(),
				 * newCarRegistration.getToDate());
				 */
				// JOptionPane.showMessageDialog(null, "Meeting Reservation confirmed");

				
				insertCarRegister(user.getCustomerID(), user.getCustomerName(),  CarRegistrationNo,
				          model, make, fromDate, toDate, price);
			
				updateTable();			
			
			}
		});
		btnRegisterCar.setForeground(new Color(0, 0, 128));
		btnRegisterCar.setFont(new Font("Consolas", Font.BOLD, 14));
		btnRegisterCar.setBounds(32, 346, 247, 34);
		frame.getContentPane().add(btnRegisterCar);

		JButton btnChangeInformation = new JButton("Change Information");
		btnChangeInformation.addActionListener(new ActionListener() {
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
		btnChangeInformation.setForeground(new Color(0, 0, 128));
		btnChangeInformation.setFont(new Font("Consolas", Font.BOLD, 14));
		btnChangeInformation.setBounds(300, 346, 247, 34);
		frame.getContentPane().add(btnChangeInformation);

		JButton btnRemoveYourCar = new JButton("Remove Your Car");
		btnRemoveYourCar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				deleteCarInventory(Integer.parseInt(IDtextField.getText()));
				updateTable();
				
			}
		});
		btnRemoveYourCar.setForeground(new Color(0, 0, 128));
		btnRemoveYourCar.setFont(new Font("Consolas", Font.BOLD, 14));
		btnRemoveYourCar.setBounds(569, 346, 247, 34);
		frame.getContentPane().add(btnRemoveYourCar);

		JLabel label = new JLabel("");
		label.setForeground(new Color(0, 0, 128));
		label.setFont(new Font("Candara Light", Font.BOLD, 13));
		label.setBounds(10, 11, 196, 23);
		frame.getContentPane().add(label);
		label.setText(user.getCustomerName());
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(454, 41, 534, 238);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
				}
			));
		table.setBackground(Color.WHITE);
		scrollPane.setViewportView(table);
		
		JLabel lblPrice = new JLabel("Price");
		lblPrice.setForeground(new Color(0, 0, 128));
		lblPrice.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPrice.setBounds(108, 188, 57, 23);
		frame.getContentPane().add(lblPrice);
		
		pricetextField = new JTextField();
		pricetextField.setBounds(206, 177, 86, 20);
		frame.getContentPane().add(pricetextField);
		pricetextField.setColumns(10);
		
		IDtextField = new JTextField();
		IDtextField.setBounds(206, 39, 86, 20);
		frame.getContentPane().add(IDtextField);
		IDtextField.setColumns(10);
		
		JLabel lblId = new JLabel("ID");
		lblId.setForeground(new Color(0, 0, 128));
		lblId.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblId.setBounds(108, 44, 46, 14);
		frame.getContentPane().add(lblId);
		System.out.println(user.getCustomerName());
		
		updateTable();

	}

	/**
	 * @author Amanpreet Singh
	 * @param customerID         id of customer from register table
	 * @param customerName       customer name
	 * @param CarRegistrationNo, register ur car
	 * @param model,             car model
	 * @param make,              make car
	 * @param fromDate,          From available date
	 * @param toDate,            To available date
	 */

	// CarRegistrationNo, model , make, fromDate, toDate

	public void insertCarRegister( int customerID,  String customerName, String CarRegistrationNo, String model,
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
}
