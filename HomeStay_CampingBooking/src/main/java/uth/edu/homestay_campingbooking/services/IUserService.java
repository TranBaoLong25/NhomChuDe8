package uth.edu.homestay_campingbooking.services;
import uth.edu.homestay_campingbooking.models.User;

import java.util.List;

public interface IUserService {
    void save(User user);
    User findById(Long id);
    List<User> findAll();
    User findByUser(String username);
    void deleteById(Long id);
    void update(Long id, User user);
}
