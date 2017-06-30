package Cossu.bcs345.hwk.purchases.business;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * Contains Member variables and Methods for class Customer. This class reads and
 * writes a customer's first and last name Customer's address from a text file
 * and uses the Address class to read and write the Customer's address from a
 * text file.
 * 
 * @author Christopher Cossu
 * @version 1.0
 * @since 10/24/2016
 */
public class Customer {

	private String First;
	private String Last;
	private Address Address;

	/**
	 * Default Constructor. This sets the member variables to a default value.
	 * 
	 */
	public Customer() {
		// *********************
		// Set the default values
		// *********************
		First = "";
		Last = "";
		Address = new Address();
	}

	/**
	 * This method returns the first name.
	 * 
	 * @return The customer's first name.
	 */
	public String GetFirst() {
		// Get the first name
		return First;
	}

	/**
	 * This method returns the last name.
	 * 
	 * @return The customer's last name.
	 */
	public String GetLast() {
		// Get the last name
		return Last;
	}

	/**
	 * This method returns the address.
	 * 
	 * @return The customer's address.
	 */
	public Address GetAddress() {
		// Get the address
		return Address;
	}

	/**
	 * This method sets the customer's first name.
	 * 
	 * @param first
	 *            Customer's first name.
	 */
	public void SetFirst(String first) {
		// Set the first name
		First = first;
	}

	/**
	 * This method sets the customer's last name.
	 * 
	 * @param last
	 *            Customer's last name.
	 */
	public void SetLast(String last) {
		// Set the last name
		Last = last;
	}

	/**
	 * This method sets the customer's address
	 * 
	 * @param address
	 *            instance of class Address that contains the customer's
	 *            address.
	 */
	public void SetAddress(Address address) {
		// Set the address
		Address = address;
	}

	/**
	 * This method writes the customer's first, last name and their address to a
	 * file
	 * 
	 * @param ps
	 *            instance of PrintStream containing the output file.
	 */
	public void Write(PrintStream ps) {
		// **********************************************
		// Print the member variables to the output file.
		// **********************************************
		ps.println(First);
		ps.println(Last);
		Address.Write(ps);
	}

	/**
	 * This method reads the customer's first, last name, and address and sets
	 * the corresponding member variables.
	 * 
	 * @param s
	 *            instance of Scanner containing name of the input file.
	 */
	public void Read(Scanner s) {
		// ************************************
		// Read in from the input file and
		// set the member variables accordingly
		// ************************************
		First = s.nextLine();
		Last = s.nextLine();
		Address.Read(s);
	}

	/**
	 * This method puts the Customer's first,last name, and address in JSON
	 * format.
	 * 
	 * @return A string containing the JSON format of the Customers information.
	 */
	public String GetJSON() {
		// JSON format of the Customer's first and last name and their address.
		String s = "";
		s = String.format("{\"first\" : \"%s\", \"last\" : \"%s\", \"address\" : %s}", First, Last, Address.GetJSON());
		return s;
	}

	/**
	 * This method displays the Customer's first and last name and address to
	 * the user.
	 * 
	 * @return A string containing the first and last name and address.
	 */
	@Override
	public String toString() {
		// Show the first,last name, and address
		String s = String.format("%s %s \n%s", First, Last, Address.toString());
		return s;
	}
}
