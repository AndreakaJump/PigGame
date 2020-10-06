package application;

import java.time.LocalDateTime;

public class Game 
{
	public Player p1 = null;
	public Player p2 = null;
	public LocalDateTime dateTime = null;
	
	
	Game(String name1, String name2, LocalDateTime dt)
	{
		p1 = new Player(name1, true);
		p2 = new Player(name2, false);
		dateTime = dt;
	}
	
	//Gets the player with the most points 
	public Player getWinner() 
	{
		if(p1.getScore()>p2.getScore()) 
		{
			return p1;
		}
		else 
		{
			return p2;
		}
	}
	
	//Updates the player objects with points from their turn
	void setPts(int i) 
	{
		if(p1.isTurn()) 
		{
			p1.setScore(p1.getScore()+i);
			p1.setTurn(false);
			p2.setTurn(true);
		}
		else 
		{
			p2.setScore(p2.getScore()+i);
			p2.setTurn(false);
			p1.setTurn(true);
		}
	}
}
