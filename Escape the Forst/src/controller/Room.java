/**
 * 
 */
package controller;

import java.util.ArrayList;

import model.ItemDB;
import model.MonsterDB;
import model.PuzzleDB;
import model.RoomDB;

/**
 * @author chris
 * @version 1.0, ITEC 3860, 5-28-22
 * 
 * This class represents a room. It contains all information of the room including navigation routes. It also has methods for interacting with the databases and commands
 *
 */
public class Room {
	private int roomNumber;
	private String roomName;
	private String roomDescription;
	private boolean roomVisited = false;
	private ArrayList <Navigation> navList = new ArrayList<>();
	private ArrayList<Item> items = new ArrayList<>();
	private Puzzle puzzle =null;
	private Monster monster = null;
	private RoomDB rdb = RoomDB.getInstrance();
	private ItemDB idb = ItemDB.getInstance();
	private PuzzleDB pdb = PuzzleDB.getInstance();
	private MonsterDB mdb = MonsterDB.getInstance();
		
		
		
		public Room(String roomNum, String roomName, String roomDesc) {
			this.roomNumber = Integer.parseInt(roomNum);
			this.roomName = roomName;
			this.roomDescription = roomDesc;
		// TODO Auto-generated constructor stub
		}
		
		public Room () {}


		@Override
		public String toString() {
			String flag = "";
			if(roomVisited) {flag = "This place seems familiar";}
			return "[roomNumber= " + roomNumber + ", roomName= " + roomName + "\nroomDescription= " + roomDescription
					+ "\n" + flag  + " navInfo=  " + displayExits() +"\n"+ getNavList()+"]\n\n";
		}

		
		/**
		 * Method Display shows all contents of the room
		 * @param 
		 * @return String
		 */
		public String getDisplay() {
			String flag = "";
			if(roomVisited) {flag = "\nThis place seems familiar";}
			return("Room Number: "+ roomNumber +"\nRoom Name: "+ roomName + flag +"\nRoom Description: "+ roomDescription  +displayObstacles()+ displayExits()) + "\n" + buildItems();
			
		}
		
		/**
		 * Method DisplayExits shows all contents of the Navigation array
		 * @param 
		 * @return String
		 */
		private String displayExits() {
			String l ="You can go: ";
			for (Navigation x : navList) {l +=  x.getDirection().toUpperCase() + ", ";}
			return l.substring(0, l.length()-2);
		}
		
		private String displayObstacles() {
			String temp ="\n";
			if(monster !=null) {temp += "You see a " +monster.getMonsterName() +", " +monster.getMonsterDescription()+ " blocking the way " + monster.getDirection()+"\n";}
			if (puzzle !=null) {temp += "You see a " +puzzle.getName() +", " + puzzle.getPuzzleDesc()+ " blocking the way " + puzzle.getDirection()+"\n";}
			return temp;
		}
		
		/**
		 * Method addNavigation adds Navigation to Array
		 * @param x
		 * @return 
		 */
		public void addNavigation(Navigation x) {
		navList.add(x);
		}
		
		/**
		 * Method addItem adds Item to Array
		 * @param x
		 * @return 
		 */
		public void addItem (Item x) {
		items.add(x);
		}
		

		/**
		 * Method removeitem removes item to Array
		 * @param x
		 * @return 
		 */
		public void removeItem (Item x) {
		items.remove(x);
		}
		
		/**
		 * Method dropItem adds item to arrau
		 * @param x
		 * @return 
		 */
		public void dropItem (Item x) {
			addItem(x);
		}
		
		/**
		 * Method buildItems creates display of items in the room
		 * @param 
		 * @return string 
		 */
		private String buildItems() {
			String itemList ="";
			if(items.size() < 1) {return itemList;}
			for(Item x : items) {
				itemList +=x.getItemName() + ", ";
			}
			return(itemList.substring(0, itemList.length()-2));
			
			/**String itemList ="";
			for(Integer x : items) {
				itemList +=idb.getInstance().getItem(x);
			}
			return itemList;**/
		}
		
		/**
		 * Method updateRoom updates the room
		 * @param 
		 * @return 
		 */
		public void updateRoom() {
			rdb = RoomDB.getInstrance();
			rdb.updateRoom(this.retirieveByID(roomNumber));
		}
		
		
		/**
		 * @return the roomNumber
		 */
		public int getRoomNumber() {
			return roomNumber;
		}
		
