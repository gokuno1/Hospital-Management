package com.example.ZuulApiGateway.authtoken;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class UserDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	public SpringSession getSessionBySessionId(String sessionID) throws NoResultException {
		try {
			List<Predicate> predicateList = new ArrayList<>();
			CriteriaBuilder builder = entityManager.getCriteriaBuilder();
			CriteriaQuery<SpringSession> criteria = builder.createQuery(SpringSession.class);
			Root<SpringSession> root = criteria.from(SpringSession.class);
			if (!sessionID.isEmpty()) {
				Predicate userPredicate = builder.equal(root.get("sessionId"), sessionID);
				predicateList.add(userPredicate);
			}
			if (predicateList != null && !predicateList.isEmpty()) {
				criteria.where(builder.and(predicateList.toArray(new Predicate[] {})));
			}
			criteria.select(root);
			TypedQuery<SpringSession> query = entityManager.createQuery(criteria);
			SpringSession session = query.getSingleResult();
			return session;
		} catch (NoResultException e) {
			e.printStackTrace();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return null;
	}

	public void updateSessionDetails(SpringSession springSession) {
		entityManager.unwrap(Session.class).merge(springSession);
	}

}
