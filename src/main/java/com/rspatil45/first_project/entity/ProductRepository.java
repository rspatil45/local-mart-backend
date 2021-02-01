package com.rspatil45.first_project.entity;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rspatil45.first_project.ui.model.request.ProductRRModel;



@Repository
public interface ProductRepository extends CrudRepository<ProductEntity, Long> {
	

	Page<ProductEntity>  findAll(Pageable pageableRequest);
	//Page<UserEntity> findAllUsersWithConfirmedEmailAddress( Pageable pageableRequest );
	ProductEntity findById(long id);
	Page<ProductEntity> findByCategory(String category, Pageable pageable);
	long countByCategory(String name);
	
	@Query(value ="select * from products p where p.name REGEXP ?1",nativeQuery = true)
	List<ProductEntity> searchKeyword(String keyword);
		
}
