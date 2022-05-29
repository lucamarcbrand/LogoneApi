package com.app.io.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "app_users")
public class UserEntity implements Serializable {

	private static final long serialVersionUID = -6294297332112615875L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private long id;

	@Column(nullable = false,name = "UU_ID")
	private String userId;
	
	@Column(nullable = true,length = 50)
	private String firstName;
	
	@Column(nullable = true,length = 50)
	private String lastName;
	
	@Column(nullable = false,length = 110 ,unique = true )
	private String email;
	
	@Column(nullable = false)
	private String encryptedPassword;
	

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id")
	private AddressEntity address;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "invoice_address_id")
	private InvoiceAddressEntity invoiceAddress;
	
	@Column(nullable = true)
	private String mobileNumber;
	
	
	
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public InvoiceAddressEntity getInvoiceAddress() {
		return invoiceAddress;
	}
	public void setInvoiceAddress(InvoiceAddressEntity invoiceAddress) {
		this.invoiceAddress = invoiceAddress;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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
	public String getEncryptedPassword() {
		return encryptedPassword;
	}
	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}

	public AddressEntity getAddress() {
		return address;
	}
	public void setAddress(AddressEntity address) {
		this.address = address;
	}

	

}
