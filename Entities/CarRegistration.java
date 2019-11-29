/**
 * 
 */
package Entities;

import java.sql.Date;
import java.util.Vector;

/**
 * @author Amanpreet Singh
 *
 */
public class CarRegistration {

	private int ID;
	private String CarRegistrationNo;
	private String model;
	private String make;
	private Date fromDate;
	private Date toDate;
	private float price;
	
	/**
	 * @return the iD
	 */
	public int getID() {
		return ID;
	}
	/**
	 * @param iD the iD to set
	 */
	public void setID(int iD) {
		ID = iD;
	}
	/**
	 * @return the carRegistrationNo
	 */
	public String getCarRegistrationNo() {
		return CarRegistrationNo;
	}
	/**
	 * @param carRegistrationNo the carRegistrationNo to set
	 */
	public void setCarRegistrationNo(String carRegistrationNo) {
		CarRegistrationNo = carRegistrationNo;
	}
	/**
	 * @return the model
	 */
	public String getModel() {
		return model;
	}
	/**
	 * @param model the model to set
	 */
	public void setModel(String model) {
		this.model = model;
	}
	/**
	 * @return the make
	 */
	public String getMake() {
		return make;
	}
	/**
	 * @param make the make to set
	 */
	public void setMake(String make) {
		this.make = make;
	}
	/**
	 * @return the fromDate
	 */
	public Date getFromDate() {
		return fromDate;
	}
	/**
	 * @param fromDate the fromDate to set
	 */
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	/**
	 * @return the toDate
	 */
	public Date getToDate() {
		return toDate;
	}
	/**
	 * @param toDate the toDate to set
	 */
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}


	/**
	 * @return the price
	 */
	public float getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(float price) {
		this.price = price;
	}
	
	public Vector getVector() {
		Vector v = new Vector();
		
		// CarRegistrationNo, model , make, fromDate, toDate

		
		v.add(CarRegistrationNo);
		v.add(model);
		v.add(make);
		v.add(fromDate);
		v.add(toDate);
		v.add(price);
	
		
		return v;
	}
	
	
	public Vector getVectSel() {
		Vector v = new Vector();
		
		// CarRegistrationNo, model , make, fromDate, toDate

		
	//	v.add(CarRegistrationNo);
		v.add(model);
		v.add(make);
		v.add(price);
	//	v.add(fromDate);
	//	v.add(toDate);
	
		
		return v;
	}
	
	
	
	public Vector getVectoradmin() {
		Vector v = new Vector();
		
		// CarRegistrationNo, model , make, fromDate, toDate

		v.add(ID);
		v.add(CarRegistrationNo);
		v.add(model);
		v.add(make);
		v.add(fromDate);
		v.add(toDate);
		v.add(price);
	
		
		return v;
	}
	
}
