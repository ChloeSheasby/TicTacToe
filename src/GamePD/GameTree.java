package GamePD;

import java.util.ArrayList;

@SuppressWarnings("unused")
public class GameTree
{
	private GameTreeNode root;
	private String myMark;
	
	public GameTree(Board board, String mark)
	{
		root = new GameTreeNode(null, board);
		this.myMark = mark;
		root.createChildren(mark);
	}
	
	public void evaluateTree()
	{
		root.evaluate(myMark);
	}
		
	public int getNextX()
	{
		return root.getNextMoveX();
	}
	
	public int getNextY()
	{
		return root.getNextMoveY();
	}
	
	public GameTreeNode getRoot()
	{
		return this.root;
	}
	
}

