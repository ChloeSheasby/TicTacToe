package GamePD;

import java.util.ArrayList;

public class GameTreeNode
{
	@SuppressWarnings("unused")
	private GameTreeNode parent;
	private ArrayList<GameTreeNode> children;
	private Board board;
	private int lastX;
	private int lastY;
	private String lastMark;
	private int score;	
	
	public GameTreeNode(GameTreeNode parent, Board board)
	{
		this.parent = parent;
		this.board = board;
		this.children = new ArrayList<GameTreeNode>();
	}
	
	public void addChild(GameTreeNode child)
	{
		this.children.add(child);
	}
	
	public void createChildren(String mark)
	{
		if(!board.isGameComplete())
		{
			for(int y = 0; y < 3; y++)
			{
				for(int x = 0; x < 3; x++)
				{
					if(board.getBoard()[x][y] == 0)
					{
						GameTreeNode child = new GameTreeNode(this, board.clone());
						if(mark.equals("x"))
							child.getArrayBoard()[x][y] = -1;
						else if(mark.equals("o"))
							child.getArrayBoard()[x][y] = 1;
						child.setLastX(x);
						child.setLastY(y);
						child.setLastMark(mark);
						addChild(child);
						child.createChildren(child.switchMark());
					}
				}
			}
		}
	}
	
	public Board getCurrentBoard()
	{
		return this.board;
	}
	
	public int[][] getArrayBoard()
	{
		return this.board.getBoard();
	}
	
	public ArrayList<GameTreeNode> getChildren()
	{
		return this.children;
	}
	
	public String switchMark()
	{
		String mark = "";
		if(lastMark.equals("x"))
			mark = "o";
		else if(lastMark.equals("o"))
			mark = "x";
		return mark;
	}
	
	public void setLastMark(String m)
	{
		this.lastMark = m;
	}
	
	public int evaluate(String mark)
	{
		if(board.whoIsWinner().equals(mark))
		{
			this.score = 1;
			return this.score;
		}
		else if(!board.whoIsWinner().equals(mark) && !board.whoIsWinner().equals(" "))
		{
			this.score = -1;
			return this.score;
		}
		else if(board.isGameComplete()) 
		{
			this.score = 0;
			return this.score;
		}
		
		if(mark.equals(this.lastMark))
		{
			int smallest = 2;
			for(GameTreeNode child : getChildren())
			{
				int currentScore = child.evaluate(mark);
				if(currentScore < smallest)
				{
					smallest = currentScore;
				}
			}
			this.score = smallest;
		}
		else
		{
			int biggest = -2;
			for(GameTreeNode child : getChildren())
			{
				int currentScore = child.evaluate(mark);
				if(currentScore > biggest)
				{
					biggest = currentScore;
				}
			}
			this.score = biggest;
		}
		
		return this.score;
	}
	
	public int getScore()
	{
		return this.score;
	}
	
	public int getLastX()
	{
		return this.lastX;
	}
	
	public void setLastX(int x)
	{
		this.lastX = x;
	}
	
	public int getLastY()
	{
		return this.lastY;
	}
	
	public void setLastY(int y)
	{
		this.lastY = y;
	}
	
	public int getNextMoveX()
	{
		int biggest = -2;
		GameTreeNode remember = null;
		for(GameTreeNode child : getChildren())
		{
			int currentScore = child.getScore();
			if(currentScore > biggest)
			{
				biggest = currentScore;
				remember = child;
			}
		}
		return remember.getLastX();
	}
	
	public int getNextMoveY()
	{
		int biggest = -2;
		GameTreeNode remember = null;
		for(GameTreeNode child : getChildren())
		{
			int currentScore = child.getScore();
			if(currentScore > biggest)
			{
				biggest = currentScore;
				remember = child;
			}
		}
		return remember.getLastY();
	}
	
}
