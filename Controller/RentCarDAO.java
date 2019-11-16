
package Controller;

import java.awt.EventQueue;

import javax.swing.JFrame;

import Boundry.Main;
import Entities.UserInfo;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RentCarDAO {

	private JFrame frame;
	UserInfo user;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RentCarDAO window = new RentCarDAO();
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
	public RentCarDAO() {
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
		
		JButton btnback = new JButton(">>>Back");
		btnback.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.main(null, user);
				frame.dispose();

			}
		});
		btnback.setBounds(316, 213, 89, 23);
		frame.getContentPane().add(btnback);
	}
}
