package Entities;

import java.sql.Date;

public class RentCar {

	   private int rentalId ;
	   private int customerID ; 
	   private String selMake ;
	   private Double fee ;  
	   private Date fromdate ;  
	   private Date todate  ;
	   private int days  ;
	
	   
    /**
	 * @return the rentalId
	 */
	public int getRentalId() {
		return rentalId;
	}
	/**
	 * @param rentalId the rentalId to set
	 */
	public void setRentalId(int rentalId) {
		this.rentalId = rentalId;
	}
	/**
	 * @return the customerID
	 */
	public int getCustomerID() {
		return customerID;
	}

	
	
	/**
	 * @return the selMake
	 */

	/**
	 * @return the selMake
	 */
	public String getSelMake() {
		return selMake;
	}
	/**
	 * @param selMake the selMake to set
	 */
	public void setSelMake(String selMake) {
		this.selMake = selMake;
	}
	/**
	 * @param customerID the customerID to set
	 */
	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}
	/**
	 * @return the fee
	 */
	public Double getFee() {
		return fee;
	}
	/**
	 * @param fee the fee to set
	 */
	public void setFee(Double fee) {
		this.fee = fee;
	}
	/**
	 * @return the fromdate
	 */
	public Date getFromdate() {
		return fromdate;
	}
	/**
	 * @param fromdate the fromdate to set
	 */
	public void setFromdate(Date fromdate) {
		this.fromdate = fromdate;
	}
	/**
	 * @return the todate
	 */
	public Date getTodate() {
		return todate;
	}
	/**
	 * @param todate the todate to set
	 */
	public void setTodate(Date todate) {
		this.todate = todate;
	}
	/**
	 * @return the days
	 */
	public int getDays() {
		return days;
	}
	/**
	 * @param days the days to set
	 */
	public void setDays(int days) {
		this.days = days;
	}
	   
	   
	   
	
}
