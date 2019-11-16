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

	// CarRegistrationNo, model , make, fromDate, toDate

	DBHelper helper = new DBHelper();
	private ResultSet rs = null;
	private Statement stmt = null;
	private PreparedStatement pstmt = null;

	CarRegistration newCarRegistration = new CarRegistration();
	private JTable table;
	
	private ListSelectionListener listener ;
	private DefaultTableModel tm = new DefaultTableModel();


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
		
		
		
		/**
		 * define listener for table
		 */
		listener = new ListSelectionListener() {
			
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				//updateTable();
			}
			
		};
		
		
		

		JButton btnback = new JButton(">>>Back");
		btnback.setForeground(new Color(0, 0, 128));
		btnback.setFont(new Font("Consolas", Font.BOLD, 14));
		btnback.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Main.main(null, user);
				frame.dispose();

			}
		});
		btnback.setBounds(559, 357, 93, 23);
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
		lblAvaiableDate.setBounds(50, 188, 115, 25);
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
		lblTo.setBounds(195, 232, 46, 14);
		frame.getContentPane().add(lblTo);

		JDateChooser fromdateChooser = new JDateChooser();
		fromdateChooser.setBounds(244, 193, 127, 20);
		frame.getContentPane().add(fromdateChooser);

		JLabel lblFrom = new JLabel("From");
		lblFrom.setForeground(new Color(0, 0, 128));
		lblFrom.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblFrom.setBounds(195, 193, 46, 14);
		frame.getContentPane().add(lblFrom);

		JDateChooser todateChooser = new JDateChooser();
		todateChooser.setBounds(244, 226, 127, 20);
		frame.getContentPane().add(todateChooser);

		JButton btnRegisterCar = new JButton("Register Car");
		btnRegisterCar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				CarRegistrationNo = carRegisNotextField.getText();
				model = modeltextField.getText();
				make = maketextField.getText();
				java.sql.Date fromDate = new java.sql.Date(fromdateChooser.getDate().getTime());
				java.sql.Date toDate = new java.sql.Date(todateChooser.getDate().getTime());

				/*
				 * insertCarRegister(user.getCustomerID(), user.getCustomerName(),
				 * newCarRegistration.getCarRegistrationNo(), newCarRegistration.getModel() ,
				 * newCarRegistration.getMake(), newCarRegistration.getFromDate(),
				 * newCarRegistration.getToDate());
				 */
				// JOptionPane.showMessageDialog(null, "Meeting Reservation confirmed");

				
				insertCarRegister(user.getCustomerID(), user.getCustomerName(),  CarRegistrationNo,
				          model, make, fromDate, toDate);
			}
		});
		btnRegisterCar.setForeground(new Color(0, 0, 128));
		btnRegisterCar.setFont(new Font("Consolas", Font.BOLD, 14));
		btnRegisterCar.setBounds(54, 276, 247, 34);
		frame.getContentPane().add(btnRegisterCar);

		JButton btnChangeInformation = new JButton("Change Information");
		btnChangeInformation.setForeground(new Color(0, 0, 128));
		btnChangeInformation.setFont(new Font("Consolas", Font.BOLD, 14));
		btnChangeInformation.setBounds(54, 321, 247, 34);
		frame.getContentPane().add(btnChangeInformation);

		JButton btnRemoveYourCar = new JButton("Remove Your Car");
		btnRemoveYourCar.setForeground(new Color(0, 0, 128));
		btnRemoveYourCar.setFont(new Font("Consolas", Font.BOLD, 14));
		btnRemoveYourCar.setBounds(308, 324, 247, 29);
		frame.getContentPane().add(btnRemoveYourCar);

		JLabel label = new JLabel("");
		label.setForeground(new Color(0, 0, 128));
		label.setFont(new Font("Candara Light", Font.BOLD, 13));
		label.setBounds(10, 24, 196, 23);
		frame.getContentPane().add(label);
		label.setText(user.getCustomerName());
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(426, 35, 434, 211);
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
		System.out.println(user.getCustomerName());
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
			String make, Date fromDate, Date toDate) {
		// TODO Auto-generated method stub

		String insertSql = "INSERT INTO carregistration ( customerID,  customerName, CarRegistrationNo,model,make,fromDate,toDate ) "
				+ "values (?,?,?,?,?,?,?)";

		try {
			helper.connectDB();

			// create statement
			pstmt = helper.getConnection().prepareStatement(insertSql);

			// declare the parameter starting at 1
			pstmt.setInt(1,customerID);
			pstmt.setString(2, customerName);
			pstmt.setString(3, CarRegistrationNo);
			pstmt.setString(4, model);
			pstmt.setString(5, make);
			pstmt.setDate(6, fromDate);
			pstmt.setDate(7, toDate);

			pstmt.executeUpdate();

			helper.disconnectDB();
		} catch (SQLException sx) {
			System.out.println("Error inserting data into the database");
			System.out.println(sx.getMessage());
			System.out.println(sx.getErrorCode());
			System.out.println(sx.getSQLState());
		}
	}


	/*private void updateTable()	{
	 	table.getSelectionModel().removeListSelectionListener(listener);
		tm = new DefaultTableModel();
		
		//textFieldItem, textFieldType, textFieldQuantity , textFieldPrice , textFieldCategory , textFieldUnitPrice

		//Add the columns
		tm.addColumn("ID");
		tm.addColumn("Item");
		tm.addColumn("Type");
		tm.addColumn("Quantity");
		tm.addColumn("Price");
		tm.addColumn("Category");
		tm.addColumn("UnitPrice");
		
		/*		
		//Add the rows
		ArrayList<AddProperty> sl = new ArrayList<AddProperty>();
		
		//Populate the arraylist with the getShoes
		sl = listAddPropertyInventory();
		
		for (AddProperty s : sl)	{
			tm.addRow(s.getVector());
		}
		
		table.setModel(tm);
		table.getSelectionModel().addListSelectionListener(listener);
	}
*/



}
