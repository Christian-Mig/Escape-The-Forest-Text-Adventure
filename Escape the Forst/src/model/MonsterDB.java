/**
 * 
 */
package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import controller.Item;
import controller.Monster;

import java.util.Scanner;
/**
 * @author chris
 * @version 1.0, ITEC 3860, 5-28-22
 * 
 * This class represents monster Database and the array of all items. also has the converting from txt to objects and formatting
 *
 */

public class MonsterDB {
	private ArrayList<Monster> monsters;
	public static MonsterDB instance = null;
	
	public static MonsterDB getInstance() {
		if(instance == null) {
			instance = new MonsterDB();
		}
	return instance;
	}
	
	private MonsterDB() {
		monsters = new ArrayList<Monster>();
	}
	
	/**
	 * Method getItem gets an item from array
	 * @param id
	 * @return 
	 */
	public Monster getMonster(int id) {
		return monsters.get(id);
	}
	
	/**
	 * Method readItems reads from monsters.txt and creates items
	 * @param 
	 * @return 
	 */
	public void readMonsters() {

		File roomsText = new File("ETFMonsters.txt");
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
			Monster r = new Monster();
			r.setMonsterNumber(Integer.parseInt(reader.nextLine()));
			//System.out.println(roomNum);
			r.setMonsterName(reader.nextLine());
			//System.out.println(roomName);
			r.setMonsterDescription(reader.nextLine());
			r.setHitPoints(Integer.parseInt(reader.nextLine()));
			r.setMinDamage(Integer.parseInt(reader.nextLine()));
			r.setMaxDamage(Integer.parseInt(reader.nextLine()));
			r.setDirection(reader.nextLine().charAt(0));
			monsters.add(r);
		}
		reader.close();
	}
}
