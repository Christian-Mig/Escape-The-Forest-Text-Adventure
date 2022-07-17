/**
 * 
 */
package controller;

import java.util.ArrayList;

import gameExceptions.GameException;

/**
 * @author chris
 * @version 1.0, ITEC 3860, 5-28-22
 * 
 * This class represents Commands class. It interprets the user inputs into the appropriate methods to complete the requests
 *
 */
public class Commands {
	final protected char[] VALID_DIRECTIONS = {'W','N','S','E','U','D'};
	final protected String[] ITEM_COMMANDS = {"G","R","I","L","B","U"};
	final public int EXIT_COMMAND = 5;
	private Player player = new Player();
	private boolean inCombat = false;
	Room current = new Room("0","test","test");
	//Room current = new Room("0","test","test").retirieveByID(player.getCurRoom());

	/**
	 * 
	 */
	protected Commands() {
		super();
	}
	
	/**
	 * Method validCommand attempts to parse cmd to the related action
	 * @param cmd
	 * @return String
	 * @throws GameException
	 */
	private int validateCommand(String cmd) throws GameException{
		try {
			//System.out.println(cmd);
			if(	cmd.split(" ")[0].equalsIgnoreCase("get") || cmd.substring(0, 1).equalsIgnoreCase(ITEM_COMMANDS[0])) {return 2;}
			
			else if( cmd.split(" ")[0].equalsIgnoreCase("remove") || cmd.substring(0, 1).equalsIgnoreCase(ITEM_COMMANDS[1])) {return 3;}
			
			else if( cmd.split(" ")[0].equalsIgnoreCase("inspect") || cmd.substring(0, 1).equalsIgnoreCase(ITEM_COMMANDS[2])) {return 4;}
			
			else if ( cmd.split(" ")[0].equalsIgnoreCase("look") || cmd.substring(0, 1).equalsIgnoreCase(ITEM_COMMANDS[3])) {return 5;}
			
			else if ( cmd.split(" ")[0].equalsIgnoreCase("backpack") || cmd.substring(0, 1).equalsIgnoreCase(ITEM_COMMANDS[4])) {return 6;}
			
			else if ( cmd.split(" ")[0].equalsIgnoreCase("use") ||  ( (cmd.substring(0, 1).equalsIgnoreCase(ITEM_COMMANDS[5])) && (cmd.length() > 4) ) ) {return 7;}
			
			else if ( cmd.split(" ")[0].equalsIgnoreCase("fight") ||  cmd.substring(0, 1).equalsIgnoreCase("f")) {return 8;}
			
			else if ( cmd.split(" ")[0].equalsIgnoreCase("help") ||  cmd.substring(0, 1).equalsIgnoreCase("h")) {return 9;}
			
			else {for(int i =0;  i < VALID_DIRECTIONS.length; i++) { 
				String direc =Character.toString(VALID_DIRECTIONS[i]);
				if(cmd.substring(0,1).equalsIgnoreCase(direc)){ return 0;}
				}
			}
			
		}catch(Exception e) {return 11;}

		return 11;
	}
	
	/**
	 * Method lookItem attempts to display description for selected item
	 * @param cmd, room
	 * @return String
	 * @throws GameException
	 */
	protected String executeCommand(String cmd) throws GameException {
		int cmdType =validateCommand(cmd);
		Room current =this.current.retirieveByID(player.getCurRoom());
		//System.out.println(cmdType);
		switch(cmdType) {
		case 0: return move(cmd);
		case 1: return itemCommand(cmd);
		case 2: return get(cmd, current);
		case 3: return remove(cmd, current);
		case 4: return lookItem(cmd, current);
		case 5: return current.getDisplay();
		case 6: return player.printBackpack();
		case 7: return useItem(cmd, current);
		case 8: return fight(cmd, current);
		case 9: return getHelp();
		default: throw new GameException("A valid command was not found");
		}
	}
	
