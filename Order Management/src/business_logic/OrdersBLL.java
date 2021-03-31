package business_logic;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import data_access.CustomerDAO;
import data_access.OrdersDAO;
import data_access.ProductDAO;
import model.Customer;
import model.Orders;
import model.Product;
/**
 * This class encapsulates the application logic of the orders.
 * 
 * @author Lidia
 *
 */
public class OrdersBLL {
	
	private OrdersDAO ordersDAO = new OrdersDAO();
	private ProductDAO productDAO = new ProductDAO();
	private CustomerDAO customerDAO = new CustomerDAO();

	public OrdersBLL() {
		super();
	}
	
	public List<Orders> findAll() {
		return ordersDAO.findAll();
	}
	/**
	 * Method used to help generating the pdf order reports.
	 * 
	 * @return list of attributes of the orders
	 */
	public List<String[]> display() {
		
		List<String[]> list = new ArrayList<String[]>();
		int count = 1;
		for(Orders o: ordersDAO.findAll()) {
			String[] attr  = new String[4];
			attr[0] = String.valueOf(count);
			count++;
			int idCustomer = o.getCustomerId();
			Customer c = customerDAO.findById(idCustomer);
			attr[1] = c.getName();
			int idProduct = o.getProductId();
			Product p = productDAO.findById(idProduct);
			attr[2] = p.getName();
			attr[3] = String.valueOf(o.getProductQuantity());
			list.add(attr);
		}
		return list;
	}
	/**
	 * Method used to help printing the bill.
	 * 
	 * @param o order to be printed
	 * @return attr attributes of the order
	 */
	public String[] printBill(Orders o) {
		
		String[] attr  = new String[5];	
		attr[0] = String.valueOf(o.getId());
		int idCustomer = o.getCustomerId();
		Customer c = customerDAO.findById(idCustomer);
		attr[1] = c.getName();
		int idProduct = o.getProductId();
		Product p = productDAO.findById(idProduct);
		attr[2] = p.getName();
		attr[3] = String.valueOf(o.getProductQuantity());
		float price = o.getProductQuantity() * p.getPrice();
		attr[4] = String.valueOf(price) + " lei";
		return attr;
	}
	/**
	 * Method used to find the row in the orders table by id.
	 * 
	 * @param id id order to be found
	 * @return orders founded
	 */
	public Orders findOrdersById(int id) {
		Orders orders = ordersDAO.findById(id);
		if (orders == null) {
			throw new NoSuchElementException("The order with id =" + id + " was not found!");
		}
		return orders;
	}
	/**
	 * Method used to find the row in the orders table by id.
	 * 
	 * @param name of the order to be found
	 * @return o the found order
	 */
	public Orders findOrdersByName(String name) {
		Orders o = ordersDAO.findByName(name);
		if (o == null) {
			return null;
		}
		return o;
	}
	/**
	 * Method used to insert a row in the orders table.
	 * First, the product is searched by findById().
	 * If the stock is enough, the quantity of the product is updated, and the order is inserted.
	 * 
	 * @param order to be inserted
	 * @return the id of the order inserted, or -1, when the stock is not enough.
	 */
	public int insertOrder(Orders order) {
		Product p = productDAO.findById(order.getProductId());
		int id = 0;
		if(p != null) {
			int newQuantity = p.getQuantity() - order.getProductQuantity();
			if(newQuantity >= 0) {
				productDAO.update(p.getName(), newQuantity);	
				id = ordersDAO.insert(order);
			}else
				return -1;
		}
		return id;
	}
	/**
	 * Method used to delete the entire orders table.
	 */
	public void deleteAll() {
		ordersDAO.deleteAll();
	}

}