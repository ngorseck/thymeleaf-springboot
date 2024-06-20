package com.samanecorp.thymeleaf1.dao;

import com.samanecorp.thymeleaf1.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserDao extends JpaRepository<UserEntity, Long> {

	Optional<UserEntity> findByEmailAndPassword(String email, String password);

	Optional<UserEntity> findByEmail(String email);

	@Query("select u from UserEntity  u where u.email = :emailParam")
	UserEntity getUserByEmail(@Param("emailParam") String email);
}
