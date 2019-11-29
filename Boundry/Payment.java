package Boundry;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Controller.RentCarDAO;
import Entities.CarRegistration;
import Entities.PaymentInfo;
import Entities.RentCar;
import Entities.UserInfo;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Payment {

	private JFrame frame;
	private JTextField CNtextField;
	private JTextField ExpDatetextField;
	private JTextField SCtextField;

	String cardNumber;
	String expiryDate;
	String SecurityCode;
	
	UserInfo user;
	RentCar rentP;
	PaymentInfo payment;
	CarRegistration newCarRegistration;
	
	DBHelper sd = new DBHelper(); 
	private ResultSet rs = null;
	private java.sql.Statement stmt = null;
	private PreparedStatement pstmt = null;
	
	Double fee = 0.0; 
	

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args, UserInfo user, RentCar rentP) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Payment window = new Payment(user, rentP);
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
	public Payment(UserInfo user, RentCar rentP) {
		this.user = user;
		this.rentP = rentP;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setForeground(new Color(0, 0, 51));
		frame.getContentPane().setFont(new Font("Tahoma", Font.BOLD, 12));
		frame.setBounds(100, 100, 491, 385);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblAmountToBe = new JLabel("Amount to be paid:");
		lblAmountToBe.setForeground(new Color(0, 0, 51));
		lblAmountToBe.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblAmountToBe.setBounds(41, 41, 132, 24);
		frame.getContentPane().add(lblAmountToBe);
		
		JLabel lblPaymentMethod = new JLabel("Payment Method:");
		lblPaymentMethod.setForeground(new Color(0, 0, 51));
		lblPaymentMethod.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPaymentMethod.setBounds(41, 76, 148, 24);
		frame.getContentPane().add(lblPaymentMethod);
		
		JLabel lblCard = new JLabel("Card");
		lblCard.setForeground(new Color(0, 0, 51));
		lblCard.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCard.setBounds(168, 81, 46, 14);
		frame.getContentPane().add(lblCard);
		
		JPanel panel = new JPanel();
		panel.setBounds(41, 106, 369, 170);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblEnterYourCard = new JLabel("Enter Your Card Information");
		lblEnterYourCard.setForeground(new Color(0, 0, 51));
		lblEnterYourCard.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblEnterYourCard.setBounds(157, 11, 189, 21);
		panel.add(lblEnterYourCard);
		
		CNtextField = new JTextField();
		CNtextField.setForeground(new Color(0, 0, 51));
		CNtextField.setFont(new Font("Tahoma", Font.BOLD, 12));
		CNtextField.setBounds(157, 43, 172, 20);
		panel.add(CNtextField);
		CNtextField.setColumns(10);
		
		ExpDatetextField = new JTextField();
		ExpDatetextField.setForeground(new Color(0, 0, 51));
		ExpDatetextField.setFont(new Font("Tahoma", Font.BOLD, 12));
		ExpDatetextField.setBounds(157, 71, 172, 21);
		panel.add(ExpDatetextField);
		ExpDatetextField.setColumns(10);
		
		SCtextField = new JTextField();
		SCtextField.setForeground(new Color(0, 0, 51));
		SCtextField.setFont(new Font("Tahoma", Font.BOLD, 12));
		SCtextField.setBounds(157, 103, 86, 20);
		panel.add(SCtextField);
		SCtextField.setColumns(10);
		
		JLabel lblCardNumber = new JLabel("Card Number");
		lblCardNumber.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCardNumber.setForeground(new Color(0, 0, 128));
		lblCardNumber.setBounds(43, 47, 101, 14);
		panel.add(lblCardNumber);
		
		JLabel lblExpiryDate = new JLabel("Expiry Date");
		lblExpiryDate.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblExpiryDate.setForeground(new Color(0, 0, 128));
		lblExpiryDate.setBounds(43, 75, 86, 14);
		panel.add(lblExpiryDate);
		
		JLabel lblSecurityDate = new JLabel("Security Date");
		lblSecurityDate.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSecurityDate.setForeground(new Color(0, 0, 128));
		lblSecurityDate.setBounds(43, 107, 86, 14);
		panel.add(lblSecurityDate);
		
		JButton btnCancel_1 = new JButton("Cancel");
		btnCancel_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				CNtextField.setText(" ");
				ExpDatetextField.setText(" ");
				SCtextField.setText(" ");
				
			}
		});
		btnCancel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCancel_1.setForeground(new Color(0, 0, 128));
		btnCancel_1.setBounds(240, 136, 89, 23);
		panel.add(btnCancel_1);
		
		JButton btnCancel = new JButton("Back");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				RentCarDAO.main(null, user);
				frame.dispose();
				
			}
		});
		btnCancel.setForeground(new Color(0, 0, 51));
		btnCancel.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnCancel.setBounds(321, 300, 89, 23);
		frame.getContentPane().add(btnCancel);
		
		JButton btnPayNow = new JButton("Pay Now");
		btnPayNow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		
				
				  cardNumber = CNtextField.getText(); 
				  expiryDate = ExpDatetextField.getText();
				  SecurityCode = SCtextField.getText();
				 
				
				
				/*payment.setSecurityCode(String.valueOf(SecurityCode));
				
*/				

				
				System.out.print("Card Number " + cardNumber + "\n");
				System.out.print("Expiry Date " + expiryDate + "\n");
				System.out.print("Security Code " + SecurityCode + '\n');

				
				//payment.getCardNumber(), payment.getExpiryDate() , payment.getSecurityCode()
				payment(user.getCustomerID(), user.getCustomerName(), rentP.getFee(), cardNumber,  expiryDate, SecurityCode );
				
				PaymentReceipt.main(null, user, rentP , payment, newCarRegistration);
				frame.dispose();

				/*
				 * payment.setCardNumber((cardNumber)); payment.setExpiryDate((expiryDate));
				 */
			
			}
		});
		btnPayNow.setForeground(new Color(0, 0, 51));
		btnPayNow.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnPayNow.setBounds(204, 300, 89, 23);
		frame.getContentPane().add(btnPayNow);
		
		
		
		
		JLabel amountlabel = new JLabel("");
		amountlabel.setForeground(new Color(0, 0, 128));
		amountlabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		amountlabel.setBounds(168, 47, 106, 14);
		amountlabel.setText(Double.toString(rentP.getFee()));
		frame.getContentPane().add(amountlabel);
		
		JLabel userlabel = new JLabel("");
		userlabel.setForeground(new Color(0, 0, 128));
		userlabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		userlabel.setBounds(352, 11, 113, 24);
		frame.getContentPane().add(userlabel);
		userlabel.setText(user.getCustomerName());


	}


	public void payment(int customerID,  String customerName, double amount, String CardNumber, String ExpiryDate, String SecurityCode)	{
		
		String insertSql = "INSERT INTO payment (customerID, customerName, amount,  CardNumber, ExpiryDate, SecurityCode)"
				+ "values (?,?,?,?,?,?)";
		
	try {
		
		//Connect to the database
		sd.connectDB();
		
		// create statement
		pstmt = sd.getConnection().prepareStatement(insertSql);

		// declare the parameter starting at 1
		pstmt.setInt(1,customerID);
		pstmt.setString(2, customerName);
		pstmt.setDouble(3, amount);
		pstmt.setString(4, CardNumber);
		pstmt.setString(5, ExpiryDate);
		pstmt.setString(6, SecurityCode);

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
