/*
 * Name:  Viktor Langeryt and Robert Verdi
*   Assignment:  Video Game Inventory (PROG24178 Final Project)
*   Program: Internet Communications Technology

 * This is a program that will allow a store owner to maintain an inventory of 
 * video games. They will be organized by title, platform, year made, price, 
 * publisher, and whether the game in inventory is a used or new game.
 */
package prog24178.javafx;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.*;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

/**
 * This is a program that will allow a store owner to maintain an inventory of 
 * video games. They will be organized by title, platform, year made, price, 
 * publisher, and whether the game in inventory is a used or new game. 
 * 
 * @author Viktor Langeryt
 * @author Robert Verdi
 */
public class VideoGameInventory extends Application {

	// Applying stylesheet
	String myStylesheet = getClass().getResource("main.css").toExternalForm();

	// Creating table for display of list
	private TableView listTable = new TableView();
	
	// create radio buttons for search options
	private RadioButton optTitle = new RadioButton("Title");
	private RadioButton optPlatform = new RadioButton("Platform");
	private RadioButton optYear = new RadioButton("Year");
	private RadioButton optPublisher = new RadioButton("Publisher");
	private RadioButton optPrice = new RadioButton("Price");
	private RadioButton optUsed = new RadioButton("Used"); 
	
	// search textfield, label, and "Go" button
	private Label lblSearch = new Label("Search");
	private TextField txtSearch = new TextField();
	private Button btnGo = new Button("Go");
	
	private Button btnEdit = new Button("Edit");
	private Button btnExit = new Button("Exit");
	
//	------------------------ Nodes for edit window ---------------------------
	private TextField txtTitleInput = new TextField("Title must be 30 characters"
			+ " or less");
	private TextField txtPublisherInput = new TextField("Publisher name");
	private TextField txtPrice = new TextField("0.00");
	private TextField txtYear = new TextField("2000");
//	private TextField txtAmount = new TextField(); not sure what this field is
	
	private Label lblTitle = new Label("Title");
	private Label lblUsed = new Label("Used?");
	private Label lblYear = new Label("Year");
	private Label lblPlatform = new Label("Platform");
	private Label lblPrice = new Label("Price: $");
	private Label lblPublisher = new Label("Publisher");
	
	private RadioButton optYes = new RadioButton("Yes");
	private RadioButton optNo = new RadioButton("No");
	
	private ComboBox<Platform> cmbPlatform = new ComboBox<>();
	
