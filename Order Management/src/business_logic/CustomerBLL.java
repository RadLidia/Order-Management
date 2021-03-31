package business_logic;


import java.util.List;
import java.util.NoSuchElementException;

import data_access.CustomerDAO;
import model.Customer;

/**
 * This class encapsulates the application logic of the customers.
 * 
 * @author Lidia
 *
 */
public class CustomerBLL {
	
	private CustomerDAO customerDAO;
	
	/**
	 * constructor
	 */
	public CustomerBLL() {
		customerDAO = new CustomerDAO();
	}
	
	public List<Customer> findAll() {
		return customerDAO.findAll();
	}
	
	/**
	 * Method used to find the row in the customer table by id.
	 * 
	 * @param id customer to be found
	 * @return customer the found Customer
	 */
	public Customer findCustomerById(int id) {
		Customer customer = customerDAO.findById(id);
		if (customer == null) {
			throw new NoSuchElementException("The customer with id =" + id + " was not found!");
		}
		return customer;
	}
	/**
	 * Method used to find the row in the customer table by name.
	 * 
	 * @param name to be found
	 * @return customer found
	 */
	public Customer findCustomerByName(String name) {
		Customer customer = customerDAO.findByName(name);
		if (customer == null) {
			throw new NoSuchElementException("The customer with name =" + name + " was not found!");
		}
		return customer;
	}

	/**
	 * Method used to insert a row in the customer table.
	 * 
	 * @param customer to be inserted
	 * @return id of the customer inserted
	 */
	public int insertCustomer(Customer customer) {
		return customerDAO.insert(customer);
	}
	/**
	 * Method used to delete a row in the customer table.
	 * 
	 * @param customer to be deleted
	 *
	 */
	public void deleteCustomer(Customer customer) {
		if(findCustomerByName(customer.getName()) != null) {
			customerDAO.delete(customer.getName());
		}
	}
	/**
	 * Method used to delete the entire customer table.
	 */
	public void deleteAll() {
		customerDAO.deleteAll();
	}
}