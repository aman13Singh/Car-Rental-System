/**
 * 
 */
package Controller;

import java.util.ArrayList;

/**
 * @author Amanpreet Singh
 *
 */
public class Authenticate {

	
	private String customerName = "";
	private String password = "";
	private String purpose = "";
	private LoginDAO homeDAO = new LoginDAO();
	private RegistrationDAO registrationDAO =  new RegistrationDAO();
	AdminDAO adminDAO = new AdminDAO();
	
	//default constructor
	public Authenticate() {	
		
	}
	
	
	
	
	
	/**
	 * @return the customerName
	 */
	public String getCustomerName() {
		return customerName;
	}





	/**
	 * @param customerName the customerName to set
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}





	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}





	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}





	/**
	 * @return the purpose
	 */
	public String getPurpose() {
		return purpose;
	}





	/**
	 * @param purpose the purpose to set
	 */
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}





	//method to verify username input
	/**
	 * @return the boolean value whether username verified or not
	 */
	public boolean matchUserName()
	{
		ArrayList<String> list = new ArrayList<>();
		if(purpose.equals("Login Task"))
			list = homeDAO.listUserNames();
		if(purpose.equals("Registration Task"))
			list = registrationDAO.listUserNames();
		if(purpose.equals(""))
			list = homeDAO.listUserNames();
			
		for(String str: list)
		{
			if(customerName.equals(str))
				return true;
		}		
		return false;		
	}
	
	//method to verify password input
	/**
	 * @return the boolean value whether password verified or not
	 */
	public boolean matchpassword()
	{
		String pswrd = homeDAO.getPassword(customerName);
		
			if(password.equals(pswrd))
			{
				return true;
			}	
		return false;		
	}

	
	
	//method to verify username input
		/**
		 * @return the boolean value whether username verified or not
		 */
		public boolean matchAdminUserName()
		{
			ArrayList<String> list = new ArrayList<>();
			if(purpose.equals("Login Task"))
				list = adminDAO.listAdminUserNames();
			
			if(purpose.equals(""))
				list = homeDAO.listUserNames();
				
			for(String str: list)
			{
				if(customerName.equals(str))
					return true;
			}		
			return false;		
		} 
		
		//method to verify password input
		/**
		 * @return the boolean value whether password verified or not
		 */
		public boolean matchAdminpassword()
		{
			String pswrd = adminDAO.getAdminPassword(customerName);
			
				if(password.equals(pswrd))
				{
					return true;
				}	
			return false;		
		}
	

	
}
