package presentation;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Date;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import business_logic.CustomerBLL;
import business_logic.OrdersBLL;
import business_logic.ProductBLL;
import model.Customer;
import model.Orders;
import model.Product;
/**
 * Class used to generate the pdf reports for clients, products, and orders. 
 * Also for error from understock and the bill.
 * 
 * @author Lidia
 *
 */
public class PDFGenerator {
	
	/**
	 * Fields to make different pdf reports.
	 */
	private int countClientReports = 1;
	private int countProductReports = 1;
	private int countOrdersReports = 1;
	private int countErrorReports = 1;
	private int countBills = 1;
	/**
	 * 
	 * Method used to generate the clients report.
	 * Open document, create paragraph, create the cells and add them to the table.
	 * Finally add the objects to the document.
	 * 
	 * @throws FileNotFoundException exception
	 * @throws DocumentException exception
	 * 
	 */
	public void PDFClient() throws FileNotFoundException, DocumentException {
		
		CustomerBLL customerBLL = new CustomerBLL();
		
		Document document = new Document();
		
		String fileName = "Client-report" + countClientReports + ".pdf";
		countClientReports++;
		PdfWriter.getInstance(document, new FileOutputStream(fileName));

		document.open();

		Paragraph intro = new Paragraph("Client Report");
		Paragraph space = new Paragraph(" ");
		PdfPTable table = new PdfPTable(3);

		PdfPCell c1 = new PdfPCell(new Paragraph("Id"));
		PdfPCell c2 = new PdfPCell(new Paragraph("Name"));
		PdfPCell c3 = new PdfPCell(new Paragraph("Address"));

		table.addCell(c1);
		table.addCell(c2);
		table.addCell(c3);

		for(Customer c: customerBLL.findAll()) {
			c1 = new PdfPCell(new Paragraph(String.valueOf(c.getId())));
			c2 = new PdfPCell(new Paragraph(c.getName()));
			c3 = new PdfPCell(new Paragraph(c.getAddress()));
			
			table.addCell(c1);
			table.addCell(c2);
			table.addCell(c3);
		}

		document.add(intro);
		document.add(space);
		document.add(space);
		document.add(space);
		document.add(table);

		document.close();
	}
	/**
	 * 
	 * Method used to generate the products report, using findAll() method.
	 * 
	 * @throws FileNotFoundException exception
	 * @throws DocumentException exception
	 * */
	public void PDFProduct() throws FileNotFoundException, DocumentException {
		
		ProductBLL productBLL = new ProductBLL();
		
		Document document = new Document();
		
		String fileName = "Product-report" + countProductReports + ".pdf";
		countProductReports++;
		PdfWriter.getInstance(document, new FileOutputStream(fileName));
		
		document.open();//open document

		//create paragraph
		Paragraph intro = new Paragraph("Product Report");
		Paragraph space = new Paragraph(" ");
		PdfPTable table = new PdfPTable(4);

		//create a cell
		PdfPCell c1 = new PdfPCell(new Paragraph("Id"));
		PdfPCell c2 = new PdfPCell(new Paragraph("Name"));
		PdfPCell c3 = new PdfPCell(new Paragraph("Quantity"));
		PdfPCell c4 = new PdfPCell(new Paragraph("Price"));

		//add the cells to the table
		table.addCell(c1);
		table.addCell(c2);
		table.addCell(c3);
		table.addCell(c4);

		for(Product p: productBLL.findAll()) {
			c1 = new PdfPCell(new Paragraph(String.valueOf(p.getId())));
			c2 = new PdfPCell(new Paragraph(p.getName()));
			c3 = new PdfPCell(new Paragraph(String.valueOf(p.getQuantity())));
			c4 = new PdfPCell(new Paragraph(String.valueOf(p.getPrice())));

			table.addCell(c1);
			table.addCell(c2);
			table.addCell(c3);
			table.addCell(c4);

		}

		//add the objects to the document
		document.add(intro);
		document.add(space);
		document.add(space);
		document.add(space);
		document.add(table);

		document.close();
	}
	
