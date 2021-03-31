package model;
/**
 * Class used to represent the product.
 * It contains constructors, setters and getters.
 * 
 * @author Lidia
 *
 */
public class Product {
	private int id;
	private String name;
	private int quantity;
	private float price;
	
	/**
     * This method is one of the constructors of this class.
     * @param id of product
     * @param name of product
     * @param quantity of product in stock
     * @param price of product
     * 
     */
	public Product(int id, String name, int quantity, float price) {
		this.id = id;
		this.name = name;
		this.quantity = quantity;
		this.price = price;
	
	}
	
	public Product(String name, int quantity, float price) {
		this.name = name;
		this.quantity = quantity;
		this.price = price;
	
	}
	
	public Product() {
		
	}
	
	public Product(String productName) {
		this.name = productName;
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

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Product [name=" + name + ", quantity=" + quantity + ", price=" + price + "]";
	}

}