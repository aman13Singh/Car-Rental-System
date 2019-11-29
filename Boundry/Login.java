package Boundry;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Controller.Authenticate;
import Controller.Validate;
import Entities.UserInfo;

import java.awt.SystemColor;
import javax.swing.JMenuBar;

public class Login {

	private JFrame frame;
	private JTextField usernametextField;
	private JTextField passwordtextField;
	private String userName = "";
	private String password = "";

	UserInfo user;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
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
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 638, 401);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblUsername.setForeground(SystemColor.activeCaption);
		lblUsername.setBounds(172, 107, 80, 31);
		frame.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPassword.setForeground(SystemColor.activeCaption);
		lblPassword.setBounds(172, 149, 80, 41);
		frame.getContentPane().add(lblPassword);
		
		usernametextField = new JTextField();
		usernametextField.setBounds(262, 110, 148, 26);
		frame.getContentPane().add(usernametextField);
		usernametextField.setColumns(10);
		
		passwordtextField = new JTextField();
		passwordtextField.setBounds(262, 159, 148, 26);
		frame.getContentPane().add(passwordtextField);
		passwordtextField.setColumns(10);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				userName = usernametextField.getText();
				password = passwordtextField.getText();
				
				

				  //Create an instance of Validate class and pass all the inputs given by the user
				  Validate validate = new Validate(userName,password);
				  
				  if(validate.isLoginDataValid()) 
				  {
					  System.out.println("All inputs are valid."); 
					  
					  //create an instance of Authenticate class to verify userName and password inputs
					  Authenticate auth = new Authenticate();
						auth.setCustomerName(userName);
						auth.setPassword(password);
						System.out.println("P:  "+ auth.getPassword());
						if(auth.matchUserName() && auth.matchpassword())
						{
							System.out.println("Login Successful");
							JOptionPane.showMessageDialog(null,"Login Successful");
							
							//set user information
							UserInfo user = new UserInfo();
							DBHelper helper = new DBHelper();
							user.setCustomerName(userName);
							user.setCustomerID(helper.getUserId(userName));
						
							//reservation class to be called upon successful login 
						/*	if(userName == "admin" && password == "@Dmin111") {
							manageCars.main(null, user);
							}
							*/
							
							
							Main.main(null, user);
							frame.dispose();
							
						}/*else if(username.equals("admin")&& password.equals("admin1")){
							
							manageCars.main(null);
				            frame.dispose();
				            
				        }*/else
						{
							System.out.println("Login Unsuccessful");
							JOptionPane.showMessageDialog(null,"Wrong username or password.");
						}
							
				  }
				
				
				/* if(userName.equals("csis")&& password.equals("123"))
					   
			        {
			            Main.main(null);
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
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnLogin.setForeground(SystemColor.activeCaption);
		btnLogin.setBounds(262, 232, 89, 23);
		frame.getContentPane().add(btnLogin);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				usernametextField.setText("");
				passwordtextField.setText("");
				
			}
		});
		btnCancel.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnCancel.setForeground(SystemColor.activeCaption);
		btnCancel.setBounds(381, 232, 89, 23);
		frame.getContentPane().add(btnCancel);
		
		JLabel lblNotAMember = new JLabel("Not a member...");
		lblNotAMember.setForeground(SystemColor.activeCaption);
		lblNotAMember.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNotAMember.setBounds(172, 295, 129, 26);
		frame.getContentPane().add(lblNotAMember);
		
		JButton btnSignUp = new JButton("Sign up");
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Registration.main(null);
				frame.dispose();
			}
		});
		btnSignUp.setForeground(SystemColor.activeCaption);
		btnSignUp.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnSignUp.setBounds(308, 295, 89, 23);
		frame.getContentPane().add(btnSignUp);
		
		JButton adminLoginbtn = new JButton("Admin ");
		adminLoginbtn.setFont(new Font("Tahoma", Font.BOLD, 13));
		adminLoginbtn.setForeground(new Color(0, 0, 128));
		adminLoginbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				AdminLogin.main(null, user);
			}
		});
		adminLoginbtn.setBounds(523, 11, 89, 23);
		frame.getContentPane().add(adminLoginbtn);
	
		
		
		
		
	}
}
