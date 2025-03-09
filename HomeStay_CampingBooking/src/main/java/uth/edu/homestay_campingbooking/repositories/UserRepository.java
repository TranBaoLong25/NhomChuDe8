package uth.edu.homestay_campingbooking.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import uth.edu.homestay_campingbooking.models.User;

import java.util.List;
@Repository
@Transactional
public class UserRepository implements IUserRepository {
    @PersistenceContext
    private EntityManager entityManager;
    public void save(User user) {
        entityManager.persist(user);
    }
    public User findById(long id) {
        return entityManager.find(User.class, id);
    }
    public List<User> findAll() {
        return entityManager.createQuery("from User", User.class).getResultList();
    }
    public User findByUsername(String username) {
        try {
            return entityManager.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class)
                    .setParameter("username", username)
                    .getSingleResult();
        } catch (Exception e) {
            return null; // Trả về null nếu không tìm thấy
        }
    }
    public void deleteById(long id) {
        User user = findById(id);
        if (user != null) {
            entityManager.remove(user);
        }
    }
    public void update(User user) {
        entityManager.merge(user);
    }
}