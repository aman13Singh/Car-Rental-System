package Boundry;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Main {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
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
	public Main() {
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
		lblCarRegistration.setBounds(10, 160, 155, 34);
		frame.getContentPane().add(lblCarRegistration);
		
		JButton btnCarRegistration = new JButton("Car Registration");
		btnCarRegistration.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CarRegistration.main(null);
			}
		});
		btnCarRegistration.setForeground(SystemColor.activeCaption);
		btnCarRegistration.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnCarRegistration.setBounds(10, 230, 155, 23);
		frame.getContentPane().add(btnCarRegistration);
		
		JLabel lblCustomer = new JLabel("Customer");
		lblCustomer.setForeground(SystemColor.activeCaption);
		lblCustomer.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCustomer.setBounds(167, 160, 103, 34);
		frame.getContentPane().add(lblCustomer);
		
		JButton btnCustomer = new JButton("Customer");
		btnCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Customer.main(null);
			}
		});
		btnCustomer.setForeground(SystemColor.activeCaption);
		btnCustomer.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnCustomer.setBounds(177, 230, 103, 23);
		frame.getContentPane().add(btnCustomer);
		
		JLabel lblRentCar = new JLabel("Rent  Car");
		lblRentCar.setForeground(SystemColor.activeCaption);
		lblRentCar.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblRentCar.setBounds(290, 160, 123, 34);
		frame.getContentPane().add(lblRentCar);
		
		JButton btnRental = new JButton("Rental");
		btnRental.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RentCar.main(null);
			}
		});
		btnRental.setForeground(SystemColor.activeCaption);
		btnRental.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnRental.setBounds(290, 230, 123, 23);
		frame.getContentPane().add(btnRental);
		
		JLabel lblReturnCar = new JLabel("Return Car");
		lblReturnCar.setForeground(SystemColor.activeCaption);
		lblReturnCar.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblReturnCar.setBounds(436, 160, 115, 34);
		frame.getContentPane().add(lblReturnCar);
		
		JButton btnReturnCar = new JButton("Return Car");
		btnReturnCar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReturnCar.main(null);
			}
		});
		btnReturnCar.setForeground(SystemColor.activeCaption);
		btnReturnCar.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnReturnCar.setBounds(436, 230, 115, 23);
		frame.getContentPane().add(btnReturnCar);
		
		JLabel lblWelcome = new JLabel("Welcome ");
		lblWelcome.setForeground(SystemColor.activeCaption);
		lblWelcome.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblWelcome.setBounds(135, 27, 213, 89);
		frame.getContentPane().add(lblWelcome);
		
		JButton btnSignOut = new JButton("Sign Out");
		btnSignOut.setForeground(SystemColor.activeCaption);
		btnSignOut.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnSignOut.setBounds(436, 327, 115, 23);
		frame.getContentPane().add(btnSignOut);
	}
}
