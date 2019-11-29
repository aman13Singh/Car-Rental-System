package Boundry;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import Controller.CarRegistrationDAO;
import Controller.CustomerDAO;
import Controller.RentCarDAO;
import Controller.ReturnCarDAO;
import Entities.UserInfo;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Main {

	private JFrame frame;
	UserInfo user;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args , UserInfo user) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main(user);
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
	public Main(UserInfo user) {
		this.user = user;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setForeground(SystemColor.activeCaption);
		frame.getContentPane().setFont(new Font("Tahoma", Font.BOLD, 15));
		frame.setBounds(100, 100, 613, 431);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblCarRegistration = new JLabel("Car Registration");
		lblCarRegistration.setForeground(SystemColor.activeCaption);
		lblCarRegistration.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCarRegistration.setBounds(46, 136, 155, 83);
		frame.getContentPane().add(lblCarRegistration);
		
		JButton btnCarRegistration = new JButton("Car Registration");
		btnCarRegistration.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CarRegistrationDAO.main(null, user);
			}
		});
		btnCarRegistration.setForeground(SystemColor.activeCaption);
		btnCarRegistration.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnCarRegistration.setBounds(36, 230, 165, 23);
		frame.getContentPane().add(btnCarRegistration);
		
		JLabel lblRentCar = new JLabel("Rent  Car");
		lblRentCar.setForeground(SystemColor.activeCaption);
		lblRentCar.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblRentCar.setBounds(253, 136, 123, 83);
		frame.getContentPane().add(lblRentCar);
		
		JButton btnRental = new JButton("Rental");
		btnRental.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RentCarDAO.main(null, user);
			}
		});
		btnRental.setForeground(SystemColor.activeCaption);
		btnRental.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnRental.setBounds(253, 230, 123, 23);
		frame.getContentPane().add(btnRental);
		
		JLabel lblReturnCar = new JLabel("Return Car");
		lblReturnCar.setForeground(SystemColor.activeCaption);
		lblReturnCar.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblReturnCar.setBounds(417, 136, 115, 83);
		frame.getContentPane().add(lblReturnCar);
		
		JButton btnReturnCar = new JButton("Return Car");
		btnReturnCar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReturnCarDAO.main(null, user);
			}
		});
		btnReturnCar.setForeground(SystemColor.activeCaption);
		btnReturnCar.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnReturnCar.setBounds(417, 230, 115, 23);
		frame.getContentPane().add(btnReturnCar);
		
		JLabel lblWelcome = new JLabel("Welcome ");
		lblWelcome.setForeground(SystemColor.activeCaption);
		lblWelcome.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblWelcome.setBounds(36, 11, 213, 89);
		frame.getContentPane().add(lblWelcome);
		lblWelcome.setText("Welcome " + user.getCustomerName());
		System.out.println(user.getCustomerName());

		
		JButton btnSignOut = new JButton("Sign Out");
		btnSignOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Login.main(null);
				frame.dispose();
			}
		});
		btnSignOut.setForeground(SystemColor.activeCaption);
		btnSignOut.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnSignOut.setBounds(432, 327, 115, 23);
		frame.getContentPane().add(btnSignOut);
	}
}
