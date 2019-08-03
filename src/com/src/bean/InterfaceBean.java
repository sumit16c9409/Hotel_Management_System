package com.src.bean;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement; 
import javax.xml.bind.annotation.XmlRootElement; 
@XmlRootElement(name = "Interface") 

public class InterfaceBean implements Serializable {
	private static final long serialVersionUID = 1L; 
	private int id; 
	private String name;
	private String address_line;
	private String city;
	private int rating;
	private float price;
	
	public InterfaceBean() {}
	public InterfaceBean(int id, String name, String address_line, String city, int rating, float price) {
		// TODO Auto-generated constructor stub
		this.id = id; 
        this.name = name; 
        this.address_line = address_line;
        this.city = city;
        this.rating = rating;
        this.price = price;
	}
	
	public String getAddress_line() {
		return address_line;
	}
	@XmlElement(name = "Address")
	public void setAddress_line(String address_line) {
		this.address_line = address_line;
	}
	public String getCity() {
		return city;
	}
	@XmlElement(name = "City")
	public void setCity(String city) {
		this.city = city;
	}
	public int getRating() {
		return rating;
	}
	@XmlElement(name = "Rating")
	public void setRating(int rating) {
		this.rating = rating;
	}
	public float getPrice() {
		return price;
	}
	@XmlElement(name = "Price")
	public void setPrice(float price) {
		this.price = price;
	}
	public int getId() {
		return id;
	}
	@XmlElement(name = "Id")
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	@XmlElement(name = "Name")
	public void setName(String name) {
		this.name = name;
	}
}
