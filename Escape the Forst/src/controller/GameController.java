/**
 * 
 */
package controller;

import gameExceptions.GameException;
import model.RoomDB;

/**
 * @author chris
 * @version 1.0, ITEC 3860, 5-28-22
 * 
 * This class represents the game controller and acts as a messanger between the user command and the command class. It also has starting information for the game
 *
 */
public class GameController {
	public final int FIRST_ROOM =0;
	private Commands commands = new Commands();
	
	/**
	 * Method displayFirstRoom displays the first room
	 * @param 
	 * @return String
	 * @throws GameException
	 */
	public String displayFirstRoom() throws GameException{
		String temp = (RoomDB.getInstrance().getRoom(FIRST_ROOM).getDisplay());
		RoomDB.getInstrance().getRoom(FIRST_ROOM).setRoomVisited(true);
		return (temp); //first room display
	}
	
	/**
	 * Method executeCommand transfers the command to Commands object
	 * @param cmd
	 * @return String
	 * @throws GameException
	 */
	public String executeCommand (String cmd) throws GameException {
		return (commands.executeCommand(cmd));
	}
	
	/**
	 * Method prints the entire text of the game
	 * @param 
	 * @return String
	 */
	public String printMap() {
		return (RoomDB.getInstrance().getMap());
	}
}
