/**
 * 
 */
package controller;

/**
 * @author chris
 *
 */
public class Puzzle {
	private String name;
	private int puzzleID;
	private String puzzleDesc;
	private String hint;
	private boolean solved = false;
	private char direction;
	
	public Puzzle() {
		
	}
	
	public Puzzle(String name, int id, String desc, String hint, char direction) {
		this.name =name;
		puzzleID = id;
		puzzleDesc = desc;
		this.hint = hint;
		this.direction = direction;
		
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the puzzleID
	 */
	public int getPuzzleID() {
		return puzzleID;
	}

	/**
	 * @param puzzleID the puzzleID to set
	 */
	public void setPuzzleID(int puzzleID) {
		this.puzzleID = puzzleID;
	}

	/**
	 * @return the puzzleDesc
	 */
	public String getPuzzleDesc() {
		return puzzleDesc;
	}

	/**
	 * @param puzzleDesc the puzzleDesc to set
	 */
	public void setPuzzleDesc(String puzzleDesc) {
		this.puzzleDesc = puzzleDesc;
	}

	/**
	 * @return the hint
	 */
	public String getHint() {
		return hint;
	}

	/**
	 * @param hint the hint to set
	 */
	public void setHint(String hint) {
		this.hint = hint;
	}

	
	
	/**
	 * @return the solved
	 */
	public boolean isSolved() {
		return solved;
	}

	/**
	 * @param solved the solved to set
	 */
	public void setSolved(boolean solved) {
		this.solved = solved;
	}

	@Override
	public String toString() {
		return "Puzzle [name=" + name + ", puzzleID=" + puzzleID + ", puzzleDesc=" + puzzleDesc + ", hint=" + hint
				+ "]";
	}

	/**
	 * @return the direction
	 */
	public char getDirection() {
		return direction;
	}

	/**
	 * @param direction the direction to set
	 */
	public void setDirection(char direction) {
		this.direction = direction;
	}
	
	
}
