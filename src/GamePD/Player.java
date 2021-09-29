package GamePD;

public class Player
{
	private String name;
	private int wins = 0;
	private int losses = 0;
	private int ties = 0;
	private PlayerType type;
	private String mark;			
	private Game game;
	public enum PlayerType { HUMAN, COMPUTER };
	
	public Player(String name, PlayerType type, String mark)
	{
		this.name = name;
		this.type = type;
		this.mark = mark;
	}
	
	public void move(int x, int y)
	{
		this.game.getBoard().move(x, y, this);
	}
	
	public void addLoss()
	{
		this.losses++;
	}
	
	public int getLosses()
	{
		return this.losses;
	}
	
	public void addWin()
	{
		this.wins++;
	}
	
	public int getWins()
	{
		return this.wins;
	}
	
	public void addTie()
	{
		this.ties++;
	}
	
	public int getTies()
	{
		return this.ties;
	}
	
	public PlayerType getType()
	{
		return this.type;
	}
	
	public String getMark()
	{
		return this.mark;
	}
	
	public String toString()
	{
		return this.name;
	}
	
	public int getTotalGames()
	{
		return this.losses + this.wins + this.ties;
	}
}