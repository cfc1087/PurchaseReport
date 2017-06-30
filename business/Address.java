package Cossu.bcs345.hwk.purchases.business;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * Contains Member variables and Methods for class Address.
 * This class reads and writes an address from a text file.
 * 
 *
 * @author Christopher Cossu
 * @version 1.0
 * @since 10/10/2016
 */
public class Address {

	private String Number;
	private String Street;
	private String City;
	private String State;
	private String Zip;
	
	/**
	 * Default constructor. This sets the member variables
	 * to a default value
	 */
	public Address(){
		//**********************
		//Set the default values
		//**********************
		Number = "";
		Street = "";
		City = "";
		State = "";
		Zip = "";
	}
	/**
	 * Constructor that sets the values of each member variable to the
	 * corresponding value.
	 * 
	 * @param number House number.
	 * @param street Street name.
	 * @param city Name of the city.
	 * @param state Name of the state.
	 * @param zip Zip code.
	 */
	public Address(String number, String street, String city, String state, String zip){
		Number = number;
		Street = street;
		City = city;
		State = state;
		Zip = zip;
	}
	
	/**
	 * This method returns the number.
	 * 
	 * @return The Number.
	 */
	public String GetNumber(){
		//get the house number
		return Number;
	}
	/**
	 * This method returns the street.
	 * 
	 * @return The Street name.
	 */
	public String GetStreet(){
		//get the street name
		return Street;
	}
	/**
	 * This method returns the City
	 * 
	 * @return The City.
	 */
	public String GetCity(){
		//get the city
		return City;
	}
	/**
	 * This method returns the State.
	 * 
	 * @return The State.
	 */
	public String GetState(){
		//get the state
		return State;
	}
	/**
	 *This method returns the Zip code.
	 *
	 * @return The Zip code.
	 */
	public String GetZip(){
		//get the zip code
		return Zip;
	}
	/**
	 * This method sets the house number.
	 * 
	 * @param num House number.
	 * 
	 */
	public void SetNumber(String num){
		//set the house number
		Number = num;
	}
	/**
	 * This method sets the street name.
	 * 
	 * @param street Street name.
	 */
	public void SetStreet(String street){
		//set the street name
		Street = street;
	}
	/**
	 * This method sets the city.
	 * 
	 * @param city Name of the city.
	 */
	public void SetCity(String city){
		//set the city
		City = city;
	}
	/**This method sets the state.
	 * 
	 * @param state Name of the State.
	 */
	public void SetState(String state){
		//set the state
		State = state;
	}
	/**
	 * This method sets the zip code.
	 * 
	 * @param zip Zip code.
	 */
	public void SetZip(String zip){
		//set the zip code
		Zip = zip;
	}
	/**
	 * This method writes the house number, street name, city,
	 * state, and zip code to a file.
	 * 
	 * @param ps instance of PrintStream containing the output file.
	 */
	public void Write(PrintStream ps){
		//*********************************************
		//Print the member variables to the output file.
		//*********************************************
		ps.println(Number);
		ps.println(Street);
		ps.println(City);
		ps.println(State);
		ps.println(Zip);
	}
	/**
	 * This method reads an address from a file and sets the 
	 * corresponding member variables.
	 * 
	 * @param s instance of Scanner containing the name of the input file.
	 */
	public void Read(Scanner s){
		//****************************************
		//Read in from the input file 
		//and set the member variables accordingly
		//****************************************
		Number = s.nextLine();
		Street = s.nextLine();
		City = s.nextLine();
		State = s.nextLine();
		Zip = s.nextLine();
	}
	/**
	 * This method puts the address in JSON format
	 * 
	 * @return A string containing the JSON format of the address
	 */
	public String GetJSON(){
		//JSON format of the address
		String s = "";
		s = String.format("{ \"number\" : \"%s\", \"street\" : \"%s\" , \"city\" : \"%s\", \"state\" : \"%s\", \"zip\" : \"%s\" }",Number,Street,City,State,Zip);
		return s;
	}
	/**
	 * This method displays an address to the user.
	 * @return A string containing the address.
	 */
	@Override
	public String toString(){
		//show the address
		String s = String.format("%s %s\n%s, %s %s",Number,Street,City,State,Zip);
		return s;
	}
	
}