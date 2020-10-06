package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class ScoreBoardViewController implements Initializable
{
	@FXML private StackPane stackPane;
	@FXML private Button rulesBtn;
	@FXML private TableView<LeaderboardData> table;
	@FXML private TableColumn<LeaderboardData, String> winningColumn;
	@FXML private TableColumn<LeaderboardData, Integer> winningScoreColumn;
	@FXML private TableColumn<LeaderboardData, String> losingColumn;
	@FXML private TableColumn<LeaderboardData, Integer> losingScoreColumn;
	@FXML private TableColumn<LeaderboardData, String> dateTimeColumn;

	/*
	 * This initialize method sets up the tableview object
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
		 winningColumn.setCellValueFactory(new PropertyValueFactory<LeaderboardData, String>("p1name"));
		 winningScoreColumn.setCellValueFactory(new PropertyValueFactory<LeaderboardData, Integer>("p1score"));
		 losingColumn.setCellValueFactory(new PropertyValueFactory<LeaderboardData, String>("p2name"));
		 losingScoreColumn.setCellValueFactory(new PropertyValueFactory<LeaderboardData, Integer>("p2score"));
		 dateTimeColumn.setCellValueFactory(new PropertyValueFactory<LeaderboardData, String>("dt"));
		 table.setItems(getData());
		 winningScoreColumn.setSortType(TableColumn.SortType.DESCENDING);
		 table.getSortOrder().add(winningScoreColumn);
	}
	
	//Returns an Observable List for the table view object 
	ObservableList<LeaderboardData> getData() 
	{
		ObservableList<LeaderboardData> list = FXCollections.observableArrayList();
		File file = new File("LeaderboardData.txt");
		Scanner scanner = null;
		try 
		{
			scanner = new Scanner(file);
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		while(scanner.hasNext()) 
		{
			String line = scanner.nextLine();
			String[] items = line.split(",");
			LeaderboardData data = new LeaderboardData(items[0], items[1],items[2], items[3], items[4]);
			list.add(data);
		}
		return list;
	}
	 
	 /*
	  * Scene change method
	  */
	 @FXML
	 void changeSceneToHomeView(ActionEvent event) throws IOException 
	 {
		 Parent nameViewParent = FXMLLoader.load(getClass().getResource("HomePageView.fxml"));
		 Scene nameViewScene = new Scene(nameViewParent);
		 nameViewScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		 Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		 window.setScene(nameViewScene);
		 window.show();
	 }
}
