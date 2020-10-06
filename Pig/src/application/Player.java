package application;

public class Player {
	private String name = "";
	private boolean isTurn = false;
	private int score = 0;
	
	//Constructor
	Player(String name, boolean isTurn)
	{
		this.name = name;
		this.isTurn = isTurn;
	}
	
	/*
	 * Getters and Setters 
	 */
	public String getName() 
	{
		return name;
	}

	public void setName(String name) 
	{
		this.name = name;
	}

	public boolean isTurn() 
	{
		return isTurn;
	}

	public void setTurn(boolean isTurn) 
	{
		this.isTurn = isTurn;
	}

	public int getScore() 
	{
		return score;
	}

	public void setScore(int score) 
	{
		this.score = score;
	}
	

}
