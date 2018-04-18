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
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.*;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
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
	private final TableView<Game> listTable = new TableView();
	
	// Columns for list display table
	TableColumn<Game, String> colTitle = new TableColumn<>("Title");
	TableColumn<Game, String> colPlatform = new TableColumn<>("Platform");
	TableColumn<Game, Integer> colYear = new TableColumn("Year");
	TableColumn<Game, Integer> colStockLevel = new TableColumn("Stock Level");
	TableColumn<Game, String> colPublisher = new TableColumn<>("Publisher");
	TableColumn<Game, Double> colPrice = new TableColumn<>("Price");
	TableColumn<Game, Boolean> colUsed = new TableColumn<>("Used?");

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
	
	private Label lblTitle = new Label("Title");
	private Label lblUsed = new Label("Used?");
	private Label lblYear = new Label("Year");
	private Label lblPlatform = new Label("Platform");
	private Label lblPrice = new Label("Price: $");
	private Label lblPublisher = new Label("Publisher");
	private Label lblStockLevel = new Label("Stock Level");
	
//	------------------------ Nodes for edit window ---------------------------
	private TextField txtTitleInput = new TextField("Title must be 30 characters"
			+ " or less");
	private TextField txtPublisher = new TextField("Publisher name");
	private TextField txtPrice = new TextField("0.00");
	private TextField txtYear = new TextField("2000");
	private TextField txtStockLevel = new TextField("0");

	// Labels for edit window
	private Label lblTitleEditWindow = new Label("Title");
	private Label lblUsedEditWindow = new Label("Used?");
	private Label lblYearEditWindow = new Label("Year");
	private Label lblPlatformEditWindow = new Label("Platform");
	private Label lblPriceEditWindow = new Label("Price: $");
	private Label lblPublisherEditWindow = new Label("Publisher");
	private Label lblStockLevelEditWindow = new Label("Stock Level");
	
	// Display of information labels
	private Label lblTitleDisplay = new Label(" ");
	private Label lblUsedDisplay = new Label(" ");
	private Label lblYearDisplay = new Label(" ");
	private Label lblPlatformDisplay = new Label(" ");
	private Label lblPriceDisplay = new Label(" ");
	private Label lblPublisherDisplay = new Label(" ");
	private Label lblStockLevelDisplay = new Label(" ");
	
	
	private RadioButton optYes = new RadioButton("Yes");
	private RadioButton optNo = new RadioButton("No");
	
	private ComboBox<String> cmbPlatform = new ComboBox<>();
	
	private Button btnSave = new Button("Save");
	private Button btnCancel = new Button("Cancel");
	private Button btnDelete = new Button("Delete");
	
	// Making a list of Games that can be used in this program
	private ObservableList<Game> gameData = 
			FXCollections.observableArrayList();

	@Override
	public void start(Stage primaryStage) throws Exception {

		// make main pane
		HBox mainPane = new HBox();
		VBox leftPane = new VBox();
		GridPane editWindow = new GridPane();
		
		// adding left pane to main pane
		mainPane.getChildren().addAll(leftPane, getRightPane());
		
		leftPane.setSpacing(10);
		
		// Apply stylesheet layout to main pane, and side panes
		leftPane.getStyleClass().add("root");
		leftPane.setPadding(new Insets(10));
		mainPane.getStyleClass().add("root");
		mainPane.setPadding(new Insets(10));
		
		// File writing stuff, to be addressed later
		File gameFile = new File("data/GamesList.dat");
		RandomAccessFile raf = new RandomAccessFile(new File
		("data/GamesList.dat"),"r");

//	---------------------- Stuff for main window ------------------------------- 
		
		// Associates the gameData List with the table
		listTable.setItems(gameData);
		// dummy data for display purposes
		gameData.add(new Game("Witcher 3", "Somepublisher", "Xbox One", 
				2011, 10.00, 2, false));
        gameData.add(new Game("Another game", "Another publisher", 
				"Playstation 4", 2012, 5.00, 3, true));
		
		colPublisher.setPrefWidth(100);
		colPlatform.setPrefWidth(100);

		listTable.getColumns().setAll(colTitle, colPlatform, colYear,
				colPublisher, colPrice, colUsed);

		colTitle.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
		colPlatform.setCellValueFactory(cellData -> cellData.getValue().systemProperty());
		colYear.setCellValueFactory(new PropertyValueFactory<>("year"));
		colStockLevel.setCellValueFactory(new PropertyValueFactory<>("stockLevel"));
		colPublisher.setCellValueFactory(cellData -> cellData.getValue().publisherProperty());
		colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
		colUsed.setCellValueFactory(new PropertyValueFactory<>("used"));

		// Clear game details.
		showGameDetails(null);

		// Listen for selection changes and show the game details when changed.
		listTable.getSelectionModel().selectedItemProperty().addListener(
		(observable, oldValue, newValue) -> showGameDetails((Game) newValue));

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
//			try (ObjectOutputStream out = new ObjectOutputStream
//		(new FileOutputStream(gameFile))) {
//				for (int dex = 0; dex < gameData.size(); dex++) {
//					out.writeObject(gameData.get(dex));	
//				}
//			}		catch (IOException ex) {
//						System.out.println(ex.getMessage());
//						System.exit(1);
//					}
			System.exit(0);
		});
		
		// adding pieces to main pane
		leftPane.getChildren().addAll(getSearchPane(), getTable());

		// Setting the primary panes of the two windows
        Scene scene1 = new Scene(mainPane, 1080, 500);
		Scene scene2 = new Scene(editWindow, 490, 200);
		
		// Handler for Edit button
		btnEdit.setOnAction((ActionEvent e) -> primaryStage.setScene(scene2));
		
				// Handler for Back button
