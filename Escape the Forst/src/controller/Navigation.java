/**
 * 
 */
package controller;


/**
 * @author chris
 * @version 1.0, ITEC 3860, 5-28-22
 * 
 * This class represents Navigation mapping between rooms. It functions essentially a key value pair
 *
 */

public class Navigation {
	private String direction;
	private int roomDestination;
	final private String[] VALID_DIRECTIONS = {"WEST","NORTH","SOUTH","EAST","UP","DOWN"};

	public Navigation(String direction, String roomDestination) {
		super();
		this.direction = direction;
		this.roomDestination = Integer.parseInt(roomDestination);
	}
	
	@Override
	public String  toString() {
		return "Navigation [direction " + direction + ", roomDestination " + roomDestination + "]";
	}

	/**
	 * @return the direction
	 */
	protected String getDirection() {
		return direction;
	}

	/**
	 * @param direction the direction to set
	 */
	protected void setDirection(String direction) {
		this.direction = direction;
	}

	/**
	 * @return the roomDestination
	 */
	protected int getRoomDestination() {
		return roomDestination;
	}

	/**
	 * @param roomDestination the roomDestination to set
	 */
	protected void setRoomDestination(int roomDestination) {
		this.roomDestination = roomDestination;
	}
	
	//this is handled in Room
	public void buildExit(String exit) {
		
	}
}

