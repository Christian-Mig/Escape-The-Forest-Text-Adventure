/**
 * 
 */
package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import controller.Puzzle;
import java.util.Scanner;
/**
 * @author chris
 * @version 1.0, ITEC 3860, 5-28-22
 * 
 * This class represents Puzzle Database and the array of all Puzzles. also has the converting from txt to objects and formatting
 *
 */

public class PuzzleDB {
	private ArrayList<Puzzle> Puzzles;
	public static PuzzleDB instance = null;
	
	public static PuzzleDB getInstance() {
		if(instance == null) {
			instance = new PuzzleDB();
		}
	return instance;
	}
	
	private PuzzleDB() {
		Puzzles = new ArrayList<Puzzle>();
	}
	
	/**
	 * Method getPuzzle gets an Puzzle from array
	 * @param id
	 * @return 
	 */
	public Puzzle getPuzzle(int id) {
		return Puzzles.get(id);
	}
	
	/**
	 * Method readPuzzles reads from Puzzles.txt and creates Puzzles
	 * @param 
	 * @return 
	 */
	public void readPuzzles() {

		File roomsText = new File("ETFPuzzles.txt");
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
			Puzzle r = new Puzzle();
			r.setPuzzleID(Integer.parseInt(reader.nextLine()));
			r.setName(reader.nextLine());
			//System.out.println(roomNum);
			r.setPuzzleDesc(reader.nextLine());
			//System.out.println(roomName);
			r.setHint(reader.nextLine());
			r.setDirection(reader.nextLine().charAt(0));
			Puzzles.add(r);
		}
		reader.close();
	}
}
