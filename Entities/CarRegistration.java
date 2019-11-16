/**
 * 
 */
package Entities;

import java.sql.Date;

/**
 * @author Amanpreet Singh
 *
 */
public class CarRegistration {

	private String CarRegistrationNo;
	private String model;
	private String make;
	private Date fromDate;
	private Date toDate;
	
	
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

	
	
}
