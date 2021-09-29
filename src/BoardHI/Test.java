package BoardHI;
import java.util.Scanner;

import GamePD.Game;
import GamePD.GameTree;
import GamePD.Player;
import GamePD.Player.PlayerType;

public class Test
{
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		Player Chloe = new Player("Chloe", PlayerType.HUMAN, "x");
		Player Joseph = new Player("Joseph", PlayerType.HUMAN, "o");
		//Player comp = new Player("Computer", PlayerType.COMPUTER, "o");
		//Player comp2 = new Player("comp", PlayerType.COMPUTER, "x");
		Boolean done = false;
		GameTree decisionTree = null;
		
		while(!done)
		{
			Game game = new Game(Chloe, Joseph);
//			Game game = new Game(Chloe, comp);
//			Game game = new Game(comp2, comp); 
			game.getBoard().printBoard();
			while(!game.isComplete())
			{
				game.printCurrentPlayer();
				if(game.getCurrentPlayer().getType() == PlayerType.HUMAN)
				{
					System.out.println(game.getCurrentPlayer().toString() + ", please enter coordinates for where you would like to play: ");
					game.move(scan.nextInt(), scan.nextInt());
				}
				else if(game.getCurrentPlayer().getType() == PlayerType.COMPUTER)
				{
					decisionTree = new GameTree(game.getBoard(), game.getCurrentPlayer().getMark());
					decisionTree.evaluateTree();
					game.move(decisionTree.getNextX(), decisionTree.getNextY());
				}
				game.getBoard().printBoard();
				if(game.getBoard().isGameComplete())
					System.out.println(game.getWinner() + "\n");
			}
			System.out.println("Would you like to play again (Y/N)? ");
			if(scan.next().contains("N"))	// doesn't work like I want it to
			{
				done = true;
				game.printStats();
			}
		}
		
		
		scan.close();
	}

}
