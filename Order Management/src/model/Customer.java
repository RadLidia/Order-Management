package model;

/**
 * Class used to represent the customer.
 * It contains constructors, setters and getters.
 * 
 * @author Lidia
 *
 */
public class Customer {

	private int id;
	private String name;
	private String address;
	
	 /**
     * This method is one of the constructors of this class.
     * @param id of customer
     * @param name of customer
     * @param address of customer
     * 
     */
	public Customer(int id, String name, String address) {
		this.id = id;
		this.name = name;
		this.address = address;
	
	}

	public Customer(String name, String address) {
		this.name = name;
		this.address = address;
	
	}
	
	public Customer() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}