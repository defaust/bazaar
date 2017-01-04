package ua.com.codespace.repository.impl;

import ua.com.codespace.model.User;
import ua.com.codespace.repository.CustomUserRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


public class UserRepositoryImpl implements CustomUserRepository {
    @PersistenceContext
    EntityManager em;

    @Transactional
    public User findByUsername(String username) {
        return em.createQuery("from User u where u.username= :username", User.class)
                .setParameter("username", username)
                .getSingleResult();
    }
}
