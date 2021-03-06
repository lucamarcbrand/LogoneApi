package com.app.io.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
//Specifies that the class is an entity. This annotation is applied to the entity class

@Table(name = "address" )
//Specifies the primary table for the annotated entity. 
public class AddressEntity implements Serializable {
	
	private static final long serialVersionUID = 6409759544615263918L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private long id;


	@Column( nullable = false)
	private String street;
	
	@Column( nullable = false)
	private String postcode;
	
	@Column( nullable = false)
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
