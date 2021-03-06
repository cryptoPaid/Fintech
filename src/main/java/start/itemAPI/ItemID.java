package start.itemAPI;

public class ItemID {

	private String space;
	private String id;
	
	public ItemID() {
		super();
	}

	public ItemID(String space, String id) {
		super();
		this.space = space;
		this.id = id;
	}

	public String getSpace() {
		return space;
	}


	public void setSpace(String space) {
		this.space = space;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return "ItemID [space=" + space + ", id=" + id + "]";
	}


}
