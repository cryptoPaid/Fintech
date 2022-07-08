package start.logic;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import start.data.TransactionEntity;
import start.data.UserEntity;
import start.data.UserRole;
import start.itemAPI.TransactionBoundary;
import start.itemAPI.ItemID;
import start.userAPI.UserID;

@Service
public class ItemLogicImplementation implements AdvancedItemsService {

	private ItemDao itemDao;
	private ObjectMapper jackson;
	private AtomicLong atomicLong;
	private UserDao userDao;
		
	@Autowired
	public ItemLogicImplementation(ItemDao itemDao, ObjectMapper jackson, UserDao userDao) {
		super();
		this.itemDao = itemDao;
		this.jackson = new ObjectMapper();
		this.atomicLong = new AtomicLong();
		this.userDao = userDao;

	}
	//TODO : CHANGE TO createTransaction
	@Override
	@Transactional // (readOnly = false)
	public TransactionBoundary createTransaction(String userSpace, String userEmail, TransactionBoundary item) {

		Optional<UserEntity> op = this.userDao.findById(userEmail + "$" + userSpace);
		
		System.out.println("" + item.toString());
		
		if (op.isPresent()) {
			if (isUserManger(op) ) {
			//	if(item.getItemId()==null)
				//{
					TransactionEntity i = this.convertFromBoundary(item);
					i.setCreatedTimestamp(new Date());
					i.setId(item.getItemId().getId() + "");
					i.setEmail(userEmail);
					i.setSpace(userSpace);
					i.setIdSpace( userSpace +  "$" + i.getId() );
					// transaction fields
					i.setFromAddress(item.getFromAddress());
					i.setToAddress(item.getToAddress());
					i.setAmount(item.getAmount());
					i.setHash(item.getHash());
					// store entity to database using INSERT query
					i = this.itemDao.save(i);
					
					return this.convertToBoundary(i);
			//	}
				//else
			//	{
					//throw new RuntimeException("item exsist in the system");
				//}
			}
		}
		else {
			throw new RuntimeException("item cant create without User in the system"); // TODO: return status = 404 instead of status = 500
		}
		throw new RuntimeException("item cant create without permssion for MANGER"); // TODO: return status = 404
																						// instead of status = 500
	}


	

	@Override
	@Transactional // (readOnly = false)
	public TransactionBoundary updateTransaction(String userSpace, String userEmail, String itemId, String itemSpace,
			TransactionBoundary update) {

		System.out.println("User space: " + userSpace + " UserEmail: " + userEmail + " ItemSpace: " + itemSpace
				+ " Item Id: " + itemId);
		String tempIdSpace = itemId + "$" + itemSpace;

		System.out.println("idSpace = " + tempIdSpace);

		Optional<UserEntity> op_user = this.userDao.findById(userEmail + "$" + userSpace);
		Optional<TransactionEntity> op_item = this.itemDao.findById(itemId + "$" + itemSpace);
		
		TransactionEntity updatedEntity;
		if (op_user.isPresent() && op_item.isPresent()) {
			if (isUserManger(op_user)) {
				TransactionEntity existing = op_item.get();
				updatedEntity = this.convertFromBoundary(update);
				/*updatedEntity.setId(existing.getId());
				updatedEntity.setSpace(existing.getSpace());
				updatedEntity.setEmail(existing.getEmail());
				updatedEntity.setItemAttributes(this.marshal(update.getItemAttributes()));
				updatedEntity.setActive(update.getActive());
				updatedEntity.setCreatedTimestamp(existing.getCreatedTimestamp());
				updatedEntity.setType(update.getType());
				updatedEntity.setName(existing.getName());
				updatedEntity.setIdSpace(existing.getId() + "$" + existing.getSpace());
				// transaction fields
				updatedEntity.setFromAddress(existing.getFromAddress());
				updatedEntity.setToAddress(existing.getToAddress());
				updatedEntity.setAmount(update.getAmount()-existing.getAmount());
				updatedEntity.setHash(existing.getHash());
				updatedEntity.setApprove(true);
				*/
				// this for minus to balance'
				System.err.println("updated entity : " + updatedEntity.toString() );
				UserEntity user= op_user.get();
				user.setBalance(user.getBalance()-updatedEntity.getAmount());
				
				// this for plus 
				Optional<UserEntity> user_plus = this.userDao.findById(existing.getToAddress() + "$" + userSpace);
				if(user_plus.isPresent())
				{
					user_plus.get().setBalance(user_plus.get().getBalance()+updatedEntity.getAmount());
				}
				else throw new UserNotFoundException("User dosent exist");
				
				this.userDao.save(user);
				this.userDao.save(user_plus.get());
				//update blance in wallet
				this.itemDao.save(updatedEntity);
				return this.convertToBoundary(updatedEntity);

			}
		} else {
			throw new RuntimeException(); // TODO: return status = 404 instead of status = 500
		}
		throw new RuntimeException("item cant update without permssion for MANGER"); // TODO: return status = 404 //
																						// instead of status = 500
	}

