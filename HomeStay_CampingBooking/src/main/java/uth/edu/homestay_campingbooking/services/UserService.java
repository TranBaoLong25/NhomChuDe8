package uth.edu.homestay_campingbooking.services;
import uth.edu.homestay_campingbooking.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uth.edu.homestay_campingbooking.repositories.IUserRepository;

import java.util.List;

@Service
public class UserService implements IUserService {
    private IUserRepository userRepository;
    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public User findById(long id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findByUser(User user) {
        return userRepository.findByUsername(user.getUsername());
    }

    @Override
    public void deleteById(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void update(User user) {
        userRepository.save(user);
    }
}
