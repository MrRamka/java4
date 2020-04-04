package com.yabcompany.entityManager;

import com.yabcompany.models.MainUser;
import com.yabcompany.models.RegistrationForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.EntityManager;
import java.util.List;

public class UserEntityManagerCommands {

    private EntityManager entityManager;

    // Getting password encoder
    @Autowired
    private PasswordEncoder passwordEncoder;


    public UserEntityManagerCommands() {
        UserEntityManager uemf = new UserEntityManager("mainUser");
        entityManager = uemf.getEntityManager();
    }

    /**
     * Save user entity to database
     *
     * @param user
     * @return MainUser
     */
    public MainUser saveUser(MainUser user) {
        MainUser mainUser = user;

        // Starting transaction
        entityManager.getTransaction().begin();

        // Use persist() over merge()
        entityManager.persist(mainUser);

        // Commit changes
        entityManager.getTransaction().commit();

        return mainUser;
    }

    /**
     * Getting single MainUser by ID using find()
     *
     * @param userId
     * @return MainUser
     */
    public MainUser getMainUserById(Long userId) {

        MainUser mainUser;

        try {
            entityManager.getTransaction().begin();
            mainUser = entityManager.find(MainUser.class, userId);
            entityManager.getTransaction().commit();
        } finally {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            entityManager.close();
        }

        return mainUser;

    }

    /**
     * Getting single MainUser by Email
     *
     * @param userEmail
     * @return MainUser
     */
    public MainUser getMainUserByEmail(String userEmail) {

        MainUser mainUser;

        try {
            entityManager.getTransaction().begin();
            mainUser = entityManager.createQuery("SELECT mainuser FROM MainUser mainuser WHERE mainuser.email = ?1", MainUser.class)
                    .setParameter(1, userEmail)
                    .getSingleResult();
            entityManager.getTransaction().commit();
        } finally {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            entityManager.close();
        }

        return mainUser;

    }

    /**
     * Remove mainUser by ID using find()
     *
     * @param userId
     * @return MainUser
     */
    public MainUser removeMainUserById(Long userId) {

        MainUser mainUser;

        try {
            entityManager.getTransaction().begin();
            mainUser = entityManager.find(MainUser.class, userId);
            entityManager.remove(mainUser);
            entityManager.getTransaction().commit();
        } finally {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            entityManager.close();
        }

        return mainUser;

    }

    /**
     * Updating MainUser Email
     *
     * @param oldEmail
     * @param newEmail
     * @return MainUser
     */
    public MainUser updateMainUserEmail(String oldEmail, String newEmail) {

        MainUser mainUser = getMainUserByEmail(oldEmail);

        try {
            // Detach this user
            entityManager.detach(mainUser);

            // Updating email
            mainUser.setEmail(newEmail);

            entityManager.getTransaction().begin();

            // Merging users
            entityManager.merge(mainUser);

            entityManager.getTransaction().commit();
        } finally {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            entityManager.close();
        }

        return mainUser;

    }

    /**
     * Get all users
     *
     * @return List<MainUser>
     */
    public List<MainUser> getAllMainUsers() {

        List<MainUser> mainUser;

        try {
            entityManager.getTransaction().begin();
            mainUser = entityManager.createQuery("SELECT mainuser FROM MainUser mainuser", MainUser.class)
                    .getResultList();
            entityManager.getTransaction().commit();
        } finally {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            entityManager.close();
        }

        return mainUser;

    }


}
