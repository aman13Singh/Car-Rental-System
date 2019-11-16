package Controller;

import javax.swing.JOptionPane;

public class Validate {

	
	private String customerName = "";
	private String address = "";
	private String number = "";
	private String password = "";
	
	

	/**
	 * @param customerName
	 * @param address
	 * @param number
	 * @param password
	 */	
	//constructor used for Registration class
	public Validate(String customerName, String address, String number, String password) {
		super();
		this.customerName = customerName;
		this.address = address;
		this.number = number;
		this.password = password;
	}
		
	/**
	 * @param customerName
	 * @param password
	 */
	//constructor used by Home class
	public Validate(String customerName, String password) {
		super();
		this.customerName = customerName;
		this.password = password;
	}
	
	
	//method to validate the registration page's user credentials
		/**
		 * @return boolean value as per Registration data validation results
		 */
		public boolean isSignUpDataValid()
		{
			if(customerName.equals("") || address.equals("") || number.equals("") || password.equals(""))
			{
				JOptionPane.showMessageDialog(null,"No field should be empty");
				System.out.println("No field should be empty....");
				return false;
			}
			
			if(!checkPassword(password))
				return false;
					
			return true;
		}
		
		//method to validate the Home(or Login) page's user credentials
		/**
		 * @return boolean value as per Login data validation results
		 */
		public boolean isLoginDataValid()
		{
			if(customerName.equals("") || password.equals(""))
			{
				JOptionPane.showMessageDialog(null,"No field should be empty");
				System.out.println("No field should be empty....");
				return false;
			}
			
			if(!checkPassword(password))
				return false;
			
			return true;
		}
	
		
		//method to validate if the password meets all the minimum requirements
		/**
		 * @param password the password to be validated
		 * @return boolean value as per password validation results
		 */
		private boolean checkPassword(String password) {
			// TODO Auto-generated method stub
			
			String errorMsg = "";
			boolean containsDigit = false;
			boolean isValid = true;
			if((password.length() < 5))
			{
				errorMsg += "Password should contain atleast 5 characters!!\n";
				System.out.println("Password should contain atleast 5 characters!!");
				isValid = false;
			}
			
			String p = password.toUpperCase();
			if(!(password.charAt(0) == (p.charAt(0))))
			{
				errorMsg += "Password should begin with an uppercase!!\n";
				System.out.println("Password should begin with an uppercase!!");
				isValid = false;
			}
			
			for(int i = 0; i<password.length(); i++)
			{
				if(Character.isDigit(password.charAt(i)))
				{
					containsDigit = true;
				}
			}

			if(!containsDigit)
			{
				errorMsg += "Password must contain atleast one digit!!\n";
				System.out.println("Password must contain atleast one digit!!");
				isValid = false;
			}
		
			if(errorMsg != "")
				JOptionPane.showMessageDialog(null,errorMsg);
			
			if(!isValid)
				return false;
			
			return true;
		}

	
}
