/**
 * 
 */
package controller;

/**
 * @author chris
 * @version 1.0, ITEC 3860, 5-28-22
 * 
 * This class represents the Items and contains all items information
 *
 */
public class Item {
	private int Itemid = 0; 
	private String itemName =null;
	private String itemDescription = null;
	private String ItemEffect;
	private int Itemlimit;
	private boolean itemFirst = true;
	
	public Item() {}
	

	/**
	 * Method Display shows basic information of the item
	 * @param 
	 * @return String
	 */
	public String Display() {
		return (this.itemName+"\n" + this.itemDescription +"\n" + this.Itemlimit +" Uses remaining");
	}

	/**
	 * @return the itemid
	 */
	protected int getItemid() {
		return Itemid;
	}

	/**
	 * @param itemid the itemid to set
	 */
	public void setItemid(int itemid) {
		Itemid = itemid;
	}

	/**
	 * @return the itemName
	 */
	protected String getItemName() {
		return itemName;
	}

	/**
	 * @param itemName the itemName to set
	 */
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	/**
	 * @return the itemDescription
	 */
	protected String getItemDescription() {
		return itemDescription;
	}

	/**
	 * @param itemDescription the itemDescription to set
	 */
	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}
	
	
	
	/**
	 * @return the itemEffect
	 */
	protected String getItemEffect() {
		return ItemEffect;
	}


	/**
	 * @param itemEffect the itemEffect to set
	 */
	public void setItemEffect(String itemEffect) {
		ItemEffect = itemEffect;
	}

	
	
	/**
	 * @return the itemlimit
	 */
	protected int getItemlimit() {
		return Itemlimit;
	}


	/**
	 * @param itemlimit the itemlimit to set
	 */
	public void setItemlimit(int itemlimit) {
		Itemlimit = itemlimit;
	}

	/**
	 * Method consumeUse reduces item uses by 1 if possible
	 * @return boolean
	 */
	public boolean consumeUse() {
		if(this.Itemlimit ==99) {return true;}
		else if(this.Itemlimit >1) {this.Itemlimit--; return true;}
		else return false;
	}


	@Override
	public String toString() {
		return "Item [Itemid=" + Itemid + ", itemName=" + itemName + ", itemDescription=" + itemDescription
				+ ", ItemEffect=" + ItemEffect + ", Itemlimit=" + Itemlimit + "]";
	}


	/**
	 * @return the itemFirst
	 */
	public boolean getIsItemFirst() {
		return itemFirst;
	}


	/**
	 * @param itemFirst the itemFirst to set
	 */
	public void setItemFirst(boolean itemFirst) {
		this.itemFirst = itemFirst;
	}
	

}