	/**
	 * 
	 * Method used to generate the products report, using display() method from orderBLL class.
	 * 
	 * @throws FileNotFoundException exception
	 * @throws DocumentException exception
	 * */
	public void PDFOrder() throws FileNotFoundException, DocumentException {
		
		OrdersBLL orderBLL = new OrdersBLL();
		
		Document document = new Document();
		
		String fileName = "Orders-report" + countOrdersReports + ".pdf";
		countOrdersReports++;
		PdfWriter.getInstance(document, new FileOutputStream(fileName));
		
		document.open();//open document

		//create paragraph
		Paragraph intro = new Paragraph("Order Report");
		Paragraph space = new Paragraph(" ");
		PdfPTable table = new PdfPTable(4);

		//create a cell
		PdfPCell c1 = new PdfPCell(new Paragraph("Id"));
		PdfPCell c2 = new PdfPCell(new Paragraph("Customer Name"));
		PdfPCell c3 = new PdfPCell(new Paragraph("Product Name"));
		PdfPCell c4 = new PdfPCell(new Paragraph("Quantity"));

		//add the cells to the table
		table.addCell(c1);
		table.addCell(c2);
		table.addCell(c3);
		table.addCell(c4);
		
		

		for(String[] str: orderBLL.display()) {
			c1 = new PdfPCell(new Paragraph(str[0]));
			c2 = new PdfPCell(new Paragraph(str[1]));
			c3 = new PdfPCell(new Paragraph(str[2]));
			c4 = new PdfPCell(new Paragraph(str[3]));

			table.addCell(c1);
			table.addCell(c2);
			table.addCell(c3);
			table.addCell(c4);

		}

		//add the objects to the document
		document.add(intro);
		document.add(space);
		document.add(space);
		document.add(space);
		document.add(table);

		document.close();
	}
	/**
	 * 
	 * Method used to generate the bills, using printBill().
	 * @param o order
	 * @throws FileNotFoundException exception
	 * @throws DocumentException exception
	 * */
	public void PDFBill(Orders o) throws FileNotFoundException, DocumentException {

		OrdersBLL orderBLL = new OrdersBLL();
		
		Date d = new Date();
		
		Document document = new Document();
		
		String fileName = "Bill-report" + countBills + ".pdf";
		countBills++;
		PdfWriter.getInstance(document, new FileOutputStream(fileName));
		
		document.open();//open document

		//create paragraph
		Paragraph intro = new Paragraph("Bill");
		Paragraph space = new Paragraph(" ");
		Paragraph date = new Paragraph(d.toString());
		PdfPTable table = new PdfPTable(5);

		//create a cell
		PdfPCell c1 = new PdfPCell(new Paragraph("Id"));
		PdfPCell c2 = new PdfPCell(new Paragraph("Customer Name"));
		PdfPCell c3 = new PdfPCell(new Paragraph("Product Name"));
		PdfPCell c4 = new PdfPCell(new Paragraph("Quantity"));
		PdfPCell c5 = new PdfPCell(new Paragraph("Total price"));


		//add the cells to the table
		table.addCell(c1);
		table.addCell(c2);
		table.addCell(c3);
		table.addCell(c4);
		table.addCell(c5);

		String[] arr = orderBLL.printBill(o);
		c1 = new PdfPCell(new Paragraph(arr[0]));
		c2 = new PdfPCell(new Paragraph(arr[1]));
		c3 = new PdfPCell(new Paragraph(arr[2]));
		c4 = new PdfPCell(new Paragraph(arr[3]));
		c5 = new PdfPCell(new Paragraph(arr[4]));


		table.addCell(c1);
		table.addCell(c2);
		table.addCell(c3);
		table.addCell(c4);
		table.addCell(c5);


		//add the objects to the document
		document.add(space);
		document.add(intro);
		document.add(space);
		document.add(date);
		document.add(space);
		document.add(table);

		document.close();
	}

	/**
	 * 
	 * Method used to generate the error report.
	 * 
	 * @throws FileNotFoundException exception
	 * @throws DocumentException exception
	 * */
	public void PDFOrderError() throws FileNotFoundException, DocumentException {
		
		Date d = new Date();

		Document document = new Document();
		
		String fileName = "Order-error" + countErrorReports + ".pdf";
		countErrorReports++;
		PdfWriter.getInstance(document, new FileOutputStream(fileName));
		
		document.open();//open document

		//create paragraph
		Paragraph intro = new Paragraph("Under Stock");
		Paragraph space = new Paragraph(" ");
		Paragraph date = new Paragraph(d.toString());
		Paragraph text = new Paragraph("We are sorry, the products are out of stock! Please, come back later!");
		

		//add the objects to the document
		document.add(intro);
		document.add(space);
		document.add(date);
		document.add(space);
		document.add(text);

		document.close();
	}
}

