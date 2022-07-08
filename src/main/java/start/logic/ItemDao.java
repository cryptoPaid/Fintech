<<<<<<< HEAD
package start.logic;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import start.data.TransactionEntity;


public interface ItemDao extends PagingAndSortingRepository<TransactionEntity, String> {

	public List<TransactionEntity> findAllByActive(@Param("active") boolean active, Pageable pageable);

	public List<TransactionEntity> findAllByType(@Param("type") String type);

}
=======
package start.logic;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import start.data.TransactionEntity;


public interface ItemDao extends PagingAndSortingRepository<TransactionEntity, String> {

	public List<TransactionEntity> findAllByActive(@Param("active") boolean active, Pageable pageable);

	public List<TransactionEntity> findAllByType(@Param("type") String type);

}
>>>>>>> c813bdfdde53cb9a25135a0dcf4dc84cf0ce9f7c
