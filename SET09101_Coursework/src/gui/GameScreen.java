package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import swampWars.actors.Enemy;
import swampWars.actors.Ogre;
import swampWars.actors.SwampDenizen;
import swampWars.control.GameControl;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ArrayList;

public class GameScreen extends JFrame {

	private JPanel gui = new JPanel(new BorderLayout(3, 3));
	private JPanel gameBoard;
	private JPanel buttons;
	private JPanel counters;
	private JLabel swampSquares[][];
	private JLabel smoosh, turn, game;
	private static int xSize = 4, ySize = 4;
	private static String ogreName;
	private static GameControl gc;
	private final JButton move, undo, redo, quit;

	private final JComboBox dietList;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameScreen frame = new GameScreen(xSize, ySize, ogreName);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GameScreen(int xSize, int ySize, String name) {
		this.xSize = xSize;
		this.ySize = ySize;
		this.ogreName = name;

		GameControl.setX_SIZE(this.xSize - 1);
		GameControl.setY_SIZE(this.ySize - 1);

		this.gc = new GameControl(this.ogreName);
		this.swampSquares = new JLabel[this.xSize][this.ySize];

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, (xSize * 55), (ySize * 55) + 200);
		gui.setBorder(new EmptyBorder(5, 5, 5, 5));
		// gui.setLayout(new BorderLayout());
		setContentPane(gui);

		gameBoard = new JPanel();
		gui.add(gameBoard, BorderLayout.PAGE_START);
		gameBoard.setLayout(new GridLayout(this.ySize, this.xSize, 0, 0));
		// gameBoard.setBorder(new LineBorder(Color.BLACK, 5));

		for (int y = 0; y < this.ySize; y++) {
			for (int x = 0; x < this.xSize; x++) {
				JLabel l = new JLabel(/* x + "," + y */);
				ImageIcon icon = new ImageIcon(new BufferedImage(50, 50, BufferedImage.TYPE_INT_ARGB));
				l.setIcon(icon);
				l.setBorder(new LineBorder(Color.BLACK));
				l.setHorizontalTextPosition(JLabel.CENTER);
				swampSquares[x][y] = l;
				gameBoard.add(swampSquares[x][y]);
			}
		}

		buttons = new JPanel();
		buttons.setLayout(new GridLayout(4, 0));
		move = new JButton("Move");
		buttons.add(move);
		move.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gc.nextTurn();
				updateGrid();
			}
		});

		undo = new JButton("Undo");
		buttons.add(undo);
		undo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gc.undo();
				updateGrid();
			}
		});

		redo = new JButton("Redo");
		buttons.add(redo);
		redo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gc.redo();
				updateGrid();
			}
		});

		String[] dietSrtings = { "Hungry for Knights!", "Hungry for Enemies!", "Hungry for Burgers!" };

		// Create the combo box, select item at index 4.
		// Indices start at 0, so 4 specifies the pig.
		dietList = new JComboBox(dietSrtings);
		dietList.setSelectedIndex(0);
		dietList.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int dietNum = dietList.getSelectedIndex();
				System.out.println(dietNum);
				gc.getCurrentState().getPlayer().updateDiet(dietNum);
				System.out.println(gc.getCurrentState().getPlayer().getDiet());
			}
		});

		buttons.add(dietList);
		gui.add(buttons);

		counters = new JPanel();
		counters.setLayout(new GridLayout(4, 0));

		game = new JLabel("Swamp Wars!");
		counters.add(game);

		turn = new JLabel("Turn: " + gc.getTurnCount());
		counters.add(turn);

		smoosh = new JLabel("Points: " + gc.getCurrentState().getPlayer().getSmooshCounter());
		counters.add(smoosh);

		quit = new JButton("Quit");
		quit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			}
		});

		gui.add(counters, BorderLayout.EAST);

		updateGrid();
	}

	private void updateGrid() {
		if (gc.isGameOgre()) {
			move.setEnabled(false);
			undo.setEnabled(false);
			redo.setEnabled(false);
			dietList.setEnabled(false);
			game.setText("Game Ogre!");
		}

		turn.setText("Turn: " + gc.getTurnCount());
		smoosh.setText("Points: " + gc.getCurrentState().getPlayer().getSmooshCounter());
		for (int x = 0; x < this.xSize; x++) {
			for (int y = 0; y < this.ySize; y++) {
				swampSquares[x][y].setText("");
				ImageIcon icon = new ImageIcon(new BufferedImage(50, 50, BufferedImage.TYPE_INT_ARGB));
				swampSquares[x][y].setIcon(icon);
			}
		}

		ArrayList<Enemy> el = gc.getCurrentState().getEnemyList();
		Ogre og = gc.getCurrentState().getPlayer();
		for (int x = 0; x < xSize; x++) {
			for (int y = 0; y < ySize; y++) {
				int count = 0;

				if ((og.getXCoord() == x) && (og.getYCoord() == y)) {
					URL imgURL = GameScreen.class.getResource("/Ogre.jpg");

					ImageIcon ogreIcon = new ImageIcon(imgURL);
					swampSquares[x][y].setIcon(ogreIcon);
					swampSquares[x][y].setHorizontalTextPosition(JLabel.CENTER);
				}
				for (Enemy en : el) {
					if ((en.getXCoord() == x && en.getYCoord() == y)) {
						count++;
						URL imgURL = GameScreen.class.getResource("/" + en.getName() + ".jpg");
						ImageIcon icon = new ImageIcon(imgURL);
						swampSquares[x][y].setIcon(icon);
						swampSquares[x][y].setText(count + "");
						swampSquares[x][y].setHorizontalTextPosition(JLabel.CENTER);
						swampSquares[x][y].setForeground(Color.BLACK);

					}
				}
			}
		}
	}

}
