<<<<<<< HEAD
package start.logic;

import java.util.List;

import start.itemAPI.TransactionBoundary;


public interface ItemsService {
	public TransactionBoundary createTransaction(String userSpace, String userEmail, TransactionBoundary item);

	public TransactionBoundary updateTransaction(String userSpace, String userEmail, String itemSpace, String itemId,
			TransactionBoundary update);

	public List<TransactionBoundary> getAllTransactions(String userSpace, String userEmail);

	public TransactionBoundary getSpecificTransaction(String userSpace, String userEmail, String itemSpace, String itemId);

	public void deleteAllTransactions(String adminSpace, String adminEmail);

}
=======
package start.logic;

import java.util.List;

import start.itemAPI.TransactionBoundary;


public interface ItemsService {
	public TransactionBoundary createTransaction(String userSpace, String userEmail, TransactionBoundary item);

	public TransactionBoundary updateTransaction(String userSpace, String userEmail, String itemSpace, String itemId,
			TransactionBoundary update);

	public List<TransactionBoundary> getAllTransactions(String userSpace, String userEmail);

	public TransactionBoundary getSpecificTransaction(String userSpace, String userEmail, String itemSpace, String itemId);

	public void deleteAllTransactions(String adminSpace, String adminEmail);

}
>>>>>>> c813bdfdde53cb9a25135a0dcf4dc84cf0ce9f7c
