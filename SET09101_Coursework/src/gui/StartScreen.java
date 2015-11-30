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
	private JTextField txtName;
	private int swampXSize = 4;
	private int swampYSize = 4;
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
		frmSwampwars.setBounds(100, 100, 316, 176);
		frmSwampwars.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSwampwars.getContentPane().setLayout(null);

		JButton btnStartGame = new JButton("Start Game");
		btnStartGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String tempName = txtName.getText();

				if ((tempName != null) && (tempName.trim().length() > 0)) {
					ogreName = tempName;
					GameScreen gs = new GameScreen(swampXSize, swampYSize, ogreName);
					gs.setVisible(true);
				}

			}
		});
		btnStartGame.setBounds(142, 111, 104, 23);
		frmSwampwars.getContentPane().add(btnStartGame);

		txtName = new JTextField();
		txtName.setText("Drek");
		txtName.setBounds(120, 11, 148, 20);
		frmSwampwars.getContentPane().add(txtName);
		txtName.setColumns(10);

		JLabel lblOgreName = new JLabel("Ogre Name:");
		lblOgreName.setBounds(10, 14, 78, 14);
		frmSwampwars.getContentPane().add(lblOgreName);

		JLabel lblSwampWidth = new JLabel("Swamp Width:");
		lblSwampWidth.setBounds(10, 45, 100, 14);
		frmSwampwars.getContentPane().add(lblSwampWidth);

		final JButton buttonXBigger = new JButton("\u2192");
		buttonXBigger.setBounds(215, 41, 53, 23);
		frmSwampwars.getContentPane().add(buttonXBigger);

		final JButton buttonXSmaller = new JButton("\u2190");
		buttonXSmaller.setEnabled(false);
		buttonXSmaller.setBounds(120, 41, 53, 23);
		frmSwampwars.getContentPane().add(buttonXSmaller);

		final JLabel lblXSize = new JLabel("" + swampXSize);
		lblXSize.setHorizontalAlignment(SwingConstants.CENTER);
		lblXSize.setBounds(170, 46, 46, 14);
		frmSwampwars.getContentPane().add(lblXSize);

		JLabel lblSwampHieght = new JLabel("Swamp Hieght:");
		lblSwampHieght.setBounds(10, 74, 100, 14);
		frmSwampwars.getContentPane().add(lblSwampHieght);

		final JButton buttonYSmaller = new JButton("\u2190");

		buttonYSmaller.setEnabled(false);
		buttonYSmaller.setBounds(120, 70, 53, 23);
		frmSwampwars.getContentPane().add(buttonYSmaller);

		final JLabel lblYSize = new JLabel("" + swampYSize);
		lblYSize.setHorizontalAlignment(SwingConstants.CENTER);
		lblYSize.setBounds(170, 75, 46, 14);
		frmSwampwars.getContentPane().add(lblYSize);

		final JButton buttonYBigger = new JButton("\u2192");

		buttonYBigger.setBounds(215, 70, 53, 23);
		frmSwampwars.getContentPane().add(buttonYBigger);

		buttonXBigger.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				swampXSize++;
				lblXSize.setText("" + swampXSize);
				if (swampXSize > 4) {
					buttonXSmaller.setEnabled(true);
				}
				if (swampXSize >= 8) {
					buttonXBigger.setEnabled(false);
				}
			}
		});

		buttonXSmaller.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				swampXSize--;
				lblXSize.setText("" + swampXSize);
				if (swampXSize <= 4) {
					buttonXSmaller.setEnabled(false);
				}
				if (swampXSize < 8) {
					buttonXBigger.setEnabled(true);
				}
			}
		});

		buttonYSmaller.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				swampYSize--;
				lblYSize.setText("" + swampYSize);
				if (swampYSize <= 4) {
					buttonYSmaller.setEnabled(false);
				}
				if (swampYSize < 8) {
					buttonYBigger.setEnabled(true);
				}
			}
		});

		buttonYBigger.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				swampYSize++;
				lblYSize.setText("" + swampYSize);
				if (swampYSize > 4) {
					buttonYSmaller.setEnabled(true);
				}
				if (swampYSize >= 8) {
					buttonYBigger.setEnabled(false);
				}
			}
		});
	}

	public int getSwampSize() {
		return swampXSize;
	}

	public String getOgreName() {
		return ogreName;
	}
}
