package application;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class LeaderboardData {
	SimpleStringProperty p1name;
	SimpleIntegerProperty p1score;
	SimpleStringProperty p2name;
	SimpleIntegerProperty p2score;
	LocalDateTime dt;
	
	/*
	 * This constructor takes in strings
	 * and then parses then to the correct type
	 */
	LeaderboardData(String p1name, String p1score, String p2name, String p2score, String dt)
	{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"); 
		LocalDateTime dateTime = LocalDateTime.parse(dt, formatter);
		if(Integer.parseInt(p1score)>=Integer.parseInt(p2score)) 
		{
			this.p1name = new SimpleStringProperty(p1name);
			this.p1score = new SimpleIntegerProperty(Integer.parseInt(p1score));
			this.p2score = new SimpleIntegerProperty(Integer.parseInt(p2score));
			this.p2name = new SimpleStringProperty(p2name);
			

			this.dt = dateTime;
			
		} 
		else 
		{
			this.p1name = new SimpleStringProperty(p2name);
			this.p1score = new SimpleIntegerProperty(Integer.parseInt(p2score));
			this.p2score = new SimpleIntegerProperty(Integer.parseInt(p1score));
			this.p2name = new SimpleStringProperty(p1name);
			this.dt = dateTime;
		}
	}

	/*
	 * Getters and setters
	 */
	public String getP1name() {
		return p1name.get();
	}


	public void setP1name(SimpleStringProperty p1name) {
		this.p1name = p1name;
	}


	public Integer getP1score() {
		return p1score.get();
	}


	public void setP1score(SimpleIntegerProperty p1score) {
		this.p1score = p1score;
	}


	public String getP2name() {
		return p2name.get();
	}


	public void setP2name(SimpleStringProperty p2name) {
		this.p2name = p2name;
	}


	public Integer getP2score() {
		return p2score.get();
	}


	public void setP2score(SimpleIntegerProperty p2score) {
		this.p2score = p2score;
	}


	public String getDt() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy 'at' hh:mma");
		return formatter.format(dt);
	}


	public void setDt(LocalDateTime dt) {
	
		this.dt = dt;
	}
	
	

}
