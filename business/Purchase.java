package Cossu.bcs345.hwk.purchases.business;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * Contains Member variables and Methods for class Purchase. This class reads
 * and writes purchase information from a text file.
 * 
 * @author Christopher Cossu
 * @version 1.0
 * @since 10/24/2016
 */

public class Purchase {

	private Product Product;
	private int Quantity;

	/**
	 * Default constructor. Sets member variables to default values.
	 */
	public Purchase() {
		// *********************
		// Set the default values
		// *********************
		Quantity = 0;
		Product = new Product();
	}

	/**
	 * This method returns the quantity of a product was purchased.
	 * 
	 * @return The quantity
	 */
	public int GetQuantity() {
		// Get the quantity
		return Quantity;
	}

	/**
	 * This method gets the product's description and price.
	 * 
	 * @return The product description and price.
	 */
	public Product GetProduct() {
		// Get the product's description and price.
		return Product;
	}

	/**
	 * This method sets the quantity that a product was purchased.
	 * 
	 * @param quantity
	 *            Quantity that was purchased.
	 */
	public void SetQuantity(int quantity) {
		// Sets the quantity
		Quantity = quantity;
	}

	/**
	 * This method sets the product's description and price
	 * 
	 * @param p
	 *            instance of class product that contains the product
	 *            description and price.
	 */
	public void SetProduct(Product p) {
		// Set the product' description and price
		Product = p;
	}

	/**
	 * This method writes the product description, price, and quantity to a
	 * file.
	 * 
	 * @param ps instance of PrintStream containing the output file.
	 */
	public void Write(PrintStream ps) {
		// **********************************************
		// Print the member variables to the output file.
		// **********************************************
		Product.Write(ps);
		ps.println(Quantity);
	}

	/**
	 * This method reads the Product's description, price, and the quantity that
	 * was purchased from a file.
	 * 
	 * @param s  instance of Scanner containing name of the input file.
	 */
	public void Read(Scanner s) {
		// ************************************
		// Read from the input file and
		// set the member variables accordingly
		// ************************************
		Product.Read(s);
		Quantity = s.nextInt();
	}

	/**
	 * This method puts the Purchase information in JSON format.
	 * 
	 * @return A string containing the JSON format of the purchase information
	 */
	public String GetJSON() {
		// JSON format of the product description, price, and quantity.
		String s = String.format("{ \"product\" : %s, \"quantity\" : %d}", Product.GetJSON(), Quantity);
		return s;
	}

	/**
	 * This method displays the display the purchase information to the user.
	 * 
	 * @return A string containing the purchase information.
	 */
	@Override
	public String toString() {
		// Show the quantity, product's description and price.
		String s = String.format("%d, %s", Quantity, Product.toString());
		return s;
	}

}
