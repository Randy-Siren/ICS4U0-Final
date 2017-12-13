package pongGameISU;

/**
 * The GameStatus Enum which provides a method for organized information of
 * storing the data of the games current status and easy switching as the
 * program progresses
 * 
 * @author Mandeep Sran, Navaneeth Mulolli Panikan
 * @version June 17, 2015
 *
 */
public enum GameStatus {

	/**
	 * Initialization of the Enum values
	 */
	START(0, "The GUI menu that is opened when game is first started"), PLAY(1,
			"When the game is being played and pieces are moving"), PAUSE(2,
			"The GUI menu that is opened when game is paused"), CONTROLS(3,
			"Displays the controls to play the game"), HELP(4,
			"Displays the instructions on playing the game"), CREDITS(5,
			"Displays the names and description of the software creators");

	/**
	 * Constructor for the GameStatus enum. All values initialized here
	 * 
	 * @param state
	 *            The integer value of the GameStatus
	 * @param description
	 *            The definition of the GameStatus and what it does
	 */
	GameStatus(int state, String description) {
		this.state = state;
		this.description = description;
	}

	/**
	 * Returns the integer value of the GameStatus
	 * 
	 * @return state The value itself
	 */
	public int getState() {
		return state;
	}

	/**
	 * Sets the integer value of the GameStatus
	 * 
	 * @param state
	 *            The value itself
	 */
	public void setState(int state) {
		this.state = state;
	}

	/**
	 * Returns the definition of the GameStatus and what it does
	 * 
	 * @return The definition itself
	 */
	public String getDescription() {
		return description;
	}

	private int state;
	private String description;

}