//		btnBack.setOnAction((ActionEvent e) -> primaryStage.setScene(scene1));
		
		// Handler for New button
//		btnNew.setOnAction((ActionEvent e) -> )
		
		/* Handler for delete button, uses the currently selected record in the 
		table to delete based on it's index in the list, we still need to add 
		a dialog for confirmation */
		btnDelete.setOnAction((ActionEvent e) -> {

			if ((listTable.getSelectionModel().
					getSelectedIndex()) >= 0) {
				listTable.getItems().remove(listTable.getSelectionModel().
						getSelectedIndex());
			} else {
				// Nothing selected.
				Alert alert = new Alert(AlertType.WARNING);
				alert.initOwner(primaryStage);
				alert.setTitle("No Selection");
				alert.setHeaderText("No game is selected");
				alert.setContentText("Can't delete, no selected game.");

				alert.showAndWait();
			}
		}
		);

// ---------------------- Stuff for Edit Window --------------------------------
	
		ComboBox<String> cmbPlatformEditWindow = new ComboBox();
		String[] platformArrayEditWindow = {"Xbox One","Xbox 360", 
			"Playstation 3", "Playstation 4", "PC", "Nintendo Switch",
			"Nintendo Wii", "Nintendo Wii-U", "Nintendo 3DS"};
		cmbPlatformEditWindow.getItems().addAll(platformArrayEditWindow);
		
		
		GridPane.setConstraints(lblTitleEditWindow,			0, 0);
		GridPane.setConstraints(txtTitleInput,				1, 0, 2, 1);
		GridPane.setConstraints(lblYearEditWindow,			0, 1);
		GridPane.setConstraints(txtYear,					1, 1, 2, 1);
		GridPane.setConstraints(lblPriceEditWindow,			0, 2);
		GridPane.setConstraints(txtPrice,					1, 2, 2, 1);
		
		GridPane.setConstraints(lblPublisherEditWindow,		4, 0, 2, 1);
		GridPane.setConstraints(txtPublisher,				5, 0, 2, 1);
		GridPane.setConstraints(lblPlatformEditWindow,		4, 1, 2, 1);
		GridPane.setConstraints(lblStockLevelEditWindow,	4, 1, 2, 1);
		GridPane.setConstraints(txtStockLevel,				5, 0, 2, 1);
		GridPane.setConstraints(cmbPlatformEditWindow,		5, 1, 2, 1);
		GridPane.setConstraints(lblUsedEditWindow,			4, 2);
		GridPane.setConstraints(optYes,						6, 2);
		GridPane.setConstraints(optNo,						6, 3);
		GridPane.setConstraints(btnCancel,					6, 3);

		editWindow.getChildren().addAll(lblTitleEditWindow, txtTitleInput, 
				lblYearEditWindow, txtYear, lblPriceEditWindow, txtPrice, 
				lblPublisherEditWindow, txtPublisher, lblPlatformEditWindow,
				cmbPlatform, lblUsedEditWindow, optYes, optNo, 
				lblStockLevelEditWindow, txtStockLevel, btnCancel);
		
		/* Sets the text field values to the values of the selected game when
		*  the edit window open
		*/
//		Game selectedGame = listTable.getSelectionModel().getSelectedItem();
//		txtTitleInput.setText(selectedGame.getTitle());
//        txtYear.setText(Integer.toString(selectedGame.getReleaseYear()));
//		txtStockLevel.setText(Integer.toString(selectedGame.getStockLevel()));
//        txtPrice.setText(Double.toString(selectedGame.getPrice()));
//        txtPublisher.setText(selectedGame.getPublisher());
//		cmbPlatformEditWindow.getSelectionModel.select(selectedGame.getSelectedIndex()); 
		/* this is confusing, it is trying to get an index value, but since 
		platform is a string, it doesn't like it*/
		
