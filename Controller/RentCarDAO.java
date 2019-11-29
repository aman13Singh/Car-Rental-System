
package Controller;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;

import Boundry.DBHelper;
import Boundry.Main;
import Boundry.Payment;
import Entities.CarRegistration;
import Entities.RentCar;
import Entities.UserInfo;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JScrollPane;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import com.toedter.calendar.JDateChooser;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RentCarDAO {

	private JFrame frame;
	UserInfo user;
	private JTable table;
	private Double toatlPrice = 0.0;
	private Double eachCarRentPerDay = 0.0;
	long difference = 0;
	private int days = 0;
	private long sec;
	private long min;
	private long hours;
	String Price;
	String currMake;

	
	// CarRegistrationNo, model , make, fromDate, toDate

	private ResultSet rs = null;
	private java.sql.Statement stmt = null;
	private PreparedStatement pstmt = null;

	PreparedStatement pstmt1;
	PreparedStatement pstmt2;
	PreparedStatement pstmt3;
	PreparedStatement pstmt4;

	private DefaultTableModel tm = new DefaultTableModel();
	private DBHelper sd = new DBHelper();
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

	CarRegistration newCarRegistration = new CarRegistration();
	RentCar rentP = new RentCar();

	private ListSelectionListener listener;
	private JTextField searchtextField;
	private JTextField DaystextField;
	JLabel PricetextField ;
	/*
	 * Date d1 = null; Date d2 = null;
	 */

	/**
	 * Launch the application.
	 */
	public static void main(String[] args, UserInfo user) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RentCarDAO window = new RentCarDAO(user);
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
	public RentCarDAO(UserInfo user) {
		this.user = user;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 680, 431);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		/**
		 * define listener for table
		 */
		listener = new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				
				String currId =  table.getValueAt(table.getSelectedRow(),2).toString();
				PricetextField.setText((currId));
				
				currMake =  table.getValueAt(table.getSelectedRow(),0).toString();
				 newCarRegistration.setMake(currMake);
				
                System.out.print("Selected Car: "+ newCarRegistration.getMake());		
				
				updateTable();
				
			}

		};

		
		
		JButton btnback = new JButton(">>>Back");
		btnback.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnback.setForeground(new Color(0, 0, 128));
		btnback.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.main(null, user);
				frame.dispose();

			}
		});
		btnback.setBounds(530, 331, 103, 36);
		frame.getContentPane().add(btnback);

		JLabel Namelabel = new JLabel("");
		Namelabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		Namelabel.setForeground(new Color(0, 0, 128));
		Namelabel.setBounds(530, 21, 124, 36);
		frame.getContentPane().add(Namelabel);
		Namelabel.setText(user.getCustomerName());

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			
			int index  = table.getSelectedRow();
			tm = (DefaultTableModel) table.getModel();
				/*
				 * PricetextField.setText(tm.getValueAt(index, 3).toString()); Price =
				 * PricetextField.getText().toString();
				 */
			
			}
		});
		scrollPane.setBounds(25, 109, 452, 114);
		frame.getContentPane().add(scrollPane);

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] {}));
		table.setBackground(Color.WHITE);
		scrollPane.setViewportView(table);

		JLabel lblSearchVechile = new JLabel("Search Vechile :");
		lblSearchVechile.setForeground(new Color(0, 0, 128));
		lblSearchVechile.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSearchVechile.setBounds(25, 58, 111, 23);
		frame.getContentPane().add(lblSearchVechile);

		searchtextField = new JTextField();
		searchtextField.setBounds(146, 61, 232, 20);
		frame.getContentPane().add(searchtextField);
		searchtextField.setColumns(10);

		JButton btnGo = new JButton("Go");
		btnGo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				searchUpdateTable();
			}
		});
		btnGo.setBounds(388, 60, 89, 23);
		frame.getContentPane().add(btnGo);

		JLabel lblDate = new JLabel("Date ");
		lblDate.setForeground(new Color(0, 0, 128));
		lblDate.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDate.setBounds(25, 257, 46, 14);
		frame.getContentPane().add(lblDate);

		JLabel lblFrom = new JLabel("From");
		lblFrom.setForeground(new Color(0, 0, 128));
		lblFrom.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblFrom.setBounds(72, 257, 46, 14);
		frame.getContentPane().add(lblFrom);

		JDateChooser fromdateChooser = new JDateChooser();
		fromdateChooser.setBounds(117, 251, 97, 20);
		frame.getContentPane().add(fromdateChooser);
		fromdateChooser.setDateFormatString("yyyy-MM-dd");


		JLabel lblTo = new JLabel("To");
		lblTo.setForeground(new Color(0, 0, 128));
		lblTo.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTo.setBounds(237, 257, 46, 14);
		frame.getContentPane().add(lblTo);

		JDateChooser todateChooser = new JDateChooser();
		todateChooser.setBounds(275, 251, 111, 20);
		frame.getContentPane().add(todateChooser);
		System.out.println(user.getCustomerName());
		todateChooser.setDateFormatString("yyyy-MM-dd");

		JLabel lblNumberOfDays = new JLabel("Number Of Days");
		lblNumberOfDays.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNumberOfDays.setForeground(new Color(0, 0, 128));
		lblNumberOfDays.setBounds(25, 297, 124, 23);
		frame.getContentPane().add(lblNumberOfDays);

		DaystextField = new JTextField();
		DaystextField.setText("0");
		DaystextField.setBounds(159, 299, 71, 20);
		frame.getContentPane().add(DaystextField);
		DaystextField.setColumns(10);

		JButton btnCheckout = new JButton("Checkout");
		btnCheckout.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnCheckout.setForeground(new Color(0, 0, 128));
		btnCheckout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				
			}
		});
		btnCheckout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				days = Integer.parseInt(DaystextField.getText());
				eachCarRentPerDay = Double.valueOf(PricetextField.getText());
				
				toatlPrice = (Double) (days * eachCarRentPerDay);
				Price = Double.toString(toatlPrice);
				
				rentP.setFee(toatlPrice);
				
				java.sql.Date fromDate = new java.sql.Date(fromdateChooser.getDate().getTime());
				java.sql.Date toDate = new java.sql.Date(todateChooser.getDate().getTime());

				
				
				rentCar(user.getCustomerID(), currMake,   toatlPrice,  fromDate, toDate, days);
				
				Payment.main(null, user, rentP );
				System.out.println(days);
	            System.out.println(eachCarRentPerDay);
	            System.out.println(Price);
	            System.out.println(currMake);
	            System.out.print("TOtal rent : " + rentP.getFee());
	            
				frame.dispose();


			}
		});	
		btnCheckout.setBounds(25, 331, 111, 36);
		frame.getContentPane().add(btnCheckout);
		
		PricetextField = new JLabel("");
		PricetextField.setBounds(352, 297, 71, 23);
		frame.getContentPane().add(PricetextField);
		
		JLabel lblPricevech = new JLabel("Price/Vech");
		lblPricevech.setForeground(new Color(0, 0, 128));
		lblPricevech.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPricevech.setBounds(281, 302, 71, 14);
		frame.getContentPane().add(lblPricevech);
		
		JLabel lblRentYourFavourite = new JLabel("Rent your Favourite Vechile");
		lblRentYourFavourite.setForeground(new Color(0, 0, 128));
		lblRentYourFavourite.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblRentYourFavourite.setBounds(25, 21, 205, 26);
		frame.getContentPane().add(lblRentYourFavourite);
		System.out.println(Price);
		
		updateTable();
        System.out.print("TOtal rent : " + rentP.getFee());
	}

	private Date myFormat(JDateChooser fromdateChooser) {
		// TODO Auto-generated method stub
		return null;
	}

	private void updateTable() {
		table.getSelectionModel().removeListSelectionListener(listener);
		tm = new DefaultTableModel();

		// textFieldItem, textFieldType, textFieldQuantity , textFieldPrice ,
		// textFieldCategory , textFieldUnitPrice

		// Add the columns
		// tm.addColumn("RegNo");
		tm.addColumn("Model");
		tm.addColumn("Make");
		tm.addColumn("Price");
		// tm.addColumn("FromDate");
		// tm.addColumn("ToDate");

		// Add the rows
		ArrayList<CarRegistration> sl = new ArrayList<CarRegistration>();

		// Populate the arraylist with the getShoes
		sl = listCarRegistration();

		for (CarRegistration s : sl) {
			tm.addRow(s.getVectSel());
		}

		table.setModel(tm);
		table.getSelectionModel().addListSelectionListener(listener);
	}
	public ArrayList<CarRegistration> listCarRegistration() {
		ArrayList<CarRegistration> s1 = new ArrayList<CarRegistration>();

		String sql = "SELECT * FROM carregistration order by carregistration.ID";
		try {
			// connect to the database
			sd.connectDB();
			stmt = sd.getConnection().createStatement();
			rs = ((java.sql.Statement) stmt).executeQuery(sql);

			while (rs.next()) {
				// CarRegistrationNo, model , make, fromDate, toDate

				CarRegistration s = new CarRegistration();

				// Get the right type (string) from the right column ("itemId");
				// s.setCarRegistrationNo((rs.getString("CarRegistrationNo")));
				s.setModel((rs.getString("model")));
				s.setMake((rs.getString("make")));
				s.setPrice((rs.getFloat("price")));

				// s.setFromDate((rs.getDate("fromDate")));
				// s.setToDate((rs.getDate("toDate")));

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

	
    private void searchUpdateTable() {
		table.getSelectionModel().removeListSelectionListener(listener);
		tm = new DefaultTableModel();

		// Add the columns
		// tm.addColumn("RegNo");
		tm.addColumn("Model");
		tm.addColumn("Make");
		tm.addColumn("Price");
		// tm.addColumn("FromDate");
		// tm.addColumn("ToDate");

		// Add the rows
		ArrayList<CarRegistration> sl = new ArrayList<CarRegistration>();

		// Populate the arraylist with the getShoes
		sl = searchCar();

		for (CarRegistration s : sl) {
			tm.addRow(s.getVectSel());
		}

		table.setModel(tm);
		table.getSelectionModel().addListSelectionListener(listener);
	}

	public ArrayList<CarRegistration> searchCar() {
		
		ArrayList<CarRegistration> s1 = new ArrayList<CarRegistration>();

		// select * from carregistration where model = 'sdcds4' ;
		String model = searchtextField.getText();
		String sql = "SELECT * FROM carregistration where model = ?";
		try {
			// connect to the database
			sd.connectDB();

			// Create the statement
			pstmt = sd.getConnection().prepareStatement(sql);

			// Declare the parameter (starting at 1)
			pstmt.setString(1, model);

			rs = pstmt.executeQuery();

			/*
			 * stmt = sd.getConnection().createStatement(); // stmt.setString(1, model); rs
			 * = ((java.sql.Statement) stmt).executeQuery(sql);
			 */

			while (rs.next()) {
				// CarRegistrationNo, model , make, fromDate, toDate

				CarRegistration s = new CarRegistration();

				// Get the right type (string) from the right column ("itemId");
				// s.setCarRegistrationNo((rs.getString("CarRegistrationNo")));
				s.setModel((rs.getString("model")));
				s.setMake((rs.getString("make")));
				s.setPrice((rs.getFloat("price")));

				// s.setFromDate((rs.getDate("fromDate")));
				// s.setToDate((rs.getDate("toDate")));

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
	
	
    public CarRegistration getPrice(String CarRegistrationNo) {
		
		CarRegistration cr = new CarRegistration();
			
			String sql = "SELECT * FROM carregistration  WHERE CarRegistrationNo  = ?";
			
			try {
				
				//Connect to the database
				sd.connectDB();
				
				//Create the statement
				pstmt = sd.getConnection().prepareStatement(sql);
				
				//Declare the parameter (starting at 1)
				pstmt.setString(1,CarRegistrationNo);
				
				rs = pstmt.executeQuery();
				
				while (rs.next())	{
					
					//Get the right type (string) from the right column ("");									
					cr.setCarRegistrationNo((rs.getString("CarRegistrationNo")));					
					cr.setModel((rs.getString("model")));
					cr.setMake((rs.getString("make")));
					cr.setFromDate((rs.getDate("fromDate")));
					cr.setToDate((rs.getDate("toDate")));
					cr.setPrice((rs.getFloat("price")));				
				} 
				
				sd.disconnectDB();
					
				} catch (SQLException sx) {
					System.out.println("Error Connecting to Database");
					System.out.println(sx.getMessage());
					System.out.println(sx.getErrorCode());
					System.out.println(sx.getSQLState());
					
				}
				return cr;			
			}
	
	public String CheckPrice() {

		toatlPrice = (Double) ((days) * (eachCarRentPerDay));
		String FinalPrice = Double.toString(toatlPrice);

		return FinalPrice;
	}

	public void rentCar(int customerID,  String currMake, Double fee, java.sql.Date fromDate, java.sql.Date toDate, int days)	{
		
			String insertSql = "INSERT INTO rental (customerID, currMake, fee, fromDate, toDate, days)"
					+ "values (?,?,?,?,?,?)";
			
		try {
			
			//Connect to the database
			sd.connectDB();
			
			// create statement
			pstmt = sd.getConnection().prepareStatement(insertSql);

			// declare the parameter starting at 1
			pstmt.setInt(1,customerID);
			pstmt.setString(2, currMake);
			pstmt.setDouble(3, fee);
			pstmt.setDate(4, fromDate);
			pstmt.setDate(5, toDate);
			pstmt.setInt(6, days);

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
