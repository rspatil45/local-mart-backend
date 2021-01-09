package com.rspatil45.first_project.entity;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface ProductRepository extends CrudRepository<ProductEntity, Long> {
	

	Page<ProductEntity>  findAll(Pageable pageableRequest);
	//Page<UserEntity> findAllUsersWithConfirmedEmailAddress( Pageable pageableRequest );
	ProductEntity findById(long id);
	Page<ProductEntity> findByCategory(String category, Pageable pageable);


	
}
