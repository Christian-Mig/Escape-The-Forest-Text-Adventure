/**
 * 
 */
package controller;

import java.util.ArrayList;

/**
 * @author chris
 * @version 1.0, ITEC 3860, 5-28-22
 * 
 * This class represents the PLayer the user controls. It contains the current room and item inventory
 *
 */
public class Player {
	private int curRoom;
	private ArrayList<Item> backpack;
	private int score;
	private int hp;
	private final int MAX_HP =100;
	private final int MAX_BP_SIZE =4;
	
	public Player() {
		backpack = new ArrayList<>();
		curRoom =0;
		score =0;
		hp = 100;
	}
	
	/**
	 * @return the backpack
	 */
	protected ArrayList<Item> getBackpack() {
		return backpack;
	}

	/**
	 * @param backpack the backpack to set
	 */
	protected void setBackpack(ArrayList<Item> backpack) {
		this.backpack = backpack;
	}

	/**
	 * @return the curRoom
	 */
	protected int getCurRoom() {
		return curRoom;
	}

	/**
	 * @param curRoom the curRoom to set
	 */
	protected void setCurRoom(int curRoom) {
		this.curRoom = curRoom;
	}
	
	/**
	 * Method addItem adds Item to end of the Array
	 * @param item
	 * @return 
	 */
	protected void addItem(Item x) {
		backpack.add(x);
	}
	
	/**
	 * Method removeItem removes the item from array
	 * @param item
	 * @return 
	 */
	protected void removeItem(Item x) {
		backpack.remove(x);
	}
	
	
	
	/**
	 * @return the score
	 */
	protected int getScore() {
		return score;
	}

	/**
	 * @param score the score to set
	 */
	protected void AddScore(int score) {
		this.score += score;
		if(score < 0) score =0;
	}
	
	

	/**
	 * @return the hp
	 */
	protected int getHp() {
		return hp;
	}

	/**
	 * @param hp the hp to set
	 */
	protected void setHp(int hp) {
		this.hp = hp;
	}
	
	protected void addHP(int hhp) {
		hp += hhp;
		if(hp < 0) hp =0;
		else if (hp > MAX_HP) hp = MAX_HP;
	}

	/**
	 * Method printBackpack displays contents of the Items List
	 * @param 
	 * @return String
	 */
	protected String printBackpack() {
		String temp = "Hp: " + getHp() + "\n";
		if(backpack.size() <1) {return temp + "you have nothing";}
		else {
			for(Item bp : backpack) {temp += bp.Display() + ", \n";}
			return(temp.substring(0, temp.length()-2));
		}
	}

	/**
	 * @return the mAX_HP
	 */
	public int getMAX_HP() {
		return MAX_HP;
	}

	/**
	 * @return the mAX_BP_SIZE
	 */
	protected int getMAX_BP_SIZE() {
		return MAX_BP_SIZE;
	}
	
}
