/**
 * 
 */
package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import controller.Item;
import java.util.Scanner;
/**
 * @author chris
 * @version 1.0, ITEC 3860, 5-28-22
 * 
 * This class represents Item Database and the array of all items. also has the converting from txt to objects and formatting
 *
 */

public class ItemDB {
	private ArrayList<Item> items;
	public static ItemDB instance = null;
	
	public static ItemDB getInstance() {
		if(instance == null) {
			instance = new ItemDB();
		}
	return instance;
	}
	
	private ItemDB() {
		items = new ArrayList<Item>();
	}
	
	/**
	 * Method getItem gets an item from array
	 * @param id
	 * @return 
	 */
	public Item getItem(int id) {
		return items.get(id);
	}
	
	/**
	 * Method readItems reads from Items.txt and creates items
	 * @param 
	 * @return 
	 */
	public void readItems() {

		File roomsText = new File("ETFItems.txt");
		int roomIndex =0;
		Scanner reader = null;
		try {
			reader = new Scanner(roomsText);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//reading of rooms.txt
		try {
			reader = new Scanner(roomsText);
		}
		catch (IOException ex) {
			System.out.println("File error: Cannot find " + roomsText);
		}
		
		while (reader.hasNext()) // if nothing is next returns false
		{	
			Item r = new Item();
			r.setItemid(Integer.parseInt(reader.nextLine()));
			//System.out.println(roomNum);
			r.setItemName(reader.nextLine());
			//System.out.println(roomName);
			r.setItemDescription(reader.nextLine());
			r.setItemEffect(reader.nextLine());
			r.setItemlimit(Integer.parseInt(reader.nextLine()));
			items.add(r);
		}
		reader.close();
	}
}
