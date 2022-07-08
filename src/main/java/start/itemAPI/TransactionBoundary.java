package start.itemAPI;

import java.util.Date;
import java.util.Map;


public class TransactionBoundary {

	private ItemID itemID;
	private String type;
	private String name;
	private boolean active;
	private Date createdTimestamp;
	//private CreatedBy createdBy;
	//private String 
	private Map<String, Object> itemAttributes;
	//TODO : CHANGE TO Transaction + create Get&Set + Constrauctor 
	// Transaction boundry
 	private int amount;
    private String toAddress;
    private String fromAddress;
	private String hash;
	private boolean approve;
	
	public TransactionBoundary(ItemID itemID, String type, String name, boolean active, Date createdTimestamp,
			 Map<String, Object> itemAttributes, int amount, String toAddress, String fromAddress,
			String hash,boolean approve) {
		super();
		this.itemID = itemID;
		this.type = type;
		this.name = name;
		this.active = active;
		this.createdTimestamp = createdTimestamp;
		//this.createdBy = createdBy;
		this.itemAttributes = itemAttributes;
		this.amount = amount;
		this.toAddress = toAddress;
		this.fromAddress = fromAddress;
		this.hash = hash;
		this.approve=approve;
	}
	
    public boolean isApprove() {
		return approve;
	}

	public void setApprove(boolean approve) {
		this.approve = approve;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getFromAddress() {
		return fromAddress;
	}

	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}

    
    


	public String getToAddress() {
		return toAddress;
	}

	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public TransactionBoundary() {
		super();
	}

	public ItemID getItemId() {
		return itemID;
	}

	public void setItemId(ItemID itemID) {
		this.itemID = itemID;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean getActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Date getCreatedTimestamp() {
		return createdTimestamp;
	}

	public void setCreatedTimestamp(Date createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}
/*
	public CreatedBy getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(CreatedBy createdBy) {
		this.createdBy = createdBy;
	}
*/
	public Map<String, Object> getItemAttributes() {
		return itemAttributes;
	}

	public void setItemAttributes(Map<String, Object> itemAttributes) {
		this.itemAttributes = itemAttributes;
	}

	@Override
	public String toString() {
		return "ItemBoundary [itemID=" + itemID + ", type=" + type + ", name=" + name + ", active=" + active
				+ ", createdTimestamp=" + createdTimestamp + ", itemAttributes="
				+ itemAttributes + ", amount=" + amount + ", toAddress=" + toAddress + ", fromAddress=" + fromAddress
				+ ", hash=" + hash + "]" + " ,approved = " + approve;
	}

	

}
