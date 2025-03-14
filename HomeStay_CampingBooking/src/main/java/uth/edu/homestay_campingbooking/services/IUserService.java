package uth.edu.homestay_campingbooking.services;
import uth.edu.homestay_campingbooking.models.User;

import java.util.List;

public interface IUserService {
    void save(User user);
    User findById(long id);
    List<User> findAll();
    User findByUser(String username);
    void deleteById(long id);
    void update(long id, User user);
}