	@Override
	@Transactional(readOnly = true)
	public List<TransactionBoundary> getAllTransactions(String userSpace, String userEmail) {
		List<TransactionBoundary> rv = new ArrayList<>();
		Optional<UserEntity> op = this.userDao.findById(userEmail + "$" + userSpace);

		if (op.isPresent()) {
			if (isUserManger(op)) {
				Iterable<TransactionEntity> allEntities = this.itemDao.findAll();

				for (TransactionEntity entity : allEntities) {
					TransactionBoundary boundary = convertToBoundary(entity);

					rv.add(boundary);
				}

			}
			return rv;

		} else
			throw new UserNotFoundException();
	}

	@Override
	@Transactional(readOnly = true)
	public TransactionBoundary getSpecificTransaction(String userSpace, String userEmail, String itemSpace, String itemId) {
		// TODO Auto-generated method stub

		Optional<TransactionEntity> op_item = this.itemDao.findById(itemId + "$" + userSpace);
		Optional<UserEntity> op_user = this.userDao.findById(userEmail + "$" + userSpace);

		if (op_user.isPresent() && op_item.isPresent()) {
			if (isUserManger(op_user)) {
				TransactionEntity entity = op_item.get();
				return this.convertToBoundary(entity);
			} else if (isUserPlayer(op_user)) {
				if (op_item.get().getActive()) {
					TransactionEntity entity = op_item.get();
					return this.convertToBoundary(entity);
				} else {
					throw new ItemExceptionNotActive();
				}
			} else
				throw new RuntimeException("no permssion are allowed");

		} else {
			throw new UserNotFoundException(); // TODO: return status = 404 instead of status = 500
		}

	}


	@Override
	@Transactional // (readOnly = false)
	public void deleteAllTransactions(String adminSpace, String adminEmail) {
		Optional<UserEntity> op = this.userDao.findById(adminEmail + "$" + adminSpace);
		if (op.isPresent()) {
			if (UserLogicImplementation.isUserAdmin(op))
				this.itemDao.deleteAll();
		}

		else {
			throw new UserNotFoundException(); // TODO: return status = 404 instead of status = 500

		}

	}

	private TransactionBoundary convertToBoundary(TransactionEntity entity) {
		TransactionBoundary boundary = new TransactionBoundary();

		boundary.setItemId(new ItemID(entity.getSpace(), entity.getId()));
		if (entity.getType() == null || entity.getName() == null) {
			throw new RuntimeException(); // TODO: return status = 404 instead of status = 500
		} else {
			boundary.setType(entity.getType());
			boundary.setName(entity.getName());
		}
		boundary.setActive(entity.getActive());
		boundary.setCreatedTimestamp(entity.getCreatedTimestamp());
		boundary.setItemAttributes(
				((Map<String, Object>) this.unmarshal(entity.getItemAttributes().toString(), Map.class)));
		// transaction fields
		boundary.setFromAddress(entity.getFromAddress());
		boundary.setToAddress(entity.getToAddress());
		boundary.setAmount(entity.getAmount());
		boundary.setHash(entity.getHash());
		boundary.setApprove(entity.isApprove());
		
		return boundary;
	}