	private Button btnBack = new Button("Back");
	private Button btnSave = new Button("Save");
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {

		// make main pane
		VBox mainPane = new VBox();
		mainPane.setSpacing(10);
		
		// Apply stylesheet layout to main page
		mainPane.getStyleClass().add("root");
		mainPane.setPadding(new Insets(10));
		
		// File writing stuff, to be addressed later
		File gameFile = new File("data/GamesList.dat");
//		PrintWriter fileOut = new PrintWriter(new BufferedWriter(new FileWriter(gameFile, false)));
		
		// An instance of GameList, using the class, not a plain old array
		GameList gameList = new GameList();

		// Array of Game objects
		Game[] listOfGames = new Game[10];
		
		/* I think this takes the file contents and writes them to the array 
		(not ArrayList) */
		try (ObjectInputStream in = new ObjectInputStream(new FileInputStream
		(gameFile))) {
			for (int dex = 0; dex < listOfGames.length; dex++) {
				listOfGames[dex] = (Game) in.readObject();
			}
		}

//	---------------------- Stuff for main window ------------------------------- 
		// Columns for list display table
        TableColumn colTitle = new TableColumn("Title");
        TableColumn colPlatform = new TableColumn("Platform");
        TableColumn colYear = new TableColumn("Year");
		TableColumn colPublisher = new TableColumn("Publisher");
		TableColumn colPrice = new TableColumn("Price");
        TableColumn colUsed = new TableColumn("Used?");
		colPublisher.setPrefWidth(100);
		colPlatform.setPrefWidth(100);
		
		listTable.getColumns().addAll(colTitle, colPlatform, colYear, 
				colPublisher, colPrice, colUsed);
		
		// togglegroup for radio buttons
		ToggleGroup searchGroup = new ToggleGroup();
		optTitle.setToggleGroup(searchGroup);
		optPlatform.setToggleGroup(searchGroup);
		optYear.setToggleGroup(searchGroup);
		optPublisher.setToggleGroup(searchGroup);
		optUsed.setToggleGroup(searchGroup);

		// Button text set
		btnGo.setText("Go");
		btnEdit.setText("Edit record");
		btnExit.setText("Exit");
		btnSave.setText("Save");
		
		/* Handler for exit button, I think the weird bit inside makes it write
		*  the array contents to the file when the exit button is closed.
		*/
		btnExit.setOnAction((ActionEvent e) -> {
			try (ObjectOutputStream out = new ObjectOutputStream
		(new FileOutputStream(gameFile))) {
				for (int dex = 0; dex < gameList.size(); dex++) {
					out.writeObject(gameList.get(dex));
					System.exit(0);
				}
			}		catch (IOException ex) {
						Logger.getLogger(VideoGameInventory.class.getName())
								.log(Level.SEVERE, null, ex);
					}
		});
		
		// adding pieces to main pane
		mainPane.getChildren().addAll(getSearchPane(), getTable());
		
//  ------------------------ Stuff for edit window -----------------------------		

		// pane for second window
		GridPane secondPane = new GridPane();
		secondPane.setHgap(10);
		secondPane.setVgap(10);
		
//		secondPane.setGridLinesVisible(true);

		// Creates 6 columns 50 pixels wide
		for (int i = 0; i < 6; i++) {
			ColumnConstraints column = new ColumnConstraints(50);
			secondPane.getColumnConstraints().add(column);
		}
		
		// Assigning yes/no for Used category options to toggle group
		ToggleGroup yesNo = new ToggleGroup();
		optYes.setToggleGroup(yesNo);
		optNo.setToggleGroup(yesNo);
		
		GridPane.setConstraints(lblTitle,			0, 0);
		GridPane.setConstraints(txtTitleInput,		1, 0, 2, 1);
		GridPane.setConstraints(lblYear,			0, 1);
		GridPane.setConstraints(txtYear,			1, 1, 2, 1);
		GridPane.setConstraints(lblPrice,			0, 2);
		GridPane.setConstraints(txtPrice,			1, 2, 2, 1);
		
		GridPane.setConstraints(lblPublisher,		4, 0, 2, 1);
		GridPane.setConstraints(txtPublisherInput,	5, 0, 2, 1);
		GridPane.setConstraints(lblPlatform,		4, 1, 2, 1);
		GridPane.setConstraints(cmbPlatform,		5, 1, 2, 1);
		GridPane.setConstraints(lblUsed,			5, 2);
		GridPane.setConstraints(optYes,				6, 2);
		GridPane.setConstraints(optNo,				6, 3);
		GridPane.setConstraints(btnBack,			4, 4, 2, 1);
		GridPane.setConstraints(btnSave,			3, 4, 2, 1);

		// Creating array with string values for enum names
//		Platform[] vals = Platform.values();
//		String[] platformArr =  new String[vals.length];
//		for (int i = 0; i < vals.length; i++) {
//			platformArr[i] = Platform.valueOf();
//		}
//		for(Platform v : Platform.values()){
//		int i = 0;
//		vals[i] = v;
//		platformArr[i] = vals[i]
//		i++;
//		}
//		cmbPlatform.getItems().addAll(platformArr); 
		cmbPlatform.getItems().addAll(Platform.values()); // not right
		// trying to figure out this enum/combobox shit is pissing me off
		
		secondPane.getChildren().addAll(lblTitle, txtTitleInput,
				txtPublisherInput, lblPrice, txtPrice, txtYear, lblUsed, optYes,
				optNo, lblPlatform, cmbPlatform, lblYear, lblPublisher, btnBack,
				btnSave);

//  ------------------------ End Stuff for edit window -------------------------

        Scene scene1 = new Scene(mainPane, 580, 500);
		Scene scene2 = new Scene(secondPane, 490, 200);
		
		// Handler for Edit button
		btnEdit.setOnAction((ActionEvent e) -> primaryStage.setScene(scene2));
		
				// Handler for Back button
		btnBack.setOnAction((ActionEvent e) -> primaryStage.setScene(scene1));
		
        primaryStage.setTitle("Video Game Inventory");
		scene1.getStylesheets().add(myStylesheet);
		scene2.getStylesheets().add(myStylesheet);
        primaryStage.setScene(scene1);
		primaryStage.setResizable(false); // window not resizable
        primaryStage.show();
    }

	// end of start method
	
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
	/**
	 * Creates and retrieves the top search part of the main pane.
	 * 
	 * @return GridPane of the top search area of screen
	 */
	private GridPane getSearchPane() {
		GridPane searchPane = new GridPane();

		searchPane.setGridLinesVisible(true);
		
		searchPane.setHgap(10);
		searchPane.setVgap(10);

		GridPane.setConstraints(lblSearch,		1, 1);
		GridPane.setConstraints(txtSearch,		2, 1, 2, 1);
		GridPane.setConstraints(btnGo,			4, 1);
		GridPane.setConstraints(optTitle,		0, 2);
		GridPane.setConstraints(optPlatform,	1, 2);
		GridPane.setConstraints(optYear,		2, 2);
		GridPane.setConstraints(optPublisher,	3, 2);
		GridPane.setConstraints(optPrice,		4, 2);
		GridPane.setConstraints(optUsed,		5, 2);
		GridPane.setConstraints(btnEdit,		2, 3);
		GridPane.setConstraints(btnExit,		3, 3);
		
		// width of search text bar
		txtSearch.setMinWidth(200);

		searchPane.getChildren().addAll(lblSearch, txtSearch, btnGo, optTitle, 
				optPlatform, optYear, optPublisher, optPrice, optUsed, btnEdit, 
				btnExit);

		return searchPane;
	}
	
	/**
	 * Retrieves the bottom table pane. 
	 * 
	 * @return a pane with a table containing search results 
	 */
	private VBox getTable() {
		VBox tablePane = new VBox();
		tablePane.setSpacing(5);
		tablePane.getChildren().addAll(listTable);
		return tablePane;
	}
}