//		if (optYes.isSelected()) {
//		} else if (optNo.isSelected()) {
//		} also not sure what to do with these

		/* Cancel button handler, will close edit window without making any 
		changes when pressed */
		btnCancel.setOnAction((ActionEvent e) -> {
		primaryStage.setScene(scene1);
		});

// --------------------------- End Edit Window ---------------------------------

		
		primaryStage.setTitle("Video Game Inventory");
		scene1.getStylesheets().add(myStylesheet);
		scene2.getStylesheets().add(myStylesheet);
		
		// Primary window Stage
        primaryStage.setScene(scene1);
		// Edit window Stage
		
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
	 * Fills all text fields to show details about the game. This is for the 
	 * right side of the window
	 * If the specified game is null, all text labels are cleared.
	 *
	 * @param game the game or null, whichever
	 */
	private void showGameDetails(Game game) {
		if (game != null) {
			// Fill the labels with info from the person object.
			lblTitleDisplay.setText(game.getTitle());
			lblPublisherDisplay.setText(game.getPublisher());
			lblPlatformDisplay.setText(game.getPlatform());
			lblYearDisplay.setText(Integer.toString(game.getReleaseYear()));
			lblPriceDisplay.setText(Double.toString(game.getPrice()));
			lblStockLevelDisplay.setText(Integer.toString(game.getStockLevel()));
			lblUsedDisplay.setText(Boolean.toString(game.isUsed()));

		} else {
			// If the game is null, remove text.
			lblTitleDisplay.setText("");
			lblPublisherDisplay.setText("");
			lblPlatformDisplay.setText("");
			lblYearDisplay.setText("");
			lblPriceDisplay.setText("");
			lblStockLevelDisplay.setText("");
			lblUsedDisplay.setText("");
		}
	}
	
	/**
	 * Creates and retrieves the top search part of the main pane.
	 * 
	 * @return GridPane of the top search area of screen
	 */
	private GridPane getSearchPane() {
		GridPane searchPane = new GridPane();

//		searchPane.setGridLinesVisible(true);
		
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
				optPlatform, optYear, optPublisher, optPrice, optUsed, 
				btnEdit, btnExit);

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
	
	private GridPane getRightPane() {
	GridPane rightPane = new GridPane();

		rightPane.setHgap(10);
		rightPane.setVgap(10);
		
		rightPane.setGridLinesVisible(true);

		// Creates 6 columns 50 pixels wide
		for (int i = 0; i < 6; i++) {
			ColumnConstraints column = new ColumnConstraints(50);
			rightPane.getColumnConstraints().add(column);
		}
		
		// Assigning yes/no for Used category options to toggle group
		ToggleGroup yesNo = new ToggleGroup();
		optYes.setToggleGroup(yesNo);
		optNo.setToggleGroup(yesNo);
		
		GridPane.setConstraints(lblTitle,			0, 0);
		GridPane.setConstraints(lblTitleDisplay,	1, 0);
		GridPane.setConstraints(lblYear,			0, 1);
		GridPane.setConstraints(lblYearDisplay,		1, 1);
		GridPane.setConstraints(lblPrice,			0, 2);
		GridPane.setConstraints(lblPriceDisplay,	1, 2);
		
		GridPane.setConstraints(lblPublisher,		4, 0, 2, 1);
		GridPane.setConstraints(lblPlatform,		4, 1, 2, 1);
		GridPane.setConstraints(lblPlatformDisplay,	5, 1, 2, 1);
		GridPane.setConstraints(lblUsed,			4, 2);
		GridPane.setConstraints(lblUsedDisplay,		5, 2);
		GridPane.setConstraints(btnSave,			3, 4, 2, 1);
		GridPane.setConstraints(btnDelete,			2, 4, 2, 1);

		/*Creating array with string values for platform names, adding to 
		 * combobox
		 */
		String[] platformArray = {"Xbox One","Xbox 360", "Playstation 3", 
		"Playstation 4", "PC", "Nintendo Switch", "Nintendo Wii", 
		"Nintendo Wii-U", "Nintendo 3DS"};
		cmbPlatform.getItems().addAll(platformArray);

		rightPane.getChildren().addAll(lblTitle, lblTitleDisplay , lblPrice, 
				lblPriceDisplay, lblUsed, lblUsedDisplay, lblPlatform, 
				lblPlatformDisplay, lblYear, lblYearDisplay, lblPublisher, 
				lblPublisherDisplay, btnSave, btnDelete);

	
	return rightPane;
	}
}