		/**
		 * Method getRoomItems gets item from itemDatbase
		 * @param x
		 * @return 
		 */
		public Item getRoomItems(int x) {
			return idb.getItem(x);
		}
		
		public Monster getRoomMonsters (int x) {
			return mdb.getMonster(x);
		}
		
		public Puzzle getRoomPuzzles (int x) {
			return pdb.getPuzzle(x);
		}
		
		/**
		 * Method retrieveByID gets room from RoomDB
		 * @param roomnum
		 * @return 
		 */
		public Room retirieveByID(int roomNum) {
			return RoomDB.getInstrance().getRoom(roomNum);
		}
		
		/**
		 * Method validDirection checks to see if input is a valid nevigation destination and returns roomID
		 * @param cmd
		 * @return int
		 */
		public int validDirection(char cmd) {
			//System.out.println(cmd);
			//System.out.println(roomName);
			//System.out.println(navList);
			String destination = Character.toString(cmd);
			int x = 666;
			if(puzzle == null && monster ==null) {
				for(int i =0; i < navList.size(); i++) {
					if(destination.equalsIgnoreCase(navList.get(i).getDirection().substring(0,1))){ x = navList.get(i).getRoomDestination()-1;}		
				}
			}else if (puzzle != null && monster != null) { //Need puzzle and room DB
				for(int i =0; i < navList.size(); i++) {
					if(destination.equalsIgnoreCase(navList.get(i).getDirection().substring(0,1) )
					&&( puzzle.getDirection() != (cmd)) && (monster.getDirection() != (cmd)) ){ x= navList.get(i).getRoomDestination()-1;}	
				}	
			}else if ( monster !=null) { //Need puzzle and room DB
				for(int i =0; i < navList.size(); i++) {
					if(destination.equalsIgnoreCase(navList.get(i).getDirection().substring(0,1) )
					&& (monster.getDirection() != (cmd)) ){ x= navList.get(i).getRoomDestination()-1;}	
				}
			}else { //Need puzzle and room DB
				for(int i =0; i < navList.size(); i++) {
					if(destination.equalsIgnoreCase(navList.get(i).getDirection().substring(0,1) )
					&&( puzzle.getDirection() != (cmd)) ){ x= navList.get(i).getRoomDestination()-1;}	
				}
				if (x ==666) return 555;
			}
			return x;
		}
		
		

		/**
		 * @param roomNumber the roomNumber to set
		 */
		public void setRoomNumber(int roomNumber) {
			this.roomNumber = roomNumber;
		}




		/**
		 * @return the roomName
		 */
		public String getRoomName() {
			return roomName;
		}




		/**
		 * @param roomName the roomName to set
		 */
		public void setRoomName(String roomName) {
			this.roomName = roomName;
		}




		/**
		 * @return the roomDescription
		 */
		public String getRoomDescription() {
			return roomDescription;
		}




		/**
		 * @param roomDescription the roomDescription to set
		 */
		public void setRoomDescription(String roomDescription) {
			this.roomDescription = roomDescription;
		}




		/**
		 * @return the roomVisited
		 */
		public boolean isRoomVisited() {
			return roomVisited;
		}




		/**
		 * @param roomVisited the roomVisited to set
		 */
		public void setRoomVisited(boolean roomVisited) {
			this.roomVisited = roomVisited;
		}

		

		/**
		 * @return the navList
		 */
		public ArrayList<Navigation> getNavList() {
			return navList;
		}





		/**
		 * @return the items
		 */
		public ArrayList<Item> getItems() {
			return items;
		}




		/**
		 * @return the puzzle
		 */
		protected Puzzle getPuzzle() {
			return puzzle;
		}

		/**
		 * @param puzzle the puzzle to set
		 */
		public void setPuzzle(Puzzle puzzle) {
			this.puzzle = puzzle;
		}

		/**
		 * @return the monster
		 */
		protected Monster getMonster() {
			return monster;
		}

		/**
		 * @param monster the monster to set
		 */
		public void setMonster(Monster monster) {
			this.monster = monster;
		}

		/**
		 * @param items the items to set
		 */
		public void setItems(ArrayList<Item> items) {
			this.items = items;
		}

	
}
