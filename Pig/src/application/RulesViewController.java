package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class RulesViewController {

	/*
	 * Scene change method
	 */
	 @FXML
	 void changeSceneToHomeView(ActionEvent event) throws IOException {
	    	Parent parent = FXMLLoader.load(getClass().getResource("HomePageView.fxml"));
	    	Scene scene = new Scene(parent);
	    	scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
	    	Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
	    	window.setScene(scene);
	    	window.show();
	    }

}
