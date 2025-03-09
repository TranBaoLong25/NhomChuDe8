package uth.edu.homestay_campingbooking.services;
import uth.edu.homestay_campingbooking.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uth.edu.homestay_campingbooking.models.User;
import uth.edu.homestay_campingbooking.repositories.UserRepository;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public void create(User user) {
        userRepository.save(user);
    }
    public User getUserByName(String username) {
        return userRepository.findByUsername(username);
    }
    public User getUserById(long id) {
        return userRepository.findById(id);
    }
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    public void update(User user) {
        userRepository.update(user);
    }
    public void delete(User user) {
        userRepository.deleteById(user.getId());
    }
}
