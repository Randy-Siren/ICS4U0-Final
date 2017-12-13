package pongGameISU.gui;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

import pongGameISU.Bounds;
import pongGameISU.GameStatus;
import pongGameISU.entities.Ball;
import pongGameISU.entities.Paddle;
import pongGameISU.utils.ImageLoader;

/**
 * The class where all the drawing and game logic is done. Most important class
 * regarding the games source code.
 * 
 * @author Mandeep Sran, Navaneeth Mulolli Panikan
 * @version June 17, 2015
 *
 */
public class Board extends JPanel implements KeyListener, ActionListener {

	/**
	 * Default serial ID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The current status of the game GameStatus instance
	 */
	GameStatus status;

	/**
	 * Current selection in the Start menu to select one of the four options
	 */
	private int currentSelectionStart = 0;

	/**
	 * The BufferedImage array which consists of all four options for the Start
	 * menu
	 */
	private BufferedImage[] startImages = new BufferedImage[4];

	/**
	 * Paddle instance of the first player
	 */
	private Paddle player1;

	/**
	 * Paddle instance of the second player
	 */
	private Paddle player2;

	/**
	 * Ball instance which is only created once
	 */
	private Ball ball;

	/**
	 * Bonus speed counter which is given to ball when current round continues
	 * for too long
	 */
	private int ballBonus;

	/**
	 * The pressed keys [W,S,UP,DOWN] for player key checking
	 */
	private boolean[] pressedKeys = new boolean[4];

	/**
	 * The BufferedImage array which consists of the background
	 */
	private BufferedImage[] playImages = new BufferedImage[1];

	/**
	 * The BufferedImage array which consists of the winning messages
	 */
	private BufferedImage[] winImages = new BufferedImage[2];

	/**
	 * The current score of the plays, Player 1 holds index 0 while Player 2
	 * holds index 1
	 */
	private int[] scores = { 0, 0 };

	/**
	 * The font for drawing the score
	 */
	private final Font scoreFont = new Font("Century Gothic", 1, 50);

	/**
	 * The BufferedImage array which consists of the Control menu images
	 */
	private BufferedImage[] controlsImages = new BufferedImage[1];

	/**
	 * The BufferedImage array which consists of the Help menu images
	 */
	private BufferedImage[] helpImages = new BufferedImage[1];

	/**
	 * The BufferedImage array which consists of the Credits menu images
	 */
	private BufferedImage[] creditsImages = new BufferedImage[1];

