package Entities;

import java.util.Vector;

public class UserInfo {

	private int customerID;
	private String customerName;
	private String address;
	private String number;
	private String password;
	
	
	/**
	 * @return the customerID
	 */
	public int getCustomerID() {
		return customerID;
	}
	/**
	 * @param customerID the customerID to set
	 */
	public void setCustomerID(int customerID) {
		this.customerID = customerID;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public Vector getVectorCustmor() {
		Vector v = new Vector();
		
		// CarRegistrationNo, model , make, fromDate, toDate

		v.add(customerID);
		v.add(customerName);
		v.add(address);
		v.add(number);
		v.add(password);
		
		
		return v;
	}
	
	
}
