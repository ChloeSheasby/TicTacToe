package GameHI;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

import GamePD.Game;
import GamePD.Player;
import GamePD.Player.PlayerType;

import javax.swing.JLabel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.event.AncestorListener;
import javax.swing.event.AncestorEvent;

@SuppressWarnings({ "serial", "unused" })
public class StartPanel extends JPanel
{
	private DefaultComboBoxModel<String> player1Type;
	private DefaultComboBoxModel<String> player2Type;
	private JTextField textField;
	private JTextField textField_1;
	/**
	 * Create the panel.
	 */
	@SuppressWarnings("unchecked")
	public StartPanel(JFrame currentFrame)
	{
		addAncestorListener(new AncestorListener() {
			public void ancestorAdded(AncestorEvent event) {
			}
			public void ancestorMoved(AncestorEvent event) {
			}
			public void ancestorRemoved(AncestorEvent event) {
			}
		});
		setLayout(null);
		
		JLabel lblTicTacToe = new JLabel("Tic Tac Toe");
		lblTicTacToe.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblTicTacToe.setBounds(228, 17, 130, 60);
		add(lblTicTacToe);
		
		JLabel lblSelectPlayers = new JLabel("Select players:");
		lblSelectPlayers.setBounds(238, 97, 93, 16);
		add(lblSelectPlayers);
		
		JLabel lblX = new JLabel("X");
		lblX.setBounds(134, 146, 17, 16);
		add(lblX);
		
		player1Type = new DefaultComboBoxModel<String>();
		player1Type.addElement("Player 1");
		player1Type.addElement("CPU");
		player1Type.setSelectedItem("Player 1");
		
		@SuppressWarnings("rawtypes")
		JComboBox comboBox = new JComboBox(player1Type);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboBox.getSelectedItem().equals("CPU"))
				{
					textField.setEditable(false);
					textField.setText("BB-8");
				}
				else
				{
					textField.setEditable(true);
					textField.setText("");
				}
			}
		});
		comboBox.setBounds(163, 142, 115, 27);
		add(comboBox);
		
		JLabel lblO = new JLabel("O");
		lblO.setBounds(134, 183, 17, 16);
		add(lblO);
		
		player2Type = new DefaultComboBoxModel<String>();
		player2Type.addElement("Player 2");
		player2Type.addElement("CPU");
		player2Type.setSelectedItem("Player 2");
		
		@SuppressWarnings("rawtypes")
		JComboBox comboBox_1 = new JComboBox(player2Type);
		comboBox_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboBox_1.getSelectedItem().equals("CPU"))
				{
					textField_1.setEditable(false);
					textField_1.setText("R2-D2");
				}
				else
				{
					textField_1.setEditable(true);
					textField_1.setText("");
				}
			}
		});
		comboBox_1.setBounds(163, 179, 115, 27);
		add(comboBox_1);
		
		textField = new JTextField();
		textField.setBounds(290, 141, 130, 26);
		add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(290, 178, 130, 26);
		add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnNewGame = new JButton("New Game");
		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PlayerType pt1, pt2;
				if(comboBox.getSelectedItem().equals("Player 1"))
					pt1 = PlayerType.HUMAN;
				else
					pt1 = PlayerType.COMPUTER;
				
				if(comboBox_1.getSelectedItem().equals("Player 2"))
					pt2 = PlayerType.HUMAN;
				else
					pt2 = PlayerType.COMPUTER;
				
				Player p1 = new Player(textField.getText(), pt1, "x");
				Player p2 = new Player(textField_1.getText(), pt2, "o");
				
				Game game = new Game(p1, p2);

				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new GamePanel(currentFrame, game));
				currentFrame.getContentPane().revalidate();
				currentFrame.getContentPane().repaint();
			}
		});
		btnNewGame.setBounds(214, 249, 117, 45);
		add(btnNewGame);
	}
}
