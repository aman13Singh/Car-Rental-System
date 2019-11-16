package Boundry;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Registration {

	private JFrame frame;
	private JTextField customerIDtextField;
	private JTextField customernametextField;
	private JTextField addresstextField;
	private JTextField numbertextField;
	private String customerID = "";
	private String customerName = "";
	private String password = "";
	private String address = "";
	private String number = "";
	private JTextField textField;
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
		
		JLabel lblCustomerId = new JLabel("Customer ID");
		lblCustomerId.setForeground(SystemColor.activeCaption);
		lblCustomerId.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCustomerId.setBounds(39, 68, 128, 24);
		frame.getContentPane().add(lblCustomerId);
		
		JLabel lblName = new JLabel("Name");
		lblName.setForeground(SystemColor.activeCaption);
		lblName.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblName.setBounds(39, 103, 110, 24);
		frame.getContentPane().add(lblName);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setForeground(SystemColor.activeCaption);
		lblAddress.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblAddress.setBounds(39, 138, 97, 24);
		frame.getContentPane().add(lblAddress);
		
		JLabel lblNumber = new JLabel("Number");
		lblNumber.setForeground(new Color(153, 180, 209));
		lblNumber.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNumber.setBounds(39, 179, 90, 14);
		frame.getContentPane().add(lblNumber);
		
		customerIDtextField = new JTextField();
		customerIDtextField.setBounds(168, 70, 140, 20);
		frame.getContentPane().add(customerIDtextField);
		customerIDtextField.setColumns(10);
		
		customernametextField = new JTextField();
		customernametextField.setBounds(168, 105, 140, 20);
		frame.getContentPane().add(customernametextField);
		customernametextField.setColumns(10);
		
		addresstextField = new JTextField();
		addresstextField.setBounds(168, 140, 140, 20);
		frame.getContentPane().add(addresstextField);
		addresstextField.setColumns(10);
		
		numbertextField = new JTextField();
		numbertextField.setBounds(168, 178, 140, 20);
		frame.getContentPane().add(numbertextField);
		numbertextField.setColumns(10);
		
		JButton btnBecomeCustomer = new JButton("Become Customer");
		btnBecomeCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
			}
		});
		btnBecomeCustomer.setForeground(SystemColor.activeCaption);
		btnBecomeCustomer.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnBecomeCustomer.setBounds(115, 275, 193, 23);
		frame.getContentPane().add(btnBecomeCustomer);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setForeground(SystemColor.activeCaption);
		btnCancel.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnCancel.setBounds(343, 309, 97, 23);
		frame.getContentPane().add(btnCancel);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setForeground(SystemColor.activeCaption);
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPassword.setBounds(39, 211, 90, 24);
		frame.getContentPane().add(lblPassword);
		
		textField = new JTextField();
		textField.setForeground(SystemColor.activeCaption);
		textField.setFont(new Font("Tahoma", Font.BOLD, 15));
		textField.setBounds(168, 213, 140, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
	}
}
