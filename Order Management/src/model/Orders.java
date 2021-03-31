package model;
/**
 * Class used to represent the order.
 * It contains constructors, setters and getters.
 * 
 * @author Lidia
 *
 */
public class Orders {
	private int id;
	private int customerId;
	private int productId;
	private int productQuantity;
	
	 /**
     * This method is one of the constructors of this class.
     * @param id of order
     * @param customerId of the customer that take the order
     * @param productId of the product that is ordered
     * @param productQuantity - quantity of the product ordered
     * 
     */
	public Orders(int id, int customerId, int productId, int productQuantity){
		this.id = id;
		this.customerId = customerId;
		this.productId = productId;
		this.productQuantity = productQuantity;

	}
	
	public Orders() {
		
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.customerId = id;
	}
	
	public int getCustomerId() {
		return customerId;
	}
	
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	
	public int getProductId() {
		return productId;
	}
	
	public void setProductId(int productId) {
		this.productId = productId;
	}
	
	public int getProductQuantity() {
		return productQuantity;
	}
	
	public void setProductQuantity(int productQuantity) {
		this.productQuantity = productQuantity;
	}

	
}