
/**
 * @author valei - vlunderwood
 * CIS175 - Fall 2023
 * Sep 10, 2023
 */
package model;
//javax.persistence.Entity annotation.
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

//POJO

//annotations
@Entity
@Table(name = "resorts")
public class ListResort {

	
	@Id
	@GeneratedValue
	@Column(name="ID")
	private int id;
	@Column(name="RESORT")
	private String resort;
	@Column(name="CITY")
	private String city;
	@Column(name="COUNTRY")
	private String country;
	@Column(name="PRICE")
	private Double price;
	
	//default no arg constructor
	public ListResort() {}
	
	//constructor
	public ListResort(String resort, String city, String country, Double price) {
		this.resort = resort;
		this.city = city;
		this.country = country;
		this.price = price;
		
		
	}
	//getters & setters

	/**
	 * @return the price
	 */
	public Double getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(Double price) {
		this.price = price;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the resort
	 */
	public String getResort() {
		return resort;
	}

	/**
	 * @param resort the resort to set
	 */
	public void setResort(String resort) {
		this.resort = resort;
	}
	
	
	//print method
	public String returnResortDetails() {
		return this.id  + " - Resort Name: "+ this.resort + " Location: " + this.city + ", " + this.country + " Price: $" + String.format("%.2f", this.price);
	}
	
	
}
