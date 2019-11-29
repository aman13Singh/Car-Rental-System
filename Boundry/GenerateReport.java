package Boundry;

import java.awt.Component;
import java.awt.EventQueue;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JRadioButton;
import javax.swing.ListCellRenderer;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class GenerateReport {

	private JFrame frame;

	DBHelper helper = new DBHelper();
	private ResultSet rs = null;
	private Statement stmt = null;

	
	private Boolean choiceReturn = true;
	private Boolean choicePayment = true;
	private Boolean choiceCarRegist = true;
	private Boolean choiceRental = true;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GenerateReport window = new GenerateReport();
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
	public GenerateReport() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 585, 365);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JRadioButton rdbtnReturnCar = new JRadioButton("Return Car");
		rdbtnReturnCar.setBounds(32, 47, 109, 23);
		frame.getContentPane().add(rdbtnReturnCar);
		
		JRadioButton rdbtnPayment = new JRadioButton("Payment");
		rdbtnPayment.setBounds(143, 47, 109, 23);
		frame.getContentPane().add(rdbtnPayment);
		
		JRadioButton rdbtnRental = new JRadioButton("Rental");
		rdbtnRental.setBounds(261, 47, 109, 23);
		frame.getContentPane().add(rdbtnRental);
		
		JRadioButton rdbtnCarRegistration = new JRadioButton("Car Registration");
		rdbtnCarRegistration.setBounds(372, 47, 109, 23);
		frame.getContentPane().add(rdbtnCarRegistration);
		
		JButton btnGenerate = new JButton("Generate");
	//	btnGenerate.setVisible(false);

		btnGenerate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				
				ButtonGroup bgroup = new ButtonGroup();
				bgroup.add(rdbtnReturnCar);
				bgroup.add(rdbtnPayment);
				bgroup.add(rdbtnRental);
				bgroup.add(rdbtnCarRegistration);

				choiceReturn = rdbtnReturnCar.isSelected();
				choicePayment = rdbtnPayment.isSelected();
				choiceRental = rdbtnRental.isSelected();
				choiceCarRegist = rdbtnCarRegistration.isSelected();
				
				String choice = bgroup.getSelection().getActionCommand();
				
				
				if(rdbtnReturnCar.isSelected()) {
					//helper.connectDB();
					ArrayList<String> ReturnCarData = null;
					ReturnCarData = getRentalData();
					writeToFile("Return Car_Report", ReturnCarData);
					
				} else if(rdbtnPayment.isSelected()) {
					ArrayList<String> PaymentData = null;
					PaymentData = getpaymentData();
					writeToFile("Payment_Report", PaymentData);
					
				} else if(rdbtnRental.isSelected()) {
					ArrayList<String> RentalData = null;
					RentalData = getRentalData();
					writeToFile("Rental_Report", RentalData);
					
				} else if(rdbtnCarRegistration.isSelected()) {
					ArrayList<String> CarRegistrationData = null;
					CarRegistrationData = getCarregistrationData();
					writeToFile("Car_Registration_Report", CarRegistrationData);
				}
				else {
			//		btnGenerate.setVisible(false);
					System.out.print("Something wrong to generate report");
				}
			
			
			
			}
		});
		btnGenerate.setBounds(195, 106, 89, 23);
		frame.getContentPane().add(btnGenerate);
	}



	/**
	 * 
	 * @return contents of getRentalData table
	 */
	public  ArrayList<String> getRentalData() {
		// TODO Auto-generated method stub
		ArrayList<String> s1 = new ArrayList<String>();
		  
		  String sql = "SELECT * FROM rental";
		  
		  try {
			  helper.connectDB();
			  this.stmt = helper.getConnection().createStatement();
				rs = stmt.executeQuery(sql);

				while (rs.next())
				{
				
				  s1.add("rentalId: " + rs.getInt("rentalId") + "\n" + "customerID: " + rs.getInt("customerID") + ", currMake: " + rs.getString("currMake") +
						  ", fee : " + rs.getInt("fee") + ", fromdate: " + rs.getDate("fromdate") + 
						  ", todate : " + rs.getDate("todate") + ", days: " + rs.getString("days"));
				  System.out.println(s1);
			  }	  
		  helper.disconnectDB();
		  }catch(SQLException sx)
		  {
			  System.out.println("Error fetching data from the database");
			  System.out.println(sx.getMessage());
			  System.out.println(sx.getErrorCode());
			  System.out.println(sx.getSQLState()); 
		  }
		  
		  return s1;
	}
	
	
	/**
	 * 
	 * @return contents of getCarregistrationData table
	 */
	public  ArrayList<String> getCarregistrationData() {
		ArrayList<String> s1 = new ArrayList<String>();
		  
		  String sql = "SELECT * FROM carregistration";
		  
		  try {
			  helper.connectDB();
			  this.stmt = helper.getConnection().createStatement();
				rs = stmt.executeQuery(sql);

				while (rs.next())
				{
				
				  s1.add("ID: " + rs.getInt("ID") + "\n" + "customerID: " + rs.getInt("customerID") + ", customerName: " + rs.getString("customerName") +
						  ", CarRegistrationNo: " + rs.getString("CarRegistrationNo") + ", model: " + rs.getString("model") + 
						  ", make: " + rs.getString("make") + ", fromDate: " + rs.getDate("fromDate") + ", toDate: " + rs.getDate("toDate") + 
						  ", price: " + rs.getFloat("price"));
				  
				  System.out.println(s1);
			  }	    
		  helper.disconnectDB();
		  }catch(SQLException sx)
		  {
			  System.out.println("Error fetching data from the database");
			  System.out.println(sx.getMessage());
			  System.out.println(sx.getErrorCode());
			  System.out.println(sx.getSQLState()); 
		  }
		  
		  return s1;
		
	}
	
	
	/**
	 * 
	 * @return contents of getpaymentData table
	 */
	public  ArrayList<String> getpaymentData() {
		ArrayList<String> s1 = new ArrayList<String>();
		  
		  String sql = "SELECT * FROM payment";
		  
		  try {
			  helper.connectDB();
			  this.stmt = helper.getConnection().createStatement();
				rs = stmt.executeQuery(sql);

				while (rs.next())
				{
				
				  s1.add("idPayment: " + rs.getInt("idPayment") + "\n" + "customerID: " + rs.getInt("customerID") + ", customerName: " + rs.getString("customerName")+
						  ", amount : " + rs.getDouble("amount")  +  ", CardNumber : " + rs.getString("CardNumber") + 
						  ", ExpiryDate: " + rs.getString("ExpiryDate"));
				  
				  System.out.println(s1);
			  }	  
		  helper.disconnectDB();
		  }catch(SQLException sx)
		  {
			  System.out.println("Error fetching data from the database");
			  System.out.println(sx.getMessage());
			  System.out.println(sx.getErrorCode());
			  System.out.println(sx.getSQLState()); 
		  }
		  
		  return s1;
		
	}
	
	
	/**
	 * 
	 * @return list of getreturncarList as arraylist
	 */
	public ArrayList<String> getreturncarList(){
		ArrayList<String> s1 = new ArrayList<String>();
		  
		  String sql = "SELECT * FROM returncar";
		  
		  try {
			  helper.connectDB();
			  this.stmt = helper.getConnection().createStatement();
				rs = stmt.executeQuery(sql);

				while (rs.next())
				{
				
				  s1.add("idreturn: " + rs.getInt("idreturn") + "\n" + "customerID: " + rs.getInt("customerID") + "\n" +
						  ", customerName: " + rs.getString("customerName") +
						  ", model : " + rs.getString("model") + ", make: " + rs.getString("make") +
						  ", LicenceNo : " + rs.getString("LicenceNo") +
						  ", location : " + rs.getString("location"));
				  System.out.println(s1);
			  }	  
		  helper.disconnectDB();
		  }catch(SQLException sx)
		  {
			  System.out.println("Error fetching data from the database");
			  System.out.println(sx.getMessage());
			  System.out.println(sx.getErrorCode());
			  System.out.println(sx.getSQLState()); 
		  }
		  
		  return s1;
		
	}
	
	
	/**
	 * 
	 * @param fileName is the name of the file to be created
	 * @return the message for confirmation of file generation
	 */
	public String writeToFile(String fileName, ArrayList<String> data) {
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(fileName + ".txt", "UTF-8");
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return "Report Not Generated";
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return "Report Not Generated";
		}
		
		for(int i = 0; i < data.size(); i++) {
			writer.println(data.get(i) + "\n");
			writer.println("---------------------------------------------------------------------------------------------------------------------------"+
			"---------------------------------------------------------------------------------------------------------------------------");
		}
		
		writer.close();
		return "Report Generated";
	}
	
	
	
	/**
	 * class for comboBoxRenderring
	 * @author Mann
	 *
	 */
	class MyComboBoxRenderer extends JLabel implements ListCellRenderer {
		  private String _title;

		  public MyComboBoxRenderer(String title) {
		    _title = title;
		  }

		  @Override
		  public Component getListCellRendererComponent(JList list, Object value,
		      int index, boolean isSelected, boolean hasFocus) {
		    if (index == -1 && value == null)
		      setText(_title);
		    else
		      setText(value.toString());
		    return this;
		  }
	}
}
