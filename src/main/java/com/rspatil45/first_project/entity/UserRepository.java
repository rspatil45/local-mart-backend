package com.rspatil45.first_project.entity;



import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;





@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {
	UserEntity findByEmail(String email);

	UserEntity findByPublicUid(String uid);

}
