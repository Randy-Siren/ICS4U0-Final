package pongGameISU.entities;

import pongGameISU.Bounds;

/**
 * The Ball object which will act as the ball for when the game is in progress
 * extending a GameObject
 * 
 * @author Mandeep Sran, Navaneeth Mulolli Panikan
 * @version June 17, 2015
 *
 */
public class Ball extends GameObject implements Bounds {

	/**
	 * The displacement in X component every tick in the game, can be considered
	 * the 'X-speed'
	 */
	public int displacementX;

	/**
	 * The displacement in Y component every tick in the game, can be considered
	 * the 'Y-speed'
	 */
	public int displacementY;

	/**
	 * Creates the ball and initializes all the values
	 * 
	 * @param x
	 *            The starting x coordinate of the ball
	 * @param y
	 *            The starting y coordinate of the ball
	 * @param width
	 *            The width of the ball
	 * @param height
	 *            The height of the ball
	 */
	public Ball(int x, int y, int width, int height) {
		super(x, y, width, height);
	}

	/**
	 * Moves the ball taking into account any obstacles
	 */
	public void move() {
		if (isCollidingY())
			displacementY *= -1;
		y += displacementY;
		x += displacementX;
		boundingBox.y = y;
		boundingBox.x = x;

	}

	/**
	 * Returns a boolean informing whether the ball hits the minimum and maximum
	 * x values for the board, this means a point is scored
	 * 
	 * @return If the ball hit the left or right walls
	 */
	public boolean isCollidingX() {
		if (x + displacementX < Bounds.BOARD_MIN_X
				|| x + displacementX > Bounds.BOARD_MAX_X)
			return true;
		return false;
	}

	/**
	 * Returns a boolean informing whether the ball hits the minimum and maximum
	 * y values for the board, this means the ball will bounce back in the
	 * opposite y direction
	 * 
	 * @return If the ball hit the top or bottom walls
	 */
	public boolean isCollidingY() {
		if (y + displacementY < Bounds.BOARD_MIN_Y
				|| y + displacementY + height > Bounds.BOARD_MAX_Y)
			return true;
		return false;
	}

	/**
	 * Returns a boolean informing whether the ball hits an imaginary line at
	 * the front of each paddle in the game, this means the ball will bounce
	 * back in the opposite x direction
	 * 
	 * @param player
	 *            The player the ball is colliding with
	 * @param paddle
	 *            The paddle the ball is colliding with
	 * @return If the ball collides with the paddle
	 */
	public boolean isCollidingPaddle(int player, Paddle paddle) {
		if (player == 1)
			if (boundingBox.intersectsLine(paddle.getBoundingBox().getX()
					+ paddle.getWidth(), paddle.getBoundingBox().getY(), paddle
					.getBoundingBox().getX() + paddle.getWidth(), paddle
					.getBoundingBox().getY() + paddle.getHeight()))
				return true;
		if (player == 2)
			if (boundingBox.intersectsLine(paddle.getBoundingBox().getX(),
					paddle.getBoundingBox().getY(), paddle.getBoundingBox()
							.getX(),
					paddle.getBoundingBox().getY() + paddle.getHeight()))
				return true;
		return false;
	}

	/**
	 * Handles paddle collision by reversing the x direction
	 */
	public void handlePaddleCollision() {
		displacementX *= -1;
		x += displacementX;
		boundingBox.x = x;
	}

	/**
	 * Resets the ball into its original location and its speed
	 * 
	 * @param player
	 *            The player that the ball will move relative to
	 */
	public void reset(int player) {
		if (player == 1) {
			displacementX = 5;
			displacementY = 5;
		}
		if (player == 2) {
			displacementX = -5;
			displacementY = -5;
		}

		x = (Bounds.BOARD_MIN_X + Bounds.BOARD_MAX_X) / 2;
		y = (Bounds.BOARD_MIN_Y + Bounds.BOARD_MAX_Y) / 2;
	}
}
