package application;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.format.DateTimeFormatter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ResultsViewController 
{
	@FXML private StackPane stackPane;
	@FXML private Button scoreboardBtn;
	@FXML private Button playBtn;
	@FXML private Button rulesBtn;
	@FXML private Text p1name;
	@FXML private Text p1score;
	@FXML private Text p2name;
	@FXML private Text p2score;
	@FXML private Text whoWonTitle;
	Game game = null;

	/*
	 * Initializes the page by setting text fields 
	 * based on the game object it is passed.
	 */
	void initialize(Game g) {
		game = g;
		whoWonTitle.setText(game.getWinner().getName() + " is the Winner!");
		p1name.setText(game.p1.getName()+ ": " + game.p1.getScore());
		p2name.setText(game.p2.getName()+ ": " + game.p2.getScore());
		
		//Adds data from the game object to the leaderboard text file.
		addToLeaderboardTxt();
	}

	/*
	 * Scene change methods for the buttons
	 */
	@FXML
	void changeSceneToNameView(ActionEvent event) throws IOException {	
		Parent nameViewParent = FXMLLoader.load(getClass().getResource("NameView.fxml"));
		Scene nameViewScene = new Scene(nameViewParent);
		nameViewScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		window.setScene(nameViewScene);
		window.show();
	}
	@FXML
	void changeSceneToScoreBoard(ActionEvent event) throws IOException {
		Parent nameViewParent = FXMLLoader.load(getClass().getResource("ScoreBoardView.fxml"));
		Scene nameViewScene = new Scene(nameViewParent);
		nameViewScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		window.setScene(nameViewScene);
		window.show();
	}

	@FXML
	void changeSceneToHomePageView(ActionEvent event) throws IOException {
		Parent parent = FXMLLoader.load(getClass().getResource("HomePageView.fxml"));
		Scene scene = new Scene(parent);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		window.setScene(scene);
		window.show();
	}
	
	/*
	 * Method to write data to the leaderboard text file that stores games
	 * to be read later.
	 */
	void addToLeaderboardTxt()  {
		File file = new File("LeaderboardData.txt");
		FileWriter fw = null;
		try {
			fw = new FileWriter(file, true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		PrintWriter pw = new PrintWriter(fw);
		pw.println(game.p1.getName() + "," + game.p1.getScore() + "," + game.p2.getName() + "," + game.p2.getScore() +"," + game.dateTime.format(formatter));
		pw.close();
	}
    
    

}
