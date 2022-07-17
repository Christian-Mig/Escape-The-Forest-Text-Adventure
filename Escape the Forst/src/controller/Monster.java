/**
 * 
 */
package controller;

/**
 * @author chris
 *
 */
public class Monster {
	private int monsterNumber;
	private String monsterName;
	private String monsterDescription;
	private int hitPoints;
	private int minDamage;
	private int maxDamage;
	private char direction;
	
	public Monster () {
		
	}

	/**
	 * @param monsterNumber
	 * @param monsterName
	 * @param monsterDescription
	 * @param hitPoints
	 * @param minDamage
	 * @param maxDamage
	 */
	public Monster(int monsterNumber, String monsterName, String monsterDescription, int hitPoints, int minDamage,int maxDamage) {
		this.monsterNumber = monsterNumber;
		this.monsterName = monsterName;
		this.monsterDescription = monsterDescription;
		this.hitPoints = hitPoints;
		this.minDamage = minDamage;
		this.maxDamage = maxDamage;
	}

	/**
	 * @return the monsterNumber
	 */
	public int getMonsterNumber() {
		return monsterNumber;
	}

	/**
	 * @param monsterNumber the monsterNumber to set
	 */
	public void setMonsterNumber(int monsterNumber) {
		this.monsterNumber = monsterNumber;
	}

	/**
	 * @return the monsterName
	 */
	public String getMonsterName() {
		return monsterName;
	}

	/**
	 * @param monsterName the monsterName to set
	 */
	public void setMonsterName(String monsterName) {
		this.monsterName = monsterName;
	}

	/**
	 * @return the monsterDescription
	 */
	public String getMonsterDescription() {
		return monsterDescription;
	}

	/**
	 * @param monsterDescription the monsterDescription to set
	 */
	public void setMonsterDescription(String monsterDescription) {
		this.monsterDescription = monsterDescription;
	}

	/**
	 * @return the hitPoints
	 */
	public int getHitPoints() {
		return hitPoints;
	}

	/**
	 * @param hitPoints the hitPoints to set
	 */
	public void setHitPoints(int hitPoints) {
		this.hitPoints = hitPoints;
	}

	/**
	 * @return the minDamage
	 */
	public int getMinDamage() {
		return minDamage;
	}

	/**
	 * @param minDamage the minDamage to set
	 */
	public void setMinDamage(int minDamage) {
		this.minDamage = minDamage;
	}

	/**
	 * @return the maxDamage
	 */
	public int getMaxDamage() {
		return maxDamage;
	}

	/**
	 * @param maxDamage the maxDamage to set
	 */
	public void setMaxDamage(int maxDamage) {
		this.maxDamage = maxDamage;
	}
	
	public void damageHP(int dmg) {
		hitPoints -= dmg;
		if(hitPoints < 0) { hitPoints =0;}
	}

	
	public int attack() {
		return (int) (Math.random()*(maxDamage-minDamage)+minDamage);
	}
	
	@Override
	public String toString() {
		return "Monster [monsterNumber=" + monsterNumber + ", monsterName=" + monsterName + ", monsterDescription="
				+ monsterDescription + ", hitPoints=" + hitPoints + ", minDamage=" + minDamage + ", maxDamage="
				+ maxDamage + "]";
	}

	/**
	 * @return the direction
	 */
	public char getDirection() {
		return direction;
	}

	/**
	 * @param direction the direction to set
	 */
	public void setDirection(char direction) {
		this.direction = direction;
	}
	
	
	
}
