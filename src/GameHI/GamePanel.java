package GameHI;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JToggleButton;

import GamePD.Game;
import GamePD.GameTree;
import GamePD.Player.PlayerType;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

@SuppressWarnings({ "serial", "unused" })
public class GamePanel extends JPanel
{
	private JButton[][] buttons = new JButton[3][3];
	private int placementOffsetX = 0, placementOffsetY = 0;
	private JLabel lblNewLabel;
	private JButton btnPlayAgain;
	private JButton btnDone;
	private JLabel label_2, lblWins;
	private JLabel lblNewLabel_1;
	private JLabel label_3;

	/**
	 * Create the panel.
	 */
	public GamePanel(JFrame currentFrame, Game game)
	{
		setLayout(null);
		
		JLabel lblPlayer = new JLabel("Player 1: ");
		lblPlayer.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblPlayer.setBounds(67, 90, 77, 16);
		add(lblPlayer);
		
		JLabel lblPlayer_1 = new JLabel("Player 2:");
		lblPlayer_1.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblPlayer_1.setBounds(67, 118, 61, 16);
		add(lblPlayer_1);
		
		JLabel label = new JLabel(game.getPlayer1().toString());
		label.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		label.setBounds(140, 90, 93, 16);
		add(label);
//		if(game.getCurrentPlayer() == game.getPlayer1())
//			label.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		
		JLabel label_1 = new JLabel(game.getPlayer2().toString());
		label_1.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		label_1.setBounds(140, 118, 93, 16);
		add(label_1);
//		if(game.getCurrentPlayer() == game.getPlayer2())
//			label_1.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		
		
		for(int y = 0; y < 3; y++)
		{
			for(int x = 0; x < 3; x++)
			{
				buttons[x][y] = new JButton("");
				buttons[x][y].setFont(new Font("Lucida Grande", Font.PLAIN, 50));
				buttons[x][y].addActionListener(new ButtonListener(game));
				buttons[x][y].setBackground(Color.LIGHT_GRAY);
				buttons[x][y].setOpaque(true);
				buttons[x][y].setBorderPainted(false);	
				buttons[x][y].setBounds(300 + placementOffsetX, 90 + placementOffsetY, 75, 75);
				add(buttons[x][y]);
				placementOffsetX += 77;
			}
			placementOffsetX = 0;
			placementOffsetY += 77;
		}
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		lblNewLabel.setBounds(95, 158, 102, 41);
		add(lblNewLabel);
		
		btnPlayAgain = new JButton("Play Again");
		btnPlayAgain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Game newGame = new Game(game.getPlayer1(), game.getPlayer2());

				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new GamePanel(currentFrame, newGame));
				currentFrame.getContentPane().revalidate();
				currentFrame.getContentPane().repaint();
			}
		});
		btnPlayAgain.setBounds(80, 255, 117, 29);
		add(btnPlayAgain);
		btnPlayAgain.setVisible(false);
		
		btnDone = new JButton("Done");
		btnDone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new EndPanel(game));
				currentFrame.getContentPane().revalidate();
				currentFrame.getContentPane().repaint();
			}
		});
		btnDone.setBounds(80, 283, 117, 29);
		add(btnDone);
		btnDone.setVisible(false);
		
		label_2 = new JLabel("Tic Tac Toe");
		label_2.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		label_2.setBounds(247, 6, 130, 73);
		add(label_2);
		
		lblWins = new JLabel("WINS!!!");
		lblWins.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		lblWins.setBounds(95, 199, 102, 44);
		lblWins.setVisible(false);
		add(lblWins);
		
		lblNewLabel_1 = new JLabel("X");
		lblNewLabel_1.setBounds(48, 90, 17, 16);
		add(lblNewLabel_1);
		
		label_3 = new JLabel("O");
		label_3.setBounds(48, 119, 17, 16);
		add(label_3);
		
		computerFirst(game);
		bothComputers(game);
	}
	
	private class ButtonListener implements ActionListener
	{
		private Game game;
		public ButtonListener(Game game)
		{
			this.game = game;
		}
		public void actionPerformed(ActionEvent e)
		{
			JButton src = (JButton) e.getSource();
			for(int j = 0; j < 3; j++)
			{
				for(int i = 0; i < 3; i++)
				{
					if(src == buttons[i][j])
					{
						if(game.getCurrentPlayer() == game.getPlayer1())
							src.setText("x");
						else
							src.setText("o");
						src.setEnabled(false);
						game.move(i, j);
						nextTurn(game);
					}
				}
			}
		}
	}
	
	public void nextTurn(Game game)
	{
		if(game.getCurrentPlayer().getType() == PlayerType.COMPUTER && !game.isComplete())
		{
			GameTree decisionTree = new GameTree(game.getBoard(), game.getCurrentPlayer().getMark());
			decisionTree.evaluateTree();
			for(int y = 0; y < 3; y++)
			{
				for(int x = 0; x < 3; x++)
				{
					if(x == decisionTree.getNextX() && y == decisionTree.getNextY())
					{
						if(game.getCurrentPlayer() == game.getPlayer1())
							buttons[x][y].setText("x");
						else
							buttons[x][y].setText("o");
						buttons[x][y].setEnabled(false);
					}
				}
			}
			game.move(decisionTree.getNextX(), decisionTree.getNextY());
		}
		
		if(game.isComplete())
		{
			lblNewLabel.setText(game.getWinner());
			if(game.getWinner() != "TIE")
			{
				lblWins.setVisible(true);
				findWinningLine(game);
			}
			btnPlayAgain.setVisible(true);
			btnDone.setVisible(true);
			
			for(int y = 0; y < 3; y++)
			{
				for(int x = 0; x < 3; x++)
				{
					buttons[x][y].setEnabled(false);
				}
			}
		}
	}
	
	public void findWinningLine(Game game)
	{
		if((game.getBoard().getBoard()[0][0] + game.getBoard().getBoard()[1][0] + game.getBoard().getBoard()[2][0]) == -3 || 
			(game.getBoard().getBoard()[0][0] + game.getBoard().getBoard()[1][0] + game.getBoard().getBoard()[2][0]) == 3)
		{
			buttons[0][0].setBackground(Color.green);
			buttons[1][0].setBackground(Color.green);
			buttons[2][0].setBackground(Color.green);
		}
		else if((game.getBoard().getBoard()[0][1] + game.getBoard().getBoard()[1][1] + game.getBoard().getBoard()[2][1]) == -3 || 
				(game.getBoard().getBoard()[0][1] + game.getBoard().getBoard()[1][1] + game.getBoard().getBoard()[2][1]) == 3)
		{
			buttons[0][1].setBackground(Color.green);
			buttons[1][1].setBackground(Color.green);
			buttons[2][1].setBackground(Color.green);
		}
		else if((game.getBoard().getBoard()[0][2] + game.getBoard().getBoard()[1][2] + game.getBoard().getBoard()[2][2]) == -3 || 
				(game.getBoard().getBoard()[0][2] + game.getBoard().getBoard()[1][2] + game.getBoard().getBoard()[2][2]) == 3)
		{
			buttons[0][2].setBackground(Color.green);
			buttons[1][2].setBackground(Color.green);
			buttons[2][2].setBackground(Color.green);
		}
		else if((game.getBoard().getBoard()[0][0] + game.getBoard().getBoard()[0][1] + game.getBoard().getBoard()[0][2]) == -3 || 
				(game.getBoard().getBoard()[0][0] + game.getBoard().getBoard()[0][1] + game.getBoard().getBoard()[0][2]) == 3)
		{
			buttons[0][0].setBackground(Color.green);
			buttons[0][1].setBackground(Color.green);
			buttons[0][2].setBackground(Color.green);
		}
		else if((game.getBoard().getBoard()[1][0] + game.getBoard().getBoard()[1][1] + game.getBoard().getBoard()[1][2]) == -3 || 
				(game.getBoard().getBoard()[1][0] + game.getBoard().getBoard()[1][1] + game.getBoard().getBoard()[1][2]) == 3)
		{
			buttons[1][0].setBackground(Color.green);
			buttons[1][1].setBackground(Color.green);
			buttons[1][2].setBackground(Color.green);
		}
		else if((game.getBoard().getBoard()[2][0] + game.getBoard().getBoard()[2][1] + game.getBoard().getBoard()[2][2]) == -3 || 
				(game.getBoard().getBoard()[2][0] + game.getBoard().getBoard()[2][1] + game.getBoard().getBoard()[2][2]) == 3)
		{
			buttons[2][0].setBackground(Color.green);
			buttons[2][1].setBackground(Color.green);
			buttons[2][2].setBackground(Color.green);
		}
		else if((game.getBoard().getBoard()[0][0] + game.getBoard().getBoard()[1][1] + game.getBoard().getBoard()[2][2]) == -3 || 
				(game.getBoard().getBoard()[0][0] + game.getBoard().getBoard()[1][1] + game.getBoard().getBoard()[2][2]) == 3)
		{
			buttons[0][0].setBackground(Color.green);
			buttons[1][1].setBackground(Color.green);
			buttons[2][2].setBackground(Color.green);
		}
		else if((game.getBoard().getBoard()[2][0] + game.getBoard().getBoard()[1][1] + game.getBoard().getBoard()[0][2]) == -3 || 
				(game.getBoard().getBoard()[2][0] + game.getBoard().getBoard()[1][1] + game.getBoard().getBoard()[0][2]) == 3)
		{
			buttons[2][0].setBackground(Color.green);
			buttons[1][1].setBackground(Color.green);
			buttons[0][2].setBackground(Color.green);
		}
	}
	
	public void bothComputers(Game game)
	{
		if(game.getPlayer1().getType() == PlayerType.COMPUTER && game.getPlayer2().getType() == PlayerType.COMPUTER)
		{
			for(int y = 0; y < 3; y++)
			{
				for(int x = 0; x < 3; x++)
				{
					buttons[x][y].setEnabled(false);
				}
			}
			
			while(!game.isComplete())
			{
				nextTurn(game);
			}
		}
	}
	
	public void computerFirst(Game game)
	{
		if(game.getPlayer1().getType() == PlayerType.COMPUTER)
		{
			nextTurn(game);
		}
	}
}
