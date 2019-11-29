package Boundry;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Entities.CarRegistration;
import Entities.PaymentInfo;
import Entities.RentCar;
import Entities.UserInfo;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextArea;

public class PaymentReceipt {

	private JFrame frame;
	UserInfo user;
	RentCar rentP;
	PaymentInfo payment;
	CarRegistration newCarRegistration;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args, UserInfo user,RentCar rentP, PaymentInfo payment, CarRegistration newCarRegistration) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PaymentReceipt window = new PaymentReceipt(user, rentP, payment, newCarRegistration);
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
	public PaymentReceipt(UserInfo user,RentCar rentP, PaymentInfo payment, CarRegistration newCarRegistration) {
		
		this.user = user;
		this.rentP = rentP;
		this.payment = payment;
		this.newCarRegistration = newCarRegistration;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 661, 409);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblCustomerReceipt = new JLabel("Customer Receipt ");
		lblCustomerReceipt.setForeground(new Color(0, 0, 51));
		lblCustomerReceipt.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCustomerReceipt.setBounds(270, 22, 124, 29);
		frame.getContentPane().add(lblCustomerReceipt);
		
		JLabel lblPayer = new JLabel("Payer :");
		lblPayer.setForeground(new Color(0, 0, 51));
		lblPayer.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPayer.setBounds(73, 316, 74, 14);
		frame.getContentPane().add(lblPayer);
		
		JLabel lblAmountPaid = new JLabel("Amount Paid :");
		lblAmountPaid.setForeground(new Color(0, 0, 51));
		lblAmountPaid.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblAmountPaid.setBounds(73, 278, 115, 14);
		frame.getContentPane().add(lblAmountPaid);
		
		JLabel lblPaymentMode = new JLabel("Payment Mode :");
		lblPaymentMode.setForeground(new Color(0, 0, 51));
		lblPaymentMode.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPaymentMode.setBounds(73, 238, 115, 14);
		frame.getContentPane().add(lblPaymentMode);

		
		JLabel lblDate = new JLabel("Date");
		lblDate.setForeground(new Color(0, 0, 51));
		lblDate.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDate.setBounds(484, 29, 46, 14);
		frame.getContentPane().add(lblDate);
		
		JLabel lblTime = new JLabel("Time");
		lblTime.setForeground(new Color(0, 0, 51));
		lblTime.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTime.setBounds(484, 66, 46, 14);
		frame.getContentPane().add(lblTime);
		
		JLabel payerlabel = new JLabel("");
		payerlabel.setForeground(new Color(0, 0, 51));
		payerlabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		payerlabel.setBounds(181, 316, 104, 14);
		frame.getContentPane().add(payerlabel);
		payerlabel.setText(user.getCustomerName());
		
		
		
		JLabel Amountlbl = new JLabel("");
		Amountlbl.setForeground(new Color(0, 0, 51));
		Amountlbl.setFont(new Font("Tahoma", Font.BOLD, 12));
		Amountlbl.setHorizontalAlignment(SwingConstants.CENTER);
		Amountlbl.setBounds(181, 278, 84, 14);
		frame.getContentPane().add(Amountlbl);
		Amountlbl.setText(rentP.getFee().toString());
		
		
		JLabel lblCash = new JLabel("Debit Card Sale");
		lblCash.setForeground(new Color(0, 0, 51));
		lblCash.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCash.setBounds(198, 238, 124, 14);
		frame.getContentPane().add(lblCash);
		
		JTextArea txtrSale = new JTextArea();
		txtrSale.setText("     App Monstor!\r\n     700 Royal Ave \r\n  New Westminster\r\n            BC \r\n      V3M 5Z5                                                              \r\n    778-385-2427\r\n                                                        \r\n         Sale!  \r\n");
		txtrSale.setBounds(231, 62, 196, 147);
		frame.getContentPane().add(txtrSale);
		
		JLabel datelabel = new JLabel("");
		datelabel.setBounds(540, 30, 95, 14);
		frame.getContentPane().add(datelabel);
		datelabel.setText(java.time.LocalDate.now().toString());  
		
		JLabel timelabel = new JLabel("");
		timelabel.setBounds(540, 67, 84, 14);
		frame.getContentPane().add(timelabel);
		timelabel.setText(java.time.LocalTime.now().toString());

	}
}