	/**
	 * Method getHelp returns list of commands than puzzle hint
	 * @return String
	 */
	private String getHelp() {
		String help = "**Commands**\nLook (get info on current room)\nInspect <item_Name> (get info of selected item)"
				+ " \nBackpack (see health and items in backpack)\nGet <item_Name> (take selected item from room)\nRemove <item_Name> (remove selected item from backpack)"
				+ "\nFight (fight monsters in current room if available)\nXuse <item_Name> (uses selected item)\nHelp (Self-explanatory)";
		//TODO create check for if room has puzzle; add puzzle hint too if true
		//if(Room has puzzle) help += "\n\n" +getPuzzleHint;
		if(current.getPuzzle() !=null) {help += "\n" + current.getPuzzle().getHint();}
		return help;
	}
	
	/**
	 * Method lookItem attempts to move player to a new room base on direction entered
	 * @param cmdroom
	 * @return String
	 */
	private String move (String cmdRoom) {
		
		current = new Room("0","test","test").retirieveByID(player.getCurRoom());
		char dest = cmdRoom.charAt(0);
		current = current.retirieveByID(player.getCurRoom());
		//System.out.println(current.getRoomName());
		int rm = current.validDirection(dest);
		if(rm == 666) {return "That was not a valid direction";}
		else if(rm == 555) {return "That direction is blocked";}
		//System.out.println(rm);
		current = current.retirieveByID(rm);
		//System.out.println(current.getRoomName());
		player.setCurRoom(rm);
		String temp = current.getDisplay();
		if(!current.isRoomVisited()) {current.retirieveByID(rm).setRoomVisited(true); player.AddScore(25);}
		if(win()) return "Score: " + player.getScore();
		return temp;
	}
	
	private String itemCommand (String cmd) {
		return "ItemCommand";

	}
	
	/**
	 * Method get attempts to remove an item from current room and transfer it to player backpack
	 * @param cmd, room
	 * @return String
	 */
	private String get(String cmd, Room room) {
		ArrayList<Item> item = room.getItems();
		try {
			for(Item x : item) {
				//System.out.println(cmd.split(" ",2)[1]);
				//System.out.println(cmd.split(" ",2)[1].equalsIgnoreCase(x.getItemName()));
				if (cmd.split(" ", 2)[1].equalsIgnoreCase(x.getItemName())) {
					if(player.getBackpack().size() > player.getMAX_BP_SIZE()){return "Your backpack is full";}
					room.removeItem(x);
					player.addItem(x);
					if(x.getIsItemFirst()) {player.AddScore(15); x.setItemFirst(false);}
					room.updateRoom();

					return ( x.getItemName()+" added");
				};
			}
		}catch(Exception e) {return "That was not a valid command";}
		return ("That item was not found in the room");
	}
	
	/**
	 * Method remove remove an item from player backpack and transfer it to current room
	 * @param cmd, room
	 * @return String
	 */
	private String remove(String cmd, Room room) {
		ArrayList<Item> item = player.getBackpack();
		try {
			for(Item x : item) {
				if (cmd.split(" ",2)[1].equalsIgnoreCase(x.getItemName())) {
				player.removeItem(x);
				room.addItem(x);
				room.updateRoom();
				return ( x.getItemName()+" removed");
				};
			}
		}catch(Exception e) {return "That was not a valid command";}
		return "That item was not found in your backpack;\n" + player.printBackpack();
	}
	
	/**
	 * Method lookItem attempts to display description for selected item
	 * @param cmd, room
	 * @return String
	 */
	private String lookItem(String cmd, Room room) {
		ArrayList<Item> item = room.getItems();
		try {
			for(Item x : item) {
				if (cmd.split(" ",2)[1].equalsIgnoreCase(x.getItemName())) {return x.Display();};
			}
		}catch(Exception e) {return "That was not a valid command";}
		return ("That item was not found in the room");
	}
	
	
	/**
	 * Method fight controls the main combat encounters; it holds the player in the combat loop
	 * until either the player or monster is defeated
	 * @param cmd, room
	 * @return String
	 */
	private String fight(String cmd, Room room) throws GameException {
		//TODO create check that a living monster is in current room; end and return statement if false
		//System.out.println(room.getDisplay());
		current = room;
		boolean won = false;
		//System.out.println(room.getMonster());
		java.util.Scanner fight = new java.util.Scanner(System.in);
		if((room.getMonster() != null ) && (room.getMonster().getHitPoints() >0)) {
		inCombat = true;
		System.out.println("fight started!");

		while(inCombat) {
			System.out.println("Enter your action");
			cmd = fight.nextLine();
			if (fightCheck(cmd)) {
				System.out.println(executeCommand(cmd));
				if (room.getMonster().getHitPoints() < 1) {inCombat = false; player.AddScore(50); won = true; current.setMonster(null); break;}
				int monsterAttack =room.getMonster().attack();
				player.addHP(-monsterAttack);
				System.out.println(room.getMonster().getMonsterName()+" attacks you for "+ monsterAttack+"!\nHealth: "+player.getHp()+ "/" +player.getMAX_HP());
				if(player.getHp() < 1) { inCombat = false; player.AddScore(-50); player.addHP(5);break;}

				
			}else System.out.println("That cannot be done in combat");
		}
		//TODO what happens when we loss?
		String temp =  won?" you won":" you lost and managed to crawl away to safety";
		return "The fight is over!" +temp;
		}else {return "there is nothing to fight";}
	}
	
