package Boundry;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Controller.Authenticate;
import Controller.Validate;
import Entities.UserInfo;

import Entities.UserInfo;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdminLogin {

	private JFrame frame;
	private JTextField adminusernametextField;
	private JTextField adminpasswordtextField;
	
	String username;
	String password;
	
	UserInfo user;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args, UserInfo user) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminLogin window = new AdminLogin(user);
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
	public AdminLogin(UserInfo user) {
		this.user = user;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(72, 81, 77, 25);
		frame.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(72, 117, 59, 25);
		frame.getContentPane().add(lblPassword);
		
		adminusernametextField = new JTextField();
		adminusernametextField.setBounds(141, 83, 91, 20);
		frame.getContentPane().add(adminusernametextField);
		adminusernametextField.setColumns(10);
		
		adminpasswordtextField = new JTextField();
		adminpasswordtextField.setBounds(141, 119, 91, 20);
		frame.getContentPane().add(adminpasswordtextField);
		adminpasswordtextField.setColumns(10);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				username = adminusernametextField.getText();
				password = adminpasswordtextField.getText();
				
				
				//Create an instance of Validate class and pass all the inputs given by the user
				Validate validate = new Validate(username,password);
				
				if(validate.isLoginDataValid()) {
					System.out.println("Admin inputs are valid."); 
					
					//create an instance of Authenticate class to verify userName and password inputs
					Authenticate auth = new Authenticate();
					auth.setCustomerName(username);
					auth.setPassword((password));
						
						if(auth.matchAdminUserName() && auth.matchAdminpassword())
						{
							System.out.println("Login Successful");
							JOptionPane.showMessageDialog(null," Admin Login Successful");
							
							//set user information
							UserInfo user = new UserInfo();
							DBHelper helper = new DBHelper();
							user.setCustomerName(username);
							user.setCustomerID(helper.getUserId(username));
							
							AdminHome.main(null,user);
							frame.dispose();
						}
				}
				  
				
				
				
				/*
				if(username.equals("admin")&& password.equals("@Dmin111")){
					
					manageCars.main(null, user);
		            frame.dispose();
		            
		        }
		        else
		        {
		        	System.out.println("Login Unsuccessful");
					JOptionPane.showMessageDialog(null,"Wrong username or password.");
		        }
				*/
			}
		});
		btnLogin.setBounds(141, 163, 89, 23);
		frame.getContentPane().add(btnLogin);
	}
}
