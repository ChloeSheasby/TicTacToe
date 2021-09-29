package GamePD;

public class Game
{
	private Player player1;
	private Player player2;
	private Board currentBoard;
	private Player currentPlayer;
	private String winner = "TIE";
	public enum Status {READY, INPROGRESS, COMPLETED};
	
	public Game(Player p1, Player p2)
	{
		this.player1 = p1;
		this.player2 = p2;
		this.currentBoard = new Board();
		this.currentPlayer = player1;
	}
	
	public Board getBoard()
	{
		return this.currentBoard;
	}
	
	public Player getCurrentPlayer()
	{
		return this.currentPlayer;
	}
	
	public void printCurrentPlayer()
	{
		System.out.println("\nIt is " + getCurrentPlayer().toString() + "'s turn.");
	}
	
	public void startGame()			// what is this useful for?
	{
		this.currentPlayer = player1;
	}
	
	public void move(int x, int y)
	{
		currentBoard.move(x, y, currentPlayer);
		updateCurrentPlayer();
		if(currentBoard.whoIsWinner().equals("x"))
		{
			this.winner = player1.toString();
			player1.addWin();
			player2.addLoss();
		}
		else if(currentBoard.whoIsWinner().equals("o"))
		{
			this.winner = player2.toString();
			player1.addLoss();
			player2.addWin();
		}
		else if(currentBoard.isGameComplete())
		{
			player1.addTie();
			player2.addTie();
		}
	}
	
	public void updateCurrentPlayer()
	{
		if(currentPlayer == player1)
			currentPlayer = player2;
		else
			currentPlayer = player1;
	}
	
	public Boolean isComplete()
	{
		return currentBoard.isGameComplete();
	}
	
	public String getWinner()
	{
		return this.winner;
	}
	
	public Player getPlayer1()
	{
		return this.player1;
	}
	
	public Player getPlayer2()
	{
		return this.player2;
	}
	
	public void printStats()
	{
		System.out.println("\nGame Stats\n");
		System.out.println("Player 1: " + player1.toString() + "\nWins: " + player1.getWins() + "\nLosses: " + player1.getLosses() + "\n");
		System.out.println("Player 2: " + player2.toString() + "\nWins: " + player2.getWins() + "\nLosses: " + player2.getLosses() + "\n");
		System.out.println("Total Games: " + player1.getTotalGames());
	}
}
