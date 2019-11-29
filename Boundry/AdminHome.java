package Boundry;

import java.awt.EventQueue;

import javax.swing.JFrame;

import Controller.StaffDAO;
import Entities.UserInfo;

import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdminHome {

	private JFrame frame;
    UserInfo user;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args,  UserInfo user) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminHome window = new AdminHome(user);
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
	public AdminHome( UserInfo user) {
		this.user = user;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 671, 393);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnManageCars = new JButton("Manage Cars");
		btnManageCars.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				manageCars.main(null,user);
				
			}
		});
		btnManageCars.setBounds(29, 153, 95, 72);
		frame.getContentPane().add(btnManageCars);
		
		JButton btnManageStaff = new JButton("Manage Customer ");
		btnManageStaff.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				StaffDAO.main(null);
				
			}
		});
		btnManageStaff.setBounds(187, 153, 146, 72);
		frame.getContentPane().add(btnManageStaff);
		
		JButton btnGenerateReport = new JButton("Generate Report");
		btnGenerateReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				GenerateReport.main(null);
			}
		});
		btnGenerateReport.setBounds(380, 153, 128, 72);
		frame.getContentPane().add(btnGenerateReport);
	}
}
