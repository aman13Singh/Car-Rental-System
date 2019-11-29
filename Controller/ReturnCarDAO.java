package Controller;

import java.awt.CheckboxGroup;
import java.awt.EventQueue;

import javax.swing.JFrame;

import Boundry.DBHelper;
import Boundry.Main;
import Entities.UserInfo;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JToolBar;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.AbstractListModel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JCheckBox;
import java.awt.Font;
import java.awt.Color;

public class ReturnCarDAO {

	private JFrame frame;
	UserInfo user;
	private JTextField maketextField;
	private JTextField modeltextField;
	private JTextField licencePtextField;

	CheckboxGroup CGLocation  = new CheckboxGroup();  

	String model;
	String make;
	String LicenceNo;
	String location;	
	// model make LicenceNo location
	
	
	private ResultSet rs = null;
	private java.sql.Statement stmt = null;
	private PreparedStatement pstmt = null;

	private DefaultTableModel tm = new DefaultTableModel();
	private DBHelper sd = new DBHelper();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args,UserInfo user  ) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReturnCarDAO window = new ReturnCarDAO(user);
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
	public ReturnCarDAO(UserInfo user) {
		this.user = user;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Tahoma", Font.BOLD, 12));
		frame.getContentPane().setForeground(new Color(0, 0, 128));
		frame.setBounds(100, 100, 748, 414);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton btnback = new JButton(">>>Back");
		btnback.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnback.setForeground(new Color(0, 0, 128));
		btnback.setBounds(333, 318, 114, 23);
		btnback.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.main(null,user);
				frame.dispose();

			}
		});
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(btnback);
		
		JLabel lblMake = new JLabel("Make ");
		lblMake.setForeground(new Color(0, 0, 128));
		lblMake.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblMake.setBounds(41, 65, 46, 14);
		frame.getContentPane().add(lblMake);
		
		JLabel lblModel = new JLabel("Model");
		lblModel.setForeground(new Color(0, 0, 128));
		lblModel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblModel.setBounds(41, 110, 67, 14);
		frame.getContentPane().add(lblModel);
		
		JLabel lblLicencePlate = new JLabel("Licence plate");
		lblLicencePlate.setForeground(new Color(0, 0, 128));
		lblLicencePlate.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblLicencePlate.setBounds(41, 150, 83, 14);
		frame.getContentPane().add(lblLicencePlate);
		
		JLabel lblDropOffLocation = new JLabel("Drop Off Location");
		lblDropOffLocation.setForeground(new Color(0, 0, 128));
		lblDropOffLocation.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDropOffLocation.setBounds(41, 190, 126, 22);
		frame.getContentPane().add(lblDropOffLocation);
		
		
		licencePtextField = new JTextField();
		licencePtextField.setFont(new Font("Tahoma", Font.BOLD, 12));
		licencePtextField.setForeground(new Color(0, 0, 128));
		licencePtextField.setBounds(184, 144, 126, 20);
		frame.getContentPane().add(licencePtextField);
		licencePtextField.setText("");
		licencePtextField.setColumns(10);
		
		modeltextField = new JTextField();
		modeltextField.setFont(new Font("Tahoma", Font.BOLD, 12));
		modeltextField.setForeground(new Color(0, 0, 128));
		modeltextField.setBounds(184, 104, 126, 20);
		frame.getContentPane().add(modeltextField);
		modeltextField.setText("");
		modeltextField.setColumns(10);
		
		maketextField = new JTextField();
		maketextField.setFont(new Font("Tahoma", Font.BOLD, 12));
		maketextField.setForeground(new Color(0, 0, 128));
		maketextField.setBounds(184, 62, 126, 20);
		frame.getContentPane().add(maketextField);
		maketextField.setColumns(10);
		
		JCheckBox chckbxSurrey = new JCheckBox("Surrey");
		chckbxSurrey.setForeground(new Color(0, 0, 128));
		chckbxSurrey.setFont(new Font("Tahoma", Font.BOLD, 12));
		chckbxSurrey.setBounds(184, 190, 97, 23);
		frame.getContentPane().add(chckbxSurrey);
		
		JCheckBox chckbxVancouver = new JCheckBox("Vancouver");
		chckbxVancouver.setForeground(new Color(0, 0, 128));
		chckbxVancouver.setFont(new Font("Tahoma", Font.BOLD, 12));
		chckbxVancouver.setBounds(184, 218, 97, 23);
		frame.getContentPane().add(chckbxVancouver);
		
		JButton btnReturn = new JButton("Return");
		btnReturn.setForeground(new Color(0, 0, 128));
		btnReturn.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				// model make LicenceNo location
			
				model = modeltextField.getText();
				make = maketextField.getText();
				LicenceNo = licencePtextField.getText(); 
				if(chckbxSurrey.isSelected()) {
					location = "Surrey";
				}
				else {
					location = "Vancouver";  	
				}
				
				System.out.print(model);
				System.out.print(make);
				System.out.print(LicenceNo);
				System.out.print(location);
				System.out.print(user.getCustomerID());
				System.out.print(user.getCustomerName());
				
				returnCar(user.getCustomerID(), user.getCustomerName(), model, make, LicenceNo, location);
			   
				JOptionPane.showMessageDialog(null,"Thanks to return vechile.");
				
				
				
			}
		});
		btnReturn.setBounds(184, 259, 126, 30);
		frame.getContentPane().add(btnReturn);
		
		JLabel lblPleaseEnterGiven = new JLabel("Please Enter Given Below Information ");
		lblPleaseEnterGiven.setForeground(new Color(0, 0, 128));
		lblPleaseEnterGiven.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPleaseEnterGiven.setBounds(41, 11, 269, 40);
		frame.getContentPane().add(lblPleaseEnterGiven);
		
	}
	
	
	// model make LicenceNo location
	public void returnCar( int customerID,  String customerName,  String model, String make, String LicenceNo, String location) {
		// TODO Auto-generated method stub

		String insertSql = "INSERT INTO returncar ( customerID,  customerName, model, make, LicenceNo, location) "
				+ "values (?,?,?,?,?,?)";

		try {
			sd.connectDB();

			// create statement
			pstmt = sd.getConnection().prepareStatement(insertSql);

			// declare the parameter starting at 1
			pstmt.setInt(1,customerID);
			pstmt.setString(2, customerName);
			pstmt.setString(3, model);
			pstmt.setString(4, make);
			pstmt.setString(5, LicenceNo);
			pstmt.setString(6, location);
			

			pstmt.executeUpdate();

			sd.disconnectDB();
		} catch (SQLException sx) {
			System.out.println("Error inserting data into the database");
			System.out.println(sx.getMessage());
			System.out.println(sx.getErrorCode());
			System.out.println(sx.getSQLState());
		}
	}
}
