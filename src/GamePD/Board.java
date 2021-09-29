package GamePD;

public class Board
{
	private int[][] board = new int[3][3];
	private String error = "";
	
	public Board()
	{
		for(int y = 0; y < 3; y++)
		{
			for(int x = 0; x < 3; x++)
			{
				board[x][y] = 0;
			}
		}
	}
	
	public int[][] getBoard()
	{
		return this.board;
	}
	
	public void setBoard(int[][] board)
	{
		this.board = board;
	}
	
	public void move(int x, int y, Player p)
	{
		error = "";
		if(isPositionOpen(x, y))
		{
			if(p.getMark().equals("x"))
			{
				board[x][y] = -1;
			}
			else
			{
				board[x][y] = 1;
			}
		}
		else
		{
			error = "That spot is filled, please try again.";
			System.out.println(error);
		}
	}
	
	public void printBoard()
	{
		System.out.println("  0 1 2");
		for(int y = 0; y < 3; y++)
		{
			System.out.print(y + " ");
			for(int x = 0; x < 3; x++)
			{
				if(board[x][y] == -1)
					System.out.print("x ");
				else if(board[x][y] == 1)
					System.out.print("o ");
				else
					System.out.print("  ");
			}
			System.out.println();
		}
	}
	
	public Boolean isGameComplete()
	{
		if(!whoIsWinner().equals(" "))
			return true;
		else
		{
			for(int y = 0; y < 3; y++)
			{
				for(int x = 0; x < 3; x++)
				{
					if(board[x][y] == 0)
						return false;
				}
			}
		}
		return true;
	}
	
	public Boolean isPositionOpen(int x, int y)
	{
		if(board[x][y] == 0)
			return true;
		return false;
	}
	
	public String whoIsWinner()
	{
		String winner = " ";
		if((board[0][0] + board[1][0] + board[2][0]) == -3 || (board[0][1] + board[1][1] + board[2][1]) == -3 || 
			(board[0][2] + board[1][2] + board[2][2]) == -3 || (board[0][0] + board[0][1] + board[0][2]) == -3 ||
			(board[1][0] + board[1][1] + board[1][2]) == -3 || (board[2][0] + board[2][1] + board[2][2]) == -3 ||
			(board[0][0] + board[1][1] + board[2][2]) == -3 || (board[2][0] + board[1][1] + board[0][2]) == -3)
			winner = "x";
		else if((board[0][0] + board[1][0] + board[2][0]) == 3 || (board[0][1] + board[1][1] + board[2][1]) == 3 || 
				(board[0][2] + board[1][2] + board[2][2]) == 3 || (board[0][0] + board[0][1] + board[0][2]) == 3 ||
				(board[1][0] + board[1][1] + board[1][2]) == 3 || (board[2][0] + board[2][1] + board[2][2]) == 3 ||
				(board[0][0] + board[1][1] + board[2][2]) == 3 || (board[2][0] + board[1][1] + board[0][2]) == 3)
			winner = "o";
		
		return winner;
	}	
	
	public Board clone()
	{
		Board copy = new Board();
		for(int y = 0; y < 3; y++)
		{
			for(int x = 0; x < 3; x++)
			{
				copy.board[x][y] = this.board[x][y];
			}
		}
		return copy;
	}
			
}
