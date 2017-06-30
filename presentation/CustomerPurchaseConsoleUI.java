package Cossu.bcs345.hwk.purchases.presentation;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.InputMismatchException;
import java.util.Scanner;

import Cossu.bcs345.hwk.purchases.business.*;

/**
 * Contains member variables and methods for class CustomerPurchaseConsoleUI.
 * This class shows the user interface when the method is called and process the
 * user's selection.
 * 
 * @author Christopher Cossu
 * 
 * @since 10/24/2016
 */
public class CustomerPurchaseConsoleUI {

	/**
	 * This method will show the user interface and process their selection.
	 * 
	 * @throws FileNotFoundException Throw if file is not found
	 */
	public void ShowUI() throws FileNotFoundException {

		Customer c = new Customer();
		Purchase p = new Purchase();

		Scanner keyboard = new Scanner(System.in);
		int choice = 0;

		// *********************************************************
		// Keep showing the user menu until the user chooses to exit.
		// *********************************************************
		do {
			// ******************************
			// Shows the user menu on screen.
			// ******************************
			try {
				choice = 0;
				// added "\n" so it looks neater in the console window
				System.out.println("\nCustomer/Purchase UI");
				System.out.println("--------------------");
				System.out.println("1 - Read customer from file");
				System.out.println("2 – Write customer to file");
				System.out.println("3 – Show customer info on screen");
				System.out.println("4 – Show customer JSON on screen");
				System.out.println("5 – Read purchase from file");
				System.out.println("6 – Write purchase to file");
				System.out.println("7 – Show purchase info on screen");
				System.out.println("8 – Show purchase JSON on screen");
				System.out.println("9 - Exit");
				System.out.print("Enter Choice:");

				// User input
				choice = keyboard.nextInt();

				// *********************************************
				// Make sure the user input is a valid selection
				// *********************************************
			} catch (InputMismatchException ime) {
				System.err.print("That is not a valid selection. Please try again.");

				// consume the \n.
				keyboard.nextLine();
			}
			// ****************************
			// Process the user's selection.
			// ****************************
			switch (choice) {

			case 1:
				// ******************************************************
				// Read in data for a customer from a user-specified file.
				// ******************************************************
				try {
					System.out.print("Enter the name of the file:");
					keyboard = new Scanner(System.in);
					Scanner CustomerInput = new Scanner(new FileReader(keyboard.nextLine()));
					c.Read(CustomerInput);
					// check if the file exist.
				} catch (FileNotFoundException f) {
					System.err.print("That file does not exist. Please try again");
					break;
				}
				break;
			case 2:
				// ****************************************************************
				// Writes data from the customer instance to a user-specified
				// file.
				// ****************************************************************
				System.out.print("Enter the name of the output file:");
				keyboard = new Scanner(System.in);
				PrintStream CustomerOutput = new PrintStream(keyboard.nextLine());
				c.Write(CustomerOutput);
				break;
			case 3:
				// Shows the customer information on screen.
				System.out.println(c.toString());
				break;
			case 4:
				// Show the JSON format of the customer on screen.
				System.out.println(c.GetJSON());
				break;
			case 5:
				// ******************************************************
				// Read in data for a purchase from a user-specified file
				// and check if the file exist.
				// ******************************************************
				try {
					System.out.print("Enter the name of the file:");
					keyboard = new Scanner(System.in);
					Scanner PurchaseInput = new Scanner(new FileReader(keyboard.nextLine()));
					p.Read(PurchaseInput);
					// check if the file exist.
				} catch (FileNotFoundException f) {
					System.err.print("That file does not exist. Please try again");
					break;
				}
				break;
			case 6:
				// ****************************************************************
				// Writes data from the Purchase instance to a user-specified
				// file.
				// ****************************************************************
				System.out.print("Enter the name of the output file:");
				keyboard = new Scanner(System.in);
				PrintStream PurchaseOutput = new PrintStream(keyboard.nextLine());
				p.Write(PurchaseOutput);
				break;
			case 7:
				// Show purchase information on screen.
				System.out.println(p.toString());
				break;
			case 8:
				// show the JSON format of the purchase on screen.
				System.out.println(p.GetJSON());
				break;
			case 9:
				// Exit the menu.
				break;
			default:
				break;
			}
		} while (choice != 9);
	}
}