	/**
	 * The constructor which initializes the BufferedImages, GameStatus and
	 * Timer for further use
	 */
	public Board() {
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);
		addKeyListener(this);
		startImages = ImageLoader.loadImages("PongStart", "jpg", 4);
		playImages = ImageLoader.loadImages("PongPlay", "jpg", 1);
		winImages = ImageLoader.loadImages("PongWin", "png", 2);
		controlsImages = ImageLoader.loadImages("PongControls", "jpg", 1);
		helpImages = ImageLoader.loadImages("PongHelp", "jpg", 1);
		creditsImages = ImageLoader.loadImages("PongCredits", "jpg", 1);
		status = GameStatus.START;
		Timer timer = new Timer(1000 / 60, this);
		timer.start();
	}

	/**
	 * An overridden method provided by JPanel class which gives access to
	 * drawing onto the JPanel
	 * 
	 * @param g2
	 *            The Graphics instance provided by JPanel class
	 */
	public void paintComponent(Graphics g2) {
		Graphics2D g = (Graphics2D) g2;
		g2.clearRect(0, 0, getWidth(), getHeight());
		switch (status) {
		case START:
			g.drawImage(startImages[currentSelectionStart], 0, 0, null);
			break;
		case PLAY:
			g.drawImage(playImages[0], 0, -10, null);
			g.setColor(Color.WHITE);
			g.fill(player1.getBoundingBox());
			g.fill(player2.getBoundingBox());
			g.fillOval(ball.getX(), ball.getY(), ball.getWidth(),
					ball.getHeight());
			g.setFont(scoreFont);
			g.drawString("" + scores[0], 35, 300);
			g.drawString("" + scores[1], 1035, 300);
			if (scores[0] == 10)
				g.drawImage(winImages[0], 10, -10, null);
			if (scores[1] == 10)
				g.drawImage(winImages[1], 10, -10, null);
			break;
		case PAUSE:
			break;
		case CONTROLS:
			g.drawImage(controlsImages[0], 0, 0, null);
			break;
		case HELP:
			g.drawImage(helpImages[0], 0, 0, null);
			break;
		case CREDITS:
			g.drawImage(creditsImages[0], 0, 0, null);
			break;
		default:
			break;
		}
	}

	/**
	 * The ActionListener which is activated every time the Timer instance ticks
	 * allowing for game logic to be modified at a fast pace
	 * 
	 * @param arg0
	 *            The ActionEvent occuring
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (status == GameStatus.PLAY) {
			if (scores[0] != 10 && scores[1] != 10) {
				if (player1 != null && player2 != null && ball != null) {
					if (!ball.isCollidingX()) {
						if (ball.isCollidingPaddle(1, player1)) {
							ball.handlePaddleCollision();
							ballBonus += 1;
							if (ballBonus == 5) {
								ball.displacementX += 1;
								ball.displacementX += 1;
								ballBonus = 0;
							}
						} else if (ball.isCollidingPaddle(2, player2)) {
							ball.handlePaddleCollision();
							ballBonus += 1;
							if (ballBonus == 5) {
								ball.displacementX -= 1;
								ball.displacementX -= 1;
								ballBonus = 0;
							}
						}
						ball.move();
					} else {
						int winner = 0;
						if (ball.getX() < player1.getX()) {
							winner = 1;
							scores[1] += 1;
						} else {
							winner = 2;
							scores[0] += 1;
						}
						ball.reset(winner);
					}
					player1.displace(pressedKeys);
					player2.displace(pressedKeys);
				}
			}
		}
		repaint();
	}

	/**
	 * The KeyListener method which activates upon a key press. Here menus are
	 * switched and values are modified as the user will navigate through the
	 * menu or play the game
	 * 
	 * @param e
	 *            The KeyEvent containing information such as the key pressed
	 *            and its value
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		if (status == GameStatus.START) {
			if (e.getKeyCode() == KeyEvent.VK_UP) {
				currentSelectionStart = currentSelectionStart == 0 ? 0
						: currentSelectionStart - 1;
			}
			if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				currentSelectionStart = currentSelectionStart == 3 ? 3
						: currentSelectionStart + 1;
			}
			if (e.getKeyCode() == KeyEvent.VK_SPACE) {
				if (currentSelectionStart == 0) {
					status = GameStatus.PLAY;
					player1 = new Paddle(25 + Bounds.BOARD_MIN_X,
							((Bounds.BOARD_MIN_Y + Bounds.BOARD_MAX_Y) / 2)
									- (75 / 2), 10, 75, 1);
					player2 = new Paddle(Bounds.BOARD_MAX_X - 25,
							((Bounds.BOARD_MIN_Y + Bounds.BOARD_MAX_Y) / 2)
									- (75 / 2), 10, 75, 2);
					ball = new Ball(
							(Bounds.BOARD_MIN_X + Bounds.BOARD_MAX_X) / 2,
							(Bounds.BOARD_MIN_Y + Bounds.BOARD_MAX_Y) / 2, 20,
							20);
					scores[0] = 0;
					scores[1] = 0;
					ballBonus = 0;
					ball.reset(1);
				}
				if (currentSelectionStart == 1) {
					status = GameStatus.CONTROLS;
				}
				if (currentSelectionStart == 2) {
					status = GameStatus.HELP;
				}
				if (currentSelectionStart == 3) {
					status = GameStatus.CREDITS;
				}
				return;
			}
		}
		if (status == GameStatus.PLAY) {
			if (scores[0] == 10 || scores[1] == 10) {
				if (e.getKeyCode() == KeyEvent.VK_SPACE) {
					status = GameStatus.START;
				}
			} else if (player1 != null && player2 != null && ball != null) {
				if (e.getKeyCode() == KeyEvent.VK_W) {
					pressedKeys[0] = true;
				}
				if (e.getKeyCode() == KeyEvent.VK_S) {
					pressedKeys[1] = true;
				}
				if (e.getKeyCode() == KeyEvent.VK_UP) {
					pressedKeys[2] = true;
				}
				if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					pressedKeys[3] = true;
				}
				if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					status = GameStatus.START;
				}
			}
		}
		if (status == GameStatus.CONTROLS) {
			if (e.getKeyCode() == KeyEvent.VK_SPACE) {
				status = GameStatus.START;
			}
		}
		if (status == GameStatus.HELP) {
			if (e.getKeyCode() == KeyEvent.VK_SPACE) {
				status = GameStatus.START;
			}
		}
		if (status == GameStatus.CREDITS) {
			if (e.getKeyCode() == KeyEvent.VK_SPACE) {
				status = GameStatus.START;
			}
		}
		repaint();
	}

	/**
	 * The KeyListener method which activates upon a key being released. Here
	 * the values of keys pressed are modified to stop infinite motion of the
	 * Paddles when the game is started
	 * 
	 * @param e
	 *            The KeyEvent containing information such as the key pressed
	 *            and its value
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		if (status == GameStatus.PLAY) {
			if (player1 != null && player2 != null && ball != null) {
				if (e.getKeyCode() == KeyEvent.VK_W)
					pressedKeys[0] = false;
				if (e.getKeyCode() == KeyEvent.VK_S)
					pressedKeys[1] = false;
				if (e.getKeyCode() == KeyEvent.VK_UP)
					pressedKeys[2] = false;
				if (e.getKeyCode() == KeyEvent.VK_DOWN)
					pressedKeys[3] = false;
			}
		}
		repaint();
	}

	/**
	 * The KeyListener method which activates upon a key being typed. This is a
	 * default method so it must be included however to the game itself it holds
	 * no relevance
	 * 
	 * @param e
	 *            The KeyEvent containing information such as the key pressed
	 *            and its value
	 */
	@Override
	public void keyTyped(KeyEvent e) {
	}

	/**
	 * Gives the focus to the JPanel allowing for the KeyListener to function
	 * properly
	 */
	public void addNotify() {
		super.addNotify();
		requestFocus();
	}
}
