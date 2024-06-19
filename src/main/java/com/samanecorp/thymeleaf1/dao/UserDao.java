package com.samanecorp.thymeleaf1.dao;

import com.samanecorp.thymeleaf1.entity.UserEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Tuple;
import lombok.AllArgsConstructor;
import org.hibernate.Session;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
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

	public Optional<List<UserEntity>> allUserOrderByLastName() {

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<UserEntity> cr = cb.createQuery(UserEntity.class);
		Root<UserEntity> user = cr.from(UserEntity.class);

		cr.orderBy(cb.asc(user.get("lastName")));
		cr.select(user);

		return Optional.ofNullable(em.createQuery(cr).getResultList());
	}

	public Optional<Long> countAllUsers() {

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cr = cb.createQuery(Long.class);
		Root<UserEntity> user = cr.from(UserEntity.class);

		cr.select(cb.count(user));

		return Optional.ofNullable(em.createQuery(cr).getSingleResult());
	}

	public Optional<List<Tuple>> allLastNameAndFirstName() {

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Tuple> cr = cb.createTupleQuery();
		Root<UserEntity> user = cr.from(UserEntity.class);

		cr.distinct(true);
		cr.orderBy(cb.asc(user.get("lastName")));
		cr.multiselect(user.get("firstName"), user.get("lastName"));

		return Optional.ofNullable(em.createQuery(cr).getResultList());
	}
}