	private TransactionEntity convertFromBoundary(TransactionBoundary boundary) {
		TransactionEntity entity = new TransactionEntity();
			// TODO: Split here with '$' to get id and space
			entity.setId(boundary.getItemId().getId());
			entity.setSpace(boundary.getItemId().getSpace());
			entity.setIdSpace(boundary.getFromAddress() + "$" + entity.getSpace());
			entity.setName(boundary.getName());	
			entity.setType(boundary.getType());
		entity.setActive(boundary.getActive());
		entity.setCreatedTimestamp(boundary.getCreatedTimestamp());
		entity.setFromAddress(boundary.getFromAddress());
		entity.setToAddress(boundary.getToAddress());
		entity.setAmount(boundary.getAmount());
		entity.setHash(boundary.getHash());
		entity.setApprove(boundary.getActive());
		String json = this.marshal(boundary.getItemAttributes());
		entity.setItemAttributes(json);
	
		return entity;

	}

	// use Jackson to convert JSON to Object
	private <T> T unmarshal(String json, Class<T> type) {
		try {
			return this.jackson.readValue(json, type);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private String marshal(Object moreDetails) {
		try {
			return this.jackson.writeValueAsString(moreDetails);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<TransactionBoundary> getAllItems(String userSpace, String userEmail, int size, int page) {

		Page<TransactionEntity> pageOfEntities = this.itemDao
				.findAll(PageRequest.of(page, size, Direction.ASC, "id", "createdTimestamp"));

		List<TransactionEntity> entities = pageOfEntities.getContent();
		List<TransactionBoundary> rv = new ArrayList<>();
		for (TransactionEntity entity : entities) {
			TransactionBoundary boundary = convertToBoundary(entity);
			rv.add(boundary);
		}
		return rv;

	}

	@Override
	@Transactional(readOnly = true)
	public List<TransactionBoundary> getActiveTransactionsOnly(String userSpace, String userEmail, int size, int page) {
		List<TransactionBoundary> rv = new ArrayList<>();
		String id_combain = userEmail + "$" + userSpace;
		Optional<UserEntity> op = this.userDao.findById(id_combain);
		if (op.isPresent() && isUserManger(op) ) {
			List<TransactionEntity> allImportantEntities = this.itemDao.findAllByActive(true,
					PageRequest.of(page, size, Direction.DESC, "createdTimestamp", "id"));
			for (TransactionEntity entity : allImportantEntities) {
					if(entity.getEmail().equals(userEmail))
					{
						TransactionBoundary boundary = this.convertToBoundary(entity);
						rv.add(boundary);	
					}
				
				
			}
			System.err.println(rv);
			return rv;

		} else {
			throw new UserNotFoundException(); // TODO: return status = 404 instead of status = 500

		}

	}

	public static boolean isUserPlayer(Optional<UserEntity> op) {
		UserEntity existing = op.get();

		if (existing.getRole().toString().equals(UserRole.PLAYER.name())) {

			return true;
		} else {
			throw new RuntimeException("user is not Player");
		}
	}

	public static boolean isUserManger(Optional<UserEntity> op) {
		UserEntity existing = op.get();

		if (existing.getRole().toString().equals(UserRole.MANAGER.name())) {

			return true;
		} else {
			throw new RuntimeException("user is not Manager");
		}
	}

	@Override
	public List<TransactionBoundary> getWaitTransactionOnly(String userSpace, String userEmail, int size, int page) {
		List<TransactionBoundary> rv = new ArrayList<>();
		String id_combain = userEmail + "$" + userSpace;
		Optional<UserEntity> op = this.userDao.findById(id_combain);
		if (op.isPresent() && isUserManger(op) ) {
			List<TransactionEntity> allImportantEntities = this.itemDao.findAllByActive(false,
					PageRequest.of(page, size, Direction.DESC, "createdTimestamp", "id"));
			for (TransactionEntity entity : allImportantEntities) {
					if(entity.getEmail().equals(userEmail))
					{
						TransactionBoundary boundary = this.convertToBoundary(entity);
						rv.add(boundary);	
					}
				
				
			}
			System.err.println(rv);
			return rv;

		} else {
			throw new UserNotFoundException(); // TODO: return status = 404 instead of status = 500

		}
	}

}
