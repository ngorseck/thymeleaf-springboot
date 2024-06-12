package com.samanecorp.thymeleaf1.dao;

import com.samanecorp.thymeleaf1.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserDao extends JpaRepository<UserEntity, Long> {

	Optional<UserEntity> findByEmailAndPassword(String email, String password);
}
