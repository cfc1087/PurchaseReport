package Cossu.bcs345.hwk.purchases.business;

import java.io.PrintStream;
import java.util.Scanner;
/**
 * Contains Member variables and Methods for class Product.
 * This class reads and writes a product descrption and its price from a text file.
 * 
 * @author Christopher Cossu
 * @version 1.0
 * @since 10/10/2010
 * 
 */
public class Product {
	private String Description;
	private double Price;
	
	/**
	 * Default constructor. This sets the member variables to default values.
	 */
	public Product(){
		//**********************
		//set the default values
		//**********************
		Description = "";
		Price = 0.0;
	}
	/**
	 * This method returns the Description of the product.
	 * @return Product description.
	 */
	public String GetDescription(){
		//get the product description
		return Description;
	}
	/**
	 * This method returns the price of the product.
	 * @return Product price.
	 */
	public double GetPrice(){
		//get the price of the product
		return Price;
	}
	/**
	 * This method sets the product description.
	 * 
	 * @param desc The description of the product.
	 */
	public void SetDescription(String desc){
		//set the description of the product
		Description = desc;
	}
	/**
	 * This method sets the price of the product.
	 * 
	 * @param price price of the product.
	 */
	public void SetPrice(double price){
		//set the price of the product
		Price = price;
	}
	/**
	 * This method writes to a file the description and price 
	 * of a product.
	 * 
	 * @param ps Instance of PrintStream containing the output file.
	 */
	public void Write(PrintStream ps){
		//*********************************************
		//Print the member variables to the output file.
		//*********************************************
		ps.println(Description);
		ps.println(Price);
	}
	/**
	 * This method reads from a file a products description and price
	 * and sets the corresponding member variables.
	 * 
	 * @param s Instance of Scanner containing the input file.
	 */
	public void Read(Scanner s){
		//****************************************
		//Read in from the input file 
		//and set the member variables accordingly
		//****************************************
		Description = s.nextLine();
		Price = s.nextDouble();
	}
	/**
	 * This method returns the product's descriptions and price in JSON format.
	 * 
	 * @return String containing the JSON format of the product.
	 */
	public String GetJSON(){
		//JSON format of the Product info
		String	s = String.format("{ \"description\" : \"%s\", \"price\" : %.2f }",Description,Price);
		return s;
	}
	/**
	 * This method shows the user the product description and price.
	 * 
	 * @return A string containing the description and price.
	 */
	@Override
	public String toString(){
		//show description and price
		String s = String.format("%s, $%.2f", Description,Price);
		return s;
	}
	
	
}
