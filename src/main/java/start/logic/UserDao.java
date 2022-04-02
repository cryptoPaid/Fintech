package start.logic;

import org.springframework.data.repository.PagingAndSortingRepository;

import start.data.UserEntity;



public interface UserDao extends PagingAndSortingRepository<UserEntity, String> {

	
	
}