	/**
	 * Method fighCheck attempts to validate combat commands
	 * @param cmd,
	 * @return boolean
	 */
	private boolean fightCheck(String cmd) throws GameException {
		int[] combatCommands = {4,5,6,7};
		boolean validCMD = false;
		for(int i =0; i < combatCommands.length; i++) {
			if( combatCommands[i] == validateCommand(cmd)) {
				validCMD =true;
			}
		}	
		return validCMD;
	}
	
	/**
	 * Method win returns true if final room is entered
	 * @return boolean
	 */
	private boolean win() {
		final int ENDROOM = 30 -1;
		//System.out.println(player.getCurRoom());
		if(player.getCurRoom() == ENDROOM) {
			player.AddScore(player.getBackpack().size() * 15 );
			return true;
		}else return false;
	}
	
	/**
	 * Method useItem attempts to use the an item and decrease uses by 1, removes items when used up
	 * @param cmd, room
	 * @return String
	 */
	private String useItem(String cmd, Room room) {
		ArrayList<Item> item = player.getBackpack();
		try {
			for(Item x : item) {
				//System.out.println(cmd.split(" ",2)[1]);
				//System.out.println(cmd.split(" ",2)[1].equalsIgnoreCase(x.getItemName()));
				if (cmd.split(" ",2)[1].equalsIgnoreCase(x.getItemName())) {
					String temp = itemEffect(x);
					if(temp.equalsIgnoreCase("NA") ){return "item was not used";}
					else if(x.consumeUse()) return ( temp+ ", "  + x.getItemName()+" has " +x.getItemlimit() + " uses remaining");
					else {item.remove(x); return (temp+ ", " + x.getItemName()+" was used up");}
				}
			}
		}catch(Exception e) {return "That was not a valid command";}
		return "That item was not found in your backpack;\n" + player.printBackpack();
	}
	
	/*
	 * Method ItemEffect attempts to find and use effects of an item, certain items can only be used in/out of combat
	 * @param x
	 * @return String
	 */
	private String itemEffect(Item x) throws GameException {
		String purpose =x.getItemEffect().substring(0,1); //what the item does
		int value = Integer.parseInt(x.getItemEffect().substring(1)); //how strong the affect is / what key 
		//TODO the cases need to be expanded so that the effects are actually used
		switch(purpose.toUpperCase()) {
		case "A": if(inCombat) { 
				current.getMonster().damageHP(value); 
				return "you attack for " +value+ " leaving the " + current.getMonster().getMonsterName() + " at " +current.getMonster().getHitPoints() + " hp";
				} else { return "NA";}
		
		case "H": if( player.getHp() >= player.getMAX_HP()){return "NA";} else {int temp = player.getHp();player.addHP(value);
				return "you heal for " +(player.getHp()-temp) + ", Health: "+player.getHp() +"/"+player.getMAX_HP();}
		
		case "K": if(!inCombat){
			//System.out.println(current.getDisplay());
			//System.out.println(current.getPuzzle().getPuzzleID());
				if((current.getPuzzle() != null) && (current.getPuzzle().getPuzzleID() == value)) {player.AddScore(50); current.getPuzzle().setSolved(true);
				current.setPuzzle(null); player.removeItem(x); return "you use the "+x.getItemName()+" and unlock the door";
				}else return "That didn't work...";
			} else { return "NA";}
		default: throw new GameException("Item use was null");
		}
	}
}
