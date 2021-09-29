package GameHI;

import javax.swing.JPanel;

import GamePD.Game;
import javax.swing.JLabel;
import java.awt.Font;

@SuppressWarnings("serial")
public class EndPanel extends JPanel
{

	/**
	 * Create the panel.
	 */
	public EndPanel(Game game)
	{
		setLayout(null);
		
		JLabel lblPlayer = new JLabel("Player 1:");
		lblPlayer.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblPlayer.setBounds(81, 77, 61, 16);
		add(lblPlayer);
		
		JLabel lblPlayer_1 = new JLabel("Player 2:");
		lblPlayer_1.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblPlayer_1.setBounds(375, 77, 61, 16);
		add(lblPlayer_1);
		
		JLabel label = new JLabel(game.getPlayer1().toString());
		label.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		label.setBounds(154, 77, 130, 16);
		add(label);
		
		JLabel label_1 = new JLabel(game.getPlayer2().toString());
		label_1.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		label_1.setBounds(448, 77, 130, 16);
		add(label_1);
		
		JLabel lblWins = new JLabel("Wins:");
		lblWins.setBounds(115, 105, 61, 16);
		add(lblWins);
		
		JLabel lblLosses = new JLabel("Losses:");
		lblLosses.setBounds(115, 133, 61, 16);
		add(lblLosses);
		
		JLabel lblWins_1 = new JLabel("Wins:");
		lblWins_1.setBounds(409, 105, 61, 16);
		add(lblWins_1);
		
		JLabel lblLosses_1 = new JLabel("Losses:");
		lblLosses_1.setBounds(409, 133, 61, 16);
		add(lblLosses_1);
		
		JLabel lblTies = new JLabel("Ties:");
		lblTies.setBounds(271, 181, 61, 16);
		add(lblTies);
		
		JLabel lblTotalGames = new JLabel("Total Games:");
		lblTotalGames.setBounds(244, 228, 82, 16);
		add(lblTotalGames);
		
		JLabel lblNewLabel = new JLabel(Integer.toString(game.getPlayer1().getWins()));
		lblNewLabel.setBounds(174, 105, 61, 16);
		add(lblNewLabel);
		
		JLabel label_2 = new JLabel(Integer.toString(game.getPlayer1().getLosses()));
		label_2.setBounds(174, 133, 61, 16);
		add(label_2);
		
		JLabel label_3 = new JLabel(Integer.toString(game.getPlayer2().getWins()));
		label_3.setBounds(470, 105, 61, 16);
		add(label_3);
		
		JLabel label_4 = new JLabel(Integer.toString(game.getPlayer2().getLosses()));
		label_4.setBounds(470, 133, 61, 16);
		add(label_4);
		
		JLabel label_5 = new JLabel(Integer.toString(game.getPlayer1().getTies()));
		label_5.setBounds(317, 181, 61, 16);
		add(label_5);
		
		JLabel label_6 = new JLabel(Integer.toString(game.getPlayer1().getTotalGames()));
		label_6.setBounds(334, 228, 61, 16);
		add(label_6);
		
		JLabel lblTicTacToe = new JLabel("Game Stats");
		lblTicTacToe.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblTicTacToe.setBounds(244, 30, 151, 16);
		add(lblTicTacToe);

	}

}
