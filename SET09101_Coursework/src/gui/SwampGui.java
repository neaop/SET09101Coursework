package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.swing.border.*;

public class SwampGui {
	private final JPanel gui = new JPanel(new BorderLayout(3, 3));
	private JButton[][] swampSquares = new JButton[8][8];
	private JPanel swampBoard;
	private final JLabel message = new JLabel("Swamp Wars!");

	SwampGui() {
		initialize();
	}

	public final void initialize() {
		gui.setBorder(new EmptyBorder(5, 5, 5, 5));
		JToolBar tools = new JToolBar();
		tools.setFloatable(false);
		gui.add(tools, BorderLayout.PAGE_START);
		tools.add(new JButton("New"));
		tools.add(new JButton("Undo"));
		tools.add(new JButton("Redo"));
		JButton quit = new JButton("Quit");
		quit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		tools.add(quit);
		tools.addSeparator();
		tools.add(message);

		gui.add(new JLabel("?"), BorderLayout.LINE_START);

		swampBoard = new JPanel(new GridLayout(0, 9));
		swampBoard.setBorder(new LineBorder(Color.BLACK));
		gui.add(swampBoard);

		Insets buttonMargin = new Insets(0, 0, 0, 0);
		for (int ii = 0; ii < swampSquares.length; ii++) {
			for (int jj = 0; jj < swampSquares[ii].length; jj++) {
				JButton b = new JButton();
				b.setMargin(buttonMargin);
				ImageIcon icon = new ImageIcon(new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
				b.setIcon(icon);
				if ((jj % 2 == 1 && ii % 2 == 1)
						// ){
						|| (jj % 2 == 0 && ii % 2 == 0)) {
					b.setBackground(Color.WHITE);
				} else {
					b.setBackground(Color.BLACK);
				}
				swampSquares[jj][ii] = b;
			}
		}

		swampBoard.add(new JLabel(" "));
		for (int ii = 0; ii < 8; ii++) {
			swampBoard.add(new JLabel("" + (ii + 1), SwingConstants.CENTER));
		}
		for (int ii = 0; ii < 8; ii++) {
			for (int jj = 0; jj < 8; jj++) {
				switch (jj) {
				case 0:
					swampBoard.add(new JLabel("" + (ii + 1), SwingConstants.CENTER));
				default:
					swampBoard.add(swampSquares[jj][ii]);
				}
			}
		}
	}

	public final JComponent getSwampBoard() {
		return this.swampBoard;
	}

	public final JComponent getGui() {
		return this.gui;
	}

	public static void main(String[] args) {
		Runnable r = new Runnable() {

			@Override
			public void run() {
				SwampGui sg = new SwampGui();

				JFrame f = new JFrame("SwampWars");
				f.add(sg.getGui());
				f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				f.setLocationByPlatform(true);

				// ensures the frame is the minimum size it needs to be
				// in order display the components within it
				f.pack();
				// ensures the minimum size is enforced.
				f.setMinimumSize(f.getSize());
				f.setVisible(true);
			}
		};
		SwingUtilities.invokeLater(r);
	}

}
