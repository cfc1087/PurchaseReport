package Cossu.bcs345.hwk.purchases.presentation;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import java.io.IOException;

import java.io.PrintStream;
import java.util.Scanner;

import Cossu.bcs345.hwk.purchases.business.Purchase;
import Cossu.bcs345.hwk.purchases.business.PurchaseCollection;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * 
 * This class displays the main window for the Purchases GUI. The GUI will give
 * the user a menu to either Open and display the Purchase information, Save the
 * information to a file or save the information to a report.
 * 
 * @author Christopher Cossu
 *
 */
public class PurchasesApplication extends Application {

	protected PurchaseCollection Purchases;
	private VBox vboxCustomer;
	private VBox vboxPurchases;
	private Scene scene;
	private TabPane tabpane;
	private MenuBar menuBar;
	private MenuItem OpenItem;
	private MenuItem SaveAsItem;
	private MenuItem SaveReportItem;
	private MenuItem ExitItem;
	private TextField FirstNameText;
	private TextField LastNameText;
	private TextField NumberText;
	private TextField StreetText;
	private TextField CityText;
	private TextField StateText;
	private TextField ZipText;

	ObservableList<String> items = FXCollections.observableArrayList();
	private ListView<String> list = new ListView<>();

	/**
	 * This method will create the main window for the GUI
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {

		// Method to create the MenuBar;
		createMenuBar();

		// Method to create the VBox for the customer tab
		createVboxCustomer();

		// Method to create the VBox for the purchases tab
		createVboxPurchases();

		// Method to create the Customer and Purchases tab;
		createTabs();

		// The VBox that will be the root node
		VBox vboxPrimary = new VBox();
		vboxPrimary.getChildren().addAll(menuBar, tabpane);

		// The container for all content
		scene = new Scene(vboxPrimary, 400, 400);

		// **************************
		// Create the Stage
		// **************************
		primaryStage.setScene(scene);
		primaryStage.setTitle("Purchases");
		primaryStage.setHeight(500);
		primaryStage.setWidth(500);
		primaryStage.show();

		// ***************************************************
		// EventHandler for the MenuItem OpenItem
		// ***************************************************
		OpenItem.setOnAction(new EventHandler<ActionEvent>() {
			FileReader fr;

			/**
			 * This method will read the selected file after the user selects
			 * "Open" in the menu.
			 */
			@Override
			public void handle(ActionEvent e) {
				try {
					list.setItems(items);
					String FileName = "";
					FileChooser filechooser = new FileChooser();
					filechooser.setTitle("Open PurchaseCollection File");
					File file = filechooser.showOpenDialog(primaryStage);
					if (file != null) {
						FileName = file.getName();
					}
					fr = new FileReader(FileName);

				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				Scanner s = new Scanner(fr);
				Purchases.Read(s);

				FirstNameText.setText(Purchases.GetCustomer().GetFirst());
				LastNameText.setText(Purchases.GetCustomer().GetLast());
				NumberText.setText(Purchases.GetCustomer().GetAddress().GetNumber());
				StreetText.setText(Purchases.GetCustomer().GetAddress().GetStreet());
				CityText.setText(Purchases.GetCustomer().GetAddress().GetCity());
				StateText.setText(Purchases.GetCustomer().GetAddress().GetState());
				ZipText.setText(Purchases.GetCustomer().GetAddress().GetZip());

				Purchase[] x = Purchases.GetPurchases();

				for (int i = 0; i < x.length; i++) {
					items.add(x[i].toString());
				}
			}
		});

		// ***************************************************
		// EventHandler for the MenuItem SaveAsItem
		// ***************************************************
		SaveAsItem.setOnAction(new EventHandler<ActionEvent>() {

			/**
			 * This method will save the Purchase Collection instance to a user
			 * specified file when the user selects "Save As" from the menu.
			 */
			@Override
			public void handle(ActionEvent e) {

				try {
					list.setItems(items);

					FileChooser filechooser = new FileChooser();
					filechooser.setTitle("Save As Purchase Collection");
					File file = filechooser.showSaveDialog(primaryStage);
					if (file != null) {
						PrintStream ps = new PrintStream(file.getName());
						Purchases.Write(ps);
					}
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

		// ***************************************************
		// EventHandler for the MenuItem SaveReportItem
		// ***************************************************
		SaveReportItem.setOnAction(new EventHandler<ActionEvent>() {
			/**
			 * This method will save the Purchase Collection as a report of the
			 * purchases the customer has made when the user selects "Save
			 * Report" from the menu
			 */
			@Override
			public void handle(ActionEvent e) {

				try {
					list.setItems(items);

					FileChooser filechooser = new FileChooser();
					filechooser.setTitle("Save PurchaseCollection Report");
					File file = filechooser.showSaveDialog(primaryStage);

					if (file != null) {
						PrintStream ps = new PrintStream(file.getName());
						Purchases.Report(ps);
					}
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

		// ***************************************************
		// EventHandler for the MenuItem ExitItem
		// ***************************************************
		ExitItem.setOnAction(new EventHandler<ActionEvent>() {
			/**
			 * This method will close the main window when the user selects
			 * "Exit" from the menu
			 */
			@Override
			public void handle(ActionEvent e) {

				primaryStage.close();

			}
		});
	}

	/**
	 * This method is the default constructor for PurchasesApplication class.
	 */
	public PurchasesApplication() {
		vboxCustomer = new VBox();
		vboxPurchases = new VBox();

		tabpane = new TabPane();
		menuBar = new MenuBar();
		Purchases = new PurchaseCollection();
		FirstNameText = new TextField();
		LastNameText = new TextField();
		NumberText = new TextField();
		StreetText = new TextField();
		CityText = new TextField();
		StateText = new TextField();
		ZipText = new TextField();
	}

	/**
	 * This method creates and returns the MenuBar that contains a single menu
	 * (File) and four menu items:OpenItem,SaveAsItem,SaveReportItem,and Exit.
	 * 
	 * @return The MenuBar
	 */
	private MenuBar createMenuBar() {

		Menu FileMenu = new Menu("File");
		SeparatorMenuItem sep = new SeparatorMenuItem();
		SeparatorMenuItem sep2 = new SeparatorMenuItem();
		OpenItem = new MenuItem("Open...");
		SaveAsItem = new MenuItem("Save As...");
		SaveReportItem = new MenuItem("Save Report...");
		ExitItem = new MenuItem("Exit");
		FileMenu.getItems().addAll(OpenItem, sep);
		FileMenu.getItems().addAll(SaveAsItem, SaveReportItem, sep2, ExitItem);
		menuBar.getMenus().add(FileMenu);

		return menuBar;
	}

	/**
	 * This method creates the VBox for Purchases Tab and contains the purchase
	 * information
	 * 
	 * @return The VBOX for Purchases Tab
	 */
	private VBox createVboxPurchases() {

		vboxPurchases = new VBox();
		vboxPurchases.getChildren().add(list);

		return vboxPurchases;
	}

	/**
	 * This method creates the VBox for the Customer tab which contains the
	 * Customer's information
	 * 
	 * @return The VBox for the Customer Tab
	 */
	private VBox createVboxCustomer() {

		Label FirstName = new Label("First name");
		Label LastName = new Label("Last name");
		Label Number = new Label("Number");
		Label Street = new Label("Street");
		Label City = new Label("City");
		Label State = new Label("State");
		Label Zip = new Label("Zip");

		GridPane gp = new GridPane();
		gp.setHgap(20);
		gp.setVgap(10);

		GridPane.setConstraints(FirstName, 0, 0);
		GridPane.setConstraints(FirstNameText, 10, 0);
		gp.getChildren().addAll(FirstName, FirstNameText);

		GridPane.setConstraints(LastName, 0, 1);
		GridPane.setConstraints(LastNameText, 10, 1);
		gp.getChildren().addAll(LastName, LastNameText);

		GridPane.setConstraints(Number, 0, 2);
		GridPane.setConstraints(NumberText, 10, 2);
		gp.getChildren().addAll(Number, NumberText);

		GridPane.setConstraints(Street, 0, 3);
		GridPane.setConstraints(StreetText, 10, 3);
		gp.getChildren().addAll(Street, StreetText);

		GridPane.setConstraints(City, 0, 4);
		GridPane.setConstraints(CityText, 10, 4);
		gp.getChildren().addAll(City, CityText);

		GridPane.setConstraints(State, 0, 5);
		GridPane.setConstraints(StateText, 10, 5);
		gp.getChildren().addAll(State, StateText);

		GridPane.setConstraints(Zip, 0, 6);
		GridPane.setConstraints(ZipText, 10, 6);
		gp.getChildren().addAll(Zip, ZipText);

		vboxCustomer.getChildren().add(gp);
		return vboxCustomer;
	}

	
	/**
	 * This method creates the TabPane which contains the Customer and Purchases
	 * Tab
	 * 
	 * @return The Tabpane with Customer and Purchase Tabs
	 */
	private TabPane createTabs() {
		tabpane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		Tab TabCustomer = new Tab("Customer");
		Tab TabPurchases = new Tab("Purchases");

		TabCustomer.setContent(vboxCustomer);
		TabPurchases.setContent(vboxPurchases);
		

		tabpane.getTabs().addAll(TabCustomer, TabPurchases);

		return tabpane;
	}

}
