package Cossu.bcs345.hwk.purchases.presentation;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.InputMismatchException;
import java.util.Scanner;

import Cossu.bcs345.hwk.purchases.business.PurchaseCollection;
import javafx.application.Application;

/**
 * Contains main program code that gives the user an option between the
 * CustomerPurchaseConsoleUI, PurchaseCollectionConsoleUI, or
 * PurchasesGraphicalUI
 * 
 * @author Christopher Cossu
 *
 * @since 12/08/2016
 */

public class Main {

	public static void main(String[] args) throws FileNotFoundException {

		Scanner keyboard = new Scanner(System.in);
		int choice = 0;
		do {
			try {
				System.out.println("Choose UI");
				System.out.println("---------");
				System.out.println("1 - CustomerPurchaseConsoleUI");
				System.out.println("2 - PurchaseCollectionConsoleUI");
				System.out.println("3 – PurchasesGraphicalUI");
				System.out.println("4 - Exit");
				System.out.print("Enter Choice: ");
				// user input
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
				// Show the CustomerPurchaseConsoleUI.
				CustomerPurchaseConsoleUI cp = new CustomerPurchaseConsoleUI();
				cp.ShowUI();
				break;
			case 2:
				// Show the PurchaseCollectionConsoleUI.
				PurchaseCollectionConsoleUI pcui = new PurchaseCollectionConsoleUI();
				pcui.ShowUI();
				break;
			case 3:
				// Show the PurchasesGraphicalUI
				PurchasesGraphicalUI pgui = new PurchasesGraphicalUI();
				pgui.ShowUI();
				break;
			case 4:
				// Exit the menu
				break;
			default:
				break;
			}
		} while (choice != 4);

	}

}
