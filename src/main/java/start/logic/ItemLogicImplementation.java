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
import start.itemAPI.CreatedBy;
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
			if (isUserManger(op) || isUserAdmin(op)) {
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
			}
		}
		else {
			throw new RuntimeException("item cant create without User in the system"); // TODO: return status = 404 instead of status = 500
		}
		throw new RuntimeException("item cant create without permssion for MANGER"); // TODO: return status = 404
																						// instead of status = 500
	}

	// /twins/items/{userSpace}/{userEmail}/{itemSpace}/{itemId}
	// {

//    "itemId": {
//        "space": "2021b.twins",
//        "id": "99"
//    },
//    "type": "ConstructionProject",
//    "name": "Project1",
//    "active": true,
//    "createdTimestamp": "2021-03-07T09:55:05.248+0000",
//    "createdBy": {
//        "userId": {
//            "space": "2021b.twins",
//            "email": "user2@demo.com"
//        }
//    },
//    "location": {
//        "lat": 32.115139,
//        "lng": 34.817804
//    },

//    "itemAttributes": {  

	// Construction Project -> Buildings -> Add a building to the array -> Convert
	// to Json
	// -> Put in item attributes -> Convert to Json -> Post to
	// /twins/items/{userSpace}/{userEmail}/{itemSpace}/{itemId}

//        "key1": "can be set to any value you wish",
//        "key2": "you can also name the attributes any name you like",
//        "key3": 58,
//        "key4": false
//    }
//}
	
	//TODO : CHANGE TO updateTransaction --> it is needed???

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
				updatedEntity.setId(existing.getId());
				updatedEntity.setSpace(existing.getSpace());
				updatedEntity.setEmail(existing.getEmail());
				updatedEntity.setItemAttributes(this.marshal(update.getItemAttributes()));
				updatedEntity.setActive(update.getActive());
				updatedEntity.setCreatedTimestamp(existing.getCreatedTimestamp());
				updatedEntity.setType(update.getType());
				updatedEntity.setName(existing.getName());
				updatedEntity.setIdSpace(existing.getId() + "$" + existing.getSpace());

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
		boundary.setCreatedBy(new CreatedBy(new UserID(entity.getSpace(), entity.getEmail())));
		boundary.setItemAttributes(
				((Map<String, Object>) this.unmarshal(entity.getItemAttributes().toString(), Map.class)));
		// transaction fields
		boundary.setFromAddress(entity.getFromAddress());
		boundary.setToAddress(entity.getToAddress());
		boundary.setAmount(entity.getAmount());
		boundary.setHash(entity.getHash());
		
		return boundary;
	}

	private TransactionEntity convertFromBoundary(TransactionBoundary boundary) {
		TransactionEntity entity = new TransactionEntity();
		if (boundary.getItemId() != null) {
			// TODO: Split here with '$' to get id and space
			entity.setId(boundary.getItemId().getId());
			entity.setSpace(boundary.getItemId().getSpace());
			entity.setIdSpace(entity.getEmail() + "$" + entity.getSpace());
		} else {
			System.out.println("Item id is null!");

		}

		if (boundary.getCreatedBy() != null) {
			entity.setEmail(boundary.getCreatedBy().getUserId().getEmail());
		} else {
			System.out.println("Created By is null!");
		}

		if (boundary.getName() != null) {
			entity.setName(boundary.getName());
		} else {
			System.out.println("Name is null!");
		}

		entity.setType(boundary.getType());
		entity.setActive(boundary.getActive());
		entity.setCreatedTimestamp(boundary.getCreatedTimestamp());

		String json = this.marshal(boundary.getItemAttributes());
		entity.setItemAttributes(json);
		
		// transaction fields
		entity.setFromAddress(boundary.getFromAddress());
		entity.setToAddress(boundary.getToAddress());
		entity.setAmount(boundary.getAmount());
		entity.setHash(boundary.getHash());
		
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
	public List<TransactionBoundary> getActiveItemsOnly(String userSpace, String userEmail, int size, int page) {
		List<TransactionBoundary> rv = new ArrayList<>();

		Optional<UserEntity> op = this.userDao.findById(userEmail + "$" + userSpace);
		if (op.isPresent() && isUserPlayer(op)) {
			List<TransactionEntity> allImportantEntities = this.itemDao.findAllByActive(true,
					PageRequest.of(page, size, Direction.DESC, "messageTimestamp", "id"));
			for (TransactionEntity entity : allImportantEntities) {
				TransactionBoundary boundary = this.convertToBoundary(entity);
				rv.add(boundary);

			}
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
	public static boolean isUserAdmin(Optional<UserEntity> op) {
		UserEntity existing = op.get();

		if (existing.getRole().toString().equals(UserRole.ADMIN.name())) {

			return true;
		} else {
			throw new RuntimeException("user is not Manager");
		}
	}

}
