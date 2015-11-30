package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.swing.border.*;

public class SwampScreen {

	private final JPanel gui = new JPanel(new BorderLayout(3, 3));
	private JButton[][] swampSquares = new JButton[8][8];
	private JPanel swampBoard;
	private final JLabel message = new JLabel("Swamp Wars!");
	private String name;
	private static int xSize;
	private static int ySize;

	SwampScreen(int x, int y) {
		this.xSize = x;
		this.ySize = y;
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
				SwampScreen sc = new SwampScreen(xSize, ySize);

				JFrame f = new JFrame("SwampWars");
				f.add(sc.getGui());
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
