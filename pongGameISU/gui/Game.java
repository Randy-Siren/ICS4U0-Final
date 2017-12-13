package pongGameISU.gui;

import javax.swing.JFrame;

/**
 * The class with a JFrame extended which holds the drawing board and everything
 * else within itself. The first step to where the magic begins.
 * 
 * @author Mandeep Sran, Navaneeth Mulolli Panikan
 * @version June 17, 2015
 *
 */
public class Game extends JFrame {

	/**
	 * Default serial ID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor to initialize the JFrame and create the drawing board
	 */
	public Game() {
		setTitle("Nav and Sran Pong");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1098, 698);
		setResizable(false);
		setContentPane(new Board());
		setVisible(true);
	}

}
