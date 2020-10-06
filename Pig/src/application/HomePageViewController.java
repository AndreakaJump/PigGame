package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class HomePageViewController {

    @FXML private Button playBtn;
    @FXML private Button rulesBtn;
    @FXML private Button scoreboardBtn;

    /*
     * Scene change methods 
     */
    @FXML
    void changeSceneToScoreBoard(ActionEvent event) throws IOException 
    {
    	Parent parent = FXMLLoader.load(getClass().getResource("ScoreBoardView.fxml"));
    	Scene scene = new Scene(parent);
    	scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
    	Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
    	window.setScene(scene);
    	window.show();
    }
    
    @FXML
    void changeSceneToNameView(ActionEvent event) throws IOException 
    {
    	Parent parent = FXMLLoader.load(getClass().getResource("NameView.fxml"));
    	Scene scene = new Scene(parent);
    	scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
    	Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
    	window.setScene(scene);
    	window.show();	
    }
    
    @FXML
    void changeSceneToRulesView(ActionEvent event) throws IOException 
    {
    	Parent parent = FXMLLoader.load(getClass().getResource("RulesView.fxml"));
    	Scene scene = new Scene(parent);
    	scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
    	Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
    	window.setScene(scene);
    	window.show();
    }
}
