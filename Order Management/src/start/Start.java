package start;

import java.io.IOException;

import java.util.Scanner;
import java.util.logging.Logger;

import business_logic.CustomerBLL;
import business_logic.OrdersBLL;
import business_logic.ProductBLL;
import presentation.DataInput;

/**
 * Date: 13/04/2020
 * Start class of the Order Management application.
 * @author Lidia
 *
 */
public class Start {
	protected static final Logger LOGGER = Logger.getLogger(Start.class.getName());

	/**
	 * The main method of the Order Management.
	 * First, if you press 1, you can reset the data from the warehouse database.
	 * @param args array of string arguments
	 * @throws IOException exception produced by failed or interrupted I/O operations. 
	 */
	public static void main(String[] args) throws IOException {
		
		Scanner keyboard = new Scanner(System.in);
		System.out.println("If you want to reset the warehouse database, before performing the commands, press '1'.");
		if(keyboard.nextInt() == 1) {

			CustomerBLL customerBll = new CustomerBLL();
			ProductBLL productBll = new ProductBLL();
			OrdersBLL orderBll = new OrdersBLL();

			orderBll.deleteAll();
			customerBll.deleteAll();
			productBll.deleteAll();	
		}

		DataInput data = new DataInput();
		//data.start(args[0]);	
		data.start();	

	}
	
}
