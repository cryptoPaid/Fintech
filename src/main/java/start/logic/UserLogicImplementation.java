package start.logic;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import start.userAPI.NewUserDetails;
import start.userAPI.UserBoundary;
import start.userAPI.UserID;
import start.logic.UserNotFoundException;
import start.data.UserEntity;
import start.data.UserRole;
import start.data.objects.BlockChain;
import start.data.objects.Wallet;

@Service
public class UserLogicImplementation implements AdvancedUsersService {
	private UserDao userDao;
	private String space;
	private ObjectMapper jackson = new ObjectMapper();
	
	@Autowired
	public UserLogicImplementation(UserDao userDao) {
		super();
		this.userDao = userDao;

	}

	@Override
	@Transactional // (readOnly = false)
	public UserBoundary createUser(UserBoundary input) {
		// Tx - BEGIN
		//System.out.println(input.toString());
		Optional<UserEntity> op = this.userDao.findById(input.getEmail() + "$" + this.space);
		if (op.isPresent()) {
			throw new UserNotFoundException("user is  exist"); // TODO: return status = 404 instead of status = 500

		} else {
			UserEntity entity = this.convertFromBoundary(input);

			// store entity to database using INSERT query
			entity = this.userDao.save(entity);

			// on success: Tx COMMIT
			// on exception: Tx ROLLBACK

			return this.convertToBoundary(entity); // convert entity to boundary
		}
		
	}

	@Override
	public UserBoundary login(String userSpace, String userEmail) {
		Optional<UserEntity> op = this.userDao.findById(userEmail + "$" + userSpace);
		// if user does not exist, throw exceptionw
		if (op.isPresent()) {
			UserEntity entity = op.get();
			return this.convertToBoundary(entity);
		} else {
			throw new RuntimeException("user is not exist"); // TODO: return status = 404 instead of status = 500
		}
	}

