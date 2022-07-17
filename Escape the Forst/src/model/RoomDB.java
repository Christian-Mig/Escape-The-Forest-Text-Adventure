/**
 * 
 */
package model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import controller.Item;
import controller.Monster;
import controller.Navigation;
import controller.Puzzle;
import controller.Room;

/**
 * @author chris
 * @version 1.0, ITEC 3860, 5-28-22
 * 
 * This class represents Room Database and the array of all rooms. also has the converting from txt to objects and formatting
 *
 */
public class RoomDB {
	private ArrayList<Room> rooms;
	private static RoomDB instance = null;
	
	public static RoomDB getInstrance() {
		
		if (instance ==null) {instance = new RoomDB();}
		return instance;
	}
	
	private RoomDB() {
		rooms  = new ArrayList<>();
	}
	
	/**
	 * Method readRooms reads Rooms.txt and Navigation.txt to creates room and naviagion objects
	 * @param 
	 * @return 
	 */
	public void readRooms() {
		int size =0;
		Scanner reader = null;
		File roomsText = new File("ETFRooms.txt");
		int roomIndex =0;
		
		//reading of rooms.txt
		try {
			reader = new Scanner(roomsText);
		}
		catch (IOException ex) {
			System.out.println("File error: Cannot find " + roomsText);
		}
		
		while (reader.hasNext()) // if nothing is next returns false
		{	
			String roomNum = reader.nextLine();
			//System.out.println(roomNum);
			String roomName = reader.nextLine();
			//System.out.println(roomName);
			String roomDesc ="";
			//This is temp scanner is needed to get get escape character to properly work when displayed
			Scanner descDelimiter = new Scanner (reader.nextLine());
			descDelimiter.useDelimiter("\\\\n");
			while(descDelimiter.hasNext()) {roomDesc +="\n"+(descDelimiter.next());}
			Room r = new Room(roomNum, roomName, roomDesc);
			RoomDB.getInstrance().addRooms(r);;
			size++;
		}
		reader.close();
		roomsText = new File("ETFNavigation.txt");
		try {
			reader = new Scanner(roomsText);
		}
		catch (IOException ex) {
			System.out.println("File error: Cannot find " + roomsText);
		}
		int lam = 0;
		while (reader.hasNext()) {
			if(reader.hasNextInt()){
				//this if identifies the roon number for the associating nav info
				int navRoom = reader.nextInt();
				for(int i =0; i < size; i++) {
					if(RoomDB.getInstrance().getRoom(i).getRoomNumber() == navRoom){lam = i;}
				}
			}
			else {
				//this converts the nav info into navigation objects to add associated to the room object
				String navBase = reader.next();
				int divider = navBase.indexOf('=');
				String compass = navBase.substring(0, divider);
				String compassRoom = navBase.substring(divider+1);
				Navigation map = new Navigation (compass, compassRoom);
				RoomDB.getInstrance().getRoom(lam).addNavigation(map);
			}
		}		
		reader.close();
		
	}
	
	/**
	 * Method getRoom gets room from array
	 * @param id
	 * @return Room
	 */
	public Room getRoom (int id) {return rooms.get(id);}
	
	/**
	 * Method getItems returns Items from ItemDB
	 * @param id
	 * @return ArrayList<Item>
	 */
	public ArrayList<Item> getItems (int id){
		
		rooms.get(id).addItem(ItemDB.getInstance().getItem(id));
		return rooms.get(id).getItems();
	}
	
	public Puzzle getPuzzles (int id){
		
		rooms.get(id).setPuzzle(PuzzleDB.getInstance().getPuzzle(id));
		return PuzzleDB.getInstance().getPuzzle(id);
	}
	
	public Monster getMonsters (int id){
		
		rooms.get(id).setMonster(MonsterDB.getInstance().getMonster(id));
		return MonsterDB.getInstance().getMonster(id);
	}
	/**
	 * Method getMap prints the map of all room information in the game
	 * @param 
	 * @return String 
	 */
	public String getMap() {
		String temp ="";
		for (Room rm : rooms) {temp +=(rm);}
		return temp;
	}
	
	/**
	 * Method updateRoom updates the room
	 * @param rn
	 * @return 
	 */
	public void updateRoom(Room rm) {
		getMap();
	}
	
	/**
	 * Method addrooms adds a room to array
	 * @param room
	 * @return 
	 */
	public void addRooms(Room room) {rooms.add(room);}
	
	//assigns items to rooms
	public static void main(String[] args) {
		//System.out.println(getItems(2));
		instance.rooms.get(0).addItem(instance.rooms.get(0).getRoomItems(2-1));
		instance.rooms.get(7).addItem(instance.rooms.get(7).getRoomItems(1-1));
		instance.rooms.get(7).addItem(instance.rooms.get(7).getRoomItems(10-1));
		instance.rooms.get(9).addItem(instance.rooms.get(9).getRoomItems(3-1));
		instance.rooms.get(18).addItem(instance.rooms.get(7).getRoomItems(4-1));
		
		instance.rooms.get(8).setMonster(instance.rooms.get(8).getRoomMonsters(1-1));
		instance.rooms.get(10).setMonster(instance.rooms.get(10).getRoomMonsters(2-1));
		instance.rooms.get(17).setMonster(instance.rooms.get(17).getRoomMonsters(3-1));
		instance.rooms.get(21).setMonster(instance.rooms.get(21).getRoomMonsters(4-1));
		instance.rooms.get(27).setMonster(instance.rooms.get(27).getRoomMonsters(5-1));
		
		instance.rooms.get(2).setPuzzle(instance.rooms.get(2).getRoomPuzzles(1-1)); //trail
		instance.rooms.get(12).setPuzzle(instance.rooms.get(12).getRoomPuzzles(2-1)); //bridge
		instance.rooms.get(16).setPuzzle(instance.rooms.get(16).getRoomPuzzles(3-1)); //stone
		instance.rooms.get(25).setPuzzle(instance.rooms.get(25).getRoomPuzzles(4-1)); //stream
		instance.rooms.get(29).setPuzzle(instance.rooms.get(29).getRoomPuzzles(5-1)); //phone
		// 1= med, 2=rock, flare = 3, water =4, scrap =10
		//keys
		instance.rooms.get(1).addItem(instance.rooms.get(1).getRoomItems(5-1));//red
		instance.rooms.get(5).addItem(instance.rooms.get(5).getRoomItems(6-1)); //beach
		instance.rooms.get(13).addItem(instance.rooms.get(13).getRoomItems(7-1)); //tent
		instance.rooms.get(24).addItem(instance.rooms.get(24).getRoomItems(8-1));//rusted
		instance.rooms.get(28).addItem(instance.rooms.get(28).getRoomItems(9-1));//strange
	}
	
}
