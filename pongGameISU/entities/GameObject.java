package pongGameISU.entities;

import java.awt.Rectangle;

/**
 * The GameObject super class which holds all common variables for the Ball and
 * Paddle such as sizes and locations and Bounding Boxes
 * 
 * @author Mandeep Sran, Navaneeth Mulolli Panikan
 * @version June 17, 2015
 *
 */
public class GameObject {

	/**
	 * The x location value of the GameObject
	 */
	public int x;

	/**
	 * The y location value of the GameObject
	 */
	public int y;

	/**
	 * The height of the GameObject
	 */
	public int height;

	/**
	 * The width of the GameObject
	 */
	public int width;

	/**
	 * The Rectangle object for the GameObject used in drawing and collision
	 * detection
	 */
	public Rectangle boundingBox;

	/**
	 * Creates the GameObject and initializes all the values
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
	public GameObject(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		boundingBox = new Rectangle(x, y, width, height);
	}

	/**
	 * Returns the x location value
	 * 
	 * @return x coordinate
	 */
	public int getX() {
		return x;
	}

	/**
	 * Returns the y location value
	 * 
	 * @return y coordinate
	 */
	public int getY() {
		return y;
	}

	/**
	 * Returns the height
	 * 
	 * @return height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Returns the width
	 * 
	 * @return width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Returns the bounding box Rectangle object
	 * 
	 * @return bounding box Rectangle
	 */
	public Rectangle getBoundingBox() {
		return boundingBox;
	}

}
