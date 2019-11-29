package Boundry;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Controller.Authenticate;
import Controller.RegistrationDAO;
import Controller.Validate;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;

public class Registration {

	private JFrame frame;
	private JTextField customernametextField;
	private JTextField addresstextField;
	private JTextField numbertextField;
	private int customerID ;
	private String customerName = "";
	private String address = "";
	private String number = "";
	private String password = "";
	private RegistrationDAO registrationDAO =  new RegistrationDAO();

	private JTextField passwordtextField;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Registration window = new Registration();
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
	public Registration() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 577, 382);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblName = new JLabel("Name");
		lblName.setForeground(SystemColor.activeCaption);
		lblName.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblName.setBounds(39, 82, 110, 24);
		frame.getContentPane().add(lblName);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setForeground(SystemColor.activeCaption);
		lblAddress.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblAddress.setBounds(39, 117, 97, 24);
		frame.getContentPane().add(lblAddress);
		
		JLabel lblNumber = new JLabel("Number");
		lblNumber.setForeground(new Color(153, 180, 209));
		lblNumber.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNumber.setBounds(39, 158, 90, 14);
		frame.getContentPane().add(lblNumber);
		
		customernametextField = new JTextField();
		customernametextField.setBounds(168, 84, 140, 20);
		frame.getContentPane().add(customernametextField);
		customernametextField.setColumns(10);
		
		addresstextField = new JTextField();
		addresstextField.setBounds(168, 119, 140, 20);
		frame.getContentPane().add(addresstextField);
		addresstextField.setColumns(10);
		
		numbertextField = new JTextField();
		numbertextField.setBounds(168, 157, 140, 20);
		frame.getContentPane().add(numbertextField);
		numbertextField.setColumns(10);
		
		JButton btnBecomeCustomer = new JButton("Customer");
		btnBecomeCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				customerName = 	customernametextField.getText();
				address = addresstextField.getText();
				number = numbertextField.getText();
				password =  passwordtextField.getText();
				
				//Create an instance of Validate class and pass all the inputs given by the user
				Validate validate = new Validate(customerName, address , number, password);
				if(validate.isSignUpDataValid())
				{
					System.out.println("All the inputs are valid.");
					Authenticate auth = new Authenticate();
					auth.setCustomerName(customerName);
					if(auth.matchUserName())
					{
						System.out.println("The username already exists, choose a different one.");
						JOptionPane.showMessageDialog(null,"The username already exists, choose a different one.");
					}else
					{
						//store the user inputs in the user_Info table
						registrationDAO.insertNewUser(customerName, address, number, password);
						ArrayList<String> newList = new ArrayList<>();
						newList = registrationDAO.getUserProfile(customerName);
						
						//to see the profile of newly added user
						for(String str: newList)
						{
							System.out.println(str);
						}	
						Login.main(null);
						frame.dispose();
					}
				}
			}	
				
				
			
		});
		btnBecomeCustomer.setForeground(SystemColor.activeCaption);
		btnBecomeCustomer.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnBecomeCustomer.setBounds(168, 233, 140, 23);
		frame.getContentPane().add(btnBecomeCustomer);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// TODO Auto-generated method stub
				customernametextField.setText("");
				addresstextField.setText("");
				numbertextField.setText("");
				passwordtextField.setText("");
				
				
				Login.main(null);
				frame.dispose();
				

			}
		});
		btnCancel.setForeground(SystemColor.activeCaption);
		btnCancel.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnCancel.setBounds(347, 273, 97, 23);
		frame.getContentPane().add(btnCancel);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setForeground(SystemColor.activeCaption);
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPassword.setBounds(39, 190, 90, 24);
		frame.getContentPane().add(lblPassword);
		
		passwordtextField = new JTextField();
		passwordtextField.setForeground(SystemColor.activeCaption);
		passwordtextField.setFont(new Font("Tahoma", Font.BOLD, 15));
		passwordtextField.setBounds(168, 192, 140, 20);
		frame.getContentPane().add(passwordtextField);
		passwordtextField.setColumns(10);
		
		JLabel lblPleaseProvideYour = new JLabel("Please Provide Your Personal Information");
		lblPleaseProvideYour.setForeground(new Color(0, 0, 128));
		lblPleaseProvideYour.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblPleaseProvideYour.setBounds(39, 22, 315, 37);
		frame.getContentPane().add(lblPleaseProvideYour);
	}
}
