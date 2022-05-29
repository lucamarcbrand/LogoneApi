package com.shared.dto;

import java.io.Serializable;


public class AddressDto  implements Serializable{

	private static final long serialVersionUID = 2737650696076246530L;
	private long id;
	private String street;
	private String postcode;
	private String place;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}


}
