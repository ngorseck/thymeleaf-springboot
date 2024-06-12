package com.samanecorp.thymeleaf1.dao;

import com.samanecorp.thymeleaf1.entity.UserEntity;
import jakarta.persistence.EntityManager;
import org.hibernate.Session;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public class UserDao {

	EntityManager em;

	public Optional<UserEntity> login(String email, String password) {

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<UserEntity> cr = cb.createQuery(UserEntity.class);
		Root<UserEntity> user = cr.from(UserEntity.class);
		//Predicate pour la clause where
		Predicate predicateEmail = cb.equal(user.get("email"), email);
		Predicate predicatePwd = cb.equal(user.get("password"), password);
		Predicate finalPredicate = cb.and(predicateEmail, predicatePwd);
		
		cr.select(user);
		cr.where(finalPredicate);
		
		return Optional.ofNullable(em.createQuery(cr).getSingleResult());
	}
}
