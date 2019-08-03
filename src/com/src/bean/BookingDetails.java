package com.src.bean;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "BookingInterface") 
public class BookingDetails {

	public BookingDetails(String firstName, String lastName, String email, String mode_of_commute,
			String reasonForVisit, String phoneNumber, String fromDate, String toDate) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.mode_of_commute = mode_of_commute;
		this.reasonForVisit = reasonForVisit;
		this.phoneNumber = phoneNumber;
		this.fromDate = fromDate;
		this.toDate = toDate;
	}
	public BookingDetails() {
		// TODO Auto-generated constructor stub
	}
	private String firstName, lastName, email, mode_of_commute, reasonForVisit, phoneNumber, fromDate, toDate;
	
	public String getFirstName() {
		return firstName;
	}
	@XmlElement(name = "FirstName")
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	@XmlElement(name = "LastName")
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	@XmlElement(name = "Email")
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMode_of_commute() {
		return mode_of_commute;
	}
	@XmlElement(name = "ModeOfCommute")
	public void setMode_of_commute(String mode_of_commute) {
		this.mode_of_commute = mode_of_commute;
	}
	public String getReasonForVisit() {
		return reasonForVisit;
	}
	@XmlElement(name = "ReasonForVisit")
	public void setReasonForVisit(String reasonForVisit) {
		this.reasonForVisit = reasonForVisit;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	@XmlElement(name = "PhoneNumber")
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getFromDate() {
		return fromDate;
	}
	@XmlElement(name = "FromDate")
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	@XmlElement(name = "ToDate")
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
}
