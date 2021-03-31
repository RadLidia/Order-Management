//package data_access;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
//import connection.ConnectionWarehouse;
//import model.Product;
//
//public class ProductDAO {
//
//	protected static final Logger LOGGER = Logger.getLogger(ProductDAO.class.getName());
//	private static final String insertStatementString = "INSERT INTO Product (id,name,quantity,price)" + " VALUES (?,?,?,?)";
//	private static final String deleteStatementString = "DELETE FROM Product where id = ?";
//	private static final String deleteAllStatementString = "DELETE FROM Product";
//	private final static String findStatementInt = "SELECT * FROM Product where id = ?";
//	private final static String findStatementString = "SELECT * FROM Product where name = ?";
//
//	public static Product findById(int ProductId) {
//		Product toReturn = null;
//
//		Connection dbConnection = ConnectionWarehouse.getConnection();
//		PreparedStatement findStatement = null;
//		ResultSet rs = null;
//		try {
//			findStatement = dbConnection.prepareStatement(findStatementInt);
//			findStatement.setLong(1, ProductId);
//			rs = findStatement.executeQuery();
//			rs.next();
//
//			String name = rs.getString("name");
//			String address = rs.getString("address");
//			toReturn = new Product(ProductId, name, quantity, price);
//		} catch (SQLException e) {
//			LOGGER.log(Level.WARNING,"StudentDAO:findById " + e.getMessage());
//		} finally {
//			ConnectionWarehouse.close(rs);
//			ConnectionWarehouse.close(findStatement);
//			ConnectionWarehouse.close(dbConnection);
//		}
//		return toReturn;
//	}
//	
//	public static Product findByName(String name) {
//		Product toReturn = null;
//
//		Connection dbConnection = ConnectionWarehouse.getConnection();
//		PreparedStatement findStatement = null;
//		ResultSet rs = null;
//		try {
//			findStatement = dbConnection.prepareStatement(findStatementString);
//			findStatement.setString(1, name);
//			rs = findStatement.executeQuery();
//			rs.next();
//			
//			int id = rs.getInt("id");
//			String address = rs.getString("address");
//			toReturn = new Product(id, name, address);
//		} catch (SQLException e) {
//			LOGGER.log(Level.WARNING,"StudentDAO:findById " + e.getMessage());
//		} finally {
//			ConnectionWarehouse.close(rs);
//			ConnectionWarehouse.close(findStatement);
//			ConnectionWarehouse.close(dbConnection);
//		}
//		return toReturn;
//	}
//
//	public static int insert(Product Product) {
//		Connection dbConnection = ConnectionWarehouse.getConnection();
//
//		PreparedStatement insertStatement = null;
//		int insertedId = 0;
//		try {
//			insertStatement = dbConnection.prepareStatement(insertStatementString);
//			insertStatement.setInt(1, Product.getId());
//			insertStatement.setString(2, Product.getName());
//			insertStatement.setString(3, Product.getAddress());
//			insertStatement.executeUpdate();
//
////			ResultSet rs = insertStatement.getGeneratedKeys();
////			if (rs.next()) {
////				insertedId = rs.getInt(1);
////			}
//			insertedId++;
//
//		} catch (SQLException e) {
//			LOGGER.log(Level.WARNING, "ProductDAO:insert " + e.getMessage());
//		} finally {
//			ConnectionWarehouse.close(insertStatement);
//			ConnectionWarehouse.close(dbConnection);
//		}
//		return insertedId;
//	}
//	
//	public static void delete(Product Product) {
//		Connection dbConnection = ConnectionWarehouse.getConnection();
//
//		PreparedStatement deleteStatement = null;
//		//int deletedId = -1;
//		try {
//			deleteStatement = dbConnection.prepareStatement(deleteStatementString);
//			deleteStatement.setInt(1, Product.getId());
//			deleteStatement.executeUpdate();
//
//			
//		} catch (SQLException e) {
//			LOGGER.log(Level.WARNING, "ProductDAO:insert " + e.getMessage());
//		} finally {
//			ConnectionWarehouse.close(deleteStatement);
//			ConnectionWarehouse.close(dbConnection);
//		}
//		System.out.println("Product " + Product.getId() + " has been deleted.");
//	}
//	
//	public static void deleteAll() {
//		try {
//			Connection dbConnection = ConnectionWarehouse.getConnection();
//
//			PreparedStatement deleteStatement = null;
//			deleteStatement = dbConnection.prepareStatement(deleteAllStatementString);
//			deleteStatement.executeUpdate();
//			 
//			System.out.println("Successfully truncated test_table");
//			 
//		} catch (SQLException e) {
//
//			System.out.println("Could not truncate test_table " + e.getMessage());
//		}
//	}
//}

package data_access;

import model.Product;
/**
 * Class used to perform the queries for the Product, by extending the superclass AbstractDAO.
 * This class will inherit the methods from the AbstractDAO.
 * 
 * @author Lidia
 *
 */
public class ProductDAO extends AbstractDAO<Product> {}
