package pongGameISU;

/**
 * The interface Bounds containing all useful values to be used in the GUI and
 * game logic.
 * 
 * @author Mandeep Sran, Navaneeth Mulolli Panikan
 * @version June 17, 2015
 *
 */
public interface Bounds {

	/**
	 * The width of the GUI excluding the window border
	 */
	public static final int WIDTH = 1091;

	/**
	 * The length of the GUI excluding the top close/minimize bar and bottom
	 * window border
	 */
	public static final int HEIGHT = 568;

	/**
	 * The board drawn out onto the JPanel's minimum X component value used for
	 * Ball/Paddle handling
	 */
	public static final int BOARD_MIN_X = 155;

	/**
	 * The board drawn out onto the JPanel's minimum Y component value used for
	 * Ball/Paddle handling
	 */
	public static final int BOARD_MIN_Y = 97;

	/**
	 * The board drawn out onto the JPanel's maximum X component value used for
	 * Ball/Paddle handling
	 */
	public static final int BOARD_MAX_X = 935;

	/**
	 * The board drawn out onto the JPanel's maximum Y component value used for
	 * Ball/Paddle handling
	 */
	public static final int BOARD_MAX_Y = 584;

}
