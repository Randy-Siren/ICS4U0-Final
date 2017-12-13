package pongGameISU.entities;

import pongGameISU.Bounds;

/**
 * The Paddle class which will act as the paddle for when the game is in
 * progress extending a GameObject
 * 
 * @author Mandeep Sran, Navaneeth Mulolli Panikan
 * @version June 17, 2015
 *
 */
public class Paddle extends GameObject implements Bounds {

	/**
	 * The value to see which paddle is being controlled, Player 1 or 2
	 */
	private int controls;

	/**
	 * The base speed of the paddle
	 */
	private int speed = 5;

	/**
	 * The displacement of the paddle, the additional value to the speed
	 */
	private int displacement = 0;

	/**
	 * Creates the Paddle and intializes all the values
	 * 
	 * @param x
	 *            The starting x coordinate of the paddle
	 * @param y
	 *            The starting y coordinate of the paddle
	 * @param width
	 *            The width of the paddle
	 * @param height
	 *            The height of the paddle
	 * @param player
	 *            The player value to act as the control
	 */
	public Paddle(int x, int y, int width, int height, int player) {
		super(x, y, width, height);
		controls = player;
	}

	/**
	 * Moves the paddle taking into account walls
	 */
	public void move() {
		if (y < Bounds.BOARD_MIN_Y) {
			y = Bounds.BOARD_MIN_Y + 1;
			return;
		}
		if (y > Bounds.BOARD_MAX_Y - height) {
			y = Bounds.BOARD_MAX_Y - height - 1;
			return;
		}
		y += displacement;
		boundingBox.y = y;
	}

	/**
	 * Displaces the paddle based on the key pressed and then moves the paddle
	 * 
	 * @param pressedKeys
	 */
	public void displace(boolean[] pressedKeys) {
		if (controls == 1) {
			if (pressedKeys[0])
				displacement = -(1 + speed);
			if (pressedKeys[1])
				displacement = 1 + speed;
		} else {
			if (pressedKeys[2])
				displacement = -(1 + speed);
			if (pressedKeys[3])
				displacement = 1 + speed;
		}
		move();
		displacement = 0;
	}
}
