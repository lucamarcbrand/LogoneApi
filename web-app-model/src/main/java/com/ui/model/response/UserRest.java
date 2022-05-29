package com.ui.model.response;


public class UserRest {
	private String userId;
	private String firstName;
	private String lastName;
	private String email;
	
	private AddressRest address;
	
	private InvoiceAddressRest invoiceAddress;
	private String mobileNumber;
	
	private String password;
	
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public AddressRest getAddress() {
		return address;
	}
	public void setAddress(AddressRest address) {
		this.address = address;
	}	
	public InvoiceAddressRest getInvoiceAddress() {
		return invoiceAddress;
	}
	public void setInvoiceAddress(InvoiceAddressRest invoiceAddress) {
		this.invoiceAddress = invoiceAddress;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
