package Cossu.bcs345.hwk.purchases.business;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Contains member variables and methods for class PurchaseCollection. This
 * class reads and writes a collection of purchases that were made by a customer
 * as well as their information.
 * 
 * @author Christopher Cossu
 *
 * @since 12/08/2016 method GetPurchases() was added to the class.
 */
public class PurchaseCollection {

	private Customer Customer;
	private Purchase[] Purchase;

	/**
	 * Default constructor. Initializes member variables to default values.
	 *
	 */
	public PurchaseCollection() {
		Customer = new Customer();
		Purchase = null;
	}

	/**
	 * This method sets the Customer's information
	 * 
	 * @param c
	 *            instance of class Customer that contains customer information
	 */
	public void SetCustomer(Customer c) {
		Customer = c;
	}

	/**
	 * This method gets the Customer's first and last name and their address.
	 * 
	 * @return Customer's first and last name and address.
	 */
	public Customer GetCustomer() {
		return Customer;
	}

	/**
	 * This method gets the largest purchase from the collection.
	 * 
	 * @return The max purchase.
	 * @throws NullPointerException
	 *             Throw if purchase collection is empty
	 */
	public Purchase GetMaxPurchase() throws NullPointerException {
		double MaxPurchase = 0.0;
		double Cost = 0.0;
		int index = 0;
		if (Purchase == null) {
			throw new NullPointerException();
		}

		for (int i = 0; i < Purchase.length; i++) {
			Cost = Purchase[i].GetProduct().GetPrice() * Purchase[i].GetQuantity();
			if (Cost > MaxPurchase) {
				MaxPurchase = Cost;
				index = i;
			}
		}
		return Purchase[index];
	}

	/**
	 * This method gets the purchase information from a specific index
	 * 
	 * @param index
	 *            The given index of the purchase collection
	 * @return The purchase information
	 * @throws ArrayIndexOutOfBoundsException
	 *             Throw if user enters an out of bounds index
	 * @throws NullPointerException
	 *             Throw if purchase collection is empty
	 */
	public Purchase GetByIndex(int index) throws ArrayIndexOutOfBoundsException, NullPointerException {
		if (Purchase == null) {
			throw new NullPointerException();
		} else if (index < 0 || index > Purchase.length) {
			throw new ArrayIndexOutOfBoundsException();
		}
		return Purchase[index];
	}

	/**
	 * This method creates a report of all purchases a customer had made.
	 * 
	 * @param ps
	 *            Instance of PrintStream containing the output file.
	 * @throws NullPointerException
	 *             Throw if purchase collection is empty
	 */
	public void Report(PrintStream ps) throws NullPointerException {
		if (Purchase == null) {
			throw new NullPointerException();
		}

		double Cost = 0.0;
		double TotalCost = 0.0;
		int TotalQuantity = 0;

		ps.printf("Purchase Report\n--------------\n");
		ps.printf("%s\n", Customer.toString());
		ps.print("\n");
		ps.printf("%-30s %12s %10s %12s \n", "Description", "Price", "Quantity", "Cost");
		ps.printf("%-30s %12s %10s %12s \n", "-----------", "-----", "--------", "----");
		for (int i = 0; i < Purchase.length; i++) {
			// Calculates the cost depending on the price and quantity
			Cost = Purchase[i].GetProduct().GetPrice() * Purchase[i].GetQuantity();
			// prints to the output file the purchases information
			ps.printf("%-30s %12.2f %10d %12.2f \n", Purchase[i].GetProduct().GetDescription(),
					Purchase[i].GetProduct().GetPrice(), Purchase[i].GetQuantity(), Cost);
			// calculates the total cost of purchases
			TotalCost = TotalCost + Cost;
			// Calculates the total number of items sold
			TotalQuantity = TotalQuantity + Purchase[i].GetQuantity();
		}
		ps.printf("%-30s %12s %10s %12s \n", "-----------", "-----", "--------", "----");

		// Prints the total number of items purchased and the total cost.
		ps.printf("%-30s %23s %12.2f", "Total", TotalQuantity, TotalCost);

	}

	/**
	 * This method writes to an output file the contents of the Purchase
	 * Collection instance.
	 * 
	 * @param ps
	 *            Instance of PrintStream containing the output file.
	 * @throws NullPointerException
	 *             Throw if purchase collection is empty
	 */
	public void Write(PrintStream ps) throws NullPointerException {
		if (Purchase == null) {
			throw new NullPointerException();
		}
		Customer.Write(ps);
		ps.println(Purchase.length);
		for (int i = 0; i < Purchase.length; i++) {
			Purchase[i].Write(ps);
		}
	}

	/**
	 * This method reads from a file the collection of purchases made and sets
	 * the corresponding member variables.
	 * 
	 * @param s
	 *            Instance of Scanner containing the input file.
	 */
	public void Read(Scanner s) {
		Customer.Read(s);
		int PurchaseCount = 0;
		PurchaseCount = s.nextInt();

		if (PurchaseCount == 0) {
			Purchase = null;
		} else {
			s.nextLine();
			Purchase = new Purchase[PurchaseCount];
			for (int i = 0; i < Purchase.length; i++) {
				Purchase[i] = new Purchase();
				Purchase[i].Read(s);
				if (s.hasNext()) {
					s.nextLine();
				}
			}

		}

	}

	/**
	 * This method returns the purchase collection in JSON format.
	 * 
	 * @return String containing the JSON format of the purchase collection.
	 * @throws NullPointerException
	 *             Throw if purchase collection is empty
	 */
	public String GetJSON() throws NullPointerException {
		if (Purchase == null) {
			throw new NullPointerException();
		}

		String s = "";
		String l = "";
		s = String.format("{\"customer\" : %s ,\n \"purchases\" : [ ", Customer.GetJSON());
		for (int i = 0; i < Purchase.length; i++) {
			if (i == Purchase.length - 1) {
				l = String.format("%s]} ", Purchase[i].GetJSON());
				s = s + l;
			} else {
				l = String.format("%s , ", Purchase[i].GetJSON());
				s = s + l;
			}
		}
		return s;
	}

	/**
	 * This method shows the user the Customer information and the purchases
	 * that were made.
	 * 
	 * @return A string containing the Customer information and purchases made.
	 * @throws NullPointerException
	 *             Throw if purchase collection is empty
	 */
	@Override
	public String toString() throws NullPointerException {
		if (Purchase == null) {
			throw new NullPointerException();
		}

		String s = "";
		String l = "";
		s = String.format("%s\n", Customer.toString());
		for (int i = 0; i < Purchase.length; i++) {
			l = String.format("%s\n", Purchase[i].toString());
			s = s + l;
		}
		return s;
	}

	/**
	 * This method is used to return the Purchase array for the
	 * PurchaseApplication class so that the OpenItem event handler can populate
	 * the ListView of the Purchase tab
	 * 
	 * @return The Purchase array
	 */
	public Purchase[] GetPurchases() {
		Purchase[] Purchases = new Purchase[Purchase.length];
		Purchases = Purchase;

		return Purchases;
	}
}
