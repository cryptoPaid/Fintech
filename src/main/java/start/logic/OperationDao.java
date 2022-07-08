package start.logic;

import org.springframework.data.repository.PagingAndSortingRepository;

import start.data.OperationEntity;


public interface OperationDao extends PagingAndSortingRepository<OperationEntity, String> {

}
