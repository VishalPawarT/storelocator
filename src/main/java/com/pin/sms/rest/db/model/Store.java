package com.pin.sms.rest.db.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "store")
public class Store {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "store_id")
	private Long storeId;
	
	@Column(name = "store_name")
	private String storeName;
	
	@Column(name = "store_url")
	private String storeUrl;
	
	@Column(name ="longitude")
	private String longitude;
	
	@Column(name = "altitude")
	private String altitude;
	
	@Column(name = "pincode")
	private String pincode;
	
	@Column(name = "area")
	private String area;
	
	@Column(name = "city")
	private String city;
	
	
}
