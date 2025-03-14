package uth.edu.homestay_campingbooking.repositories;

import uth.edu.homestay_campingbooking.models.User;
import java.util.List;

public interface IUserRepository {
    void save(User user);
    User findById(long id);
    List<User> findAll();
    User findByUsername(String username);
    void deleteById(long id);
    void update(long id, User user);
}
