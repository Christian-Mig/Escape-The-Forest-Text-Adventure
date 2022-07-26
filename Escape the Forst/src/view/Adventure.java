/**
 * 
 */
package view;

import java.util.Scanner;

import controller.GameController;
import gameExceptions.GameException;
import model.ItemDB;
import model.MonsterDB;
import model.PuzzleDB;
import model.RoomDB;

/**
 * @author chris
 * @version 1.0, ITEC 3860, 6-20-22
 * 
 * This class represents the users main interaction with the game. it recieves and sends commands from the user and initiates game assets.
 *
 */
public class Adventure {
	GameController gc = new GameController();
	Scanner pInput = null;
	
	
	public static void main(String[] args) throws GameException {
		Adventure adventure = new Adventure();
		RoomDB.getInstrance().readRooms();
		ItemDB.getInstance().readItems();
		MonsterDB.getInstance().readMonsters();
		PuzzleDB.getInstance().readPuzzles();
		RoomDB.getInstrance();
		RoomDB.main(args);
		//System.out.println(ItemDB.getInstance().getItem(1));
		adventure.PlayGame();
		/**String x= RoomDB.getInstrance().getMap();
		//RoomDB.getInstrance().getRoom(2);
		System.out.println( x);
		 x=adventure.getCommand("n");
		System.out.println( x);
	**/
	}
	
	public Adventure() {}
	
	
	/**
	 * this method is to allow the player to play the game.
	 * First, when it is called, you need to call printMap()
	 * Then, call displayFirstRoom()
	 * Then, call getCommand() then, executeCommand
	 *
	 * Method playGame Allows the player to play the game.
	 * Prints an introduction message Loops until the user chooses to exit.
	 * Prints the current rooms display text if the direction is valid.
	 * If an invalid direction is entered, catches the exception and prints the
	 * message in that exception. Calls getCommand to get users input.
	 * Passes the user's command to Commands, executeCommand for processing.
	 * This will handle move, item, look, and backpack commands.
	 * @throws GameExceptionuse x
	 */
	private void PlayGame() throws GameException {
		//System.out.println(gc.printMap());
		pInput = new Scanner(System.in);
		System.out.println("\nEnter your commands through text; enjoy!\n" + getCommand("help") +"\n");
		System.out.println(gc.displayFirstRoom());
		boolean mainPlayerLoop = true;
		while(mainPlayerLoop){
			try {
				System.out.println("What shall you do?");
				String temp = getCommand(pInput.nextLine());
				if(temp.substring(0,5).equalsIgnoreCase("Score")) { 
					temp = getCommand("look") + "Congratulations you won!\n"+temp; mainPlayerLoop = false;}
				System.out.println(temp);
			} catch (GameException e) {
				System.out.println("that was not a valid command");
			}
			
		}
		pInput.close();
	}

	/**
	 * Method getCommand recieve user input and returns this to playGame
	 * @param cmd
	 * @return
	 * @throws GameException
	 */
	private String getCommand(String cmd) throws GameException {
		return gc.executeCommand(cmd);
	}
	
}
