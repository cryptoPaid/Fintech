package start.logic;

import java.util.List;

import start.itemAPI.ItemBoundary;



public interface AdvancedItemsService extends ItemsService {
	
	
	public List<ItemBoundary> getAllItems(String userSpace, String userEmail,int size, int page);
	public List<ItemBoundary> getActiveItemsOnly(String userSpace, String userEmail,int size, int page);
}
