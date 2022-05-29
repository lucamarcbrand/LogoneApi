package com.ui.model.request;

public class UserDetailRequestModel {
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String mobileNumber;
	private AddressDetailRequestModel address; 
	private InvoiceAddressModal invoiceAddress;
	private String userId;
	private boolean changePassword;
	public boolean isChangePassword() {
		return changePassword;
	}
	public void setChangePassword(boolean changePassword) {
		this.changePassword = changePassword;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public AddressDetailRequestModel getAddress() {
		return address;
	}

	public void setAddress(AddressDetailRequestModel address) {
		this.address = address;
	}
	public InvoiceAddressModal getInvoiceAddress() {
		return invoiceAddress;
	}
	public void setInvoiceAddress(InvoiceAddressModal invoiceAddress) {
		this.invoiceAddress = invoiceAddress;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
}
