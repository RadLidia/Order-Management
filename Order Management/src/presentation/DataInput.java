package presentation;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import com.itextpdf.text.DocumentException;

import business_logic.CustomerBLL;
import business_logic.OrdersBLL;
import business_logic.ProductBLL;
import data_access.CustomerDAO;
import model.Customer;
import model.Product;
import model.Orders;
/**
 * Class used to implement a parser to read commands from the input file.
 * 
 * @author Lidia
 *
 */

public class DataInput {
	/**
	 * string value for representing the basic crud sql operations
	 */
	private String command; 
	private String tableName;

	/**
	 * fields for customer representation
	 */
	private int customerId = 0;
	private String customerName;
	private String address;
	
	/**
	 * fields for product representation
	 */
	private int productId = 0;
	private String productName;
	private int quantity;
	private float price;
	/**
	 * field for order representation, along with customerName, productName and quantity
	 */
	private int orderId = 0;
	
	/**
	 * objects formed by the new data
	 */
	private Customer newCustomer;
	private Product newProduct;
	private Orders newOrder;
	private Customer customerToBeDeleted;
	private Product productToBeDeleted;
	private Orders orders;
	
	
	private CustomerBLL customerBll = new CustomerBLL();
	private ProductBLL productBll = new ProductBLL();
	private OrdersBLL ordersBll = new OrdersBLL();
	
	private PDFGenerator pdf = new PDFGenerator();

	/**
	 * Method that reads each line, splits it and assigns the data to the correct field. 
	 */
	//public void start(String path){
	public void start(){

		try {
			//File file = new File(path);
			File file = new File("C:\\Users\\Lidia\\eclipse-workspace\\Order Management\\src\\commands.txt");

			if(file.exists()){                               
				Scanner input = new Scanner(file); 
				while(input.hasNextLine()) {
					
					String[] line = input.nextLine().split("[ ,:]+"); 
					command = line[0];
					switch(command) 
			        { 
			            case "Insert": 
			            	insertCommand(line);
			                break; 
			            case "Delete": 
			            	deleteCommand(line); 
			                break; 
			            case "Order": 
			            	orderCommand(line); 
			                break;
			            default: //"Report"
			            	reportCommand(line); 
			        }
				}
			input.close();                        
			}
		}catch(FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @param line represents the line with insert command
	 */
	public void insertCommand(String[] line) {

		tableName = line[1];
		if(tableName.equals("client")) {
			customerId++;
			customerName = line[2] + " " + line[3];
			address = line[4];
			newCustomer = new Customer(customerId, customerName, address);
			customerBll.insertCustomer(newCustomer);
		}else {
			productId++;
			productName = line[2];
			quantity = Integer.parseInt(line[3]);
			price = Float.parseFloat(line[4]); 
			newProduct = new Product(productId, productName, quantity, price);
			productBll.insertProduct(newProduct);
		}
	}
	/**
	 * @param line represents the line with delete command
	 */
	public void deleteCommand(String[] line) {

		tableName = line[1];
		if(tableName.equals("client")) {
			customerName = line[2] + " " + line[3];
			address = line[4];
			customerToBeDeleted = new Customer(customerName, address);
			customerBll.deleteCustomer(customerToBeDeleted);
			customerId--;
		}else {
			productName = line[2];
			productToBeDeleted = new Product(productName);
			productBll.deleteProduct(productToBeDeleted);
			productId--;
		}
	}
	/**
	 * Method that resolve the order command, by making the proper order object. 
	 * It also generates the bill and error pdf reports.
	 * 
	 * @param line represents the line with order command
	 * @throws FileNotFoundException exception
	 * @throws DocumentException exception
	 */
	public void orderCommand(String[] line) throws FileNotFoundException, DocumentException {
		
		orderId++;
		customerName = line[1] + " " + line[2];
		Customer c = customerBll.findCustomerByName(customerName);	
		productName = line[3];
		Product p = productBll.findProductByName(productName);	
		quantity = Integer.parseInt(line[4]);
		newOrder = new Orders(orderId, c.getId(), p.getId(), quantity);
		int id = ordersBll.insertOrder(newOrder);
		if(id == -1)
			pdf.PDFOrderError();
		else
			pdf.PDFBill(newOrder);
	}
	/**
	 * @param line represents the line with report command
	 * @throws FileNotFoundException exception
	 * @throws DocumentException exception
	 */
	public void reportCommand(String[] line) throws FileNotFoundException, DocumentException {
		
		tableName = line[1];
		if(tableName.equals("client")) { 
			pdf.PDFClient();
			
		}
		else {
			if(tableName.equals("product")) { 
				pdf.PDFProduct();
			}
			else {
				pdf.PDFOrder();
			}
		}
	}
}