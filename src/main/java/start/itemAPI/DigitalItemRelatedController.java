package start.itemAPI;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import start.logic.AdvancedItemsService;
import start.logic.ItemsService;


//TODO : CHANGE TO Transaction boundry
@RestController
public class DigitalItemRelatedController {
	private ItemsService itemsService;
	private AdvancedItemsService advancedItem;
	@Autowired
	public DigitalItemRelatedController(ItemsService itemsService) {
		this.itemsService = itemsService;
	}

	@RequestMapping(path = "/blockchain/items/{userSpace}/{userEmail}/{itemSpace}/{itemId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public TransactionBoundary getSpecificItem(@PathVariable("userSpace") String userSpace,
			@PathVariable("userEmail") String userEmail, @PathVariable("itemSpace") String itemSpace,
			@PathVariable("itemId") String itemId) {
		return this.itemsService.getSpecificTransaction(userSpace, userEmail, itemSpace, itemId);
	}

	@RequestMapping(path = "/blockchain/items/{userSpace}/{userEmail}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<TransactionBoundary> getAllItems(
			@RequestParam(name= "size", required = false , defaultValue = "10" ) int size,
			@RequestParam(name= "page", required = false , defaultValue = "0" ) int page,
			@PathVariable("userSpace") String userSpace,
			@PathVariable("userEmail") String userEmail) {
		return this.advancedItem.getAllItems(userSpace, userEmail,size,page);
	}

	@RequestMapping(path = "/blockchain/items/{userSpace}/{userEmail}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public TransactionBoundary createItem(@PathVariable("userSpace") String userSpace,
			@PathVariable("userEmail") String userEmail, @RequestBody TransactionBoundary transactionBoundary) {

		return this.itemsService.createTransaction(userSpace, userEmail, transactionBoundary);
	}
	

	@RequestMapping(path = "/blockchain/items/{userSpace}/{userEmail}/{itemSpace}/{itemId}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updateItem(@PathVariable("userSpace") String userSpace, @PathVariable("userEmail") String userEmail,
			@PathVariable("itemSpace") String itemSpace, @PathVariable("itemId") String itemId,
			@RequestBody TransactionBoundary transactionBoundary) {
		this.itemsService.updateTransaction(userSpace, userEmail, itemSpace, itemId, transactionBoundary);
	}

	/*
	//get private blockchain
	@RequestMapping(path = "/blockchain/items/{userSpace}/{userEmail}/{itemSpace}/{itemId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ItemBoundary getblockChain(@PathVariable("userSpace") String userSpace,
			@PathVariable("userEmail") String userEmail, @PathVariable("itemSpace") String itemSpace,
			@PathVariable("itemId") String itemId) {
		return this.itemsService.getBlockChain(userSpace, userEmail, itemSpace, itemId);
	}*/
	// create wallet
	

	
	
	
	
	
	@Autowired
	public void setAdvancedItem(AdvancedItemsService advancedItem) {
		this.advancedItem = advancedItem;
	}

}
