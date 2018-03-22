/*
 * Name:  Viktor Langeryt and Robert Verdi
*   Assignment:  Video Game Inventory (PROG24178 Final Project)
*   Program: Internet Communications Technology

 * This is a program that will allow a store owner to maintain an inventory of 
 * video games. They will be organized by title, platform, year made, price, 
 * publisher, and whether the game in inventory is a used or new game.
 */
package prog24178.javafx;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
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
    
    @Override
    public void start(Stage primaryStage) {
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });
        
        StackPane root = new StackPane();
        root.getChildren().add(btn);
        
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
