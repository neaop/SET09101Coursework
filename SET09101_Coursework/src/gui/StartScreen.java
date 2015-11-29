package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StartScreen {

	private JFrame frmSwampwars;
	private JTextField textField;
	private int swampSize = 4;
	private String ogreName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StartScreen window = new StartScreen();
					window.frmSwampwars.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public StartScreen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSwampwars = new JFrame();
		frmSwampwars.setTitle("SwampWars");
		frmSwampwars.setBounds(100, 100, 258, 210);
		frmSwampwars.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSwampwars.getContentPane().setLayout(null);

		JButton btnStartGame = new JButton("Start Game");
		btnStartGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GameScreen gs = new GameScreen();
				gs.setVisible(true);
			}
		});
		btnStartGame.setBounds(128, 138, 104, 23);
		frmSwampwars.getContentPane().add(btnStartGame);

		textField = new JTextField();
		textField.setBounds(84, 38, 148, 20);
		frmSwampwars.getContentPane().add(textField);
		textField.setColumns(10);

		JLabel lblOgreName = new JLabel("Ogre Name:");
		lblOgreName.setBounds(4, 41, 78, 14);
		frmSwampwars.getContentPane().add(lblOgreName);

		JLabel lblSwampSize = new JLabel("Swamp Size:");
		lblSwampSize.setBounds(4, 80, 78, 14);
		frmSwampwars.getContentPane().add(lblSwampSize);

		final JButton buttonBigger = new JButton("\u2192");
		buttonBigger.setBounds(179, 76, 53, 23);
		frmSwampwars.getContentPane().add(buttonBigger);

		final JButton buttonSmaller = new JButton("\u2190");
		buttonSmaller.setEnabled(false);
		buttonSmaller.setBounds(84, 76, 53, 23);
		frmSwampwars.getContentPane().add(buttonSmaller);

		final JLabel lblSize = new JLabel(swampSize + "x" + swampSize);
		lblSize.setHorizontalAlignment(SwingConstants.CENTER);
		lblSize.setBounds(134, 81, 46, 14);
		frmSwampwars.getContentPane().add(lblSize);

		buttonBigger.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				swampSize++;
				lblSize.setText(swampSize + "x" + swampSize);
				if (swampSize > 4) {
					buttonSmaller.setEnabled(true);
				}
				if (swampSize >= 10) {
					buttonBigger.setEnabled(false);
				}
			}
		});

		buttonSmaller.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				swampSize--;
				lblSize.setText(swampSize + "x" + swampSize);
				if (swampSize <= 4) {
					buttonSmaller.setEnabled(false);
				}
				if (swampSize < 10) {
					buttonBigger.setEnabled(true);
				}
			}
		});

	}

	public int getSwampSize() {
		return swampSize;
	}

	public String getOgreName() {
		return ogreName;
	}
}
