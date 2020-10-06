package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GameViewController  
{
    @FXML private Text whosTurnText;
    @FXML private ImageView currentDieImage;
    @FXML private Text currentPtsText;
    @FXML private Text messageText;
    @FXML private Text p1NameLabel;
    @FXML private Text p2NameLabel;
    @FXML private Text p1Score;
    @FXML private Text p2Score;
    @FXML private StackPane stackPane;
    @FXML private ImageView confetti;
   
    Game game = null;
    int scoreBank = 0;
    boolean hasRolled = false;
    boolean gameWon = false;
    
    
    void getGameInfo(Game g) 
    {
    	game = g;
    	setStartingLabels();
    }
    
    void setStartingLabels() 
    {
    	p1NameLabel.setText(game.p1.getName());
    	p2NameLabel.setText(game.p2.getName());
    	whosTurnText.setText("It is "+game.p1.getName() + "'s Turn");
    }
    
    //Roll die button
    @FXML
	private void rollDie(ActionEvent event) throws InterruptedException, FileNotFoundException 
    {
    	if(!gameWon) 
    	{
    		hasRolled = true;
        	Random rand = new Random();
        	int score = rand.nextInt(6)+1;
        	List<Image> images = new ArrayList<>();
        	images.add(new Image(new FileInputStream("1.png")));
        	images.add(new Image(new FileInputStream("2.png")));
        	images.add(new Image(new FileInputStream("3.png")));
        	images.add(new Image(new FileInputStream("4.png")));
        	images.add(new Image(new FileInputStream("5.png")));
        	images.add(new Image(new FileInputStream("6.png")));
        	Timeline t = new Timeline();
        	t.getKeyFrames().add(new KeyFrame
        			(Duration.millis(100), 
        					e-> currentDieImage.setImage(images.get(rand.nextInt(6)))));
        	t.getKeyFrames().add(new KeyFrame
        			(Duration.millis(250), 
        					e-> currentDieImage.setImage(images.get(rand.nextInt(6)))));
        	t.getKeyFrames().add(new KeyFrame
        			(Duration.millis(400), 
        					e-> currentDieImage.setImage(images.get(rand.nextInt(6)))));
        	t.getKeyFrames().add(new KeyFrame
        			(Duration.millis(550), 
        					e-> currentDieImage.setImage(images.get(rand.nextInt(6)))));
        	t.getKeyFrames().add(new KeyFrame
        			(Duration.millis(700), 
        					e-> currentDieImage.setImage(images.get(rand.nextInt(6)))));
        	t.getKeyFrames().add(new KeyFrame
        			(Duration.millis(850), 
        					e-> {currentDieImage.setImage(images.get(score-1));
    							checkScore(score);
    						}));
        	t.play();
    	}    	
	}
    
    void checkScore(int i)  
    {
    	if(i == 1) 
    	{
    		scoreBank = 0;
    		swapTurns();
    	} 
    	else 
    	{
    		scoreBank += i;
    		updateCurrentPts();
    		didYouWin();
    	}
    }
    
    void didYouWin() 
    {
    	if(game.p1.isTurn()) 
    	{
    		if(game.p1.getScore()+Integer.parseInt(currentPtsText.getText())>=100) 
    		{
    			gameWon=true;
    			swapTurns();
    			winningAnimation();
    		}
    	}
    	if(game.p2.isTurn()) 
    	{
    		if(game.p2.getScore()+Integer.parseInt(currentPtsText.getText())>=100) 
    		{
    			gameWon=true;
    			swapTurns();
    			winningAnimation();
    		}
    	}
    }
   
    void swapTurns() 
    {
    	hasRolled = false;
    	game.setPts(scoreBank);
    	if(game.p1.isTurn()) 
    	{
    		p2Score.setText(game.p2.getScore()+ "");
    		if(!gameWon) 
    		{
    			whosTurnText.setText("It is " + game.p1.getName() + "'s turn!");
    		}
    	}
    	if(game.p2.isTurn()) {
    		p1Score.setText(game.p1.getScore()+ "");
    		if(!gameWon) {
    			whosTurnText.setText("It is " + game.p2.getName() + "'s turn!");
    		}
    	}
    	scoreBank = 0;
    	updateCurrentPts();
    }
    
    void updateCurrentPts() {
    	currentPtsText.setText("" + scoreBank);
    }
    
    //Pass turn button
    @FXML
    void passTurn(ActionEvent event) 
    {
    	if(!gameWon) 
    	{
    		if(hasRolled == true) 
    		{
        		swapTurns();
        	} else {
        		Timeline t = new Timeline();
            	t.getKeyFrames().add(new KeyFrame
            			(Duration.millis(100), 
            					e-> messageText.setText("You must roll once before you can pass your turn!")));
            	t.getKeyFrames().add(new KeyFrame
            			(Duration.millis(2500), 
            					e-> messageText.setText("")));
        		t.play();
        	}
    	}    	
    }
    
    //End Animation
    @FXML
    void winningAnimation()  {
    	Timeline t = new Timeline();
    	
    	t.getKeyFrames().add(new KeyFrame
    			(Duration.millis(100), 
    					e->confetti.setOpacity(.1)));
    	t.getKeyFrames().add(new KeyFrame
    			(Duration.millis(200), 
    					e->confetti.setOpacity(.2)));
    	t.getKeyFrames().add(new KeyFrame
    			(Duration.millis(300), 
    					e->confetti.setOpacity(.3)));
    	t.getKeyFrames().add(new KeyFrame
    			(Duration.millis(400), 
    					e->confetti.setOpacity(.4)));
    	t.getKeyFrames().add(new KeyFrame
    			(Duration.millis(500), 
    					e->confetti.setOpacity(.5)));
    	t.getKeyFrames().add(new KeyFrame
    			(Duration.millis(600), 
    					e->confetti.setOpacity(.6)));
    	t.getKeyFrames().add(new KeyFrame
    			(Duration.millis(700), 
    					e->confetti.setOpacity(.7)));
    	t.getKeyFrames().add(new KeyFrame
    			(Duration.millis(800), 
    					e->confetti.setOpacity(.8)));
    	t.getKeyFrames().add(new KeyFrame
    			(Duration.millis(900), 
    					e->confetti.setOpacity(.9)));
    	t.getKeyFrames().add(new KeyFrame
    			(Duration.millis(1000), 
    					e->confetti.setOpacity(1)));
       	t.getKeyFrames().add(new KeyFrame
    			(Duration.millis(4000), 
    					e->stackPane.setOpacity(.9)));
    	t.getKeyFrames().add(new KeyFrame
    			(Duration.millis(4025), 
    					e->stackPane.setOpacity(.8)));
    	t.getKeyFrames().add(new KeyFrame
    			(Duration.millis(4050), 
    					e->stackPane.setOpacity(.7)));
    	t.getKeyFrames().add(new KeyFrame
    			(Duration.millis(4075), 
    					e->stackPane.setOpacity(.6)));
    	t.getKeyFrames().add(new KeyFrame
    			(Duration.millis(4100), 
    					e->stackPane.setOpacity(.5)));
    	t.getKeyFrames().add(new KeyFrame
    			(Duration.millis(4125), 
    					e->stackPane.setOpacity(.4)));
    	t.getKeyFrames().add(new KeyFrame
    			(Duration.millis(4150), 
    					e->stackPane.setOpacity(.3)));
    	t.getKeyFrames().add(new KeyFrame
    			(Duration.millis(4175), 
    					e->stackPane.setOpacity(.2)));
    	t.getKeyFrames().add(new KeyFrame
    			(Duration.millis(4200), 
    					e->stackPane.setOpacity(.1)));
    	t.getKeyFrames().add(new KeyFrame
    			(Duration.millis(4225), 
    					e->stackPane.setOpacity(0)));
    	t.getKeyFrames().add(new KeyFrame
    			(Duration.millis(4250), 
    					e->changeSceneToResultsView()));
    	t.play();
    }
    
   /*
    * Scene Change Method
    */
    void changeSceneToResultsView() 
    {
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(getClass().getResource("ResultsView.fxml"));
    	Parent parent = null;
		try {
			parent = loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ResultsViewController controller = loader.getController();
    	Scene scene = new Scene(parent);
    	scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
    	Stage window = (Stage)(((Node)p1Score).getScene()).getWindow();
    	window.setScene(scene);
    	window.show();
    	controller.initialize(game);
    }
}
