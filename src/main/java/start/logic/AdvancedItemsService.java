<<<<<<< HEAD
package start.logic;

import java.util.List;

import start.itemAPI.TransactionBoundary;



public interface AdvancedItemsService extends ItemsService {
	
	
	public List<TransactionBoundary> getAllItems(String userSpace, String userEmail,int size, int page);
	public List<TransactionBoundary> getActiveItemsOnly(String userSpace, String userEmail,int size, int page);
	public List<TransactionBoundary> getWaitTransactionOnly(String userSpace, String userEmail,int size, int page);
	
}
=======
package start.logic;

import java.util.List;

import start.itemAPI.TransactionBoundary;



public interface AdvancedItemsService extends ItemsService {
	
	
	public List<TransactionBoundary> getAllItems(String userSpace, String userEmail,int size, int page);
	public List<TransactionBoundary> getActiveItemsOnly(String userSpace, String userEmail,int size, int page);
}
>>>>>>> c813bdfdde53cb9a25135a0dcf4dc84cf0ce9f7c
