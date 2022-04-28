package start.logic;

import java.util.List;

import start.itemAPI.ItemBoundary;


public interface ItemsService {
	public ItemBoundary createItem(String userSpace, String userEmail, ItemBoundary item);

	public ItemBoundary updateItem(String userSpace, String userEmail, String itemSpace, String itemId,
			ItemBoundary update);

	public List<ItemBoundary> getAllItems(String userSpace, String userEmail);

	public ItemBoundary getSpecificItem(String userSpace, String userEmail, String itemSpace, String itemId);

	public void deleteAllItems(String adminSpace, String adminEmail);

	ItemBoundary getBlockChain(String userSpace, String userEmail, String itemSpace, String itemId);
}
