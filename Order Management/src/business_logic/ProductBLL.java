package business_logic;

import java.util.List;
import java.util.NoSuchElementException;

import data_access.ProductDAO;
import model.Product;
/**
 * This class encapsulates the application logic of the product.
 * 
 * @author Lidia
 *
 */
public class ProductBLL {
	
	private ProductDAO productDAO = new ProductDAO();
	
	public ProductBLL() {
		super();
	}

	public List<Product> findAll() {
		return productDAO.findAll();
	}
	/**
	 * Method used to find the row in the product table by id.
	 * 
	 * @param id id product to be found
	 * @return product the found Product
	 */
	public Product findProductById(int id) {
		Product product = productDAO.findById(id);
		if (product == null) {
			throw new NoSuchElementException("The Product with id =" + id + " was not found!");
		}
		return product;
	}
	/**
	 * Method used to find the row in the product table by name.
	 * 
	 * @param name of the product to be found
	 * @return product the found Product
	 */
	public Product findProductByName(String name) {
		Product product = productDAO.findByName(name);
		if (product == null) {
			return null;
		}
		return product;
	}
	/**
	 * Method used to insert a row in the product table.
	 * If the product is already inserted, just the quantity will be updated.
	 * 
	 * @param product to be inserted
	 * @return id of the product inserted
	 */
	public int insertProduct(Product product) {
		Product p = findProductByName(product.getName());
		if(p != null) {
			int newQuantity = p.getQuantity() + product.getQuantity();
			return productDAO.update(p.getName(), newQuantity);
		}		
		return productDAO.insert(product);
	}
	/**
	 * Method used to delete a row in the product table.
	 * 
	 * @param product to be deleted
	 *
	 */
	public void deleteProduct(Product product) {
		if(findProductByName(product.getName()) != null) {
			productDAO.delete(product.getName());
		}
	}
	/**
	 * Method used to delete the entire product table.
	 */
	public void deleteAll() {
		productDAO.deleteAll();
	}
	
}