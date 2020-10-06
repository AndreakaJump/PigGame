package application;

import java.io.IOException;
import java.time.LocalDateTime;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NameViewController 
{ 
	@FXML private TextField p1Text;
    @FXML private TextField p2Text;
    @FXML private Button goBtn;
    
	/*
	 * When the go button is pressed, it both loads the next scene
	 * as well as passes a game object to the next scene
	 */
    @FXML
    void changeSceneToGameView(ActionEvent event) throws IOException 
    {
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(getClass().getResource("GameView.fxml"));
    	Parent parent = loader.load();
    	GameViewController controller = loader.getController();    	
    	controller.getGameInfo(startGame());
    	Scene scene = new Scene(parent);
    	scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
    	Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
    	window.setScene(scene);
    	window.show();
    }
    
    /*
     * Returns a game object with player names from the text fields 
     */
    Game startGame() 
    {
    	String p1 = "Player 1";
    	String p2 = "Player 2";
    	if (!p1Text.getText().isBlank()) 
    	{
    		p1 = p1Text.getText();
    	}
    	if (!p2Text.getText().isBlank()) 
    	{
    		p2 = p2Text.getText();
    	}
    	Game g = new Game(p1,p2, LocalDateTime.now());
    	return g;
    }
}
