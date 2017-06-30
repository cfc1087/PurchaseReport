package Cossu.bcs345.hwk.purchases.presentation;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.InputMismatchException;
import java.util.Scanner;

import Cossu.bcs345.hwk.purchases.business.PurchaseCollection;

/**
 * Contains member variables and methods for class PurchaseCollectionConsoleUI.
 * This class shows the user interface when the method is called and process the
 * user's selection.
 * 
 * @author Christopher Cossu
 * 
 * @since 11/16/2016
 * 
 */
public class PurchaseCollectionConsoleUI {
	/**
	 * This method will show the user interface and process their selection.
	 * 
	 * @throws FileNotFoundException
	 *             Throw if file is not found
	 */
	public void ShowUI() throws FileNotFoundException {

		PurchaseCollection pc = new PurchaseCollection();

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
				System.out.println("\nPurchaseCollection UI");
				System.out.println("-----------------------");
				System.out.println("1 - Read PurchaseCollection from file");
				System.out.println("2 – Write PurchaseCollection to file");
				System.out.println("3 – Show Purchase by index");
				System.out.println("4 – Show maximum purchase");
				System.out.println("5 – Show PurchaseCollection as JSON string on screen");
				System.out.println("6 – Show PurchaseCollection report on screen");
				System.out.println("7 – Show PurchaseCollection toString on screen");
				System.out.println("8 – Exit");
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
					Scanner CollectionInput = new Scanner(new FileReader(keyboard.nextLine()));
					pc.Read(CollectionInput);
					// check if the file exist.
				} catch (FileNotFoundException f) {
					System.err.print("That file does not exist. Please try again");
					break;
				}
				break;

			case 2:
				// ******************************************************************
				// Writes data from the Purchase Collection to a user-specified
				// file.
				// ******************************************************************
				try {
					System.out.print("Enter the name of the output file:");
					keyboard = new Scanner(System.in);
					PrintStream PurchaseOutput = new PrintStream(keyboard.nextLine());
					pc.Write(PurchaseOutput);
				} catch (NullPointerException npe) {
					System.err.println("Cannot write the output file if the purchase collection is empty.");
					break;
				}
				break;
			case 3:
				// ************************************************
				// Get the purchase information at the given index.
				// ************************************************
				System.out.print("Please enter the index of the purchase: ");
				keyboard = new Scanner(System.in);
				try {
					int index = keyboard.nextInt();
					System.out.println(pc.GetByIndex(index));
				} catch (InputMismatchException ime) {
					System.err.println("Not a valid input. Please try again.");
					// Consume the \n
					keyboard.nextLine();
					break;
				} catch (ArrayIndexOutOfBoundsException ai) {
					System.err.println("Not a valid index. Please try again \n");
					// Consume the \n
					keyboard.nextLine();
					break;
				} catch (NullPointerException npe) {
					System.err.println("The purchase collection is empty");
					break;
				}
				break;
			case 4:
				// ******************************************************
				// Get the purchase that cost the most in the collection.
				// ******************************************************
				try {
					System.out.println(pc.GetMaxPurchase());
				} catch (NullPointerException npe) {
					System.err.println("The purchase collection is empty");
					break;
				}
				break;
			case 5:
				// **************************************************
				// Show the purchase collection made in JSON format.
				// **************************************************
				try {
					System.out.println(pc.GetJSON());
				} catch (NullPointerException npe) {
					System.err.println("The purchase collection is empty. Cannot show the JSON String on screen.");
					break;
				}
				break;
			case 6:
				// ***********************************************
				// Show the purchase collection report on screen.
				// ***********************************************
				try {
					pc.Report(System.out);
				} catch (NullPointerException npe) {
					System.err.println("The purchase collection is empty. Cannot show the purchase report on screen.");
					break;
				}
				break;
			case 7:
				// *************************************************
				// Show the purchase collection toString on screen.
				// *************************************************
				try {
					System.out.println(pc.toString());
				} catch (NullPointerException npe) {
					System.err.println("The purchase collection is empty. Cannot show the toString on screen");
					break;
				}
				break;
			case 8:
				// Exit the menu
				break;
			default:
				break;
			}
		} while (choice != 8);

	}

}