	@Override
	@Transactional // (readOnly = false)
	public UserBoundary updateUser(String userSpace, String userEmail, UserBoundary update) {
		Optional<UserEntity> op = this.userDao.findById(userEmail + "$" + userSpace);
		
		if (op.isPresent()) {
			UserEntity existing = op.get();

			UserEntity updatedEntity = this.convertFromBoundary(update);

			// this can not change in user entity!
			updatedEntity.setSpace(existing.getSpace());
			updatedEntity.setEmail(existing.getEmail());

			this.userDao.save(updatedEntity);
			return this.convertToBoundary(updatedEntity);

		} else {
			throw new RuntimeException(); // TODO: return status = 404 instead of status = 500
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<UserBoundary> getAllUsers(String adminSpace, String adminEmail) {
		Iterable<UserEntity> allEntities = this.userDao.findAll();

		List<UserBoundary> rv = new ArrayList<>();
		for (UserEntity entity : allEntities) {
			UserBoundary boundary = convertToBoundary(entity);
			rv.add(boundary);
		}
		return rv;

	}

	@Override
	public void deleteAllUsers(String adminSpace, String adminEmail) {
		Optional<UserEntity> op = this.userDao.findById(adminEmail + "$" + adminSpace);
		if (op.isPresent()) {
			if (isUserAdmin(op))
				this.userDao.deleteAll();

		}

		else {
			throw new UserNotFoundException(); // TODO: return status = 404 instead of status = 500

		}
	}

	public static boolean isUserAdmin(Optional<UserEntity> op) {
		UserEntity existing = op.get();

		if (existing.getRole().toString().equals(UserRole.ADMIN.name())) {

			return true;
		} else {
			throw new RuntimeException("user is not admin");
		}
	}

	private UserEntity convertFromBoundary(UserBoundary boundary) {
		UserEntity entity = new UserEntity();
		boundary.setUserId(new UserID(this.space, boundary.getEmail()));
		if (boundary.getUserId() != null) {
			entity.setEmail(boundary.getUserId().getEmail());
			entity.setSpace(boundary.getUserId().getSpace());
			entity.setEmail_space(boundary.getUserId().getEmail() + "$" + boundary.getUserId().getSpace());
		} else {
			throw new RuntimeException("faild to get userID"); // TODO: return status = 404 instead of status = 500
		}

		if (boundary.getUsername() != null) {
			entity.setUsername(boundary.getUsername());

		} else {
			throw new RuntimeException("faild to get user name"); // TODO: return status = 404 instead of status = 500
		}
		if (boundary.getRole().equals(UserRole.ADMIN.name()) || boundary.getRole().equals(UserRole.MANAGER.name())
				|| boundary.getRole().equals(UserRole.PLAYER.name())) {
			entity.setRole(boundary.getRole());
		} else {
			throw new RuntimeException("faild to get role"); // TODO: return status = 404 instead of status = 500
		}
		if (boundary.getPassword() != null || boundary.getPassword().isEmpty() == true) {
			entity.setPassword(boundary.getPassword());
		} else {
			throw new RuntimeException("faild to get avatar"); // TODO: return status = 404 instead of status = 500
		}
		System.err.println(boundary.getJohnStaCoin());
		entity.setJohnStaCoin(boundary.getJohnStaCoin());
		entity.setBalance(boundary.getWallet().getBalance());
		entity.setPrivateKey(boundary.getWallet().getPrivateKey());
		entity.setPublicKey(boundary.getWallet().getPublicKey());
		
		// TODO ADD CHECKS FOR FIRST LAST NAME
		/*
		 * if(boundary.getFirstName()!= null || boundary.getLastName()!=null) {
		 * 
		 * } else
		 */
			
		
			/*
		  if(boundary.getJohnStaCoin()!= null) {
			  entity.setJohnStaCoin(marshal(boundary.getJohnStaCoin()));
		  }
		  	else { 
		  		throw new RuntimeException("faild to get blockchain"); // TODO: return status = 404 instead of status = 500
		   }
		
		

		  if(boundary.getWallet() != null)
		  entity.setWallet(marshal(boundary.getWallet()));
		   else { 
			   throw new RuntimeException("faild to get wallet"); // TODO: return status = 404 instead  of status = 500
		 }
		  
		  //save pending transcation
		 // entity.setPendingTransaction(marshal(entity.getPendingTransaction()));
		 */
		return entity;

	}

	@Value("${spring.application.name:dummy}")
	public void setSpace(String space) {
		this.space = space;
	}

	// TODO:
	public UserBoundary converNewtUserDeatailsToBoundary(NewUserDetails userDeatalis) {
		//@Value(userDeatalis) private string json
		UserBoundary boundary= new UserBoundary();
		System.out.println("johny");
		System.out.println("before + \n" + userDeatalis);
		/*String value = marshal(userDeatalis);
		System.err.println(value);
		boundary = unmarshal(value, UserBoundary.class);
		System.out.println("after "+ boundary.toString());*/
		if (userDeatalis.getEmail() == null || userDeatalis.getUsername() == null || userDeatalis.getPassword() == null
				|| userDeatalis.getRole() == null) {
			throw new RuntimeException("faild to convert in new user to boundry"); // TODO: return status = 404 instead
																					// of status = 500
		} else {
			boundary.setUsername(userDeatalis.getUsername());
			boundary.setUserId(new UserID(this.space, userDeatalis.getEmail()));
			boundary.setRole(userDeatalis.getRole());
			boundary.setPassword(userDeatalis.getPassword());
			boundary.setEmail(userDeatalis.getEmail());

			//add wallet
			/*
			boundary.setWallet(userDeatalis.getWallet());
			String tmp = marshal(userDeatalis.getWallet());
			boundary.setWallet(unmarshal(tmp, Wallet.class));
			boundary.getWallet().setBalance(unmarshal(tmp, Wallet.class).getBalance());
			boundary.getWallet().setPrivateKey(unmarshal(tmp, Wallet.class).getPrivateKey());
			boundary.getWallet().setPublicKey(unmarshal(tmp, Wallet.class).getPublicKey());
*/
			
			//add blockchain
		//	System.err.println("in user deatiales = " +userDeatalis.getJohnStaCoin().toString());
		//	boundary.setJohnStaCoin(userDeatalis.getJohnStaCoin());
			//	tmp = marshal(userDeatalis.getJohnStaCoin());
			//boundary.setJohnStaCoin(unmarshal(tmp,BlockChain.class));
			//System.out.println("after \n"+ boundary.toString());

			//String json = marshal(userDeatalis);
			//boundary.setJohnStaCoin(unmarshal(json, BlockChain.class));

		/*	boundary.setWallet(userDeatalis.getWallet());
			boundary.setPendingTransaction(userDeatalis.getPendingTransaction());*/
		}
		return boundary;

	}

	private UserBoundary convertToBoundary(UserEntity entity) {
		UserBoundary boundary = new UserBoundary();
		if (entity.getUsername() == null || entity.getRole() == null || entity.getPassword() == null
				|| entity.getEmail() == null) {
			throw new RuntimeException("cant conert to bouddry from entity"); // TODO: return status = 404 instead of
																				// status = 500

		} else {

			boundary.setUsername(entity.getUsername());
			boundary.setUserId(new UserID(entity.getSpace(), entity.getEmail()));
			boundary.setRole(entity.getRole());
			boundary.setPassword(entity.getPassword());
			boundary.setJohnStaCoin(entity.getJohnStaCoin()); 
			boundary.setWallet(new Wallet(entity.getPrivateKey(), entity.getPublicKey(), entity.getBalance()));
		
			/*
			boundary.setJohnStaCoin(unmarshal(entity.getJohnStaCoin(), BlockChain.class));
			 boundary.setWallet(unmarshal(entity.getWallet(), Wallet.class));
			//  boundary.setPendingTransaction(unmarshal(entity.getPendingTransaction(), Transaction.class));
			// add extra for user*/
			/*
			 
			 */
		}

		return boundary;
	}
	
	// use Jackson to convert JSON to Object
	private <T> T unmarshal(String json, Class<T> type) {
		try {
			//jackson.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
			//System.out.println("in unmarshal " + json.toString());
			return this.jackson.readValue(json, type);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private String marshal(Object moreDetails) {
		try {
			//jackson.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
			//System.out.println("in marshal " + moreDetails.toString());
			return this.jackson.writeValueAsString(moreDetails);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * @Override public List<UserBoundary> getAllUsers(String userSpace, String
	 * userEmail, int size, int page){
	 * 
	 * Optional<UserEntity> op = this.userDao.findById(userEmail + "$" + userSpace);
	 * if (op.isPresent()) { if (isUserAdmin(op)) {
	 * 
	 * Page<UserEntity> pageOfEntities = this.userDao .findAll(PageRequest.of(page,
	 * size, Direction.ASC, "role", "email"));
	 * 
	 * List<UserEntity> entities = pageOfEntities.getContent(); List<UserBoundary>
	 * rv = new ArrayList<>(); for (UserEntity entity : entities) { UserBoundary
	 * boundary = convertToBoundary(entity); rv.add(boundary); } return rv; } }
	 * 
	 * else { throw new UserNotFoundException(); // TODO: return status = 404
	 * instead of status = 500
	 * 
	 * } throw new RuntimeException("user is not admin");
	 * 
	 * }
	 */

}
